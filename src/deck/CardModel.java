package deck;

public class CardModel {

	private String name;
	private int[] valueOfCard;

	public CardModel(String name, int firstValue, int secondValue) {
		this.name = name;
		valueOfCard = new int[2];
		valueOfCard[0] = firstValue;
		valueOfCard[1] = secondValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean checkIfAs() {
		if (name.contains("as")) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getValueIfAs() {
		if (name.contains("as")) {
			return valueOfCard[1];
		} else {
			return valueOfCard[0];
		}
	}
	
	public void setFirstValue(int value) {
		valueOfCard[0] = value;
	}

	public int getFirstValue() {
		if (valueOfCard != null)
			return valueOfCard[0];
		else
			return 0;
	}
	
	public int getSecondValue() {
		if (valueOfCard != null)
			return valueOfCard[1];
		else
			return 0;
	}

}
