package br.com.hemobile.minigames;

public class MemoGameHelper {

	private MemoGameHelper() {
		// TODO Auto-generated constructor stub
	}
	
	public static int numberOfCardsPair(int level) {
		int numberOfCards;
		switch (level) {
		case 1:
			numberOfCards = 4;
			break;
		case 2:
			numberOfCards = 6;
			break;
		case 3:
			numberOfCards = 8;
			break;
		default:
			numberOfCards = 4;
			break;
		}
		return numberOfCards;
	}
	
	public static int cardsMatchPoints(int level) {
		int numberOfCards;
		switch (level) {
		case 1:
			numberOfCards = 500;
			break;
		case 2:
			numberOfCards = 1000;
			break;
		case 3:
			numberOfCards = 2000;
			break;
		default:
			numberOfCards = 1000;
			break;
		}
		return numberOfCards;
	}

}
