package br.com.hemobile.minigames;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.main)
public class MenuActivity extends Activity {

	@ViewById
	TextView helloView;
	
	CountDownTimer countdown;
	
	@AfterViews
	public void init() {
		// Called after onCreate method
		
	}
	
	void stopTimer() {
		if (countdown != null) {
			countdown.cancel();
			countdown = null;
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		long iddleTime = this.getResources().getInteger(R.integer.menu_idle_max_time);
		countdown = new CountDownTimer(iddleTime, iddleTime) {

			public void onFinish() {
				btnRanking();
			}

			@Override
			public void onTick(long millisUntilFinished) {
				//Log.i("Tick",millisUntilFinished/1000+" seconds to finish");
			}
		}.start();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		stopTimer();
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

