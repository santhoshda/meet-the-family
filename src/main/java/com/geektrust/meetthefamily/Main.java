package com.geektrust.meetthefamily;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.geektrust.meetthefamily.enums.Gender;
import com.geektrust.meetthefamily.models.Family;
import com.geektrust.meetthefamily.models.Member;
import static com.geektrust.meetthefamily.constants.Messages.*;
import static com.geektrust.meetthefamily.constants.Commands.*;

public class Main {
	public static void main(String[] args) {
		Family family = new FamilyInitializer().initialize();
		Main main = new Main();
		try {
			main.processFile(family, args[0]);
		} catch (ArrayIndexOutOfBoundsException exception) {
			System.out.println(INVALID_FILE_LOCATION);
		}
	}

	private void processFile(Family family, String fileLocation) {
		File file = new File(fileLocation);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				List<String> args = Arrays.asList(line.split(" "));
				processCommand(family, args);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(FILE_NOT_FOUND);
		}
	}

	private void processCommand(Family family, List<String> args) {
		try {
			if (args.get(0).equals(ADD_CHILD)) {
				addChild(family, args.get(1), args.get(2), Gender.of(args.get(3)));
			} else if (args.get(0).equals(GET_RELATIONSHIP)) {
				printRelationship(family, args.get(1), args.get(2));
			} else {
				throw new Exception();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println(INVALID_COMMAND);
		}
	}

	private void addChild(Family family, String motherName, String name, Gender gender) {
		System.out.println(family.addChild(motherName, Member.builder().name(name).gender(gender).build()));
	}

	private void printRelationship(Family family, String name, String relationship) {
		System.out.println(family.findRelationship(name, relationship));
	}
}
