package com.geektrust.meetthefamily;

import com.geektrust.meetthefamily.enums.Gender;
import com.geektrust.meetthefamily.models.Family;
import com.geektrust.meetthefamily.models.Member;

public class FamilyInitializer {

	public Family initialize() {

		Member queen = Member.builder().name("Queen Anga").gender(Gender.FEMALE).build();
		Member king = Member.builder().name("King Shan").gender(Gender.MALE).spouse(queen).build();
		Family family = Family.builder().king(king).queen(queen).build();

		Member chit = addChild(family, "Queen Anga", "Chit", Gender.MALE);
		addSpouse(chit, "Amba", Gender.FEMALE);
		addChild(family, "Queen Anga", "Ish", Gender.MALE);
		Member vich = addChild(family, "Queen Anga", "Vich", Gender.MALE);
		addSpouse(vich, "Lika", Gender.FEMALE);
		Member aras = addChild(family, "Queen Anga", "Aras", Gender.MALE);
		addSpouse(aras, "Chitra", Gender.FEMALE);
		Member satya = addChild(family, "Queen Anga", "Satya", Gender.FEMALE);
		addSpouse(satya, "Vyan", Gender.MALE);
		
		Member dritha = addChild(family, "Amba", "Dritha", Gender.FEMALE);
		addSpouse(dritha, "Jaya", Gender.MALE);
		addChild(family, "Amba", "Tritha", Gender.FEMALE);
		addChild(family, "Amba", "Vritha", Gender.MALE);
		
		addChild(family, "Lika", "Vila", Gender.FEMALE);
		addChild(family, "Lika", "Chika", Gender.FEMALE);
		
		Member jnki = addChild(family, "Chitra", "Jnki", Gender.FEMALE);
		addSpouse(jnki, "Arit", Gender.MALE);
		addChild(family, "Chitra", "Ahit", Gender.MALE);
		
		Member asva = addChild(family, "Satya", "Asva", Gender.MALE);
		addSpouse(asva, "Satvy", Gender.FEMALE);
		Member vyas = addChild(family, "Satya", "Vyas", Gender.MALE);
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

	private void addSpouse(Member member, String name, Gender gender) {
		Member spouse = Member.builder().name(name).gender(gender).build();
		spouse.setSpouse(member);
		member.setSpouse(spouse);
	}

	private Member addChild(Family family, String motherName, String name, Gender gender) {
		Member child = Member.builder().name(name).gender(gender).build();
		return family.addChild(motherName, child);
	}

}
