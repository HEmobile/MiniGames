package br.com.hemobile.minigames.fragments;

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

public class UserDataFormDialog extends DialogFragment {

	//private EditText username;
	//private EditText userEmail;
	private String points;
	public EditText username;
    public EditText userEmail;

	public UserDataFormDialog() {
		
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
	    points = getArguments().getString("points");
	    String text = getString(R.string.dialog_text);
	    text = text.replace("%@", points);
	    TextView textDetails = (TextView) layoutView.findViewById(R.id.finish_game_text);
	    textDetails.setText(text);
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
	            	   UserDataFormDialog.this.getDialog().cancel();
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

}
