package CSCI5308.GroupFormationTool.PasswordValidationPolicy;

public class DefaultPasswordValidationFactory implements IPasswordValidationFactory {

	@Override
	public IPasswordValidationConfiguration createPasswordValidationConfig() {
		return new DefaultPasswordValidationConfiguration();
	}

	@Override
	public IPasswordValidationManager createPasswordValidationManager() {
		return new DefaultPasswordValidationManager();
	}

	@Override
	public IPasswordValidation createMinLengthValidation() {
		return new MinLengthValidation();
	}

	@Override
	public IPasswordValidation createMaxLenghtValidation() {
		return new MaxLengthValidation();
	}

	@Override
	public IPasswordValidation createMinLowerCaseValidation() {
		return new MinLowercaseValidation();
	}

	@Override
	public IPasswordValidation createMinUpperCaseValidation() {
		return new MinUppercaseValidation();
	}

	@Override
	public IPasswordValidation createMinNonAlphaNumValidation() {
		return new MinNonAlphaNumValidation();
	}

	@Override
	public IPasswordValidation createForbiddenCharSetValidation() {
		return new ForbiddenCharSetValidation();
	}

	@Override
	public IPasswordValidation createHistoryConstraintValidation() {
		IPasswordHistoryPersistence persistence = createPasswordHistoryPersistence();
		return new HistoryConstraintValidation(persistence);
	}

	@Override
	public IPasswordHistoryPersistence createPasswordHistoryPersistence() {
		return new PasswordHistoryDB();
	}
}
