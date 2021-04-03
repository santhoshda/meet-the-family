package com.geektrust.meetthefamily.models;

import java.util.Stack;

import com.geektrust.meetthefamily.enums.Gender;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import static com.geektrust.meetthefamily.constants.Messages.*;
import static com.geektrust.meetthefamily.constants.Relationships.*;

@Builder
@Data
@ToString
public class Family {

	private Member king;
	private Member queen;

	public Member addInitialChild(String motherName, Member child) {
		Member member = findMember(motherName);
		if (member != null) {
			child.setMother(member);
			member.addChild(child);
			return child;
		}
		return null;
	}

	public String addChild(String motherName, Member child) {
		Member member = findMember(motherName);
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

	private Member findMember(String memberName) {
		Stack<Member> stack = new Stack<>();
		stack.push(king);
		while (!stack.isEmpty()) {
			Member member = stack.pop();
			if (member.getName().equals(memberName)) {
				return member;
			} else if (member.getSpouse() != null && member.getSpouse().getName().equals(memberName)) {
				return member.getSpouse();
			} else if (member.getGender() == Gender.MALE && member.getSpouse() != null
					&& member.getSpouse().getChildren() != null) {
				for (Member child : member.getSpouse().getChildren()) {
					stack.push(child);
				}
			} else if (member.getGender() == Gender.FEMALE && member.getChildren() != null) {
				for (Member child : member.getChildren()) {
					stack.push(child);
				}
			}
		}
		return null;
	}

	public String findRelationship(String name, String relationship) {
		Member member = findMember(name);
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
