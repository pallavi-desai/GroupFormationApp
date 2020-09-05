package CSCI5308.GroupFormationTool.PasswordValidationPolicy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import CSCI5308.GroupFormationTool.Security.BCryptPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

public class HistoryConstraintValidation implements IPasswordValidation {

	private static final Logger log = LoggerFactory.getLogger(HistoryConstraintValidation.class);
	private static final String HISTORY_CONSTRAINT_LOG = "HistoryConstraintPolicy";

	private static final String HISTORY_CONSTRAINT_CONFIG = "history_constraint";
	public static final String VALID_PASSWORD_MESSAGE = "Password follows history constraints of %d.";
	public static final String INVALID_PASSWORD_MESSAGE = "Password must no be among your %d previous passwords.";

	private IPasswordHistoryPersistence passwordHistoryPersistence;
	private int historyConstraint;

	public HistoryConstraintValidation(IPasswordHistoryPersistence persistence) {
		this.passwordHistoryPersistence = persistence;
		log.info("password={}, action={}, status={}", HISTORY_CONSTRAINT_LOG, "Create", "Success");
	}

	public int getHistoryConstraint() {
		return this.historyConstraint;
	}

	private void setHistoryConstraint(String historyConstraint) {
		int intHistoryConstraint;
		try {
			intHistoryConstraint = Integer.parseInt(historyConstraint);
		} catch (NumberFormatException e) {
			log.warn("password={}, action={}, message={}", HISTORY_CONSTRAINT_LOG, "Set History", e.getMessage());
			intHistoryConstraint = 0;
		}

		if (intHistoryConstraint <= 0)
			this.historyConstraint = 0;
		else
			this.historyConstraint = intHistoryConstraint;

		log.info("password={}, action={}, value={}", HISTORY_CONSTRAINT_LOG, "Set History", getHistoryConstraint());
	}

	@Override
	public boolean isValidPassword(String password, IPasswordValidationConfiguration config) {
		String configValue;
		String bannerID = "";
		String encryptedPassword;
		IPasswordEncryption passwordEncryption = new BCryptPasswordEncryption();

		if (null == password) {
			log.info("password={}, action={}, status={}, message={}", HISTORY_CONSTRAINT_LOG, "Check Validity", "Fail",
					"Null Password");
			return false;
		}

		try {
			configValue = config.getConfig(HISTORY_CONSTRAINT_CONFIG);
		} catch (IllegalArgumentException e) {
			log.warn("password={}, action={}, message={}", HISTORY_CONSTRAINT_LOG, "Get Configuration", e.getMessage());
			configValue = null;
		}
		setHistoryConstraint(configValue);

		if (this.historyConstraint == 0) {
			log.info("password={}, action={}, status={}", HISTORY_CONSTRAINT_LOG, "Check Validity", "Success");
			return true;
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!authentication.isAuthenticated()) {
			log.info("password={}, action={}, status={}", HISTORY_CONSTRAINT_LOG, "Check Validity", "Success");
			return true;
		}
		bannerID = authentication.getPrincipal().toString();
		encryptedPassword = passwordEncryption.encryptPassword(password);

		if (passwordHistoryPersistence.followedHistoryConstraint(bannerID, encryptedPassword, historyConstraint)) {
			log.info("password={}, action={}, status={}", HISTORY_CONSTRAINT_LOG, "Check Validity", "Success");
			return true;
		}

		log.info("password={}, action={}, status={}", HISTORY_CONSTRAINT_LOG, "Check Validity", "Fail");
		return false;
	}

	@Override
	public String getValidationFailureMessage(String password, IPasswordValidationConfiguration config) {
		String message = null;
		if (isValidPassword(password, config)) {
			message = String.format(VALID_PASSWORD_MESSAGE, this.historyConstraint);
		} else {
			message = String.format(INVALID_PASSWORD_MESSAGE, this.historyConstraint);
		}
		log.info("password={}, action={}, message={}", HISTORY_CONSTRAINT_LOG, "Get Validation Message", message);
		return message;
	}

}