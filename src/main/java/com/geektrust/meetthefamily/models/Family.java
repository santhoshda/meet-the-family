package com.geektrust.meetthefamily.models;

import java.util.Stack;

import com.geektrust.meetthefamily.enums.Gender;

import lombok.Builder;
import lombok.Data;
import static com.geektrust.meetthefamily.constants.Messages.*;
import static com.geektrust.meetthefamily.constants.Relationships.*;

/**
 * Family contains the King and Queen.
 * 
 * @author Santhosh Babu A
 *
 */
@Builder
@Data
public class Family {

	private FamilyMember king;
	private FamilyMember queen;

	/**
	 * Adds child to the mother - Method used only for initial setup.
	 * 
	 * @param motherName
	 * @param child
	 * @return FamilyMember
	 */
	public FamilyMember addInitialChild(String motherName, FamilyMember child) {
		FamilyMember member = findMember(motherName);
		if (member != null) {
			child.setMother(member);
			member.addChild(child);
			return child;
		}
		return null;
	}

	/**
	 * Adds child to the mother.
	 * 
	 * @param motherName
	 * @param child
	 * @return {@link}Messages
	 */
	public String addChild(String motherName, FamilyMember child) {
		FamilyMember member = findMember(motherName);
		if (member == null) {
			return PERSON_NOT_FOUND;
		} else if (member.getGender() != Gender.FEMALE) {
			return CHILD_ADDITION_FAILED;
		} else {
			child.setMother(member);
			member.addChild(child);
			return CHILD_ADDITION_SUCCEEDED;
		}
	}

	/**
	 * Finds a member in the Family using the name. Implementation - Basic Depth
	 * First Search using Stack.
	 * 
	 * @param memberName
	 * @return FamilyMember
	 */
	private FamilyMember findMember(String memberName) {
		Stack<FamilyMember> stack = new Stack<>();
		stack.push(king);
		while (!stack.isEmpty()) {
			FamilyMember member = stack.pop();
			if (member.getName().equals(memberName)) {
				return member;
			} else if (member.getSpouse() != null && member.getSpouse().getName().equals(memberName)) {
				return member.getSpouse();
			} else if (member.getGender() == Gender.MALE && member.getSpouse() != null
					&& member.getSpouse().getChildren() != null) {
				for (FamilyMember child : member.getSpouse().getChildren()) {
					stack.push(child);
				}
			} else if (member.getGender() == Gender.FEMALE && member.getChildren() != null) {
				for (FamilyMember child : member.getChildren()) {
					stack.push(child);
				}
			}
		}
		return null;
	}

	/**
	 * Finds the members matching the relationship to the member.
	 * 
	 * @param name
	 * @param relationship
	 * @return {@link}Messages
	 */
	public String findRelationship(String name, String relationship) {
		FamilyMember member = findMember(name);
		if (member == null) {
			return PERSON_NOT_FOUND;
		}
		switch (relationship) {
		case SON:
			return member.getChildren(Gender.MALE);
		case DAUGHTER:
			return member.getChildren(Gender.FEMALE);
		case SIBLINGS:
			return member.getSiblings(null);
		case BROTHER_IN_LAW:
			return member.getInLaws(Gender.MALE);
		case SISTER_IN_LAW:
			return member.getInLaws(Gender.FEMALE);
		case MATERNAL_UNCLE:
			return member.getMother().getSiblings(Gender.MALE);
		case PATERNAL_UNCLE:
			return member.getMother().getSpouse().getSiblings(Gender.MALE);
		case MATERNAL_AUNT:
			return member.getMother().getSiblings(Gender.FEMALE);
		case PATERNAL_AUNT:
			return member.getMother().getSpouse().getSiblings(Gender.FEMALE);
		default:
			break;
		}
		return INVALID_COMMAND;
	}

}
