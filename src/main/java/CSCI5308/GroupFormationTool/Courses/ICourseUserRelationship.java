package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.InterfaceUser;

public interface ICourseUserRelationship {
	public boolean userHasRoleInCourse(InterfaceUser user, Role role, InterfaceCourse course);

	public List<Role> loadAllRoluesForUserInCourse(InterfaceUser user, InterfaceCourse course);

	public boolean enrollUserInCourse(InterfaceUser user, InterfaceCourse course, Role role);
}
