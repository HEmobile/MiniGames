package br.com.hemobile.minigames.fragments;

import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.UiThread;

import br.com.hemobile.minigames.MemoGameHelper;
import br.com.hemobile.minigames.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

@EFragment
public class UserDataFormDialog extends DialogFragment {

	//private EditText username;
	//private EditText userEmail;
	private int points;
	private int bonusPerSecond;
	
	public int secondsLeft;
	public EditText username;
    public EditText userEmail;
    public int finalPoints;
    TextView textDetails;

	public UserDataFormDialog() {
		
	}
	
	public static UserDataFormDialog getInstance(int points, long millisecondsLeft, int level) {
		UserDataFormDialog userDataForm = new UserDataFormDialog();
		Bundle args = new Bundle();
	    args.putInt("points", points);
	    args.putInt("secondsLeft", (int)(millisecondsLeft/1000));
	    args.putInt("level", level);
		userDataForm.setArguments(args);
		
		return userDataForm;
	}
	
	public interface UserDataFormDialogListener {
        public void onDialogPositiveClick(UserDataFormDialog dialog);
        public void onDialogNegativeClick(UserDataFormDialog dialog);
    }
    
    // Use this instance of the interface to deliver action events
	UserDataFormDialogListener mListener;
    
    @Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();
	    View layoutView = inflater.inflate(R.layout.user_data_form, null);
	    username = (EditText) layoutView.findViewById(R.id.username);
	    userEmail = (EditText) layoutView.findViewById(R.id.userEmail);
	    points = getArguments().getInt("points");
	    secondsLeft = getArguments().getInt("secondsLeft");
	    Log.i("SecondsLeft", ""+secondsLeft);
	    bonusPerSecond = MemoGameHelper.bonusPerSecondLeft(getArguments().getInt("level"));
	    
	    finalPoints = points + bonusPerSecond*secondsLeft;
	    
	    String text = getString(R.string.dialog_text);
	    text = text.replace("%@", points+"");
	    textDetails = (TextView) layoutView.findViewById(R.id.finish_game_text);
	    textDetails.setText(text);
	    
	    textDetails.postDelayed(new AddBonusPointsRunnable(points, bonusPerSecond, secondsLeft), 1000);
	    
	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    builder.setView(layoutView)
	    // Add action buttons
	           .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	            	   mListener.onDialogPositiveClick(UserDataFormDialog.this);
	                   // sign in the user ...
	               }
	           })
	           .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	            	   mListener.onDialogNegativeClick(UserDataFormDialog.this);
	            	   //UserDataFormDialog.this.getDialog().cancel();
	               }
	           });      
	    return builder.create();
	}
	
	// Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (UserDataFormDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement UserDataFormDialogListener");
        }
    }
    
    @UiThread
    void addBonusPoints(int oldPoints, int bonusPoints) {
    	Log.i("BonusPoints", "Adding");
    	this.points = oldPoints + bonusPoints;
    	
    	String text = textDetails.getText().toString();
	    text = text.replace(oldPoints+"", this.points+"");
	    textDetails.setText(text);
    }
    
    class AddBonusPointsRunnable implements Runnable {
		int currentPoints;
		int bonusPerSecond;
		long currentSecondsLeft;
		AddBonusPointsRunnable(int currentPoints, int bonusPerSecond, long secondsLeft) { this.currentPoints = currentPoints; this.bonusPerSecond = bonusPerSecond; this.currentSecondsLeft = secondsLeft;}
        public void run() {
        	addBonusPoints(currentPoints, bonusPerSecond);
        	currentPoints += this.bonusPerSecond;
        	currentSecondsLeft--;
        	Log.i("SecondsLeft", ""+currentSecondsLeft);
        	if (currentSecondsLeft > 0) {
        		textDetails.postDelayed(this, 100);
        	} else {
        		Log.i("FinalRun", "Stopped");
        	}
        }
    }

}
