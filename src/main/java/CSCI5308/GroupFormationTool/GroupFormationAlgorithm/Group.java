package CSCI5308.GroupFormationTool.GroupFormationAlgorithm;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.PasswordValidationPolicy.MaxLengthValidation;

public class Group implements IGroup {
	private static final Logger log = LoggerFactory.getLogger(Group.class);
	private static final String GROUP_OPERATIONS = "GroupOperations";

	private int groupSize;
	private int groupNumber;
	private List<ISurveyResponse> groupList;

	public Group() {
		this.groupNumber = -1;
		this.groupSize = 0;
		groupList = new ArrayList<ISurveyResponse>();
	}

	@Override
	public int getGroupSize() {
		return this.groupSize;
	}

	@Override
	public void setGropuSize(int groupSize) {
		this.groupSize = groupSize;
	}

	@Override
	public int getGroupNumber() {
		return this.groupNumber;
	}

	@Override
	public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}

	@Override
	public int getCurrentSize() {
		return this.groupList.size();
	}
	
	@Override
	public List<ISurveyResponse> getGroupMembers() {
		return this.groupList;
	}

	@Override
	public void addGroupMember(ISurveyResponse member) throws IllegalStateException {
		if (getCurrentSize() == getGroupSize()) {
			throw new IllegalStateException("Group is full.");
		}
		groupList.add(member);
		log.info("Group={}, action={}, status={}", GROUP_OPERATIONS, "Add Member", "Success");
	}
	
	@Override
	public boolean hasRoom() {
		if (getCurrentSize() < getGroupSize()) {
			log.info("Group={}, action={}, status={}", GROUP_OPERATIONS, "Check the Room", "Success");
			return true;
		} else {
			log.info("Group={}, action={}, status={}", GROUP_OPERATIONS, "Check the Room", "Fail");
			return false;
		}
	}
}
