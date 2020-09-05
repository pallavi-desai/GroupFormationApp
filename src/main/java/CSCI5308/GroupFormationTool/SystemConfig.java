package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.UserDB;
import CSCI5308.GroupFormationTool.Courses.CourseDBFactory;
import CSCI5308.GroupFormationTool.Courses.CourseUserRelationshipDB;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Courses.ObjectFactory;
import CSCI5308.GroupFormationTool.Database.DefaultDatabaseConfiguration;
import CSCI5308.GroupFormationTool.Database.IDatabaseConfiguration;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.DefaultGroupFormationFactory;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.IGroupFormationFactory;
import CSCI5308.GroupFormationTool.PasswordValidationPolicy.DefaultPasswordValidationFactory;
import CSCI5308.GroupFormationTool.PasswordValidationPolicy.IPasswordValidationFactory;
import CSCI5308.GroupFormationTool.Security.DefaultSecurityFactory;
import CSCI5308.GroupFormationTool.Security.ISecurityFactory;

public class SystemConfig {

	private static SystemConfig uniqueInstance = null;
	private IUserPersistence userDB;
	private IDatabaseConfiguration databaseConfiguration;
	private ICoursePersistence courseDB;
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;
	private IPasswordValidationFactory passwordValidationFactory;
	private ISecurityFactory securityFactory;
	private IGroupFormationFactory groupFormationFactory;

	private SystemConfig() {
		userDB = new UserDB();
		databaseConfiguration = new DefaultDatabaseConfiguration();
		courseDB = ObjectFactory.createDBObject(new CourseDBFactory());
		courseUserRelationshipDB = new CourseUserRelationshipDB();
		passwordValidationFactory = new DefaultPasswordValidationFactory();
		securityFactory = new DefaultSecurityFactory();
		groupFormationFactory = new DefaultGroupFormationFactory();
	}

	public static SystemConfig instance() {
		if (null == uniqueInstance) {
			uniqueInstance = new SystemConfig();
		}
		return uniqueInstance;
	}

	public IUserPersistence getUserDB() {
		return userDB;
	}

	public void setUserDB(IUserPersistence userDB) {
		this.userDB = userDB;
	}

	public IDatabaseConfiguration getDatabaseConfiguration() {
		return databaseConfiguration;
	}

	public void setDatabaseConfiguration(IDatabaseConfiguration databaseConfiguration) {
		this.databaseConfiguration = databaseConfiguration;
	}

	public void setCourseDB(ICoursePersistence courseDB) {
		this.courseDB = courseDB;
	}

	public ICoursePersistence getCourseDB() {
		return courseDB;
	}

	public void setCourseUserRelationshipDB(ICourseUserRelationshipPersistence courseUserRelationshipDB) {
		this.courseUserRelationshipDB = courseUserRelationshipDB;
	}

	public ICourseUserRelationshipPersistence getCourseUserRelationshipDB() {
		return courseUserRelationshipDB;
	}

	public IPasswordValidationFactory getPasswordValidationFactory() {
		return passwordValidationFactory;
	}

	public void setPasswordValidationFactory(IPasswordValidationFactory passwordValidationFactory) {
		this.passwordValidationFactory = passwordValidationFactory;
	}

	public ISecurityFactory getSecurityFactory() {
		return securityFactory;
	}

	public void setSecurityFactory(ISecurityFactory securityFactory) {
		this.securityFactory = securityFactory;
	}

	public IGroupFormationFactory getGroupFormationFactory() {
		return this.groupFormationFactory;
	}

	public void setGroupFormationFactory(IGroupFormationFactory groupFormationFactory) {
		this.groupFormationFactory = groupFormationFactory;
	}
}
