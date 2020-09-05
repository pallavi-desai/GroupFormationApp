package CSCI5308.GroupFormationTool.PasswordValidationPolicy;

public interface IPasswordValidationFactory {
	public IPasswordValidationManager createPasswordValidationManager();

	public IPasswordValidationConfiguration createPasswordValidationConfig();

	public IPasswordValidation createMinLengthValidation();

	public IPasswordValidation createMaxLenghtValidation();

	public IPasswordValidation createMinLowerCaseValidation();

	public IPasswordValidation createMinUpperCaseValidation();

	public IPasswordValidation createMinNonAlphaNumValidation();

	public IPasswordValidation createForbiddenCharSetValidation();

	public IPasswordValidation createHistoryConstraintValidation();

	public IPasswordHistoryPersistence createPasswordHistoryPersistence();
}
