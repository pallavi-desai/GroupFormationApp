package CSCI5308.GroupFormationTool.GroupFormationAlgorithm;

import java.util.List;

public interface IGroupAlgorithmDB {

	public List<ISurveyResponse> loadResponses(long surveyID);

}
