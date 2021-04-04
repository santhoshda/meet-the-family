package com.geektrust.meetthefamily;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.geektrust.meetthefamily.enums.Gender;
import com.geektrust.meetthefamily.models.Family;
import com.geektrust.meetthefamily.models.FamilyMember;
import static com.geektrust.meetthefamily.constants.Messages.*;
import static com.geektrust.meetthefamily.constants.Commands.*;

/**
 * Main class to run the application.
 * 
 * @author Santhosh Babu A
 *
 */
public class Main {

	public static void main(String[] args) {
		Family family = new FamilyInitializer().initialize();
		Main main = new Main();
		try {
			main.processFile(family, args[0]);
		} catch (ArrayIndexOutOfBoundsException exception) {
			System.out.println(MISSING_ARGUMENT);
		}
	}

	/**
	 * Reads file.
	 * 
	 * @param family
	 * @param fileLocation
	 */
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

	/**
	 * Process the read file
	 * 
	 * @param family
	 * @param args
	 */
	private void processCommand(Family family, List<String> args) {
		try {
			if (args.get(0).equals(ADD_CHILD)) {
				addChild(family, args.get(1), args.get(2), Gender.of(args.get(3)));
			} else if (args.get(0).equals(GET_RELATIONSHIP)) {
				printRelationship(family, args.get(1), args.get(2));
			} else {
				System.out.println(INVALID_COMMAND);
			}
		} catch (IndexOutOfBoundsException exception) {
			System.out.println(MISSING_ARGUMENT);
		}
	}

	/**
	 * Manages ADD_CHILD command.
	 * 
	 * @param family
	 * @param motherName
	 * @param name
	 * @param gender
	 */
	private void addChild(Family family, String motherName, String name, Gender gender) {
		System.out.println(family.addChild(motherName, FamilyMember.builder().name(name).gender(gender).build()));
	}

	/**
	 * Manages GET_RELATIONSHIP command.
	 * 
	 * @param family
	 * @param name
	 * @param relationship
	 */
	private void printRelationship(Family family, String name, String relationship) {
		System.out.println(family.findRelationship(name, relationship));
	}
}
