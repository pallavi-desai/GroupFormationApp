package CSCI5308.GroupFormationTool.PasswordValidationPolicy;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinNonAlphaNumValidation implements IPasswordValidation {

	private static final Logger log = LoggerFactory.getLogger(MinNonAlphaNumValidation.class);
	private static final String MIN_NON_ALPHANUM_LOG = "MinNonAlphaNumPolicy";
	private static final String MIN_NON_ALPHANUM_CONFIG = "min_non_alphanum";
	public static final String VALID_PASSWORD_MESSAGE = "Password has minimum %d special characters.";
	public static final String INVALID_PASSWORD_MESSAGE = "Password must have minimum %d special characters.";
	private final static String NON_ALPHA_NUM_PATTERN = "[^a-zA-Z0-9]";
	private int minNonAlphaNum;

	public MinNonAlphaNumValidation() {
		log.info("password={}, action={}, status={}", MIN_NON_ALPHANUM_LOG, "Create", "Success");
	}

	public int getMinNonAlphaNum() {
		return this.minNonAlphaNum;
	}

	public void setMinNonAlphaNum(String minNonAlphaNum) {
		int intMinNonAlphaNum;
		try {
			intMinNonAlphaNum = Integer.parseInt(minNonAlphaNum);
		} catch (NumberFormatException e) {
			log.warn("password={}, action={}, message={}", MIN_NON_ALPHANUM_LOG, "Set Min Non-AlphaNumeric",
					e.getMessage());
			intMinNonAlphaNum = 0;
		}

		if (intMinNonAlphaNum <= 0) {
			this.minNonAlphaNum = 0;
		} else {
			this.minNonAlphaNum = intMinNonAlphaNum;
		}
		log.info("password={}, action={}, value={}", MIN_NON_ALPHANUM_LOG, "Set Min Non-AlphaNumeric",
				getMinNonAlphaNum());
	}

	@Override
	public boolean isValidPassword(String password, IPasswordValidationConfiguration config) {
		int nonAlphaNumChars = 0;
		String configValue;

		try {
			configValue = config.getConfig(MIN_NON_ALPHANUM_CONFIG);
		} catch (IllegalArgumentException e) {
			log.warn("password={}, action={}, message={}", MIN_NON_ALPHANUM_LOG, "Get Configuration", e.getMessage());
			configValue = null;
		}

		setMinNonAlphaNum(configValue);

		if (null == password) {
			log.info("password={}, action={}, status={}, message={}", MIN_NON_ALPHANUM_LOG, "Check Validity", "Fail",
					"Null Password");
			return false;
		}

		if (this.minNonAlphaNum == 0) {
			log.info("password={}, action={}, status={}", MIN_NON_ALPHANUM_LOG, "Check Validity", "Success");
			return true;
		}

		Pattern nonAlphaNumPattern = Pattern.compile(NON_ALPHA_NUM_PATTERN);
		for (int i = 0; i < password.length(); i++) {
			if (nonAlphaNumPattern.matcher(password.substring(i, i + 1)).matches()) {
				nonAlphaNumChars++;
			}
		}

		if (nonAlphaNumChars < this.minNonAlphaNum) {
			log.info("password={}, action={}, status={}", MIN_NON_ALPHANUM_LOG, "Check Validity", "Fail");
			return false;
		} else {
			log.info("password={}, action={}, status={}", MIN_NON_ALPHANUM_LOG, "Check Validity", "Success");
			return true;
		}
	}

	@Override
	public String getValidationFailureMessage(String password, IPasswordValidationConfiguration config) {
		String message = null;
		if (isValidPassword(password, config)) {
			message = String.format(VALID_PASSWORD_MESSAGE, this.minNonAlphaNum);
		} else {
			message = String.format(INVALID_PASSWORD_MESSAGE, this.minNonAlphaNum);
		}
		log.info("password={}, action={}, message={}", MIN_NON_ALPHANUM_LOG, "Get Validation Message", message);
		return message;
	}
}