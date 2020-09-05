package CSCI5308.GroupFormationTool.CreateSurvey;

public interface ICreateSurveyDBFactory {
    ICreateSurveyDB createSurveyDB();

    IQueryQuestionsRepo queryQuestionsRepo();

    ISurveyExistRepo surveyExistRepo();

    void setQueryQuestionsRepo(IQueryQuestionsRepo queryQuestionsRepo);
}
