package br.com.hemobile.minigames;

import br.com.hemobile.gui.BaseActivity;
import br.com.hemobile.minigames.adapters.CardAdapter;
import br.com.hemobile.minigames.db.DataLayer;
import br.com.hemobile.minigames.fragments.UserDataFormDialog;

import com.googlecode.androidannotations.annotations.*;
import com.tekle.oss.android.animation.AnimationFactory;
import com.tekle.oss.android.animation.AnimationFactory.FlipDirection;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
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
public class MemoGameActivity extends BaseActivity implements UserDataFormDialog.UserDataFormDialogListener {

	@ViewById
	GridView gridView;
	
	@ViewById
	RelativeLayout gameContainer;
	
	ViewFlipper selectedCard;
	TextView score;
	TextView bonus;
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
		//addPoints();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.previewMenuBtn:
				DialogFragment userDataForm = new UserDataFormDialog();
				Bundle args = new Bundle();
			    args.putString("points", "1500");
				userDataForm.setArguments(args);
				userDataForm.show(getFragmentManager(), "User Data Form");
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
		return true;
	}
	
	@UiThread
	void addPoints(){
		AnimationFactory.fadeInThenOut(bonus, 500, 300, false);
	}
	
	@UiThread
	void finishedRightCardsAnimation() {
		differentCards--;
		if (differentCards == 0) {
			DialogFragment userDataForm = new UserDataFormDialog();
			Bundle args = new Bundle();
		    args.putString("points", score.getText().toString());
			userDataForm.setArguments(args);
			userDataForm.show(getFragmentManager(), "User Data Form");
		}
	}
	
	@Override
    public void onDialogPositiveClick(UserDataFormDialog dialog) {
		Log.i("Dialog returned name", dialog.username.getText().toString());
 	   	Log.i("Dialog returned email", dialog.userEmail.getText().toString());
 	   	
 	   	DataLayer dao = new DataLayer(this);
 	   	dao.addMemoGame(dialog.userEmail.getText().toString(), dialog.username.getText().toString(), Integer.valueOf(score.getText().toString()), 150, level);
    }

    @Override
    public void onDialogNegativeClick(UserDataFormDialog dialog) {
    }
	
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
	    MenuItem item = menu.findItem(R.id.score_item);
	    score = (TextView)item.getActionView().findViewById(R.id.score_text);
	    bonus = (TextView)item.getActionView().findViewById(R.id.bonus_text);
	    score.setText("0");
	    
	    return super.onPrepareOptionsMenu(menu);
	}
	
	class WrongCards implements Runnable {
		ViewFlipper card;
		ViewFlipper card2;
        WrongCards(ViewFlipper view, ViewFlipper view2) { card = view; card2 = view2;}
        public void run() {
        	AnimationFactory.flipTransition(card, FlipDirection.LEFT_RIGHT);
        	AnimationFactory.flipTransition(card2, FlipDirection.LEFT_RIGHT);
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
        	//card.setVisibility(View.INVISIBLE);
        	//card2.setVisibility(View.INVISIBLE);
        }
    }

}