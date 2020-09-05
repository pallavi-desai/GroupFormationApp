package CSCI5308.GroupFormationTool.GroupFormationAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.SystemConfig;

public class GroupFormationMinDistance implements IGroupFormationAlgorithm {
	private static final Logger log = LoggerFactory.getLogger(GroupFormationMinDistance.class);
	private static final String GROUP_FORMATION_ALGORITHM = "NearestNeighbor";

	private PriorityQueue<DistanceNode> pq;
	private Map<ISurveyResponse, IGroup> groupAssociation;

	public GroupFormationMinDistance() {
		pq = new PriorityQueue<GroupFormationMinDistance.DistanceNode>();
		groupAssociation = new HashMap<ISurveyResponse, IGroup>();
	}
	
	private class DistanceNode implements Comparable<DistanceNode> {
		ISurveyResponse student1;
		ISurveyResponse student2;
		Double distance;
		
		public DistanceNode(ISurveyResponse node1, ISurveyResponse node2, double distance) {
			this.student1 = node1;
			this.student2 = node2;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(DistanceNode that) {
			if (this.distance < that.distance) {
				log.info("GroupFormation={}, action={}, status={}", 
						GROUP_FORMATION_ALGORITHM, "Compare the Distance", "Less than");
				return -1;
			} else if (this.distance > that.distance) {
				log.info("GroupFormation={}, action={}, status={}", 
						GROUP_FORMATION_ALGORITHM, "Compare the Distance", "Greater than");
				return +1;
			} else {
				log.info("GroupFormation={}, action={}, status={}", 
						GROUP_FORMATION_ALGORITHM, "Compare the Distance", "Equal");
				return 0;
			}
		}
	}
	
	public static double computeDistance(ISurveyResponse student1, ISurveyResponse student2, List<ISurveyScale> surveyScales) {
		double distance = 0;
		log.info("GroupFormation={}, action={}, status={}", 
				GROUP_FORMATION_ALGORITHM, "Computing Distance", "Starting");
		for(int k=0; k < surveyScales.size(); k++) {
			ISurveyScale curScale = surveyScales.get(k);
			distance += curScale.distance(student1,student2,k);
		}
		log.info("GroupFormation={}, action={}, value={}", 
				GROUP_FORMATION_ALGORITHM, "Computing Distance", distance);
		return distance;
	}
	
	@Override
	public List<IGroup> formGroup(List<ISurveyResponse> surveyResults, 
			List<ISurveyScale> surveyScales, int groupSize) {
	
		List<IGroup> groups = new ArrayList<IGroup>();
		int numberOfGroups = (int) Math.ceil((double) surveyResults.size()/ (double) groupSize);

		if (groupSize == 0) {
			return groups;
		}
		
		
		for (int i=0; i < surveyResults.size(); i++) {
			for (int j=i+1; j < surveyResults.size(); j++) {
				double distance = 0;
				ISurveyResponse student1 = surveyResults.get(i);
				ISurveyResponse student2 = surveyResults.get(j);
				distance = computeDistance(student1, student2, surveyScales);
				pq.add(new DistanceNode(student1, student2, distance));
			}
		}
		
		IGroup curGroup;
		int assigned = 0;
		List<DistanceNode> revisitedResponses = new ArrayList<DistanceNode>();
		while(!pq.isEmpty()) {
			log.info("GroupFormation={}, action={}, status={}", 
					GROUP_FORMATION_ALGORITHM, "Finding Nearest Neighbors", "Starting...");
			DistanceNode curNode = pq.poll();
			ISurveyResponse student1 = curNode.student1;
			ISurveyResponse student2 = curNode.student2;
			if (groupAssociation.containsKey(student1) && groupAssociation.containsKey(student2)) {
				log.warn("GroupFormation={}, action={}, status={}", 
						GROUP_FORMATION_ALGORITHM, "Finding Nearest Neighbors", 
						String.format("%s,%s were visited again.", student1.getBannerID(), student2.getBannerID()));
			} else if (groupAssociation.containsKey(student1)) {
				curGroup = groupAssociation.get(student1);
				if (curGroup.hasRoom()) {
					try {
						curGroup.addGroupMember(student2);
					} catch (IllegalStateException e) {
						log.error("GroupFormation={}, action={}, status={}, message{}", 
								GROUP_FORMATION_ALGORITHM, "Adding student to the group", "Fail", e.getMessage());
						continue;
					}
					groupAssociation.put(student2, curGroup);
					assigned++;
				} else {
					revisitedResponses.add(curNode);
				}
				if ((assigned == surveyResults.size()-1) && groups.size() < numberOfGroups) {
					curGroup = SystemConfig.instance().getGroupFormationFactory().createGroup();
					curGroup.setGropuSize(groupSize);
					groups.add(curGroup);
					curGroup.setGroupNumber(groups.size());
					try {
						curGroup.addGroupMember(student2);
					} catch (IllegalStateException e) {
						log.error("GroupFormation={}, action={}, status={}, message{}", 
								GROUP_FORMATION_ALGORITHM, "Adding student to the group", "Fail", e.getMessage());
						continue;
					}
					groupAssociation.put(student2, curGroup);
					assigned++;
				}
			} else if (groupAssociation.containsKey(student2)) {
				curGroup = groupAssociation.get(student2);
				if (curGroup.hasRoom()) {
					try {
						curGroup.addGroupMember(student1);
					} catch (IllegalStateException e) {
						log.error("GroupFormation={}, action={}, status={}, message{}", 
								GROUP_FORMATION_ALGORITHM, "Adding student to the group", "Fail", e.getMessage());
						continue;
					}
					groupAssociation.put(student1, curGroup);
					assigned++;
				} else {
					revisitedResponses.add(curNode);
				} 
				if ((assigned == surveyResults.size()-1) && groups.size() < numberOfGroups) {
					curGroup = SystemConfig.instance().getGroupFormationFactory().createGroup();
					curGroup.setGropuSize(groupSize);
					groups.add(curGroup);
					curGroup.setGroupNumber(groups.size());
					try {
						curGroup.addGroupMember(student1);
					} catch (IllegalStateException e) {
						log.error("GroupFormation={}, action={}, status={}, message{}", 
							GROUP_FORMATION_ALGORITHM, "Adding student to the group", "Fail", e.getMessage());
						continue;
					}
					groupAssociation.put(student1, curGroup);
					assigned++;
				}
			} else {
				if (groups.size() < numberOfGroups) {
					curGroup = SystemConfig.instance().getGroupFormationFactory().createGroup();
					curGroup.setGropuSize(groupSize);
					groups.add(curGroup);
					curGroup.setGroupNumber(groups.size());
					try {
						curGroup.addGroupMember(student1);
					} catch (IllegalStateException e) {
						log.error("GroupFormation={}, action={}, status={}, message{}", 
								GROUP_FORMATION_ALGORITHM, "Adding student to the group", "Fail", e.getMessage());
						continue;
					}
					groupAssociation.put(student1, curGroup);
					assigned++;
					if (curGroup.hasRoom()) {
						try {
							curGroup.addGroupMember(student2);
						} catch (IllegalStateException e) {
							log.error("GroupFormation={}, action={}, status={}, message{}", 
									GROUP_FORMATION_ALGORITHM, "Adding student to the group", "Fail", e.getMessage());
							continue;
						}
						groupAssociation.put(student2, curGroup);
						assigned++;
					}
				} else {
					revisitedResponses.add(curNode);
				}
			}
			if (pq.isEmpty()) {
				for (DistanceNode node: revisitedResponses) {
					pq.add(node);
				}
				revisitedResponses.clear();
			}
		}
		return groups;
	}
}
