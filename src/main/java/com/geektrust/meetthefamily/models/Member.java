package com.geektrust.meetthefamily.models;

import java.util.ArrayList;
import java.util.List;

import com.geektrust.meetthefamily.enums.Gender;

import lombok.Builder;
import lombok.Data;

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
		StringBuilder sb = new StringBuilder("");
		for (Member m : getChildren()) {
			if (m.getGender() == gender) {
				sb.append(m.getName()).append(" ");
			}
		}
		return sb.toString().trim();
	}

	public String getSiblings() {
		StringBuilder sb = new StringBuilder("");
		for (Member m : getMother().getChildren()) {
			sb.append(m.getName()).append(" ");
		}
		return sb.toString().trim();
	}

	public String getSiblings(Gender gender) {
		StringBuilder sb = new StringBuilder("");
		for (Member m : getMother().getChildren()) {
			if (m.getGender() == gender) {
				sb.append(m.getName()).append(" ");
			}
		}
		return sb.toString().trim();
	}

	public String getInLaws(Gender gender) {
		StringBuilder sb = new StringBuilder("");
		if (getSpouse() != null && getSpouse().getMother() != null) {
			for (Member m : getSpouse().getMother().getChildren()) {
				if (m.getGender() == gender && m.getName() != getSpouse().getName()) {
					sb.append(m.getName()).append(" ");
				}
			}
		}
		if (getMother() != null && getMother().getChildren() != null) {
			for (Member m : getMother().getChildren()) {
				if (m.getSpouse() != null && m.getGender() == gender) {
					sb.append(m.getSpouse().getName()).append(" ");
				}
			}

		}
		return sb.toString().trim();
	}

}
