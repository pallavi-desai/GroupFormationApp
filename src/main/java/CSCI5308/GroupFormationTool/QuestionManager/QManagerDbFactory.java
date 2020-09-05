package CSCI5308.GroupFormationTool.QuestionManager;

public class QManagerDbFactory implements IQManagerDbFactory {
	private static QManagerDbFactory qManagerDbFactory = null;

	public static IQManagerDbFactory FactorySingleton() {
		if (qManagerDbFactory == null) {
			qManagerDbFactory = new QManagerDbFactory();
		}
		return qManagerDbFactory;
	}

	@Override
	public IQuestionsPersistence createQuestionDB() {
		return new QuestionDB();
	}

	@Override
	public IQuestionSorters createSortingDB() {
		return new SortingDB();
	}

	@Override
	public InterfaceDeleteQuestionsRepo createDeleteQuestionRepo() {
		return new DeleteQuestionsRepo();
	}

	@Override
	public InterfaceListQuestionsRepo createListQuestionsRepo() {
		return new ListQuestionsRepo();
	}
}
