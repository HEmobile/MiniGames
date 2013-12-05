package br.com.hemobile.minigames.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import br.com.hemobile.minigames.model.GameResult;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DataLayer {

	private DatabaseHelper _dbHelper;
	 
    public DataLayer(Context c) {
        _dbHelper = DatabaseHelper.getInstance(c);
    }
    
    
    public void addMemoGame(String playerEmail, String playerName, int result, int playTime, int difficulty) throws SQLiteException {
    	addMemoGame(memoGamesNextId(), playerEmail, playerName, result, playTime, difficulty);
    }
    
    public void addMemoGame(int gameId, String playerEmail, String playerName, int result, int playTime, int difficulty) throws SQLiteException {
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("GameId", gameId);
            values.put("PlayerName", playerName);
            values.put("PlayerEmail", playerEmail);
            values.put("Result", result);
            values.put("PlayTime", playTime);
            values.put("Difficulty", difficulty);
 
            db.insertOrThrow(DatabaseHelper.MEMO_GAME_TABLE, "", values);
        } finally {
            if (db != null)
                db.close();
        }
    }
    
    public void addMemoStartData() {
    	try {
	    	addMemoGame(1,"antonio@myemail.com", "Antonio Carlos", 1000, 0, 1);
	    	addMemoGame(2,"renato@myemail.com", "Renato Santos", 2550, 11, 1);
	    	addMemoGame(3,"guilherme@myemail.com", "Guilherme Dantas", 2050, 1, 1);
	    	addMemoGame(4,"eduardo@myemail.com", "Eduardo Pinto", 2500, 10, 1);
	    	addMemoGame(5,"manoel@myemail.com", "Manoel Portuga", 1500, 0, 1);
	    	addMemoGame(6,"tiago@myemail.com", "Tiago Machado", 3000, 20, 1);
	    	
	    	addMemoGame(7,"antonio@myemail.com", "Antonio Carlos", 6000, 20, 2);
	    	addMemoGame(8,"renato@myemail.com", "Renato Santos", 3000, 0, 2);
	    	addMemoGame(9,"guilherme@myemail.com", "Guilherme Dantas", 5000, 10, 2);
	    	addMemoGame(10,"eduardo@myemail.com", "Eduardo Pinto", 4100, 11, 2);
	    	addMemoGame(11,"manoel@myemail.com", "Manoel Portuga", 5100, 21, 2);
	    	addMemoGame(12,"tiago@myemail.com", "Tiago Machado", 2000, 0, 2);
	    	
	    	addMemoGame(13,"antonio@myemail.com", "Antonio Carlos", 10200, 21, 3);
	    	addMemoGame(14,"renato@myemail.com", "Renato Santos", 4000, 0, 3);
	    	addMemoGame(15,"guilherme@myemail.com", "Guilherme Dantas", 8200, 11, 3);
	    	addMemoGame(16,"eduardo@myemail.com", "Eduardo Pinto", 12000, 20, 3);
	    	addMemoGame(17,"manoel@myemail.com", "Manoel Portuga", 6000, 0, 3);
	    	addMemoGame(18,"tiago@myemail.com", "Tiago Machado", 10000, 10, 3);
    	} catch (SQLiteException e) {
    		// ignore exception
    	}
    }
    
    public void clearMemoGameData() {
    	SQLiteDatabase db = _dbHelper.getReadableDatabase();
        try {
            db.delete(DatabaseHelper.MEMO_GAME_TABLE, null, null);
        } finally {
            if (db != null)
                db.close();
        }
    }
    
    public ArrayList<GameResult> memoGamesList(String level) {
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        try {
            ArrayList<GameResult> results = new ArrayList<GameResult>();
            Cursor c = db.rawQuery("select * from " + DatabaseHelper.MEMO_GAME_TABLE + " where Difficulty = ? order by Result desc", new String[] { level });
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    results.add(new GameResult(
                            c.getInt(c.getColumnIndex("GameId")),
                            c.getString(c.getColumnIndex("PlayerName")),
                            c.getString(c.getColumnIndex("PlayerEmail")),
                            c.getInt(c.getColumnIndex("Result")),
                            c.getInt(c.getColumnIndex("PlayTime")),
                            c.getInt(c.getColumnIndex("Difficulty"))));
                } while (c.moveToNext());
            } 
            return results;
        } finally {
            if (db != null)
                db.close();
        }
    }
    
    public ArrayList<String> contactList() {
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        try {
            ArrayList<String> results = new ArrayList<String>();
            Cursor c = db.rawQuery("select distinct(PlayerEmail) from " + DatabaseHelper.MEMO_GAME_TABLE + " where GameId > 0 order by PlayerEmail asc", null);
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    results.add(c.getString(c.getColumnIndex("PlayerEmail")));
                } while (c.moveToNext());
            } 
            return results;
        } finally {
            if (db != null)
                db.close();
        }
    }
    
    public int memoGamesNextId() {
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        try {
            int nextId = 0;
            Cursor c = db.rawQuery("select count(*) from memo_games", null);
            if (c.getCount() > 0) {
                c.moveToFirst();
                nextId = c.getInt(0)+1;
            } 
            if (nextId == 1) { 
            	addMemoStartData();
            	return memoGamesNextId();
            }
        	
            return nextId;
        } finally {
            if (db != null)
                db.close();
        }
    }

}
