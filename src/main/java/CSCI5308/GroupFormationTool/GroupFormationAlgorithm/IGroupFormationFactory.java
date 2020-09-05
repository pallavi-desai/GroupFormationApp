package CSCI5308.GroupFormationTool.GroupFormationAlgorithm;

public interface IGroupFormationFactory {
	public ISurveyScale createSurveyScaleObject();
	public ISurveyScale createSurveyScaleMCQ1();
	public ISurveyScale createSurveyScaleMCQ2();
	public ISurveyScale createSurveyScaleNumeric();
	public ISurveyScale createSurveyScaleText();
	public IGroupFormationAlgorithm createGroupFormationAlgorithm();
	public IGroup createGroup();
}
