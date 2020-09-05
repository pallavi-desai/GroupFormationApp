package CSCI5308.GroupFormationTool.GroupFormationAlgorithm;

public class SurveyScaleFactory implements ISurveyScaleAbstractFactory {

	public ISurveyScale createSurveyScaleObject() {
		return new SurveyScale();
	}
}
