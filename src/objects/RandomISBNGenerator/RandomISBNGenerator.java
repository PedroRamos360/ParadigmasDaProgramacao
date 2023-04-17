package src.objects.RandomISBNGenerator;

import java.util.Random;

public class RandomISBNGenerator {

	public static String generateRandomISBN() {
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();

		// First group of 3 digits
		for (int i = 0; i < 3; i++) {
			sb.append(rand.nextInt(10));
		}

		// Separator
		sb.append("-");

		// Second group of 1 digit
		sb.append(rand.nextInt(10));

		// Separator
		sb.append("-");

		// Third group of 2 digits
		for (int i = 0; i < 2; i++) {
			sb.append(rand.nextInt(10));
		}

		// Separator
		sb.append("-");

		// Fourth group of 5 digits
		for (int i = 0; i < 5; i++) {
			sb.append(rand.nextInt(10));
		}

		// Separator
		sb.append("-");

		// Fifth group of 1 digit (checksum)
		sb.append(generateISBNChecksum(sb.toString()));

		return sb.toString();
	}

	private static int generateISBNChecksum(String isbn) {
		int sum = 0;

		// Calculate the weighted sum of the digits
		for (int i = 0; i < isbn.length(); i++) {
			int digit = Character.getNumericValue(isbn.charAt(i));
			sum += (i % 2 == 0) ? digit : digit * 3;
		}

		// Calculate the checksum as the nearest multiple of 10
		int checksum = (10 - (sum % 10)) % 10;

		return checksum;
	}
}
