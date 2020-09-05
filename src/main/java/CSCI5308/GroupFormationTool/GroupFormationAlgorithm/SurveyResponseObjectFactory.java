package CSCI5308.GroupFormationTool.GroupFormationAlgorithm;

public class SurveyResponseObjectFactory implements ISurveyResponseAbstractFactory {

	@Override
	public ISurveyResponse createSurveyResponseObject() {
		return new SurveyResponse();
	}

}
