package CSCI5308.GroupFormationTool.AccessControl;

public class UserFactory implements UserAbstractFactory {
	public InterfaceUser createUserObject() {
		return new User();
	}

}
