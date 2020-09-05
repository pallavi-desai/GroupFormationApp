package CSCI5308.GroupFormationTool.AccessControl;

public interface IUserPersistence {
	public void loadUserByID(long id, InterfaceUser user);

	public void loadUserByBannerID(String bannerID, InterfaceUser user);

	public boolean createUser(InterfaceUser user);

	public boolean updateUser(InterfaceUser user);

	public Long loadInstructorByBannerID(String bannerID);
}
