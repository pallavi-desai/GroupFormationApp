package CSCI5308.GroupFormationTool.QuestionManager;

public interface InterfaceDeleteQuestionsRepo {

	boolean checkIfResponsesExistInDB(long userId, String[] selectedQuestions);

}
