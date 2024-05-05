package view;

import java.util.Scanner;

import data.Player;

public class UI {
	
	private Scanner sc;
	
	public UI() {
		super();
		sc = new Scanner(System.in);
	}

	
	public int[] askCoordinates() { // need test ARREGLAR
		String input;
		boolean isValid = false;
		int letter;
		int number;

		do {
			boolean validateFormat;
			boolean validateLength;
			do {
				do {
					System.out.println("Introduce las coordenadas [A1:J10]");
					input = sc.nextLine();
				} while (input.equals(""));

				validateFormat = Character.isAlphabetic(input.charAt(0)) && Character.isDigit(input.charAt(1));
				validateLength = (input.length() == 2);

			} while (!validateLength && !validateFormat);

			letter = input.toUpperCase().charAt(0) - 'A'; // A in ASCII = 65 so (65-65 A would be 0)
			number = Integer.parseInt(input.substring(1)) - 1;

			boolean validLetter = letter > -1 && letter < 10;

			isValid = validateFormat && validateLength && validLetter;

			if (!isValid) {
				System.out.println("Por favor, introduce nuevamente las coordenadas");
			}
		} while (!isValid || input.equals(""));

		return new int[] { letter, number };
	}

	// mostrar tablero
	public void showStatusBoard(Player p) {
		for (int[] row : p.getBoardStatus().getTablero()) {
			for (int i : row) {
				switch (i) {
				case 0 -> System.out.print("🔷");
				case 1 -> System.out.print("⬛"); // agua
				case 2 -> System.out.print("🔲"); // barco
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	// mostrar ambos tableros SAME SIZE!!
	public void showBothStatusBoards(Player p1, Player p2) {

		char letter = 'A'; // print letter
		int number = 1;

		System.out.printf("%16s\t\t%15s\n", "*TUS BARCOS*", "*EL RIVAL*");

		for (int i = 0; i < 2; i++) {
			System.out.print(" ".repeat(2));
			for (int j = 0; j < p2.getBoardStatus().getTablero().length; j++) {
				System.out.print(number + " ");
				number++;
			}
			number = 1;
			System.out.print(" ".repeat(9));
		}
		System.out.println();

		for (int posY = 0; posY < p1.getBoardStatus().getTablero().length; posY++) {

			System.out.print(letter + "-");
			// Board 1
			for (int x1 = 0; x1 < p1.getBoardStatus().getTablero()[posY].length; x1++) {
				switch (p1.getBoardStatus().getTablero()[posY][x1]) {
				case 0 -> System.out.print("🔷");
				case 1 -> System.out.print("⬛"); // agua
				case 2 -> System.out.print("🔲"); // barco
				}

			}

			System.out.print("\t\t" + letter + "-"); // Spacing

			// Board 2
			for (int x2 = 0; x2 < p2.getBoardStatus().getTablero()[posY].length; x2++) {
				switch (p2.getBoardStatus().getTablero()[posY][x2]) {
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

	// mostrar ambos tableros de barcos SAME SIZE!!
	public void showBothBoards(Player p1, Player p2) {

		char letter = 'A'; // print letter
		int number = 1;

		System.out.printf("%16s\t\t%15s\n", "*TUS BARCOS*", "*EL RIVAL*");

		for (int i = 0; i < 2; i++) {
			System.out.print(" ".repeat(2));
			for (int j = 0; j < p2.getBoard().getTablero().length; j++) {
				System.out.print(number + " ");
				number++;
			}
			number = 1;
			System.out.print(" ".repeat(9));
		}
		System.out.println();

		for (int posY = 0; posY < p1.getBoard().getTablero().length; posY++) {

			System.out.print(letter + "-");
			// Board 1
			for (int x1 = 0; x1 < p1.getBoard().getTablero()[posY].length; x1++) {

				switch (p1.getBoard().getCell(posY, x1)) {
				case 0 -> System.out.print("🔷");
				case -1 -> System.out.print("⬛");
				case 1 -> System.out.print("🔲");
				}

			}

			System.out.print("\t\t" + letter + "-"); // Spacing

			// Board 2
			for (int x2 = 0; x2 < p2.getBoard().getTablero()[posY].length; x2++) {
				switch (p2.getBoard().getCell(posY, x2)) {
				case 0 -> System.out.print("🔷");
				case -1 -> System.out.print("⬛");
				case 1 -> System.out.print("🔲");
				}
			}
			letter++;
			System.out.println();
		}
		System.out.println();
	}

	public void showMessage(String message) {
		System.out.println(message);
	}
}
