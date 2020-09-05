package CSCI5308.GroupFormationTool.SurveyResponses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.QuestionManager.InterfaceQuestionModel;
import CSCI5308.GroupFormationTool.QuestionManager.InterfaceResponses;

public interface ISurveyResponseDB {
	HashMap<Integer, Integer> checkIfSurveyPublished(long courseID);

	List<InterfaceQuestionModel> getSurveyQuestions(long surveyID);

	Map<Long, ArrayList<InterfaceResponses>> getSurveyResponses();
}
