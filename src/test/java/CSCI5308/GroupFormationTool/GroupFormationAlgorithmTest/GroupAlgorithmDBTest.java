package CSCI5308.GroupFormationTool.GroupFormationAlgorithmTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.GroupAlgorithmDB;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.IGroupAlgorithmDB;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.ISurveyResponse;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.SurveyResponseObjectFactory;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.SurveyScaleObjectFactory;

public class GroupAlgorithmDBTest {
	IGroupAlgorithmDB algorithmDBMock = mock(GroupAlgorithmDB.class);
	List<ISurveyResponse> responses;
	
	GroupAlgorithmDBTest(){
		responses = new ArrayList<ISurveyResponse>();
		ISurveyResponse surveyResponseObj=SurveyScaleObjectFactory.createObject(new SurveyResponseObjectFactory());
		surveyResponseObj.setBannerID("B0012345");
		surveyResponseObj.setFirstName("Test");
		surveyResponseObj.setLastName("Test");
		List<String> questionList=new ArrayList<String>();
		questionList.add("10");
		surveyResponseObj.setQuestions(questionList);
		List<String> responsesList=new ArrayList<String>();
		responsesList.add("1");
		surveyResponseObj.setResponses(responsesList);
		responses.add(surveyResponseObj);
	}
	
	@Test
	public void loadResponses() {
		when(algorithmDBMock.loadResponses(1)).thenReturn(responses);
		assertEquals(algorithmDBMock.loadResponses(1),responses);
		verify(algorithmDBMock).loadResponses(1);
	}

}
