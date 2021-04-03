package com.geektrust.meetthefamily.enums;

/**
 * @author Santhosh Babu A
 *
 */
public enum Gender {
	MALE("Male"), FEMALE("Female");

	private String value;

	Gender(String value) {
		this.value = value;
	}

	public static Gender of(String s) {
		for (Gender gender : values()) {
			if (gender.value.equals(s)) {
				return gender;
			}
		}
		return MALE;
	}
}
