package CSCI5308.GroupFormationTool.SurveyResponses;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class SurveyResultDB implements ISurveyresultDB {
	private static final Logger log = LoggerFactory.getLogger(SurveyResultDB.class);

	@Override
	public boolean checkIfResponseSubmitted(long userID, long surveyID) {
		CallStoredProcedure proc = null;
		ResultSet results;
		Boolean present;
		try {
			proc = new CallStoredProcedure("spFindSurveyResponse(?,?)");
			proc.setParameter(1, userID);
			proc.setParameter(2, surveyID);
			results = proc.executeWithResults();
			present = results.next();
			log.warn("Response alrady Present={} for UserID = {} for SurveyID = {}", present, userID, surveyID);
		} catch (SQLException e) {
			log.error("Sql Exception = {}", e.getMessage());
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return present;
	}

	@Override
	public boolean submitSurveyResponse(long userID, long surveyID, long questionID, String result) {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spCreateSurveyResponse(?,?,?,?)");
			proc.setParameter(1, userID);
			proc.setParameter(2, surveyID);
			proc.setParameter(3, questionID);
			proc.setParameter(4, result);
			proc.execute();
			log.info("Response submitted for UserID={} SurveyID={} questionID ={}", userID, surveyID, questionID);
		} catch (SQLException e) {
			log.error("Sql Exception ={}", e.getMessage());
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}
}
