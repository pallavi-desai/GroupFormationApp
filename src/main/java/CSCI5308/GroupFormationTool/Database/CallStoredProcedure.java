package CSCI5308.GroupFormationTool.Database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CallStoredProcedure {
	private static final Logger log = LoggerFactory.getLogger(CallStoredProcedure.class);

	private String storedProcedureName;
	private Connection connection;
	private CallableStatement statement;

	public CallableStatement getStatement() {
		return statement;
	}

	public void setStatement(CallableStatement statement) {
		this.statement = statement;
	}

	public CallStoredProcedure(String storedProcedureName) throws SQLException {
		this.storedProcedureName = storedProcedureName;
		connection = null;
		statement = null;
		openConnection();
		createStatement();
	}

	private void createStatement() {
		try {
			statement = connection.prepareCall("{call " + storedProcedureName + "}");
		} catch (SQLException e) {
			log.error("SQL Exception while creating sql statement = {}", e.getMessage());
		}
	}

	private void openConnection() {
		try {
			connection = ConnectionManager.instance().getDBConnection();
			log.info("DB connection open");
		} catch (SQLException e) {
			log.error("SQL Exception while connection setup ={}", e.getMessage());
		}
	}

	public void cleanup() {
		try {
			if (null != statement) {
				statement.close();
			}
			if (null != connection) {
				if (!connection.isClosed()) {
					connection.close();
					log.info("Closing DB connection");
				}
			}
		} catch (Exception e) {
			log.error("SQL Exception = {}", e.getMessage());
		}
	}

	public void setParameter(int paramIndex, String value) {
		try {
			statement.setString(paramIndex, value);
		} catch (SQLException e) {
			log.error("SQL Exception while setting parameters = {}", e.getMessage());
		}
	}

	public void registerOutputParameterString(int paramIndex) throws SQLException {
		statement.registerOutParameter(paramIndex, java.sql.Types.VARCHAR);
	}

	public void setParameter(int paramIndex, long value) {
		try {
			statement.setLong(paramIndex, value);
		} catch (SQLException e) {
			log.error("SQL Exception while setting parameters = {}", e.getMessage());
		}
	}

	public void registerOutputParameterLong(int paramIndex) throws SQLException {
		statement.registerOutParameter(paramIndex, java.sql.Types.BIGINT);
	}

	public ResultSet executeWithResults() {
		try {
			if (statement.execute()) {
				return statement.getResultSet();
			}
		} catch (SQLException e) {
			log.error("SQL Exception while execution = {}", e.getMessage());
		}
		return null;
	}

	public void execute() {
		try {
			statement.execute();
		} catch (SQLException e) {
			log.error("SQL Exception while execution={}", e.getMessage());
		}
	}

	public void setParameter(int paramIndex, Timestamp timestamp) throws SQLException {
		statement.setTimestamp(paramIndex, timestamp);
	}
}
