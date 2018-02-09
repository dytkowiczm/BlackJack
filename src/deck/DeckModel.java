package deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeckModel {

	private List<CardModel> trefl;
	private List<CardModel> karo;
	private List<CardModel> kier;
	private List<CardModel> pik;

	public DeckModel() {
		trefl = new ArrayList<>();
		karo = new ArrayList<>();
		kier = new ArrayList<>();
		pik = new ArrayList<>();

		trefl.add(new CardModel("as trefl", 1, 11));
		trefl.add(new CardModel("2 trefl", 2, 0));
		trefl.add(new CardModel("3 trefl", 3, 0));
		trefl.add(new CardModel("4 trefl", 4, 0));
		trefl.add(new CardModel("5 trefl", 5, 0));
		trefl.add(new CardModel("6 trefl", 6, 0));
		trefl.add(new CardModel("7 trefl", 7, 0));
		trefl.add(new CardModel("8 trefl", 8, 0));
		trefl.add(new CardModel("9 trefl", 9, 0));
		trefl.add(new CardModel("10 trefl", 10, 0));
		trefl.add(new CardModel("walet trefl", 10, 0));
		trefl.add(new CardModel("dama trefl", 10, 0));
		trefl.add(new CardModel("krol trefl", 10, 0));

		karo.add(new CardModel("as karo", 1, 11));
		karo.add(new CardModel("2 karo", 2, 0));
		karo.add(new CardModel("3 karo", 3, 0));
		karo.add(new CardModel("4 karo", 4, 0));
		karo.add(new CardModel("5 karo", 5, 0));
		karo.add(new CardModel("6 karo", 6, 0));
		karo.add(new CardModel("7 karo", 7, 0));
		karo.add(new CardModel("8 karo", 8, 0));
		karo.add(new CardModel("9 karo", 9, 0));
		karo.add(new CardModel("10 karo", 10, 0));
		karo.add(new CardModel("walet karo", 10, 0));
		karo.add(new CardModel("dama karo", 10, 0));
		karo.add(new CardModel("krol karo", 10, 0));

		kier.add(new CardModel("as kier", 1, 11));
		kier.add(new CardModel("2 kier", 2, 0));
		kier.add(new CardModel("3 kier", 3, 0));
		kier.add(new CardModel("4 kier", 4, 0));
		kier.add(new CardModel("5 kier", 5, 0));
		kier.add(new CardModel("6 kier", 6, 0));
		kier.add(new CardModel("7 kier", 7, 0));
		kier.add(new CardModel("8 kier", 8, 0));
		kier.add(new CardModel("9 kier", 9, 0));
		kier.add(new CardModel("10 kier", 10, 0));
		kier.add(new CardModel("walet kier", 10, 0));
		kier.add(new CardModel("dama kier", 10, 0));
		kier.add(new CardModel("krol kier", 10, 0));

		pik.add(new CardModel("as pik", 1, 11));
		pik.add(new CardModel("2 pik", 2, 0));
		pik.add(new CardModel("3 pik", 3, 0));
		pik.add(new CardModel("4 pik", 4, 0));
		pik.add(new CardModel("5 pik", 5, 0));
		pik.add(new CardModel("6 pik", 6, 0));
		pik.add(new CardModel("7 pik", 7, 0));
		pik.add(new CardModel("8 pik", 8, 0));
		pik.add(new CardModel("9 pik", 9, 0));
		pik.add(new CardModel("10 pik", 10, 0));
		pik.add(new CardModel("walet pik", 10, 0));
		pik.add(new CardModel("dama pik", 10, 0));
		pik.add(new CardModel("krol", 10, 0));
	}

	public CardModel drawRandomCard() {
		Random random = new Random();

		int color = random.nextInt(4);
		List<CardModel> cardColor = new ArrayList<>();
		
		switch (color) {
		case 0:
			cardColor = trefl;
			break;
		case 1:
			cardColor = karo;
			break;
		case 2:
			cardColor = kier;
			break;
		case 3:
			cardColor = pik;
			break;
		}
		
		int valueOfCard = random.nextInt(13);
		return cardColor.get(valueOfCard);
	}
}
