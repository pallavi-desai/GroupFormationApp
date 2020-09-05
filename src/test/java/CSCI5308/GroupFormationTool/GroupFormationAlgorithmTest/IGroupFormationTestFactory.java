package CSCI5308.GroupFormationTool.GroupFormationAlgorithmTest;

import java.util.List;

import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.IGroup;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.IGroupFormationAlgorithm;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.ISurveyResponse;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.ISurveyScale;

public interface IGroupFormationTestFactory {
	public List<ISurveyResponse> createSurveyResponsesMock();
	public List<ISurveyScale>	createSurveyScalsesMock();
	public ISurveyScale createSurveyScaleMCQ1Mock();
	public ISurveyScale createSurveyScaleMCQ2Mock();
	public ISurveyScale createSurveyScaleNumericMock();
	public ISurveyScale createSurveyScaleTextMock();
	public IGroup createGroup();
	public IGroupFormationAlgorithm getRandomAlgorithm();
	public IGroupFormationAlgorithm getMinDistance();
}
