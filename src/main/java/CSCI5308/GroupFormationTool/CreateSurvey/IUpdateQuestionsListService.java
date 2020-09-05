package CSCI5308.GroupFormationTool.CreateSurvey;

public interface IUpdateQuestionsListService {
    ICreateSurveyQuestionsModel displayUpdatedQuestionList(String[] heading, String[] type, String que);

    ICreateSurveyQuestionsModel removeQuestions(String que);
}
