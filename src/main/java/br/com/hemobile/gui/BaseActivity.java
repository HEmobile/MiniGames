package br.com.hemobile.gui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.inputmethod.InputMethodManager;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.InstanceState;
import com.googlecode.androidannotations.annotations.OptionsItem;

@EActivity
public abstract class BaseActivity extends Activity {

	boolean portraitMode = false;

	@InstanceState
	protected boolean fromScreenOrientationChange;

	protected boolean firstTime = true;

	@AfterViews
	protected final void baseInit() {
		if (!fromScreenOrientationChange) {
			fromScreenOrientationChange = true;
		} else {
			setFirstTime(false);
		}
		hideKeyboard();
		setupActionBar(getActionBar());
		setTitle(getTitle());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ViewServer.get(this).addWindow(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// ViewServer.get(this).removeWindow(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// ViewServer.get(this).setFocusedWindow(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		// app.trackActivity(this, true); // Add this method.
	}

	@Override
	protected void onStop() {
		super.onStop();
		// app.trackActivity(this, false); // Add this method.
	}

	public void setupActionBar(ActionBar actionBar) {
		try {
			actionBar.setDisplayHomeAsUpEnabled(true);
		} catch (Exception e) {
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		SpannableString s = new SpannableString(" " + title);
		// s.setSpan(new TypefaceSpan(this, "Officina Serif_bold"), 0,
		// s.length(),
		// Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		super.setTitle(s);
	}

	@Override
	public void setTitle(int titleId) {
		setTitle(getString(titleId));
	}

	public boolean isFirstTime() {
		return firstTime;
	}

	public void setFirstTime(boolean firstTime) {
		this.firstTime = firstTime;
	}

	public void hideKeyboard() {
		try {
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		} catch (Exception e) {
		}
	}

	@OptionsItem(android.R.id.home)
	protected void onBackActionBarClick() {
		onBackPressed();
	}

	public void update() {

	}
}