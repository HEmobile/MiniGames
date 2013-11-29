package br.com.hemobile.minigames.adapters;

import java.util.Collections;
import java.util.List;

import br.com.hemobile.minigames.MemoGameHelper;
import br.com.hemobile.minigames.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class CardAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater layoutInflater;
	private int numberOfCards;
	private Integer[] cards;

    public CardAdapter(Context c, int l) {
        mContext = c;
        numberOfCards = MemoGameHelper.numberOfCardsPair(l);
        layoutInflater = LayoutInflater.from(mContext);
    }

    public int getCount() {
        return numberOfCards*2;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
    
    // references to our images
    private Integer[] differentCards = {
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7
    };
    
    private Integer[] getCards() {
    	if (cards == null) {
    		cards = new Integer[numberOfCards*2];
    		// Add Cards
    		for (int i=0; i < numberOfCards; i++) {
    			cards[i] = differentCards[i];
    			cards[i+numberOfCards] = differentCards[i];
    		}
    		
    		// Shuffle
    		List<Integer> cardsList = java.util.Arrays.asList(cards);
    		Collections.shuffle(cardsList);
    		cards = (Integer[])cardsList.toArray();
    	} 
    	
    	return cards;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
    	ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.card, parent, false);
            holder = new ViewHolder();
            //holder.viewFlipper = (ViewFlipper) convertView.findViewById(R.id.flipper); 
            holder.frontImage = (ImageView) convertView.findViewById(R.id.frontImage);
            holder.backImage = (ImageView) convertView.findViewById(R.id.backImage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        

        holder.frontImage.setImageResource((getCards()[position]));
        //holder.backImage.setImageResource(R.drawable.card);
        convertView.setTag(R.id.frontImage, getCards()[position]);
        //convertView.setTag(getCards()[position]);
        return convertView;
    }
    
    static class ViewHolder {
        ImageView frontImage;
        ImageView backImage;
        ViewFlipper viewFlipper;
    }

    /*
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
    	
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setBackgroundResource((getCards()[position]));
        imageView.setImageResource(R.drawable.card);
        imageView.setTag(getCards()[position]);
        return imageView;
    } 
    */
}
