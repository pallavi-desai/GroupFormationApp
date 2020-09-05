package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.InterfaceUser;

public interface ICourseUserRelationshipPersistence {
	public List<InterfaceUser> findAllUsersWithoutCourseRole(Role role, long courseID);

	public List<InterfaceUser> findAllUsersWithCourseRole(Role role, long courseID);

	public boolean enrollUser(InterfaceCourse course, InterfaceUser user, Role role);

	public List<Role> loadUserRolesForCourse(InterfaceCourse course, InterfaceUser user);
}
