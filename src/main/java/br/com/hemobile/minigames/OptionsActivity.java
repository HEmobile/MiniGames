package br.com.hemobile.minigames;

import java.util.ArrayList;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;

import android.content.Intent;
import android.util.Log;
import br.com.hemobile.gui.BaseActivity;
import br.com.hemobile.minigames.db.DataLayer;
import br.com.hemobile.minigames.fragments.BooleanDialog;
import br.com.hemobile.minigames.fragments.BooleanDialog.BooleanDialogListener;

@EActivity(R.layout.options)
public class OptionsActivity extends BaseActivity implements BooleanDialogListener {

	public OptionsActivity() {
		// TODO Auto-generated constructor stub
	}
	
	@AfterViews
	public void init() {
		// Called after onCreate method
	}
	
	@Click
	public void btnRestartRankings() {
		BooleanDialog.getInstance(getString(R.string.restart_rankings_text)).show(getFragmentManager(),"Restart Rankings Dialog");
	}

	@Override
	public void onDialogPositiveClick(BooleanDialog dialog) {
		dialog.dismiss();
		
		DataLayer dao = new DataLayer(OptionsActivity.this);
		dao.clearMemoGameData();
		dao.addMemoStartData();
	}
	
	@Click
	public void btnEmailContactList() {
		DataLayer dao = new DataLayer(OptionsActivity.this);
		StringBuilder contactList = new StringBuilder(getString(R.string.email_contact_text));
		ArrayList<String> contactArray = dao.contactList();
		
		for (String contact : contactArray) {
			contactList.append(contact).append(", ");
		}
		
		BooleanDialog.getInstance(contactList.toString()).show(getFragmentManager(),"ContactList");
		/*
		Intent email = new Intent(Intent.ACTION_SEND);
		email.putExtra(Intent.EXTRA_EMAIL, new String[]{"roberto@hemobile.com.br"});		  
		startActivity(Intent.createChooser(email, "Send Email"));
		email.putExtra(Intent.EXTRA_SUBJECT, "MiniGames - Lista de Contatos");
		email.putExtra(Intent.EXTRA_TEXT, "Segue Lista");
		email.setType("message/rfc822");
		*/
	}

}
