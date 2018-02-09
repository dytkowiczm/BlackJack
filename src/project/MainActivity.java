package project;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import core.GameCore;
import model.BlackJackPlayer;

public class MainActivity {

	private static GameCore core;
	private static BlackJackPlayer bjPlayer;

	private static boolean loop = true;

	public static void main(String[] args) {
		core = GameCore.getInstance();
		bjPlayer = new BlackJackPlayer();
		while (loop) {
			checkCash();
			checkGame();
		}
	}

	private static void checkGame() {
		System.out.print("                 - OPCJE -                  \n");
		System.out.print("                                            \n");
		System.out.print("                (B)lackJack                 \n");
		System.out.print("                (S)tan konta                \n");
		System.out.print("                    -  -             \n\n     ");

		String choice = input().toLowerCase();

		switch (choice.charAt(0)) {
		case 's':
			System.out.println("Aktualny stan konta: " + core.getCash() + "$");
			checkGame();
			break;
		case 'b':
			boolean bjLoop = true;
			while (bjLoop) {
				bjPlayer.play();

				System.out.println("\nCzy chcesz grać dalej?");
				System.out.print("                 - OPCJE -                  \n");
				System.out.print("                                            \n");
				System.out.print("            (T)ak          (N)ie            \n");
				System.out.print("                                            \n");
				System.out.print("                    -  -             \n\n     ");

				choice = input().toLowerCase();

				switch (choice.charAt(0)) {
				case 't':
					break;
				case 'n':
					countCashAfterGameBlackJack();
					bjPlayer = new BlackJackPlayer();
					bjLoop = false;
					break;
				default:
					System.out.print("\n Błąd wprowadzenia!\n");
					choice = "INVALID_INPUT";
					countCashAfterGameBlackJack();
					checkGame();
					break;
				}
			}
			break;
		default:
			System.out.print("\n Błąd wprowadzenia!\n");
			choice = "INVALID_INPUT";
			checkGame();
			break;
		}
	}

	private static void countCashAfterGameBlackJack() {
		core.addCash(bjPlayer.getPlayerWins() * 20);
		core.substractCash(bjPlayer.getDealerWins() * 20);
	}

	private static void checkCash() {
		System.out.println("Wartość twojego konta: " + core.getCash() + "$");
		if (core.getCash() <= 0) {
			System.out.println("Aby rozpocząć musisz posiadać minimum 20$");
			System.out.println("Chcesz wpłacić pieniądze?");
			System.out.print("                  - OPCJE -                  \n");
			System.out.print("                                             \n");
			System.out.print("            (T)ak          (N)ie             \n");
			System.out.print("                                             \n");
			System.out.print("                    -  -              \n\n     ");

			String choice = input().toLowerCase();

			switch (choice.charAt(0)) {
			case 't':
				core.setCash(20);
				break;
			case 'n':
				System.out.println("Nie posiadasz odpowiedniej ilości pieniędzy aby zagrać.");
				loop = false;
				break;
			default:
				System.out.print("\n Bląd wprowadzenia!\n");
				choice = "INVALID_INPUT";
				checkCash();
				break;
			}
		}
	}

	public static String input() {
		LineNumberReader BANANA = new LineNumberReader(new InputStreamReader(System.in));
		String input = "";
		try {
			input = BANANA.readLine();
		} catch (IOException APPLE) {
			System.err.println("Error taking input...");
		}
		return input;
	}
}
