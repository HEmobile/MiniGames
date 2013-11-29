package br.com.hemobile.minigames.model;

public class GameResult {
	private String playerId;
	private String playerName;
	private int result;
	private int playTime;
	private int difficulty;
    
	public GameResult(String playerId, String playerName, int result, int playTime, int difficulty) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.result = result;
        this.playTime = playTime;
        this.difficulty = difficulty;
    }

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getPlayTime() {
		return playTime;
	}

	public void setPlayTime(int playTime) {
		this.playTime = playTime;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
       
	
}
