package CSCI5308.GroupFormationTool.CreateSurvey;

public interface IQueryQuestionsRepo {

    ICreateSurveyQuestionsModel listQuestionsForUser(long userID);
}
