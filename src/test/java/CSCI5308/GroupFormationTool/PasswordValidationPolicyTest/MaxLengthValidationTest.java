package CSCI5308.GroupFormationTool.PasswordValidationPolicyTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.PasswordValidationPolicy.IPasswordValidationConfiguration;
import CSCI5308.GroupFormationTool.PasswordValidationPolicy.MaxLengthValidation;

class MaxLengthValidationTest {

	@Test
	void isValidPasswordTest() {
		IPasswordValidationConfiguration config = new  PasswordValidationConfigurationMock();
		MaxLengthValidation validator = new MaxLengthValidation();
		
		assertTrue(validator.isValidPassword("abcdefghijkl", config));
		assertTrue(validator.isValidPassword("abcdefghijk", config));
		assertFalse(validator.isValidPassword("abcdefghijklm", config));
		assertTrue(validator.isValidPassword("", config));
		assertFalse(validator.isValidPassword(null, config));
	}
	
	@Test 
	void getMaxLengthTest() {
		IPasswordValidationConfiguration config = new  PasswordValidationConfigurationMock();
		MaxLengthValidation validator = new MaxLengthValidation();
		assertTrue(validator.isValidPassword("abcdefghijkl", config));
		assertEquals(12, validator.getMaxLength());
	}
	
	@Test
	void getPasswordValidationMessage() {
		IPasswordValidationConfiguration config = new  PasswordValidationConfigurationMock();
		MaxLengthValidation validator = new MaxLengthValidation();
		
		assertEquals(validator.getValidationFailureMessage("abcdefghijkl", config),
				String.format(MaxLengthValidation.VALID_PASSWORD_MESSAGE, validator.getMaxLength()));
		assertEquals(validator.getValidationFailureMessage("abcdefghijklmn", config),
				String.format(MaxLengthValidation.INVALID_PASSWORD_MESSAGE, validator.getMaxLength()));
	}

}
