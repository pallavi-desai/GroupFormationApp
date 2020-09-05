package CSCI5308.GroupFormationTool.GroupFormationAlgorithmTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.ISurveyResponse;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.ISurveyScale;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.SurveyScaleMCQ2;

class SurveyScaleMCQ2Test {

	@Test
	public void distanceTest() {
		GroupFormationTestFactory factory = new GroupFormationTestFactory();
		ISurveyScale sc = factory.createSurveyScaleMCQ2Mock();
		
		ISurveyResponse sr1 = factory.createSurveyResponsesMock().get(0);  
		ISurveyResponse sr2 = factory.createSurveyResponsesMock().get(4);  
		
		assertEquals(4.0, sc.distance(sr1, sr1, 1));
		assertEquals(4.0, sc.distance(sr2, sr2, 1));
		assertEquals(2.0, sc.distance(sr1, sr2, 1));
		assertEquals(2.0, sc.distance(sr2, sr1, 1));
	}
	
	@Test
	void converValueTest() {
		assertEquals(0b0001, SurveyScaleMCQ2.convertValue("1"));
		assertEquals(0b1001, SurveyScaleMCQ2.convertValue("1, 4"));
		assertEquals(0b1001, SurveyScaleMCQ2.convertValue("1,4"));
		assertEquals(0b1001, SurveyScaleMCQ2.convertValue("4,1"));
		assertEquals(0b10010, SurveyScaleMCQ2.convertValue("2,5"));
	}
	
	@Test
	void getMaskTest() {
		assertEquals(0b0000, SurveyScaleMCQ2.getMask(0));
		assertEquals(0b0001, SurveyScaleMCQ2.getMask(1));
		assertEquals(0b0011, SurveyScaleMCQ2.getMask(2));
		assertEquals(0b011111, SurveyScaleMCQ2.getMask(5));
	}
	
	@Test
	void coutnSetBitsTest() {
		assertEquals(0, SurveyScaleMCQ2.countSetBits(0b0000));
		assertEquals(1, SurveyScaleMCQ2.countSetBits(0b0001));
		assertEquals(2, SurveyScaleMCQ2.countSetBits(0b0011));
		assertEquals(3, SurveyScaleMCQ2.countSetBits(0b0111));
		assertEquals(2, SurveyScaleMCQ2.countSetBits(0b1100));
		assertEquals(3, SurveyScaleMCQ2.countSetBits(0b1011));
	}
	
	@Test
	void distanceSimilarTest() {
		assertEquals(0, SurveyScaleMCQ2.distanceSimilar(0, 0));
		assertEquals(0, SurveyScaleMCQ2.distanceSimilar(4, 4));
		assertEquals(2, SurveyScaleMCQ2.distanceSimilar(0b1000, 0b0001));
		assertEquals(2, SurveyScaleMCQ2.distanceSimilar(0b0001, 0b1000));
		assertEquals(1, SurveyScaleMCQ2.distanceSimilar(0b0001, 0b1001));
		assertEquals(4, SurveyScaleMCQ2.distanceSimilar(0b0110, 0b1001));
		assertEquals(3, SurveyScaleMCQ2.distanceSimilar(0b1110, 0b1001));
	}
	
	@Test
	void distanceDissimilar() {
		assertEquals(5, SurveyScaleMCQ2.distanceDissimilar(0b00000, 0b00000, 5));
		assertEquals(5, SurveyScaleMCQ2.distanceDissimilar(0b10001, 0b10001, 5));
		assertEquals(3, SurveyScaleMCQ2.distanceDissimilar(0b00001, 0b10000, 5));
		assertEquals(1, SurveyScaleMCQ2.distanceDissimilar(0b00101, 0b10010, 5));
		assertEquals(0, SurveyScaleMCQ2.distanceDissimilar(0b00000, 0b11111, 5));
		assertEquals(0, SurveyScaleMCQ2.distanceDissimilar(0b00101, 0b10010, 3));
		assertEquals(1, SurveyScaleMCQ2.distanceDissimilar(0b00101, 0b10000, 3));

	}
}
	
