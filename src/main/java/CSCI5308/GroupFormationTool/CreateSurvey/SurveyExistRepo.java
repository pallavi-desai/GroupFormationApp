package CSCI5308.GroupFormationTool.CreateSurvey;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;


public class SurveyExistRepo implements ISurveyExistRepo {
    private static final Logger logger = LoggerFactory.getLogger(CreateSurveyDB.class);

    @Override
    public int checkSurveyStatus(long courseID) {
        int state = 2;
        CallStoredProcedure procedure = null;
        try {
            procedure = new CallStoredProcedure("spCheckSurveyExist(?)");
            procedure.setParameter(1, courseID);
            ResultSet results = procedure.executeWithResults();
            if (null != results) {
                while (results.next()) {
                    state = results.getInt(1);
                }
            }

        } catch (SQLException e) {
            logger.error("Survey status could not be retrieved  state= {}, message={}", "Fail", e.getMessage());
        } finally {
            if (null != procedure) {
                procedure.cleanup();
            }
        }
        logger.info(" Survey availability in Database state= {}", state);
        return state;
    }
}
