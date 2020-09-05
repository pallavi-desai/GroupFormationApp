package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class DeleteQuestionsRepo implements InterfaceDeleteQuestionsRepo {
	private static final Logger log = LoggerFactory.getLogger(DeleteQuestionsRepo.class);

	@Override
	public boolean checkIfResponsesExistInDB(long userId, String[] selectedQuestions) {
		CallStoredProcedure procedure = null;
		CallStoredProcedure proc = null;

		try {
			for (int i = 0; i < selectedQuestions.length; i++) {
				procedure = new CallStoredProcedure("spFindQuestionId(?)");
				procedure.setParameter(1, selectedQuestions[i]);
				ResultSet results = procedure.executeWithResults();

				if (null != results) {
					while (results.next()) {
						proc = new CallStoredProcedure("spCheckResponse(?)");
						proc.setParameter(1, results.getInt(1));
						ResultSet resultSet = proc.executeWithResults();
						if (resultSet.next()) {
							return false;
						}
					}
				}
			}
		} catch (SQLException e) {
			log.error("Sql Exception ={}", e.getMessage());
			return false;
		} finally {
			if (null != proc) {
				procedure.cleanup();
				proc.cleanup();
			}
		}
		return true;
	}

}
