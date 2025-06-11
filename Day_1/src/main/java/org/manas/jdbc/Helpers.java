package org.manas.jdbc;

import java.util.Scanner;
import java.util.function.Predicate;

public class Helpers {
	public static String cleanString(String str) {
		return str.trim().toLowerCase();
	}

	public static boolean isValidStringBasic(String str) {
		if (str == null)
			return false;
		if (str.equals(""))
			return false;
		return true;
	}

	public static String inputValidString(Scanner sc, String inputPrompt, String invalidPrompt,
			Predicate<String> isValid) {
		String inputStr;
		while (true) {
			System.out.print(inputPrompt);
			inputStr = sc.next();
			if (!(isValidStringBasic(inputStr) && isValid.test(inputStr))) {
				System.out.println(invalidPrompt);
				continue;
			}
			inputStr = cleanString(inputStr);
			break;
		}
		return inputStr;
	}
	

	public static String inputValidStringBasic(Scanner sc, String inputPrompt, String invalidPrompt) {
		return inputValidString(sc, inputPrompt, invalidPrompt, (str) -> isValidStringBasic(str));
	}
}
