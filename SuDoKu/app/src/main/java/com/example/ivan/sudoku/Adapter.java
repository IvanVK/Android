package com.example.ivan.sudoku;

/**
 * Created by IVAN on 2.2.2015 г..
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Context context;
    private List<Users> users;
    public Adapter(Context context, List<Users> users) {
        this.context = context;
        this.users = users;
    }
    @Override
    public int getCount() {
        return users.size();
    }
    @Override
    public Object getItem(int position) {
        return users.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    private static class ViewHolder {
        public TextView nickname;
        public TextView time;
        public TextView rankingPosition;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview, null);
            viewHolder = new ViewHolder();
            viewHolder.nickname = (TextView) view.findViewById(R.id.name);
            viewHolder.time = (TextView) view.findViewById(R.id.scores);
            viewHolder.rankingPosition = (TextView) view.findViewById(R.id.ranking_position);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        Users users = (Users) getItem(position);
        viewHolder.nickname.setText(users.getNickname());
        viewHolder.time.setText(users.getTime() + "");
        viewHolder.rankingPosition.setText(position + 1 + "");
        return view;
    }
}