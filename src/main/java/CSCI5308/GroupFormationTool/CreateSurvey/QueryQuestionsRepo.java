package CSCI5308.GroupFormationTool.CreateSurvey;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class QueryQuestionsRepo implements IQueryQuestionsRepo {
    private static final Logger logger = LoggerFactory.getLogger(CreateSurveyDB.class);
    ICreateSurveyModelFactory iCreateSurveyModelFactory;
    ICreateSurveyQuestionsModel iCreateSurveyQuestionsModel;

    @Override
    public ICreateSurveyQuestionsModel listQuestionsForUser(long userID) {
        iCreateSurveyModelFactory = CreateSurveyModelFactory.FactorySingleton();
        iCreateSurveyQuestionsModel = iCreateSurveyModelFactory.createSurveyQuestionsModel();

        ArrayList<String> questionHeading = new ArrayList<>();
        ArrayList<String> questionType = new ArrayList<>();
        String[] savedQuestions;
        String[] savedType;
        savedQuestions = iCreateSurveyQuestionsModel.getSelectedQuestions();
        savedType = iCreateSurveyQuestionsModel.getSelectedTypes();

        CallStoredProcedure procedure = null;
        try {
            procedure = new CallStoredProcedure("spListSurveyQuestions(?)");
            procedure.setParameter(1, userID);
            ResultSet results = procedure.executeWithResults();
            if (null != results) {
                while (results.next()) {
                    questionHeading.add(results.getString(2));
                    questionType.add(results.getString(3));
                }
            }
            logger.info(" Listing Questions from Database state= {}", "Success");
        } catch (SQLException e) {
            logger.error(" Listing Questions from Database state= {}, message={}", "Fail", e.getMessage());
        } finally {
            if (null != procedure) {
                procedure.cleanup();
            }
        }
        if (savedQuestions != null) {
            for (int i = 0; i < savedQuestions.length; i++) {
                if (questionHeading.contains(savedQuestions[i])) {
                    questionHeading.remove(savedQuestions[i]);
                    questionType.remove(savedType[i]);
                }
            }
        }
        String[] questionsList = questionHeading.toArray(new String[0]);
        String[] questionsType = questionType.toArray(new String[0]);

		iCreateSurveyQuestionsModel.setQuestionHeading(questionsList);
		iCreateSurveyQuestionsModel.setQuestionType(questionsType);
		return iCreateSurveyQuestionsModel;
	}
}
