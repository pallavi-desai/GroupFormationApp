package CSCI5308.GroupFormationTool.PasswordValidationPolicyTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.PasswordValidationPolicy.IPasswordValidationConfiguration;
import CSCI5308.GroupFormationTool.PasswordValidationPolicy.MinUppercaseValidation;

class MinUppercaseValidationTest {

	@Test
	void isValidPasswordTest() {
		IPasswordValidationConfiguration config = new PasswordValidationConfigurationMock();
		MinUppercaseValidation validator = new MinUppercaseValidation();
		
		assertTrue(validator.isValidPassword("RaouF", config));
		assertTrue(validator.isValidPassword("RAouf", config));
		assertTrue(validator.isValidPassword("rAOuf", config));
		assertTrue(validator.isValidPassword("rAouF", config));
		assertTrue(validator.isValidPassword("rAouF1@", config));

		assertFalse(validator.isValidPassword("raouF", config));
		assertFalse(validator.isValidPassword("raoufF1@", config));
		assertFalse(validator.isValidPassword("A1234", config));
		assertFalse(validator.isValidPassword("I!@#$%", config));

		assertFalse(validator.isValidPassword(null, config));
	}
	
	@Test 
	void getMinUppercaseTest() {
		IPasswordValidationConfiguration config = new  PasswordValidationConfigurationMock();
		MinUppercaseValidation validator = new MinUppercaseValidation();
		assertTrue(validator.isValidPassword("RaouF", config));
		assertEquals(2, validator.getMinUppercase());
	}
	
	@Test
	void getValidationMessageTest() {
		IPasswordValidationConfiguration config = new  PasswordValidationConfigurationMock();
		MinUppercaseValidation validator = new MinUppercaseValidation();
		
		assertEquals(validator.getValidationFailureMessage("rAOUF", config),
				String.format(MinUppercaseValidation.VALID_PASSWORD_MESSAGE, validator.getMinUppercase()));
		
		assertEquals(validator.getValidationFailureMessage("raOuf", config),
				String.format(MinUppercaseValidation.INVALID_PASSWORD_MESSAGE, validator.getMinUppercase()));
	}
}
