package core;

public class GameCore {

	private static GameCore instance;
	
	private double cash;
	
	private GameCore() {
		
	}
	
	public static GameCore getInstance() {
		if (instance == null)
			instance = new GameCore();
		
		return instance;
	}
	
	public void substractCash(double cashToBeSubstracted) {
		cash -= cashToBeSubstracted;
	}
	
	public void addCash(double cashToBeAdded) {
		cash += cashToBeAdded;
	}
	
	public void setCash(double cash) {
		this.cash = cash;
	}
	
	public double getCash() {
		return cash;
	}
	
}
