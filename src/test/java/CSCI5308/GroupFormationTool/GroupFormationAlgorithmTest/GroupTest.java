package CSCI5308.GroupFormationTool.GroupFormationAlgorithmTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.GroupFormationAlgorithm.IGroup;

class GroupTest {

	private IGroupFormationTestFactory factory;
	
	
	public GroupTest() {
		factory = new GroupFormationTestFactory();
	}
	
	@Test
	public void groupConstructor() {
		IGroup group = factory.createGroup();
		assertEquals(0, group.getGroupSize());
		assertEquals(0, group.getCurrentSize());
	}
	
	@Test
	public void getGroupSizeTest() {
		IGroup group = factory.createGroup();
		assertEquals(0, group.getGroupSize());
		
		group.setGropuSize(3);
		assertEquals(3, group.getGroupSize());
	}

	@Test
	public void setGropuSizeTest() {
		IGroup group = factory.createGroup();
		group.setGropuSize(3);
		assertEquals(3, group.getGroupSize());
	}

	@Test
	public void getGroupNumberTest() {
		IGroup group = factory.createGroup();
		assertEquals(-1, group.getGroupNumber());
		group.setGroupNumber(2);
		assertEquals(2, group.getGroupNumber());
	}

	@Test
	public void setGroupNumberTest() {
		IGroup group = factory.createGroup();
		group.setGroupNumber(2);
		assertEquals(2, group.getGroupNumber());
	}

	@Test
	public void getCurrentSizeTest() {
		IGroup group = factory.createGroup();
		assertEquals(0, group.getCurrentSize());
	}
	
	@Test
	public void getGroupMembersTest() {
		IGroup group = factory.createGroup();
		assertEquals(0, group.getGroupMembers().size());
	}

	@Test
	public void addGroupMemberTest() {
		IGroup group = factory.createGroup();
		assertEquals(0, group.getGroupMembers().size());
		
		group.setGropuSize(2);
		for(int i=0; i<2; i++) {
			group.addGroupMember(factory.createSurveyResponsesMock().get(i));
			assertEquals(i+1, group.getCurrentSize());
		}
	}
	
	@Test
	public void hasRoomText() {
		IGroup group = factory.createGroup();
		assertFalse(group.hasRoom());
		group.setGropuSize(1);
		assertTrue(group.hasRoom());
	}
}
