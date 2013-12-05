package br.com.hemobile.minigames;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.widget.TextView;
import br.com.hemobile.minigames.fragments.FinishGameDialog;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.main)
public class MenuActivity extends Activity {

	@ViewById
	TextView helloView;
	
	@AfterViews
	public void init() {
		// Called after onCreate method
	}
	
	@Click
	public void btnLevel1() {
		Intent i = new Intent(MenuActivity.this, MemoGameActivity_.class);
		i.putExtra("level", 1);
		startActivity(i);
	}
	
	@Click
	public void btnLevel2() {
		Intent i = new Intent(MenuActivity.this, MemoGameActivity_.class);
		i.putExtra("level", 2);
		startActivity(i);
	}
	
	@Click
	public void btnLevel3() {
		Intent i = new Intent(MenuActivity.this, MemoGameActivity_.class);
		i.putExtra("level", 3);
		startActivity(i);
	}
	
	@Click
	public void btnRanking() {
		Intent i = new Intent(MenuActivity.this, RankingsActivity_.class);
		startActivity(i);
	}
	
	@Click
	public void btnOptions() {
		Intent i = new Intent(MenuActivity.this, OptionsActivity_.class);
		startActivity(i);
	}
}

