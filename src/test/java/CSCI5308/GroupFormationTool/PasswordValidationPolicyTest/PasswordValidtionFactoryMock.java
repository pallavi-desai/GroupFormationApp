package CSCI5308.GroupFormationTool.PasswordValidationPolicyTest;

import CSCI5308.GroupFormationTool.PasswordValidationPolicy.DefaultPasswordValidationFactory;
import CSCI5308.GroupFormationTool.PasswordValidationPolicy.IPasswordValidationConfiguration;

public class PasswordValidtionFactoryMock extends DefaultPasswordValidationFactory {
	
	@Override
	public IPasswordValidationConfiguration createPasswordValidationConfig() {
		return new PasswordValidationConfigurationMock();
	}

}
