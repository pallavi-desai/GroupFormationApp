package CSCI5308.GroupFormationTool.AccessControl;

public class UserObjectFactory {

	public static InterfaceUser createObject(UserAbstractFactory userObj) {
		return userObj.createUserObject();
	}

}
