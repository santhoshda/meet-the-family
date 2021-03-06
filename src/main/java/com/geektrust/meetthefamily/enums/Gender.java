package com.geektrust.meetthefamily.enums;

/**
 * Gender Enumeration.
 * 
 * @author Santhosh Babu A
 *
 */
public enum Gender {
	MALE("Male"), FEMALE("Female");

	private String value;

	Gender(String value) {
		this.value = value;
	}

	/**
	 * Converts String to Enum.
	 * 
	 * @param s
	 * @return
	 */
	public static Gender of(String s) {
		for (Gender gender : values()) {
			if (gender.value.equals(s)) {
				return gender;
			}
		}
		return MALE;
	}
}
