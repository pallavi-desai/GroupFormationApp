package CSCI5308.GroupFormationTool.CreateSurvey;

public interface ICreateSurveyModelFactory {
    ICreateSurveyQuestionsModel createSurveyQuestionsModel();

    IUpdateQuestionsListService getUpdateQuestionsListService();

    IListQuestionsService getListQuestionsService();

}
