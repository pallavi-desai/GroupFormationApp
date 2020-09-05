package CSCI5308.GroupFormationTool.PasswordValidationPolicy;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class PasswordHistoryDB implements IPasswordHistoryPersistence {

	private static final Logger log = LoggerFactory.getLogger(PasswordHistoryDB.class);
	private static final String PASSWORD_HISTORY_DB_LOG = "PasswordHistoryDB";

	@Override
	public boolean followedHistoryConstraint(String bannerID, String password, int history) {
		CallStoredProcedure proc = null;
		boolean isValid = false;

		try {
			proc = new CallStoredProcedure("spFindPasswordHistory(?, ?, ?)");
			proc.setParameter(1, bannerID);
			proc.setParameter(2, password);
			proc.setParameter(3, history);
			ResultSet results = proc.executeWithResults();
			if (results.next() == false) {
				isValid = true;
			}
		} catch (SQLException e) {
			log.error("password={}, user={}, action={}, state={}, message={}", PASSWORD_HISTORY_DB_LOG, bannerID,
					"Check History Constraint", "Fail", e.getMessage());
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		log.info("password={}, user={}, action={}, valid={}, state={}", PASSWORD_HISTORY_DB_LOG, bannerID,
				"Check History Constraint", isValid, "Success");
		return isValid;
	}
}
