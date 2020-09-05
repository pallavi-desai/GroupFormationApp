package CSCI5308.GroupFormationTool.QuestionManager;

public interface IQManagerModelFactory {
	InterfaceQuestionModel createQuestionModel();

	InterfaceResponses createResponses();

	InterfaceSorters createSorters();

	InterfaceDeleteQuestionsModel createDeleteQuestionsModel();
}
