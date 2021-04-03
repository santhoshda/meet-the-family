package com.geektrust.meetthefamily.models;

import java.util.Stack;

import com.geektrust.meetthefamily.enums.Gender;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class Family {
	private Member king;
	private Member queen;

	public Member addChild(String motherName, Member child) {
		Member mother = findMember(motherName);
		if (mother != null) {
			child.setMother(mother);
			mother.addChild(child);
			return child;
		}
		return null;
	}

	private Member findMember(String memberName) {
		Stack<Member> stack = new Stack<>();
		stack.push(queen);
		while (!stack.isEmpty()) {
			Member member = stack.pop();
			if (member.getGender() == Gender.FEMALE && member.getName().equals(memberName)) {
				return member;
			} else if (member.getGender() == Gender.MALE && member.getSpouse() != null
					&& member.getSpouse().getName().equals(memberName)) {
				return member.getSpouse();
			} else if (member.getGender() == Gender.FEMALE && member.getChildren() != null) {
				for (Member child : member.getChildren()) {
					stack.push(child);
				}
			} else if (member.getGender() == Gender.MALE && member.getSpouse() != null
					&& member.getSpouse().getChildren() != null) {
				for (Member child : member.getSpouse().getChildren()) {
					stack.push(child);
				}
			}
		}
		return null;
	}

	public String findRelationship(String name, String relationship) {
		Member member = findMember(name);
		if (member == null) {
			return "PERSON_NOT_FOUND";
		}
		switch (relationship) {
		case "Son":
			return member.getChildren(Gender.MALE);
		case "Daughter":
			return member.getChildren(Gender.FEMALE);
		case "Siblings":
			return member.getSiblings();
		case "Brother-In-Law":
			return member.getInLaws(Gender.MALE);
		case "Sister-In-Law":
			return member.getInLaws(Gender.FEMALE);
		case "Maternal-Uncle":
			return member.getMother().getSiblings(Gender.MALE);
		case "Paternal-Uncle":
			return member.getMother().getSpouse().getSiblings(Gender.MALE);
		case "Maternal-Aunt":
			return member.getMother().getSiblings(Gender.FEMALE);
		case "Paternal-Aunt":
			return member.getMother().getSpouse().getSiblings(Gender.FEMALE);
		default:
			break;
		}

		return "INVALID_COMMAND";
	}

}
