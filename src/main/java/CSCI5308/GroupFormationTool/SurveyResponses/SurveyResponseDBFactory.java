package CSCI5308.GroupFormationTool.SurveyResponses;

public class SurveyResponseDBFactory implements ISurveyResponseDBFactory {
	private static SurveyResponseDBFactory surveyResponseDBFactory = null;

	public static ISurveyResponseDBFactory FactorySingleton() {
		if (surveyResponseDBFactory == null) {
			surveyResponseDBFactory = new SurveyResponseDBFactory();
		}
		return surveyResponseDBFactory;
	}

	@Override
	public ISurveyResponseDB createSurveyResponseDB() {
		return new SurveyResponseDB();
	}

	@Override
	public ISurveyresultDB createSurveyResultDB() {
		return new SurveyResultDB();
	}
}
