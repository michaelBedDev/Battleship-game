package view;

import java.util.Scanner;

import data.Board;

public class UI {

	private Scanner sc;

	/**
	 * Constructor
	 */
	public UI() {
		super();
		sc = new Scanner(System.in);
	}

	/**
	 * Ask the user for a letter and a number. Keep asking them if incorrect format.
	 * Then transform them into a array of two digits. Coordinates Y and X
	 * 
	 * @return an array of two Integers (coordinates y,x)
	 */
	public int[] askCoordinates() { /* need test ARREGLAR */
		String input;
		int letter;
		int number;

		boolean validLetter;

		do {
			input = validateCoordinatesFormat();

			letter = input.toUpperCase().charAt(0) - 'A'; /* A in ASCII = 65 so (65-65 A would be 0) */
			number = Integer.parseInt(input.substring(1)) - 1;

			validLetter = letter > -1 && letter < 10;

			if (!validLetter) {
				System.out.println("La letra no es correcta. Introduce nuevamente las coordenadas");
			}
		} while (!validLetter);
		return new int[] { letter, number };
	}

	/**
	 * Show the board itself
	 */
	public void show(Board board) {
		for (int[] row : board.getTablero()) {
			for (int i : row) {
				switch (i) {
				case 0 -> System.out.print("🔷");
				case 1 -> System.out.print("⬛");
				case 2 -> System.out.print("🔲");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Show both boards with header and legend. Works for board and StatusBoard
	 * 
	 * @param board1
	 * @param board2
	 */
	public void showBothBoards(Board board1, Board board2) {

		printHeader();

		char letter = 'A';

		for (int posY = 0; posY < board1.getTablero().length; posY++) {

			System.out.print(letter + "-");
			/* Board 1 */
			for (int x1 = 0; x1 < board1.getTablero()[posY].length; x1++) {
				switch (board1.getTablero()[posY][x1]) {
				case 0 -> System.out.print("🔷");
				case 1 -> System.out.print("⬛"); // agua
				case 2 -> System.out.print("🔲"); // barco
				}

			}

			System.out.print("\t\t" + letter + "-"); // Spacing

			/*Board 2 */
			for (int x2 = 0; x2 < board2.getTablero()[posY].length; x2++) {
				switch (board2.getTablero()[posY][x2]) {
				case 0 -> System.out.print("🔷");
				case 1 -> System.out.print("⬛"); // agua
				case 2 -> System.out.print("🔲"); // barco
				}
			}
			letter++;
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Print the header after print the board. Message and numbers
	 */
	private void printHeader() {
		System.out.printf("%16s\t\t%15s\n", "*TUS BARCOS*", "*EL RIVAL*");
		int number = 1;

		for (int i = 0; i < 2; i++) {
			System.out.print(" ".repeat(2));
			for (int j = 0; j < 10; j++) {
				System.out.print(number + " ");
				number++;
			}
			number = 1;
			System.out.print(" ".repeat(9));
		}
		System.out.println();
	}

	/**
	 * Method to show a message to user
	 * 
	 * @param message (message to show)
	 */
	public void showMessage(String message) {
		System.out.println(message);
	}

	/**
	 * Ask user for a String
	 * 
	 * @param message (to print)
	 * @return input. loop if string is empty
	 */
	private String askString(String message) {

		String input;
		do {
			System.out.println(message);
			input = sc.nextLine();
		} while (input.equals(""));

		return input;
	}

	/**
	 * Validate the format asked for coordinates is Letter, number
	 * 
	 * @return input
	 */
	private String validateCoordinatesFormat() {

		boolean validateFormat;
		boolean validateLength;
		String input;

		do {
			input = askString("Introduce las coordenadas [A1:J10]");

			validateFormat = Character.isAlphabetic(input.charAt(0)) && Character.isDigit(input.charAt(1));
			validateLength = (input.length() == 2);

		} while (!validateLength && !validateFormat);

		return input;
	}
}
