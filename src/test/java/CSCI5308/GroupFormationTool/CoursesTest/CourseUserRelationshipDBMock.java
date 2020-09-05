package CSCI5308.GroupFormationTool.CoursesTest;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.InterfaceUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.AccessControl.UserFactory;
import CSCI5308.GroupFormationTool.AccessControl.UserObjectFactory;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Courses.InterfaceCourse;
import CSCI5308.GroupFormationTool.Courses.Role;

class CourseUserRelationshipDBMock implements ICourseUserRelationshipPersistence 
{
	public List<InterfaceUser> findAllUsersWithoutCourseRole(Role role, long courseID) 
	{
		List<InterfaceUser> userList = new ArrayList<>();
		InterfaceUser u = UserObjectFactory.createObject(new UserFactory());
		u.setId(0);
		userList.add(u);
		u = new User();
		u.setId(1);
		userList.add(u);
		return userList;
	}

	public List<InterfaceUser> findAllUsersWithCourseRole(Role role, long courseID) 
	{
		List<InterfaceUser> userList = new ArrayList<>();
		InterfaceUser u = UserObjectFactory.createObject(new UserFactory());
		u.setId(0);
		userList.add(u);
		u = new User();
		u.setId(1);
		userList.add(u);
		return userList;
	}

	public boolean enrollUser(InterfaceCourse interfaceCourse, InterfaceUser user, Role role)
	{
		user.setId(0);
		interfaceCourse.setId(0);
		interfaceCourse.setTitle("Software Engineering");
		return true;

	}

	public List<Role> loadUserRolesForCourse(InterfaceCourse interfaceCourse, InterfaceUser user)
	{
		List<Role> userRoles = new ArrayList<>();
		userRoles.add(Role.STUDENT);
		userRoles.add(Role.TA);
		return userRoles;
	}

}
