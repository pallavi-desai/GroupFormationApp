package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class ListQuestionsRepo implements InterfaceListQuestionsRepo {
	private static final Logger log = LoggerFactory.getLogger(ListQuestionsRepo.class);

	@Override
	public InterfaceDeleteQuestionsModel listQuestionsFromDB(long userId) {
		ArrayList<String> questionText = new ArrayList<>();

		CallStoredProcedure procedure = null;
		try {
			procedure = new CallStoredProcedure("spListQuestions(?)");
			procedure.setParameter(1, userId);
			ResultSet results = procedure.executeWithResults();
			if (null != results) {
				while (results.next()) {
					questionText.add(results.getString(1));
				}
			}
		} catch (SQLException e) {
			log.error("Sql Exception = {}", e.getMessage());
		} finally {
			if (null != procedure) {
				procedure.cleanup();
			}
		}
		String[] array = questionText.toArray(new String[0]);
		InterfaceDeleteQuestionsModel interfaceDeleteQuestionsModel = QManagerModelFactory.FactorySingleton()
				.createDeleteQuestionsModel();
		interfaceDeleteQuestionsModel.setListQuestions(array);
		return interfaceDeleteQuestionsModel;
	}
}
