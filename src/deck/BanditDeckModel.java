package deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BanditDeckModel {

	public static final String TREFL = "trefl";
	public static final String KARO = "karo";
	public static final String KIER = "kier";
	public static final String PIK = "pik";
	
	public static final String TWO_CARD = "2";
	public static final String THREE_CARD = "3";
	public static final String FOUR_CARD = "4";
	public static final String FIVE_CARD = "5";
	
	private List<CardModel> trefl;
	private List<CardModel> karo;
	private List<CardModel> kier;
	private List<CardModel> pik;

	public BanditDeckModel() {
		trefl = new ArrayList<>();
		karo = new ArrayList<>();
		kier = new ArrayList<>();
		pik = new ArrayList<>();

		trefl.add(new CardModel("2 trefl", 2, 0));
		trefl.add(new CardModel("3 trefl", 3, 0));
		trefl.add(new CardModel("4 trefl", 4, 0));
		trefl.add(new CardModel("5 trefl", 5, 0));

		karo.add(new CardModel("2 karo", 2, 0));
		karo.add(new CardModel("3 karo", 3, 0));
		karo.add(new CardModel("4 karo", 4, 0));
		karo.add(new CardModel("5 karo", 5, 0));

		kier.add(new CardModel("2 kier", 2, 0));
		kier.add(new CardModel("3 kier", 3, 0));
		kier.add(new CardModel("4 kier", 4, 0));
		kier.add(new CardModel("5 kier", 5, 0));

		pik.add(new CardModel("2 pik", 2, 0));
		pik.add(new CardModel("3 pik", 3, 0));
		pik.add(new CardModel("4 pik", 4, 0));
		pik.add(new CardModel("5 pik", 5, 0));
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
		
		int valueOfCard = random.nextInt(4);
		return cardColor.get(valueOfCard);
	}
}
