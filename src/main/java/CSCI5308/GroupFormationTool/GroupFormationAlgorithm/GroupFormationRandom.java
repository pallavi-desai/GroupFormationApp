package CSCI5308.GroupFormationTool.GroupFormationAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.SystemConfig;

public class GroupFormationRandom implements IGroupFormationAlgorithm {
	private static final Logger log = LoggerFactory.getLogger(GroupFormationRandom.class);
	private static final String GROUP_FORMATION_ALGORITHM = "Random";
	
	@Override
	public List<IGroup> formGroup(List<ISurveyResponse> surveyResults, 
			List<ISurveyScale> surveyScales, int groupSize) {
		log.info("GroupFormation={}, action={}, status={}", 
				GROUP_FORMATION_ALGORITHM, "Computing Distance", "Starting");
		List<ISurveyResponse> newStudentList;
		List<IGroup> groups = new ArrayList<IGroup>();
		int numberOfGroups = (int) Math.ceil((double) surveyResults.size()/ (double) groupSize);

		if (groupSize == 0 ) {
			return groups;
		}
		
		newStudentList = new ArrayList<ISurveyResponse>();
		for (int i = 0; i < surveyResults.size(); i++) {
			newStudentList.add(surveyResults.get(i));
		}
		
		for (int i = 0; i < numberOfGroups; i++) {
			IGroup group = SystemConfig.instance().getGroupFormationFactory().createGroup();
			group.setGroupNumber(i+1);
			group.setGropuSize(groupSize);
			groups.add(group);
		}
		
    	Random randGen = new Random();
    	for (int i = 0; i < numberOfGroups; i++) {
			for (int j = 0; j < groupSize; j++) {
				if (newStudentList.size() > 0) {
					int randNumber = randGen.nextInt(newStudentList.size());
					ISurveyResponse student = newStudentList.get(randNumber);
					try {
						groups.get(i).addGroupMember(student);
					} catch (IllegalStateException e) {
						log.error("GroupFormation={}, action={}, status={}, message{}", 
								GROUP_FORMATION_ALGORITHM, "Adding student to the group", "Fail", e.getMessage());
						continue;
					}
					newStudentList.remove(randNumber);
				}
			}
		}
		return groups;
	}

}
