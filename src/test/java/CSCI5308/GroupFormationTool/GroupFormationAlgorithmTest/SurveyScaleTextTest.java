package CSCI5308.GroupFormationTool.GroupFormationAlgorithmTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.ISurveyResponse;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.ISurveyScale;

class SurveyScaleTextTest {

	@Test
	public void distanceTest() {
		GroupFormationTestFactory factory = new GroupFormationTestFactory();
		ISurveyScale sc = factory.createSurveyScaleTextMock();
		
		ISurveyResponse sr1 = factory.createSurveyResponsesMock().get(1); 
		ISurveyResponse sr2 = factory.createSurveyResponsesMock().get(3); 
		
		assertEquals(0.0, sc.distance(sr1, sr1, 3));
		assertEquals(0.0, sc.distance(sr2, sr2, 3));
		assertEquals(0.0, sc.distance(sr1, sr2, 3));
		assertEquals(0.0, sc.distance(sr2, sr1, 3));
	}
}
	
