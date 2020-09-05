package CSCI5308.GroupFormationTool.PasswordValidationPolicy;

public interface IPasswordHistoryPersistence {
	public boolean followedHistoryConstraint(String bannerID, String password, int history);
}
