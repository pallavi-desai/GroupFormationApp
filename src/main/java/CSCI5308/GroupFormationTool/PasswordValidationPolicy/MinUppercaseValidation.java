package CSCI5308.GroupFormationTool.PasswordValidationPolicy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinUppercaseValidation implements IPasswordValidation {

	private static final Logger log = LoggerFactory.getLogger(MinUppercaseValidation.class);
	private static final String MIN_UPPERCASE_LOG = "MinUppercasePolicy";
	private static final String MIN_UPPERCASE_CONFIG = "min_uppercase";
	public static final String VALID_PASSWORD_MESSAGE = "Password follows minimum %d uppercase letters.";
	public static final String INVALID_PASSWORD_MESSAGE = "Password must have minimum %d uppercase letters.";
	private int minUppercase;

	public MinUppercaseValidation() {
		log.info("password={}, action={}, status={}", MIN_UPPERCASE_LOG, "Create", "Success");
	}

	public int getMinUppercase() {
		return this.minUppercase;
	}

	private void setMinUppercase(String minUppercase) {
		int intMinUppercase;
		try {
			intMinUppercase = Integer.parseInt(minUppercase);
		} catch (NumberFormatException e) {
			log.warn("password={}, action={}, message={}", MIN_UPPERCASE_LOG, "Set Min Uppercase", e.getMessage());
			intMinUppercase = 0;
		}

		if (intMinUppercase <= 0) {
			this.minUppercase = 0;
		} else {
			this.minUppercase = intMinUppercase;
		}
		log.info("password={}, action={}, value={}", MIN_UPPERCASE_LOG, "Set Min Uppercase", getMinUppercase());
	}

	@Override
	public boolean isValidPassword(String password, IPasswordValidationConfiguration config) {
		int upperCase = 0;
		String configValue;

		try {
			configValue = config.getConfig(MIN_UPPERCASE_CONFIG);
		} catch (IllegalArgumentException e) {
			log.warn("password={}, action={}, message={}", MIN_UPPERCASE_LOG, "Get Configuration", e.getMessage());
			configValue = null;
		}

		setMinUppercase(configValue);

		if (null == password) {
			log.info("password={}, action={}, status={}, message={}", MIN_UPPERCASE_LOG, "Check Validity", "Fail",
					"Null Password");
			return false;
		}

		if (this.minUppercase == 0) {
			log.info("password={}, action={}, status={}", MIN_UPPERCASE_LOG, "Check Validity", "Success");
			return true;
		}

		for (int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				upperCase++;
			}
		}
		if (upperCase < this.minUppercase) {
			log.info("password={}, action={}, status={}", MIN_UPPERCASE_LOG, "Check Validity", "Fail");
			return false;
		} else {
			log.info("password={}, action={}, status={}", MIN_UPPERCASE_LOG, "Check Validity", "Success");
			return true;
		}
	}

	@Override
	public String getValidationFailureMessage(String password, IPasswordValidationConfiguration config) {
		String message = null;
		if (isValidPassword(password, config)) {
			message = String.format(VALID_PASSWORD_MESSAGE, this.minUppercase);
		} else {
			message = String.format(INVALID_PASSWORD_MESSAGE, this.minUppercase);
		}
		log.info("password={}, action={}, message={}", MIN_UPPERCASE_LOG, "Get Validation Message", message);
		return message;
	}

}
