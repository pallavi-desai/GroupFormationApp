package CSCI5308.GroupFormationTool.QuestionManager;

public class QManagerModelFactory implements IQManagerModelFactory {
	private static QManagerModelFactory qManagerModelFactory = null;

	public static IQManagerModelFactory FactorySingleton() {
		if (qManagerModelFactory == null) {
			qManagerModelFactory = new QManagerModelFactory();
		}
		return qManagerModelFactory;
	}

	@Override
	public InterfaceQuestionModel createQuestionModel() {
		return new QuestionModel();
	}

	@Override
	public InterfaceResponses createResponses() {
		return new Responses();
	}

	@Override
	public InterfaceSorters createSorters() {
		return new Sorters();
	}

	@Override
	public InterfaceDeleteQuestionsModel createDeleteQuestionsModel() {
		return new DeleteQuestionsModel();
	}
}
