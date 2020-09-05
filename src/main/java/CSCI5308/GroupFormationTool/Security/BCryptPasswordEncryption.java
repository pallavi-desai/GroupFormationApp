package CSCI5308.GroupFormationTool.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncryption implements IPasswordEncryption {
	private static final Logger logger = LoggerFactory.getLogger(BCryptPasswordEncryption.class);
	private static final String BCRYPT_ENCRYPTION_LOG = "BcryptEncryption";

	private BCryptPasswordEncoder encoder;

	public BCryptPasswordEncryption() {
		encoder = new BCryptPasswordEncoder();
		logger.info("password={}, action={}, status={}", BCRYPT_ENCRYPTION_LOG, "Create Encoder", "Success");
	}

	public String encryptPassword(String rawPassword) {
		String encoded = encoder.encode(rawPassword);
		logger.info("password={}, action={}, status={}", BCRYPT_ENCRYPTION_LOG, "Encode Raw Password", "Success");
		return encoded;
	}

	public boolean matches(String rawPassword, String encryptedPassword) {
		boolean matchResult = encoder.matches(rawPassword, encryptedPassword);
		logger.info("password={}, action={}, result={}", BCRYPT_ENCRYPTION_LOG, "Match Passwords", matchResult);
		return matchResult;
	}
}
