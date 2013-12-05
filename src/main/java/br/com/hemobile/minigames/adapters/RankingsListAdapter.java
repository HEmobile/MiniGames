package br.com.hemobile.minigames.adapters;

import java.util.ArrayList;

import br.com.hemobile.minigames.R;
import br.com.hemobile.minigames.model.GameResult;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RankingsListAdapter extends BaseAdapter {

	private ArrayList<GameResult> listData;
	private LayoutInflater layoutInflater;
	private int oddRowColor;
	private int evenRowColor;
    
	public RankingsListAdapter(ArrayList<GameResult> listData, Context context) {
		this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
        
        oddRowColor = context.getResources().getColor(R.color.OddRowGrey);
        evenRowColor = context.getResources().getColor(R.color.EvenRowGrey);
	}

	@Override
    public int getCount() {
        return listData.size();
    }
 
    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.rankings_row, parent, false);
            holder = new ViewHolder();
            holder.username = (TextView) convertView.findViewById(R.id.username); 
            holder.points = (TextView) convertView.findViewById(R.id.points);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        holder.username.setText(listData.get(position).getPlayerName());
        holder.points.setText(listData.get(position).getResult()+"");
        
        if (position%2==1) {
        	convertView.setBackgroundColor(oddRowColor);
        } else {
        	convertView.setBackgroundColor(evenRowColor);
        }
 
        return convertView;
    }
 
    static class ViewHolder {
        TextView username;
        TextView points;
    }

}
