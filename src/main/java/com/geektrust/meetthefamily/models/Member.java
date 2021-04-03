package com.geektrust.meetthefamily.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.geektrust.meetthefamily.enums.Gender;

import lombok.Builder;
import lombok.Data;
import static com.geektrust.meetthefamily.constants.Messages.*;

@Builder
@Data
public class Member {
	private String name;
	private Gender gender;
	private Member spouse;
	private List<Member> children;
	private Member mother;

	public void addChild(Member member) {
		if (children == null) {
			children = new ArrayList<>();
		}
		children.add(member);
	}

	public String getChildren(Gender gender) {
		if (getChildren() != null) {
			String children = getChildren().stream()
					.filter(child -> child.getGender() == gender)
					.map(Member::getName)
					.collect(Collectors.joining(" "));
			return children;
		}
		return NONE;
	}

	public String getSiblings(Gender gender) {
		List<Member> children = getMother().getChildren();
		if (children != null) {
			Stream<Member> stream = children.stream().filter(child -> !child.equals(Member.this));
			if (gender != null) {
				stream.filter(child -> child.getGender() == gender);
			}
			String siblings = stream.map(Member::getName).collect(Collectors.joining(" "));
			return (siblings == null || siblings.equals("")) ? NONE : siblings;
		}
		return NONE;
	}

	public String getInLaws(Gender gender) {
		StringBuilder inLaws = new StringBuilder();
		if (getSpouse() != null && getSpouse().getMother() != null && getSpouse().getMother().getChildren() != null) {
			inLaws.append(getSpouse().getMother()
					.getChildren()
					.stream()
					.filter(child -> !child.equals(getSpouse()))
					.filter(child -> child.getGender() == gender)
					.map(Member::getName)
					.collect(Collectors.joining(" ")));
		}
		if (getMother() != null && getMother().getChildren() != null) {
			inLaws.append(getMother().getChildren()
					.stream()
					.filter(child -> child.getSpouse() != null && !child.equals(getSpouse()))
					.filter(child -> child.getSpouse().getGender() == gender)
					.map(Member::getName)
					.collect(Collectors.joining(" ")));
		}
		return inLaws.length() > 0 ? inLaws.toString() : NONE;
	}

}
