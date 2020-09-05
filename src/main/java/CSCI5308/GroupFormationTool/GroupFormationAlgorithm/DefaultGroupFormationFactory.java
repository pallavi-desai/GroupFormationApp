package CSCI5308.GroupFormationTool.GroupFormationAlgorithm;

public class DefaultGroupFormationFactory implements IGroupFormationFactory {
	@Override
	public ISurveyScale createSurveyScaleObject() {
		return new SurveyScale();
	}
	
	@Override
	public ISurveyScale createSurveyScaleMCQ1() {
		return new SurveyScaleMCQ1();
	}
	
	@Override
	public ISurveyScale createSurveyScaleMCQ2() {
		return new SurveyScaleMCQ2();
	}
	
	@Override
	public ISurveyScale createSurveyScaleNumeric() {
		return new SurveyScaleNumeric();
	}
	
	@Override
	public ISurveyScale createSurveyScaleText() {
		return new SurveyScaleText();
	}
	
	@Override
	public IGroupFormationAlgorithm createGroupFormationAlgorithm() {
		return new GroupFormationMinDistance();
	}

		@Override 
	public IGroup createGroup() {
		return new Group();
	}
}
