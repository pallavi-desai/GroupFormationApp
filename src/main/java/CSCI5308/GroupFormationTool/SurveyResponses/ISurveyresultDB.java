package CSCI5308.GroupFormationTool.SurveyResponses;

public interface ISurveyresultDB {
	boolean checkIfResponseSubmitted(long userID, long surveyID);

	boolean submitSurveyResponse(long userID, long surveyID, long questionID, String response);
}
