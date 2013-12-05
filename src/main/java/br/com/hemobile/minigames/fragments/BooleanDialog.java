package br.com.hemobile.minigames.fragments;

import br.com.hemobile.minigames.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class BooleanDialog extends DialogFragment {
	
	// Use this instance of the interface to deliver action events
	BooleanDialogListener mListener;

	public BooleanDialog() {
		// TODO Auto-generated constructor stub
	}
	
	public static BooleanDialog getInstance(String message) {
		BooleanDialog booleanDialog = new BooleanDialog();
		Bundle args = new Bundle();
	    args.putString("message", message);
	    booleanDialog.setArguments(args);
		
		return booleanDialog;
	}
	
	public interface BooleanDialogListener {
        public void onDialogPositiveClick(BooleanDialog dialog);
        //public void onDialogNegativeClick(UserDataFormDialog dialog);
    }

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String message = getArguments().getString("message");
		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
               .setPositiveButton(R.string.boolean_dialog_ok, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick(BooleanDialog.this);
                   }
               })
               .setNegativeButton(R.string.boolean_dialog_cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       BooleanDialog.this.getDialog().cancel();
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
            mListener = (BooleanDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement UserDataFormDialogListener");
        }
    }
}
