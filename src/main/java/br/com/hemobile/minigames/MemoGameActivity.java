package br.com.hemobile.minigames;

import java.util.concurrent.TimeUnit;

import br.com.hemobile.gui.BaseActivity;
import br.com.hemobile.minigames.adapters.CardAdapter;
import br.com.hemobile.minigames.db.DataLayer;
import br.com.hemobile.minigames.fragments.BooleanDialog;
import br.com.hemobile.minigames.fragments.BooleanDialog.BooleanDialogListener;
import br.com.hemobile.minigames.fragments.FinishGameDialog;

import com.googlecode.androidannotations.annotations.*;
import com.tekle.oss.android.animation.AnimationFactory;
import com.tekle.oss.android.animation.AnimationFactory.FlipDirection;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

@OptionsMenu(R.menu.score_bar)
@EActivity(R.layout.memo_game)
public class MemoGameActivity extends BaseActivity implements FinishGameDialog.UserDataFormDialogListener,BooleanDialogListener {

	@ViewById
	GridView gridView;
	
	@ViewById
	RelativeLayout gameContainer;
	
	ViewFlipper selectedCard;
	TextView score;
	TextView bonus;
	TextView timer;
	CountDownTimer countdown;
	long millisecondsLeft;
	long timeToPlay;
	int differentCards = 0;
	int level;
	int cardsMatchPoints;
	
	@AfterViews
	public void init() {
		// Get Level
		Intent i = getIntent();
		level = i.getIntExtra("level", 1);
		differentCards = MemoGameHelper.numberOfCardsPair(level);
		cardsMatchPoints = MemoGameHelper.cardsMatchPoints(level);
		timeToPlay = MemoGameHelper.milliSecondsToPlay(level);
		millisecondsLeft = timeToPlay;
		// Called after onCreate method
		gridView.setAdapter(new CardAdapter(this,level));
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	ViewFlipper card = (ViewFlipper)v;//.findViewById(R.id.flipper);
	        	
	        	if (selectedCard == null) {
	        		AnimationFactory.flipTransition(card, FlipDirection.RIGHT_LEFT);
		        	selectedCard = card;
	        	} else if (selectedCard != card) {
	        		AnimationFactory.flipTransition(card, FlipDirection.RIGHT_LEFT);
		        	if (selectedCard.getTag(R.id.frontImage).equals(card.getTag(R.id.frontImage))) {
	        			gridView.postDelayed(new RightCards(selectedCard, card, score), 500);	
	        			//Integer newScore = Integer.valueOf(score.getText().toString())+1000;
	        			//score.setText(newScore.toString());
	        			addPoints();
	        			//MemoGameActivity.this.get
	        		} else {
	        			gridView.postDelayed(new WrongCards(selectedCard, card), 500);
	        		}
	        		selectedCard = null;
	        	}
	        }
	    });
		Log.i("init", "AfterViews");
		startTimer();
		//addPoints();
	}
	
	private void startTimer() {
		if (timeToPlay < 1000) return;
		countdown = new CountDownTimer(timeToPlay, 1000) {

			public void onTick(long millisUntilFinished) {
				//mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
				millisecondsLeft = millisUntilFinished;
				displayTimeLeft(millisUntilFinished);
			}

			public void onFinish() {
				timer.setText("0:00");
				millisecondsLeft = 0;
				finishGame();
				//mTextField.setText("done!");
			}
		}.start();
	}
	
	@SuppressLint("DefaultLocale")
	@UiThread
	void displayTimeLeft(long milliSeconds) {
		String time = String.format("%d:%02d", 
			    TimeUnit.MILLISECONDS.toMinutes(milliSeconds),
			    TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds))
			);
		if (timer != null) {
			timer.setText(time);
		}
	}
	
	@UiThread
	void addPoints(){
		AnimationFactory.fadeInThenOut(bonus, 500, 500, false);
	}
	
	@UiThread
	void finishGame() {
		if (countdown != null) {
			countdown.cancel();
		}
		DialogFragment dialog = FinishGameDialog.getInstance(Integer.valueOf(score.getText().toString()), millisecondsLeft, level);
		dialog.show(getFragmentManager(), FinishGameDialog.getName());
	}
	
	void finishedRightCardsAnimation() {
		differentCards--;
		if (differentCards == 0) {
			finishGame();
		}
	}
	
	@Override
    public void onDialogPositiveClick(FinishGameDialog dialog) {
		Log.i("Dialog returned name", dialog.username.getText().toString());
 	   	Log.i("Dialog returned email", dialog.userEmail.getText().toString());
 	   	
 	   	DataLayer dao = new DataLayer(this);
 	   	dao.addMemoGame(dialog.userEmail.getText().toString(), dialog.username.getText().toString(), dialog.finalPoints, dialog.secondsLeft, level);
 	   	dialog.dismiss();
 	   	
 	   	Intent i = new Intent(MemoGameActivity.this, RankingsActivity_.class);
 	   	startActivity(i);
    }

    @Override
    public void onDialogNegativeClick(FinishGameDialog dialog) {
    	dialog.dismiss();
    	NavUtils.navigateUpFromSameTask(this);
    }
	
    @OptionsItem(android.R.id.home)
	protected void onBackActionBarClick() {
		BooleanDialog dialog = BooleanDialog.getInstance(getString(R.string.leave_game_confirmation));
		dialog.show(getFragmentManager(), "Abandon Game");
	}
    
    @Override
    public void onDialogPositiveClick(BooleanDialog dialog) {
    	Log.i("Abandon Game", "OK PRessed");
    	dialog.dismiss();
    	if (countdown != null) {
			countdown.cancel();
		}
    	NavUtils.navigateUpFromSameTask(this);
    }
    
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
	    MenuItem item = menu.findItem(R.id.score_item);
	    score = (TextView)item.getActionView().findViewById(R.id.score_text);
	    score.setText("0");
	    
	    bonus = (TextView)item.getActionView().findViewById(R.id.bonus_text);
	    bonus.setText("+"+cardsMatchPoints);
	    timer = (TextView)item.getActionView().findViewById(R.id.timer_text);
	    displayTimeLeft(timeToPlay);
	    
	    return super.onPrepareOptionsMenu(menu);
	}
	
	class WrongCards implements Runnable {
		ViewFlipper card;
		ViewFlipper card2;
        WrongCards(ViewFlipper view, ViewFlipper view2) { card = view; card2 = view2;}
        public void run() {
        	AnimationFactory.flipTransition(card, FlipDirection.LEFT_RIGHT);
        	AnimationFactory.flipTransition(card2, FlipDirection.LEFT_RIGHT);
        	gridView.removeCallbacks(this);
        }
    }
	
	class RightCards implements Runnable {
		ViewFlipper card;
		ViewFlipper card2;
		TextView score;
        RightCards(ViewFlipper view, ViewFlipper view2, TextView scoreView) { card = view; card2 = view2; score = scoreView;}
        public void run() {
        	AnimationFactory.fadeOut(card, 250);
        	AnimationFactory.fadeOut(card2, 250);
        	
        	Integer newScore = Integer.valueOf(score.getText().toString())+cardsMatchPoints;
			score.setText(newScore.toString());
			finishedRightCardsAnimation();
			gridView.removeCallbacks(this);
        	//card.setVisibility(View.INVISIBLE);
        	//card2.setVisibility(View.INVISIBLE);
        }
    }

}