package CSCI5308.GroupFormationTool.PasswordValidationPolicyTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.PasswordValidationPolicy.IPasswordValidationConfiguration;
import CSCI5308.GroupFormationTool.PasswordValidationPolicy.MinLowercaseValidation;

class MinLowercaseValidationTest {

	@Test
	void isValidPasswordTest() {
		IPasswordValidationConfiguration config = new  PasswordValidationConfigurationMock();
		MinLowercaseValidation validator = new MinLowercaseValidation();
		
		assertTrue(validator.isValidPassword("RaouF", config));
		assertTrue(validator.isValidPassword("RAouf", config));
		assertTrue(validator.isValidPassword("rAOuf", config));
		assertTrue(validator.isValidPassword("rAouF", config));
		assertTrue(validator.isValidPassword("rAouF1@", config));

		assertTrue(validator.isValidPassword("RaOuF", config));
		assertTrue(validator.isValidPassword("RAOuf", config));
		assertTrue(validator.isValidPassword("rAOUf", config));
		assertTrue(validator.isValidPassword("rAOuF", config));
		assertTrue(validator.isValidPassword("rAoUF1@", config));

		assertFalse(validator.isValidPassword("RAOUF", config));
		assertFalse(validator.isValidPassword("RAOUF1@", config));
		assertFalse(validator.isValidPassword("1234", config));
		assertFalse(validator.isValidPassword("!@#$%", config));
		
		assertFalse(validator.isValidPassword(null, config));
	}
	
	@Test 
	void getMinLowercaseTest() {
		IPasswordValidationConfiguration config = new  PasswordValidationConfigurationMock();
		MinLowercaseValidation validator = new MinLowercaseValidation();
		assertTrue(validator.isValidPassword("abcdefghijkl", config));
		assertEquals(2, validator.getMinLowercase());
	}
	
	@Test
	void getValidationMessageTest() {
		IPasswordValidationConfiguration config = new  PasswordValidationConfigurationMock();
		MinLowercaseValidation validator = new MinLowercaseValidation();
		
		assertEquals(validator.getValidationFailureMessage("RAouf", config),
				String.format(MinLowercaseValidation.VALID_PASSWORD_MESSAGE, validator.getMinLowercase()));

		assertEquals(validator.getValidationFailureMessage("RaOUF", config),
				String.format(MinLowercaseValidation.INVALID_PASSWORD_MESSAGE, validator.getMinLowercase()));
	}
}
