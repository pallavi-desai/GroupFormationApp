package CSCI5308.GroupFormationTool.Courses;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.InterfaceUser;
import CSCI5308.GroupFormationTool.AccessControl.UserFactory;
import CSCI5308.GroupFormationTool.AccessControl.UserObjectFactory;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

public class StudentCSVImport {
	private static final Logger log = LoggerFactory.getLogger(StudentCSVImport.class);

	private List<String> successResults;
	private List<String> failureResults;
	private InterfaceCourse course;
	private IUserPersistence userDB;
	private IPasswordEncryption passwordEncryption;
	private IStudentCSVParser parser;

	public StudentCSVImport(IStudentCSVParser parser, InterfaceCourse course) {
		this.course = course;
		successResults = new ArrayList<String>();
		failureResults = new ArrayList<String>();
		userDB = SystemConfig.instance().getUserDB();
		passwordEncryption = SystemConfig.instance().getSecurityFactory().createPassworEncryption();
		this.parser = parser;
		enrollStudentFromRecord();
	}

	private void enrollStudentFromRecord() {
		List<InterfaceUser> studentList = parser.parseCSVFile(failureResults);
		for (InterfaceUser u : studentList) {
			String bannerID = u.getBanner();
			String firstName = u.getFirstName();
			String lastName = u.getLastName();
			String email = u.getEmail();
			String userDetails = bannerID + " " + firstName + " " + lastName + " " + email;

			InterfaceUser user = UserObjectFactory.createObject(new UserFactory());
			userDB.loadUserByBannerID(bannerID, user);
			if (!user.isValidUser()) {
				user.setBannerID(bannerID);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				if (user.createUser(userDB, passwordEncryption, null)) {
					successResults.add("Created: " + userDetails);
					userDB.loadUserByBannerID(bannerID, user);
				} else {
					failureResults.add("Unable to save this user to DB: " + userDetails);
					return;
				}
			}
			if (course.enrollUserInCourse(Role.STUDENT, user)) {
				successResults.add("User enrolled in course: " + userDetails);
				log.info("User enrolled in course USER = {}", userDetails);
			} else {
				failureResults.add("Unable to enroll user in course: " + userDetails);
				log.warn("Unable to enroll user in course USER={}", userDetails);
			}
		}
	}

	public List<String> getSuccessResults() {
		return successResults;
	}

	public List<String> getFailureResults() {
		return failureResults;
	}
}
