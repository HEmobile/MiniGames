package br.com.hemobile.minigames;

public class MemoGameHelper {

	private MemoGameHelper() {
		// TODO Auto-generated constructor stub
	}
	
	public static int numberOfCardsPair(int level) {
		int numberOfCards;
		switch (level) {
		case 1:
			numberOfCards = 6;
			break;
		case 2:
			numberOfCards = 10;
			break;
		case 3:
			numberOfCards = 14;
			break;
		default:
			numberOfCards = 4;
			break;
		}
		return numberOfCards;
	}
	
	public static int cardsMatchPoints(int level) {
		int points;
		switch (level) {
		case 1:
			points = 500;
			break;
		case 2:
			points = 1000;
			break;
		case 3:
			points = 2000;
			break;
		default:
			points = 1000;
			break;
		}
		return points;
	}
	
	public static long milliSecondsToPlay(int level) {
		long timeToPlay; //in seconds
		switch (level) {
		case 1:
			timeToPlay = 60; 
			break;
		case 2:
			timeToPlay = 120;
			break;
		case 3:
			timeToPlay = 180;
			break;
		default:
			timeToPlay = 60;
			break;
		}
		return timeToPlay*1000;
	}
	
	public static int bonusPerSecondLeft(int level) {
		int bonus; //in seconds
		switch (level) {
		case 1:
			bonus = 50; 
			break;
		case 2:
			bonus = 75;
			break;
		case 3:
			bonus = 100;
			break;
		default:
			bonus = 50;
			break;
		}
		return bonus;
	}

}
