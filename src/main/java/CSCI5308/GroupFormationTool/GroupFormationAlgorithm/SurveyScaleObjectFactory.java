package CSCI5308.GroupFormationTool.GroupFormationAlgorithm;

public class SurveyScaleObjectFactory {
	public static ISurveyScale createObject(ISurveyScaleAbstractFactory obj) {
		return obj.createSurveyScaleObject();
	}

	public static ISurveyResponse createObject(ISurveyResponseAbstractFactory obj) {
		return obj.createSurveyResponseObject();
	}
}
