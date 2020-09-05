package CSCI5308.GroupFormationTool.PasswordValidationPolicy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForbiddenCharSetValidation implements IPasswordValidation {

	private static final Logger log = LoggerFactory.getLogger(ForbiddenCharSetValidation.class);
	private static final String FORBIDDEN_CHARSET_LOG = "ForbiddenCharSetPolicy";

	private static final String FORBIDDEN_CHARSET_CONFIG = "forbidden_charset";
	public static final String VALID_PASSWORD_MESSAGE = "Password does not have forbidden characters (%s).";
	public static final String INVALID_PASSWORD_MESSAGE = "Password has forbidden characters \"%s\".";

	private String forbiddenCharSet;

	public ForbiddenCharSetValidation() {
		log.info("password={}, action={}, status={}", FORBIDDEN_CHARSET_LOG, "Create", "Success");
	}

	public String getForbiddenCharSet() {
		return this.forbiddenCharSet;
	}

	private void setForbiddernCharSet(String forbiddernCharSet) {
		if (null == forbiddernCharSet) {
			this.forbiddenCharSet = "";
		} else {
			this.forbiddenCharSet = forbiddernCharSet;
		}
		log.info("password={}, action={}, value={}", FORBIDDEN_CHARSET_LOG, "Set CharSet", getForbiddenCharSet());
	}

	@Override
	public boolean isValidPassword(String password, IPasswordValidationConfiguration config) {
		String configValue;

		try {
			configValue = config.getConfig(FORBIDDEN_CHARSET_CONFIG);
		} catch (IllegalArgumentException e) {
			log.warn("password={}, action={}, message={}", FORBIDDEN_CHARSET_LOG, "Get Configuration", e.getMessage());
			configValue = null;
		}
		setForbiddernCharSet(configValue);

		if (null == password) {
			log.info("password={}, action={}, status={}, message={}", FORBIDDEN_CHARSET_LOG, "Check Validity", "Fail",
					"Null Password");
			return false;
		}

		if (this.forbiddenCharSet.isEmpty()) {
			log.info("password={}, action={}, status={}, message={}", FORBIDDEN_CHARSET_LOG, "Check Validity",
					"Success", "Empty CharSet");
			return true;
		}

		for (int i = 0; i < this.forbiddenCharSet.length(); i++) {
			if (password.contains(String.valueOf(this.forbiddenCharSet.charAt(i)))) {
				log.info("password={}, action={}, status={}", FORBIDDEN_CHARSET_LOG, "Check Validity", "Fail");
				return false;
			}
		}
		log.info("password={}, action={}, status={}", FORBIDDEN_CHARSET_LOG, "Check Validity", "Success");
		return true;
	}

	@Override
	public String getValidationFailureMessage(String password, IPasswordValidationConfiguration config) {
		String message = null;
		if (isValidPassword(password, config)) {
			message = String.format(VALID_PASSWORD_MESSAGE, this.forbiddenCharSet);
		} else {
			message = String.format(INVALID_PASSWORD_MESSAGE, this.forbiddenCharSet);
		}
		log.info("password={}, action={}, message={}", FORBIDDEN_CHARSET_LOG, "Get Validation Message", message);
		return message;
	}
}