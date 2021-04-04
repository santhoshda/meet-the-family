package com.geektrust.meetthefamily.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.geektrust.meetthefamily.enums.Gender;

import lombok.Builder;
import lombok.Data;
import static com.geektrust.meetthefamily.constants.Messages.*;

/**
 * Family member contains the basic information of a member.
 * 
 * @author Santhosh Babu A
 *
 */
@Builder
@Data
public class FamilyMember {

	private String name;
	private Gender gender;
	private FamilyMember spouse;
	private List<FamilyMember> children;
	private FamilyMember mother;

	/**
	 * Adds a child to the current family member.
	 * 
	 * @param member
	 */
	public void addChild(FamilyMember member) {
		if (children == null) {
			children = new ArrayList<>();
		}
		children.add(member);
	}

	/**
	 * Gets the children of current member
	 * 
	 * @param gender
	 * @return
	 */
	public String getChildren(Gender gender) {
		if (getChildren() != null) {
			String children = getChildren().stream()
					.filter(child -> child.getGender() == gender)
					.map(FamilyMember::getName)
					.collect(Collectors.joining(" "));
			return children;
		}
		return NONE;
	}

	/**
	 * Gets the siblings of the current member.
	 * 
	 * @param gender
	 * @return
	 */
	public String getSiblings(Gender gender) {
		List<FamilyMember> children = getMother().getChildren();
		if (children != null) {
			Stream<FamilyMember> stream = children.stream().filter(child -> !child.equals(FamilyMember.this));
			if (gender != null) {
				stream = stream.filter(child -> child.getGender() == gender);
			}
			String siblings = stream.map(FamilyMember::getName).collect(Collectors.joining(" "));
			return (siblings == null || siblings.equals("")) ? NONE : siblings;
		}
		return NONE;
	}

	/**
	 * Gets the Mother-in-law or Father-in-law of the current member.
	 * 
	 * @param gender
	 * @return
	 */
	public String getInLaws(Gender gender) {
		StringBuilder inLaws = new StringBuilder();
		if (getSpouse() != null && getSpouse().getMother() != null && getSpouse().getMother().getChildren() != null) {
			inLaws.append(getSpouse().getMother()
					.getChildren()
					.stream()
					.filter(child -> !child.equals(getSpouse()))
					.filter(child -> child.getGender() == gender)
					.map(FamilyMember::getName)
					.collect(Collectors.joining(" ")));
		}
		if (getMother() != null && getMother().getChildren() != null) {
			inLaws.append(getMother().getChildren()
					.stream()
					.filter(child -> child.getSpouse() != null && !child.equals(getSpouse()))
					.filter(child -> child.getSpouse().getGender() == gender)
					.map(brother -> brother.getSpouse().getName())
					.collect(Collectors.joining(" ")));
		}
		return inLaws.length() > 0 ? inLaws.toString() : NONE;
	}

}
