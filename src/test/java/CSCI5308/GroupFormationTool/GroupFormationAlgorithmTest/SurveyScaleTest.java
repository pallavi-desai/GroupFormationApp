package CSCI5308.GroupFormationTool.GroupFormationAlgorithmTest;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.ISurveyScale;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.SurveyScaleFactory;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.SurveyScaleObjectFactory;

public class SurveyScaleTest {

	@Test
	void getQuestiontext() {
		ISurveyScale surveyScale = SurveyScaleObjectFactory.createObject(new SurveyScaleFactory());
		surveyScale.setQuestiontext("sample");
		assertSame("sample", surveyScale.getQuestiontext());
	}

	@Test
	void setQuestiontext() {
		ISurveyScale surveyScale = SurveyScaleObjectFactory.createObject(new SurveyScaleFactory());
		surveyScale.setQuestiontext("sample");
		assertSame("sample", surveyScale.getQuestiontext());
	}
	
	@Test
	void getQuestiontype() {
		ISurveyScale surveyScale = SurveyScaleObjectFactory.createObject(new SurveyScaleFactory());
		surveyScale.setQuestiontype("numeric");
		assertSame("numeric", surveyScale.getQuestiontype());
	}
	
	@Test
	void setQuestiontype() {
		ISurveyScale surveyScale = SurveyScaleObjectFactory.createObject(new SurveyScaleFactory());
		surveyScale.setQuestiontype("numeric");
		assertSame("numeric", surveyScale.getQuestiontype());
	}
	
	@Test
	void getCriteria() {
		ISurveyScale surveyScale = SurveyScaleObjectFactory.createObject(new SurveyScaleFactory());
		surveyScale.setCriteria("similar");
		assertSame("similar", surveyScale.getCriteria());
	}

	@Test
	void setCriteria() {
		ISurveyScale surveyScale = SurveyScaleObjectFactory.createObject(new SurveyScaleFactory());
		surveyScale.setCriteria("similar");
		assertSame("similar", surveyScale.getCriteria());
	}

	@Test
	void getThreshold() {
		ISurveyScale surveyScale = SurveyScaleObjectFactory.createObject(new SurveyScaleFactory());
		surveyScale.setThreshold("5");
		assertSame("5", surveyScale.getThreshold());
	}

	public void setThreshold() {
		ISurveyScale surveyScale = SurveyScaleObjectFactory.createObject(new SurveyScaleFactory());
		surveyScale.setThreshold("5");
		assertSame("5", surveyScale.getThreshold());
	}
	
}
