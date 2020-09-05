package CSCI5308.GroupFormationTool.PasswordValidationPolicy;

import java.util.List;

public interface IPasswordValidationManager {
	public boolean isValidPassword(String password);

	public List<String> getPasswordValidationFailures(String password);
}
