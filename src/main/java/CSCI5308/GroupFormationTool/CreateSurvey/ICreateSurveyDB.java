package CSCI5308.GroupFormationTool.CreateSurvey;

public interface ICreateSurveyDB {

    boolean saveSurvey(long courseID, long userID, int status);

    void createNewSurvey(long courseID, long status);

    int fetchSurveyID(long courseID, int state);

	boolean fetchSavedQuestions(long courseID);

	boolean updatePublishStatus(long courseID);
}
