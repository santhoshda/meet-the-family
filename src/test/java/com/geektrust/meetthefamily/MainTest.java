package com.geektrust.meetthefamily;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.geektrust.meetthefamily.enums.Gender;
import com.geektrust.meetthefamily.models.Family;
import com.geektrust.meetthefamily.models.FamilyMember;

import static com.geektrust.meetthefamily.constants.Messages.*;

/**
 * Test file
 * 
 * @author Santhosh Babu A
 *
 */
public class MainTest {

	private static Family family;

	@Before
	public void init() {
		family = new FamilyInitializer().initialize();
	}

	@Test
	public void addChildToInvalidMother() {
		assertEquals(PERSON_NOT_FOUND,
				family.addChild("Invalid", FamilyMember.builder().name("TestChild").gender(Gender.MALE).build()));
	}

	@Test
	public void addChildToMale() {
		assertEquals(CHILD_ADDITION_FAILED,
				family.addChild("Vich", FamilyMember.builder().name("TestChild").gender(Gender.MALE).build()));
	}

	@Test
	public void addChildToFemale() {
		assertEquals(CHILD_ADDITION_SUCCEEDED,
				family.addChild("Lika", FamilyMember.builder().name("TestChild").gender(Gender.MALE).build()));
	}

	@Test
	public void checkRelationship() {
		assertEquals("Vila", family.findRelationship("Chika", "Siblings"));
	}

	@Test
	public void checkInvalidRelationship() {
		assertEquals(PERSON_NOT_FOUND, family.findRelationship("Test", "Siblings"));
	}

}
