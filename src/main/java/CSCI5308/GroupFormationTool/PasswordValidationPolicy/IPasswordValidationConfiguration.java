package CSCI5308.GroupFormationTool.PasswordValidationPolicy;

public interface IPasswordValidationConfiguration {
	public String getConfig(String configKey) throws IllegalArgumentException;
}
