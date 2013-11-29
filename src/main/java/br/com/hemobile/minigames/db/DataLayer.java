package br.com.hemobile.minigames.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import br.com.hemobile.minigames.model.GameResult;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class DataLayer {

	private DatabaseHelper _dbHelper;
	 
    public DataLayer(Context c) {
        _dbHelper = DatabaseHelper.getInstance(c);
    }
    
    // I will use the player email as his id
    public void addMemoGame(String playerId, String playerName, int result, int playTime, int difficulty) throws SQLiteException {
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("PlayerId", playerId);
            values.put("PlayerName", playerName);
            values.put("Result", result);
            values.put("PlayTime", playTime);
            values.put("Difficulty", difficulty);
 
            db.insertOrThrow("memo_games", "", values);
        } finally {
            if (db != null)
                db.close();
        }
    }
    
    public void addMemoStartData() {
    	try {
	    	addMemoGame("antonio@myemail.com", "Antonio Carlos", 10000, 150, 1);
	    	addMemoGame("renato@myemail.com", "Renato Santos", 13000, 120, 1);
	    	addMemoGame("guilherme@myemail.com", "Guilherme Dantas", 8000, 50, 1);
	    	addMemoGame("eduardo@myemail.com", "Eduardo Pinto", 2000, 100, 1);
	    	addMemoGame("manoel@myemail.com", "Manoel Portuga", 1000, 250, 1);
	    	addMemoGame("tiago@myemail.com", "Tiago Machado", 5000, 180, 1);
    	} catch (SQLiteException e) {
    		// ignore exception
    	}
    }
    
    public ArrayList<GameResult> memoGamesList() {
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        try {
            ArrayList<GameResult> results = new ArrayList<GameResult>();
            Cursor c = db.rawQuery("select * from memo_games order by Result desc", null);
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    results.add(new GameResult(
                            c.getString(c.getColumnIndex("PlayerId")),
                            c.getString(c.getColumnIndex("PlayerName")),
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

}
