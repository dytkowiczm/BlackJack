package model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import deck.CardModel;
import deck.DeckModel;

public class BlackJackPlayer {

	private static final int PLAYER_HAND = 1;
	private static final int DEALER_HAND = 2;
	public static final int HOUSE_LIMIT = 17;

	private DeckModel deck;
	private List<CardModel> playerCards;
	private List<CardModel> dealerCards;

	private int playerHandValue = 0;
	private int dealerHandValue = 0;

	private int cardsToStartWith = 2;
	private int maxHandSize = 20;

	private int playerWins = 0;
	private int dealerWins = 0;

	private boolean displayAllDealerCards;
	private boolean playerStay;
	private boolean dealerStay;

	public BlackJackPlayer() {
		deck = new DeckModel();
		playerCards = new ArrayList<>();
		dealerCards = new ArrayList<>();
	}

	public void play() {
		playerCards = new ArrayList<>();
		dealerCards = new ArrayList<>();
		displayAllDealerCards = false;
		playerStay = false;
		dealerStay = false;
		dealerHandValue = 0;
		playerHandValue = 0;

		System.out.println("---- Gra w BlackJack-a ----");

		System.out.println("--- Pierwsze rozdanie ---");
		System.out.println("Gracz oraz krupier otrzymują po dwie karty");

		for (int i = 0; i < cardsToStartWith; i++) {
			drawHand(PLAYER_HAND);
			drawHand(DEALER_HAND);
		}

		checkHand(PLAYER_HAND);
		checkHand(DEALER_HAND);

		deal();

		if (!displayAllDealerCards) {
			System.out.println("Od teraz wszystkie karty krupiera są widoczne");
		}
		displayAllDealerCards = true;

		checkHand(PLAYER_HAND);
		checkHand(DEALER_HAND);

		if (playerHandValue == 21) {
			System.out.print("Wygrałes! Perfekcyjna 21!");
			playerWins++;
		}
		else if (dealerHandValue == 21) {
			System.out.print("Krupier wygrał! Perfekcyjna 21!");
			dealerWins++;
		}
		else if (playerHandValue > 21) {
			System.out.print("Przykro mi, przekroczyłeś wartość 21, przegrywasz!");
			dealerWins++;
		}
		else if (dealerHandValue > 21) {
			System.out.print("Krupier przekracza wartość 21! Wygrywasz!");
			playerWins++;
		}

		if (playerStay && dealerHandValue <= 21) {
			if (playerHandValue == dealerHandValue) {
				System.out.print("Nie możliwe! Krupier oraz gracz posiadają tyle samo punktów! Remis!");
				playerWins++;
				dealerWins++;
			} else if (playerHandValue > dealerHandValue) {
				System.out.print("Wygrałeś! Masz więcej punktów!");
				playerWins++;
			} else if (dealerHandValue > playerHandValue) {
				System.out.print("Przegrywasz! Masz mniej punktów!");
				dealerWins++;
			}
		}
	}

	private void deal() {
		String choice = "#";

		while (choice.charAt(0) != 'o' && dealerHandValue < 21 && playerHandValue < 21 && !playerStay) {
			System.out.println("Jaki ruch chcesz wykonać?");
			System.out.print("-------------------- Opcje -------------------\n");
			System.out.print("|                                            |\n");
			System.out.print("|  (D)obierz       (C)zekaj      (O)dejdz    |\n");
			System.out.print("|                                            |\n");
			System.out.print("|--------------------------------------------|\n\n     ");

			choice = input().toLowerCase();

			switch (choice.charAt(0)) {
			case 'd':
				System.out.print("\n Chcesz dobrać.\n");
				hit(PLAYER_HAND);
				break;
			case 'c':
				System.out.print("\n Chcesz czekać.\n");
				playerStay = true;
				checkHand(PLAYER_HAND);
				break;
			case 'o':
				break;
			default:
				System.out.print("\n Bład wprowadzenia!\n");
				choice = "INVALID_INPUT";
				break;
			}

			if (!choice.equals("INVALID_INPUT")) {
				if (playerHandValue < 21) {
					if (dealerHandValue < HOUSE_LIMIT) {
						System.out.print("\n Krupier decyduje się na wzięcie karty.\n");
						hit(DEALER_HAND);
					} else {
						System.out.print("\n Krupier decyduje się na czekanie.\n");
						dealerStay = true;
						checkHand(DEALER_HAND);
					}
				}
			}
		}
	}

	private void hit(int who) {
		drawHand(who);
		checkHand(who);

		if (who == PLAYER_HAND) {
			if (playerHandValue > 21) {
				for (int i = 0; i < playerCards.size(); i++) {
					CardModel card = playerCards.get(i);
					if (card.checkIfAs()) {
						System.out.println("Wartość twojej ręki jest powyżej 21 ale posiadasz Asa.");
						System.out.println("Zamieniamy wartość asa z 11 na 1");
						card.setFirstValue(1);
						break;
					}
				}

				checkHand(who);
			}
		} else {
			if (dealerHandValue > 21) {
				for (int i = 0; i < dealerCards.size(); i++) {
					CardModel card = dealerCards.get(i);
					if (card.checkIfAs()) {
						System.out.println("Wartość ręki krupiera jest powyżej 21 ale posiada Asa.");
						System.out.println("Zamieniamy wartość asa z 11 na 1");
						card.setFirstValue(1);
						break;
					}
				}

				checkHand(who);
			}
		}
	}

	private void drawHand(int who) {
		switch (who) {
		case PLAYER_HAND:
			playerCards.add(deck.drawRandomCard());
			break;
		case DEALER_HAND:
			dealerCards.add(deck.drawRandomCard());
			break;
		}
	}

	private void checkHand(int who) {
		int handValue = 0;
		switch (who) {
		case PLAYER_HAND:
			for (int i = 0; i < playerCards.size(); i++) {
				handValue += playerCards.get(i).getFirstValue();
			}
			System.out.println("Wartość punktów gracza: " + handValue);
			playerHandValue = handValue;
			break;
		case DEALER_HAND:
			if (displayAllDealerCards) {
				for (int i = 0; i < dealerCards.size(); i++) {
					handValue += dealerCards.get(i).getFirstValue();
				}
				System.out.println("Wartość punktów krupiera: " + handValue);
				dealerHandValue = handValue;
			} else {
				for (int i = 1; i < dealerCards.size(); i++) {
					handValue += dealerCards.get(i).getFirstValue();
				}
				System.out.println("Wartość punktów krupiera: " + handValue + " (UKRYTA JEDNA KARTA)");
			}
			break;
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

	public int getDealerWins() {
		return dealerWins;
	}

	public int getPlayerWins() {
		return playerWins;
	}
}
