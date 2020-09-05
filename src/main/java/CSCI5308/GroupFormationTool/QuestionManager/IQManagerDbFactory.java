package CSCI5308.GroupFormationTool.QuestionManager;

public interface IQManagerDbFactory {
	public IQuestionsPersistence createQuestionDB();

	public IQuestionSorters createSortingDB();

	public InterfaceDeleteQuestionsRepo createDeleteQuestionRepo();

	public InterfaceListQuestionsRepo createListQuestionsRepo();
}
