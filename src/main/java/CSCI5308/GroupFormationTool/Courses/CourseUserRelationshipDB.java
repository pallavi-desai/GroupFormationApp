package CSCI5308.GroupFormationTool.Courses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.AccessControl.InterfaceUser;
import CSCI5308.GroupFormationTool.AccessControl.UserFactory;
import CSCI5308.GroupFormationTool.AccessControl.UserObjectFactory;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class CourseUserRelationshipDB implements ICourseUserRelationshipPersistence {
	private static final Logger log = LoggerFactory.getLogger(CourseUserRelationshipDB.class);

	public List<InterfaceUser> findAllUsersWithoutCourseRole(Role role, long courseID) {
		List<InterfaceUser> users = new ArrayList<InterfaceUser>();
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spFindUsersWithoutCourseRole(?, ?)");
			proc.setParameter(1, role.toString());
			proc.setParameter(2, courseID);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					long userID = results.getLong(1);
					String bannerID = results.getString(2);
					String firstName = results.getString(3);
					String lastName = results.getString(4);
					InterfaceUser u = UserObjectFactory.createObject(new UserFactory());
					u.setID(userID);
					u.setBannerID(bannerID);
					u.setFirstName(firstName);
					u.setLastName(lastName);
					users.add(u);
				}
			}
		} catch (SQLException e) {
			log.error("Sql Exception = {}", e.getMessage());
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return users;
	}

	public List<InterfaceUser> findAllUsersWithCourseRole(Role role, long courseID) {
		List<InterfaceUser> users = new ArrayList<InterfaceUser>();
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spFindUsersWithCourseRole(?, ?)");
			proc.setParameter(1, role.toString());
			proc.setParameter(2, courseID);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					long userID = results.getLong(1);
					InterfaceUser u = UserObjectFactory.createObject(new UserFactory());
					u.setID(userID);
					users.add(u);
				}
			}
		} catch (SQLException e) {
			log.error("Sql Exception = {}", e.getMessage());
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return users;
	}

	public boolean enrollUser(InterfaceCourse course, InterfaceUser user, Role role) {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spEnrollUser(?, ?, ?)");
			proc.setParameter(1, course.getId());
			proc.setParameter(2, user.getID());
			proc.setParameter(3, role.toString());
			proc.execute();
			log.info("User = {} has enrolled as Role ={} in the Course={}", user.getBannerID(), role.toString(),
					course.getId());
		} catch (SQLException e) {
			log.error("Sql Exception= {}", e.getMessage());
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}

	public List<Role> loadUserRolesForCourse(InterfaceCourse course, InterfaceUser user) {
		List<Role> roles = new ArrayList<Role>();
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spLoadUserRolesForCourse(?, ?)");
			proc.setParameter(1, course.getId());
			proc.setParameter(2, user.getID());
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					Role role = Role.valueOf(results.getString(1).toUpperCase());
					roles.add(role);
				}
			}
			log.info("User = {} has  Roles={} in the CourseID= {} CourseTitle={}", user.getBannerID(), roles,
					course.getId(), course.getTitle());
		} catch (SQLException e) {
			log.error("Sql Exception ={}", e.getMessage());
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return roles;
	}

}