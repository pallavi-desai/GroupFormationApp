package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.InterfaceUser;

public class UserDBMock implements IUserPersistence
{
	public void loadUserByID(long id, InterfaceUser user)
	{
		user.setID(id);
		user.setBannerID("B00000000");
		user.setPassword("Pass@123");
		user.setFirstName("Rob");
		user.setLastName("Hawkey");
		user.setEmail("rhawkey@dal.ca");
	}

	public void loadUserByBannerID(String bannerID, InterfaceUser user)
	{
		user.setID(1);
		user.setBannerID(bannerID);
		user.setPassword("Pass@123");
		user.setFirstName("Rob");
		user.setLastName("Hawkey");
		user.setEmail("rhawkey@dal.ca");
	}
	
	public boolean createUser(InterfaceUser user)
	{
		user.setID(0);
		user.setBannerID("B00000000");
		user.setPassword("Pass@123");
		user.setFirstName("Rob");
		user.setLastName("Hawkey");
		user.setEmail("rhawkey@dal.ca");
		return true;
	}
	
	public boolean updateUser(InterfaceUser user)
	{
		user.setID(0);
		user.setBannerID("B00000000");
		user.setPassword("Pass@123");
		user.setFirstName("Rob");
		user.setLastName("Hawkey");
		user.setEmail("rhawkey@dal.ca");
		return true;
	}

	@Override
	public Long loadInstructorByBannerID(String bannerID) {
		// TODO Auto-generated method stub
		return null;
	}
}
