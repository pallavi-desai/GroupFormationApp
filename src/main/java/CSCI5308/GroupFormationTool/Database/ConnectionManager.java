package CSCI5308.GroupFormationTool.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.SystemConfig;

// Singleton for retrieving connections.
public class ConnectionManager {
	private static final Logger log = LoggerFactory.getLogger(ConnectionManager.class);
	private static ConnectionManager uniqueInstance = null;

	private String dbURL;
	private String dbUserName;
	private String dbPassword;

	public ConnectionManager() {
		IDatabaseConfiguration config = SystemConfig.instance().getDatabaseConfiguration();
		dbURL = config.getDatabaseURL();
		dbUserName = config.getDatabaseUserName();
		dbPassword = config.getDatabasePassword();
	}

	public static ConnectionManager instance() {
		if (null == uniqueInstance) {
			uniqueInstance = new ConnectionManager();
		}
		return uniqueInstance;
	}

	public Connection getDBConnection() throws SQLException {
		log.info("Opening the db connection for = {}", dbUserName);
		return DriverManager.getConnection(dbURL, dbUserName, dbPassword);
	}
}
