package CSCI5308.GroupFormationTool.GroupFormationAlgorithmTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.IGroup;
import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.IGroupFormationAlgorithm;

class GroupFormationMinDistanceTest {

	@Test
	void fromGroupTest() {
		IGroupFormationTestFactory factory = new GroupFormationTestFactory();
		IGroupFormationAlgorithm algo = factory.getMinDistance();
		List<IGroup> groups;

		groups = algo.formGroup(factory.createSurveyResponsesMock(), factory.createSurveyScalsesMock(), 0);
		assertEquals(0, groups.size());
		
		groups = algo.formGroup(factory.createSurveyResponsesMock(), factory.createSurveyScalsesMock(), 1);
		assertEquals(5, groups.size());
		
		groups = algo.formGroup(factory.createSurveyResponsesMock(), factory.createSurveyScalsesMock(), 2);
		assertEquals(3, groups.size());
		
		groups = algo.formGroup(factory.createSurveyResponsesMock(), factory.createSurveyScalsesMock(), 3);
		assertEquals(2, groups.size());
		
		groups = algo.formGroup(factory.createSurveyResponsesMock(), factory.createSurveyScalsesMock(), 4);
		assertEquals(2, groups.size());
		
		groups = algo.formGroup(factory.createSurveyResponsesMock(), factory.createSurveyScalsesMock(), 5);
		assertEquals(1, groups.size());
		
		groups = algo.formGroup(factory.createSurveyResponsesMock(), factory.createSurveyScalsesMock(), 6);
		assertEquals(1, groups.size());
	}

}
