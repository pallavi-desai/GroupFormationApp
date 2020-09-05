package CSCI5308.GroupFormationTool.AccessControl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class UserDB implements IUserPersistence {
	private static final Logger log = LoggerFactory.getLogger(UserDB.class);

	public void loadUserByID(long id, InterfaceUser user) {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spLoadUser(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					long userID = results.getLong(1);
					String bannerID = results.getString(2);
					String password = results.getString(3);
					String firstName = results.getString(4);
					String lastName = results.getString(5);
					String email = results.getString(6);
					user.setID(userID);
					user.setBannerID(bannerID);
					user.setPassword(password);
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setEmail(email);
				}
			}
			log.info("User loaded for ID= {},Banner id ={} ", user.getID(), user.getBannerID());
		} catch (SQLException e) {
			log.error("Sql Exception = {}", e.getMessage());
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
	}

	public void loadUserByBannerID(String bannerID, InterfaceUser user) {
		CallStoredProcedure proc = null;
		long userID = -1;
		try {
			proc = new CallStoredProcedure("spFindUserByBannerID(?)");
			proc.setParameter(1, bannerID);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					userID = results.getLong(1);
				}
			}
		} catch (SQLException e) {
			log.error("Sql Exception = {}", e.getMessage());
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		if (userID > -1) {
			loadUserByID(userID, user);
		}
	}

	public boolean createUser(InterfaceUser user) {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spCreateUser(?, ?, ?, ?, ?, ?)");
			proc.setParameter(1, user.getBannerID());
			proc.setParameter(2, user.getPassword());
			proc.setParameter(3, user.getFirstName());
			proc.setParameter(4, user.getLastName());
			proc.setParameter(5, user.getEmail());
			proc.registerOutputParameterLong(6);
			proc.execute();
			log.info("User Created for = {}", user.getBannerID());
		} catch (SQLException e) {
			log.error("Sql Exception = {}", e.getMessage());
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}

	public boolean updateUser(InterfaceUser user) {
		return false;
	}

	public Long loadInstructorByBannerID(String bannerID) {
		CallStoredProcedure proc = null;
		Long userId = null;
		try {
			proc = new CallStoredProcedure("spFindInstructorByBannerID(?,?)");
			proc.setParameter(1, bannerID);
			proc.registerOutputParameterLong(2);
			proc.execute();
			userId = proc.getStatement().getLong(2);
		} catch (SQLException e) {
			log.error("Sql Exception = {}", e.getMessage());
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}

		return userId;
	}

}
