package CSCI5308.GroupFormationTool.PasswordValidationPolicyTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.PasswordValidationPolicy.IPasswordValidation;
import CSCI5308.GroupFormationTool.PasswordValidationPolicy.IPasswordValidationConfiguration;
import CSCI5308.GroupFormationTool.PasswordValidationPolicy.MinLengthValidation;

class MinLengthValidationTest {

	@Test
	void isValidPasswordTest() {
		IPasswordValidationConfiguration config = new  PasswordValidationConfigurationMock();
		IPasswordValidation validator = new MinLengthValidation();

		assertTrue(validator.isValidPassword("1234567890", config));
		assertTrue(validator.isValidPassword("12345678", config));
		assertFalse(validator.isValidPassword("1234567", config));
		assertFalse(validator.isValidPassword("", config));
		assertFalse(validator.isValidPassword(null, config));
	}
	
	@Test 
	void getMinLengthTest() {
		IPasswordValidationConfiguration config = new  PasswordValidationConfigurationMock();
		MinLengthValidation validator = new MinLengthValidation();
		assertTrue(validator.isValidPassword("1234567890", config));
		assertEquals(8, validator.getMinLength());
	}
	
	@Test void getPasswordValidationMessageTest() {
		IPasswordValidationConfiguration config = new  PasswordValidationConfigurationMock();
		MinLengthValidation validator = new MinLengthValidation();
		
		assertEquals(validator.getValidationFailureMessage("1234567", config), 
				String.format(MinLengthValidation.INVALID_PASSWORD_MESSAGE, validator.getMinLength()));
		assertEquals(validator.getValidationFailureMessage("12345678", config), 
				String.format(MinLengthValidation.VALID_PASSWORD_MESSAGE, validator.getMinLength()));
	}

}
