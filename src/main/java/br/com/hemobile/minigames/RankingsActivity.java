package br.com.hemobile.minigames;

import br.com.hemobile.gui.BaseActivity;
import br.com.hemobile.minigames.adapters.RankingsListAdapter;
import br.com.hemobile.minigames.db.DataLayer;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

import android.widget.ListView;

@EActivity(R.layout.rankings)
public class RankingsActivity extends BaseActivity {

	@ViewById 
	ListView level1Rankings;
	
	public RankingsActivity() {
		// TODO Auto-generated constructor stub
	}

	@AfterViews
	public void init() {
		DataLayer dataLayer = new DataLayer(this);
		dataLayer.addMemoStartData();
		level1Rankings.setAdapter(new RankingsListAdapter(dataLayer.memoGamesList(),this));
	}
}
