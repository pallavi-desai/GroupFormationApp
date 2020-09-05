package CSCI5308.GroupFormationTool.PasswordValidationPolicyTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.PasswordValidationPolicy.ForbiddenCharSetValidation;
import CSCI5308.GroupFormationTool.PasswordValidationPolicy.IPasswordValidationConfiguration;

class ForbiddenCharSetValidationTest {

	private static final String ALLOWED_CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^*()~.,/{}[]";
	private static final String FORBIDDEN_CHARSET = "\\&\"'";
	private static final int NUM_OF_TESTS = 1000;
	private static final int LENGTH_OF_ALLOWED_STRING = 12;
	private static final int LENGTH_OF_FORBIDDEN_STRING = 4;
	
	@Test
	void isValidPasswordTest() {
		IPasswordValidationConfiguration config = new PasswordValidationConfigurationMock();
		ForbiddenCharSetValidation validator = new ForbiddenCharSetValidation();
		Random random = new Random();
		
		for (int i = 0; i < NUM_OF_TESTS; i++) {
			int randomAllowedLength = random.nextInt(LENGTH_OF_ALLOWED_STRING + 1);
			int randomForbiddernLenght = random.nextInt(LENGTH_OF_FORBIDDEN_STRING + 1);
			
			String allowedString = this.generateAllowedString(randomAllowedLength);
			String forbiddenString = this.generateForbiddenString(randomForbiddernLenght);
			
			if (forbiddenString.isEmpty()) {
				assertTrue(validator.isValidPassword(allowedString, config));
				assertTrue(validator.isValidPassword(forbiddenString+allowedString, config));
				assertTrue(validator.isValidPassword(allowedString+forbiddenString, config));
				assertTrue(validator.isValidPassword(forbiddenString+allowedString+forbiddenString, config));
			} else {
				assertTrue(validator.isValidPassword(allowedString, config));
				assertFalse(validator.isValidPassword(forbiddenString+allowedString, config));
				assertFalse(validator.isValidPassword(allowedString+forbiddenString, config));
				assertFalse(validator.isValidPassword(forbiddenString+allowedString+forbiddenString, config));
			}
			assertFalse(validator.isValidPassword(null, config));
			assertTrue(validator.isValidPassword("", config));
		}
	}
	
	@Test 
	void getForbiddenCharSetTest() {
		IPasswordValidationConfiguration config = new  PasswordValidationConfigurationMock();
		ForbiddenCharSetValidation validator = new ForbiddenCharSetValidation();
		assertTrue(validator.isValidPassword("#Messi!", config));
		assertEquals(FORBIDDEN_CHARSET, validator.getForbiddenCharSet());
	}
	
	@Test
	void getValidationMessageTest() {
		IPasswordValidationConfiguration config = new PasswordValidationConfigurationMock();
		ForbiddenCharSetValidation validator = new ForbiddenCharSetValidation();
		Random random = new Random();
		
		for (int i = 0; i < NUM_OF_TESTS; i++) {
			int randomAllowedLength = random.nextInt(LENGTH_OF_ALLOWED_STRING + 1);
			int randomForbiddernLenght = random.nextInt(LENGTH_OF_FORBIDDEN_STRING + 1);
			
			String allowedString = this.generateAllowedString(randomAllowedLength);
			String forbiddenString = this.generateForbiddenString(randomForbiddernLenght);
			
			if (forbiddenString.isEmpty()) {
				assertEquals(validator.getValidationFailureMessage(allowedString+forbiddenString , config),
						String.format(ForbiddenCharSetValidation.VALID_PASSWORD_MESSAGE, 
								validator.getForbiddenCharSet()));
			} else 	{
				assertEquals(validator.getValidationFailureMessage(allowedString, config),
						String.format(ForbiddenCharSetValidation.VALID_PASSWORD_MESSAGE, 
								validator.getForbiddenCharSet()));
				assertEquals(validator.getValidationFailureMessage(forbiddenString+allowedString, config),
						String.format(ForbiddenCharSetValidation.INVALID_PASSWORD_MESSAGE, 
								validator.getForbiddenCharSet()));
			}
		}
	}
	
	private String generateAllowedString(int length) {
		StringBuilder builder = new StringBuilder();
		Random upperLowerSelector = new Random();
		while (length-- != 0) {
			int character = (int)(Math.random()*ALLOWED_CHARSET.length());
			if (upperLowerSelector.nextInt(2) == 0) {
				builder.append(ALLOWED_CHARSET.toUpperCase().charAt(character));
			}
			else {
				builder.append(ALLOWED_CHARSET.toLowerCase().charAt(character));
			}
		}
		return builder.toString();
	}
	
	private String generateForbiddenString(int length) {
		StringBuilder builder = new StringBuilder();
		while (length-- != 0) {
			int character = (int)(Math.random()* FORBIDDEN_CHARSET.length());
			builder.append(FORBIDDEN_CHARSET.charAt(character));
		}
		return builder.toString();
	}

}
