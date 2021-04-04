package com.geektrust.meetthefamily;

import com.geektrust.meetthefamily.enums.Gender;
import com.geektrust.meetthefamily.models.Family;
import com.geektrust.meetthefamily.models.FamilyMember;

/**
 * FamilyInitializer class initializes the Family tree with preset values.
 * 
 * @author Santhosh Babu A
 *
 */
public class FamilyInitializer {

	/**
	 * Initializes the family tree with King and Queen and adds child and spouses.
	 */
	public Family initialize() {
		FamilyMember queen = FamilyMember.builder().name("Queen Anga").gender(Gender.FEMALE).build();
		FamilyMember king = FamilyMember.builder().name("King Shan").gender(Gender.MALE).spouse(queen).build();
		Family family = Family.builder().king(king).queen(queen).build();

		FamilyMember chit = addChild(family, "Queen Anga", "Chit", Gender.MALE);
		addSpouse(chit, "Amba", Gender.FEMALE);
		addChild(family, "Queen Anga", "Ish", Gender.MALE);
		FamilyMember vich = addChild(family, "Queen Anga", "Vich", Gender.MALE);
		addSpouse(vich, "Lika", Gender.FEMALE);
		FamilyMember aras = addChild(family, "Queen Anga", "Aras", Gender.MALE);
		addSpouse(aras, "Chitra", Gender.FEMALE);
		FamilyMember satya = addChild(family, "Queen Anga", "Satya", Gender.FEMALE);
		addSpouse(satya, "Vyan", Gender.MALE);

		FamilyMember dritha = addChild(family, "Amba", "Dritha", Gender.FEMALE);
		addSpouse(dritha, "Jaya", Gender.MALE);
		addChild(family, "Amba", "Tritha", Gender.FEMALE);
		addChild(family, "Amba", "Vritha", Gender.MALE);

		addChild(family, "Lika", "Vila", Gender.FEMALE);
		addChild(family, "Lika", "Chika", Gender.FEMALE);

		FamilyMember jnki = addChild(family, "Chitra", "Jnki", Gender.FEMALE);
		addSpouse(jnki, "Arit", Gender.MALE);
		addChild(family, "Chitra", "Ahit", Gender.MALE);

		FamilyMember asva = addChild(family, "Satya", "Asva", Gender.MALE);
		addSpouse(asva, "Satvy", Gender.FEMALE);
		FamilyMember vyas = addChild(family, "Satya", "Vyas", Gender.MALE);
		addSpouse(vyas, "Krpi", Gender.FEMALE);
		addChild(family, "Satya", "Atya", Gender.FEMALE);

		addChild(family, "Dritha", "Yodhan", Gender.MALE);
		addChild(family, "Jnki", "Laki", Gender.MALE);
		addChild(family, "Jnki", "Lavnya", Gender.FEMALE);
		addChild(family, "Satvy", "Vasa", Gender.MALE);
		addChild(family, "Krpi", "Kriya", Gender.MALE);
		addChild(family, "Krpi", "Krithi", Gender.FEMALE);
		return family;
	}

	/**
	 * Adds spouse to a member.
	 * 
	 * @param member
	 * @param name
	 * @param gender
	 */
	private void addSpouse(FamilyMember member, String name, Gender gender) {
		FamilyMember spouse = FamilyMember.builder().name(name).gender(gender).build();
		spouse.setSpouse(member);
		member.setSpouse(spouse);
	}

	/**
	 * Adds child to a member.
	 * 
	 * @param family
	 * @param motherName
	 * @param name
	 * @param gender
	 * @return member
	 */
	private FamilyMember addChild(Family family, String motherName, String name, Gender gender) {
		FamilyMember child = FamilyMember.builder().name(name).gender(gender).build();
		return family.addInitialChild(motherName, child);
	}

}
