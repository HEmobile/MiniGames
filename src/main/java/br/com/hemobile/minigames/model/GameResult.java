package br.com.hemobile.minigames.model;

public class GameResult {
	private int gameId;
	private String playerName;
	private String playerEmail;
	private int result;
	private int playTime;
	private int difficulty;
    
	public GameResult(int gameId, String playerName, String playerEmail, int result, int playTime, int difficulty) {
        this.gameId = gameId;
        this.playerName = playerName;
        this.result = result;
        this.playTime = playTime;
        this.difficulty = difficulty;
    }

	public int getPlayerId() {
		return gameId;
	}

	public void setPlayerId(int playerId) {
		this.gameId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public String getPlayerEmail() {
		return playerEmail;
	}

	public void setPlayerEmail(String playerEmail) {
		this.playerEmail = playerEmail;
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
