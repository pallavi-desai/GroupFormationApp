package CSCI5308.GroupFormationTool.GroupFormationAlgorithmTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.Group;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.GroupFormationMinDistance;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.GroupFormationRandom;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.IGroup;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.IGroupFormationAlgorithm;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.ISurveyResponse;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.ISurveyScale;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.SurveyResponse;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.SurveyScaleMCQ1;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.SurveyScaleMCQ2;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.SurveyScaleNumeric;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.SurveyScaleText;

public class GroupFormationTestFactory implements IGroupFormationTestFactory {

	@Override
	public List<ISurveyResponse> createSurveyResponsesMock() {
		List<ISurveyResponse> responses = new ArrayList<ISurveyResponse>();
		
		ISurveyResponse sr1 = new SurveyResponse();
		sr1.setResponses(Arrays.asList("1","1,4","7","Frank Sinatra"));
		sr1.setBannerID("B01");
		sr1.setFirstName("Johnny");
		sr1.setLastName("Cash");
		responses.add(sr1);
		
		ISurveyResponse sr2 = new SurveyResponse();
		sr2.setResponses(Arrays.asList("1","1,2","5",""));
		sr2.setBannerID("B02");
		sr2.setFirstName("Paul");
		sr2.setLastName("McCartney");
		responses.add(sr2);
		
		ISurveyResponse sr3 = new SurveyResponse();
		sr3.setResponses(Arrays.asList("1","2,3","6",""));
		sr3.setBannerID("B03");
		sr3.setFirstName("Frank");
		sr3.setLastName("Sinatra");
		responses.add(sr3);
		
		ISurveyResponse sr4 = new SurveyResponse();
		sr4.setResponses(Arrays.asList("2","1,4","4",""));
		sr4.setBannerID("B04");
		sr4.setFirstName("Elton");
		sr4.setLastName("John");
		responses.add(sr4);
		
		ISurveyResponse sr5 = new SurveyResponse();
		sr5.setResponses(Arrays.asList("2","1,2,3,4","8",""));
		sr5.setBannerID("B05");
		sr5.setFirstName("Elvis");
		sr5.setLastName("Presely");
		responses.add(sr5);
		
		return responses;
	}

	@Override
	public List<ISurveyScale> createSurveyScalsesMock() {
		List<ISurveyScale> surveyScales = new ArrayList<ISurveyScale>();
		surveyScales.add(createSurveyScaleMCQ1Mock());
		surveyScales.add(createSurveyScaleMCQ2Mock());
		surveyScales.add(createSurveyScaleNumericMock());
		surveyScales.add(createSurveyScaleTextMock());
		return surveyScales;
	}

	@Override
	public ISurveyScale createSurveyScaleMCQ1Mock() {
		ISurveyScale mcq1Mock = new SurveyScaleMCQ1();
		mcq1Mock.setQuestionid("1");
		mcq1Mock.setQuestiontext("Are you living in Canada?");
		mcq1Mock.setQuestiontype("mcq1");
		mcq1Mock.setCriteria("similar,Grtx");
		mcq1Mock.setOptionscount("2");
		return mcq1Mock;
	}
	
	@Override
	public ISurveyScale createSurveyScaleMCQ2Mock() {
		ISurveyScale mcq2Mock = new SurveyScaleMCQ2();
		mcq2Mock.setQuestionid("2");
		mcq2Mock.setQuestiontext("What programming language do you know?");
		mcq2Mock.setQuestiontype("mcq2");
		mcq2Mock.setCriteria("dissimilar,Grtx");
		mcq2Mock.setOptionscount("4");
		return mcq2Mock;
	}
	
	@Override
	public ISurveyScale createSurveyScaleNumericMock() {
		ISurveyScale numericMock = new SurveyScaleNumeric();
		numericMock.setQuestionid("3");
		numericMock.setQuestiontext("Which number can describe your knowledge in OOP?");
		numericMock.setQuestiontype("numeric");
		numericMock.setCriteria("similar");
		numericMock.setOptionscount("0");
		return numericMock;
	}
	
	@Override
	public ISurveyScale createSurveyScaleTextMock() {
		ISurveyScale textMock = new SurveyScaleText();
		textMock.setQuestionid("4");
		textMock.setQuestiontext("How do you want not to be in the same group?");
		textMock.setQuestiontype("text");
		textMock.setCriteria("similar");
		textMock.setOptionscount("0");
		return textMock;
	}
	
	@Override
	public IGroup createGroup() {
		return new Group();
	}
	
	@Override
	public IGroupFormationAlgorithm getRandomAlgorithm() {
		return new GroupFormationRandom();
	}

	@Override
	public IGroupFormationAlgorithm getMinDistance() {
		return new GroupFormationMinDistance();
	}

}
