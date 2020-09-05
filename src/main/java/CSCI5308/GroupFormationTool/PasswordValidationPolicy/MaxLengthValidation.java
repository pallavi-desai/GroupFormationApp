package CSCI5308.GroupFormationTool.PasswordValidationPolicy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaxLengthValidation implements IPasswordValidation {
	private static final Logger log = LoggerFactory.getLogger(MaxLengthValidation.class);
	private static final String MAX_LENGTH_LOG = "MaxLengthPolicy";

	private static final String MAX_LENGTH_CONFIG = "max_length";
	public static final String VALID_PASSWORD_MESSAGE = "Password follows maximum length of %d.";
	public static final String INVALID_PASSWORD_MESSAGE = "Password must have muximum length of %d.";

	private int maxLength;

	public MaxLengthValidation() {
		log.info("password={}, action={}, status={}", MAX_LENGTH_LOG, "Create", "Success");
	}

	public int getMaxLength() {
		return this.maxLength;
	}

	private void setMaxLength(String maxLength) {
		int intMaxLength;
		try {
			intMaxLength = Integer.parseInt(maxLength);
		} catch (NumberFormatException e) {
			log.warn("password={}, action={}, message={}", MAX_LENGTH_LOG, "Set Max Length", e.getMessage());
			intMaxLength = 0;
		}

		if (intMaxLength <= 0) {
			this.maxLength = 0;
		} else {
			this.maxLength = intMaxLength;
		}
		log.info("password={}, action={}, value={}", MAX_LENGTH_LOG, "Set Max Length", getMaxLength());
	}

	@Override
	public boolean isValidPassword(String password, IPasswordValidationConfiguration config) {
		String configValue;

		try {
			configValue = config.getConfig(MAX_LENGTH_CONFIG);
		} catch (IllegalArgumentException e) {
			log.warn("password={}, action={}, message={}", MAX_LENGTH_LOG, "Get Configuration", e.getMessage());
			configValue = null;
		}

		setMaxLength(configValue);

		if (null == password) {
			log.info("password={}, action={}, status={}, message={}", MAX_LENGTH_LOG, "Check Validity", "Fail",
					"Null Password");
			return false;
		}

		if (this.maxLength == 0) {
			log.info("password={}, action={}, status={}", MAX_LENGTH_LOG, "Check Validity", "Success");
			return true;
		}

		if (password.length() > this.maxLength) {
			log.info("password={}, action={}, status={}", MAX_LENGTH_LOG, "Check Validity", "Fail");
			return false;
		} else {
			log.info("password={}, action={}, status={}", MAX_LENGTH_LOG, "Check Validity", "Success");
			return true;
		}
	}

	@Override
	public String getValidationFailureMessage(String password, IPasswordValidationConfiguration config) {
		String message = null;
		if (isValidPassword(password, config)) {
			message = String.format(VALID_PASSWORD_MESSAGE, this.maxLength);
		} else {
			message = String.format(INVALID_PASSWORD_MESSAGE, this.maxLength);
		}
		log.info("password={}, action={}, message={}", MAX_LENGTH_LOG, "Get Validation Message", message);
		return message;
	}
}
