package CSCI5308.GroupFormationTool.AccessControl;

public interface IUserNotifications {
	public void sendUserLoginCredentials(InterfaceUser user, String rawPassword);
}
