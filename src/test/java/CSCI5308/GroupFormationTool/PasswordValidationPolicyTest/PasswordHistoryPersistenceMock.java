package CSCI5308.GroupFormationTool.PasswordValidationPolicyTest;

import CSCI5308.GroupFormationTool.PasswordValidationPolicy.IPasswordHistoryPersistence;

public class PasswordHistoryPersistenceMock implements IPasswordHistoryPersistence {

	private boolean result;
	
	public PasswordHistoryPersistenceMock(boolean result) {
		this.result = result;
	}
	
	@Override
	public boolean followedHistoryConstraint(String bannerID, String password, int history) {
		return this.result;
	}

}
