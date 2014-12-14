package com.example.ivan.expenselistnew;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity {

    private ExpenseListAdapter mAdapter;

    public MyActivity() {
        super();
    }

    private class ExpenseItem {
        public String label;
        public String price;
    }
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText label = (EditText)findViewById(R.id.label);
                EditText price = (EditText) findViewById(R.id.price);
                ExpenseItem item = new ExpenseItem();
                item.label = label.getText().toString();
                item.price = price.getText().toString();
                mAdapter.add(item);
            }
        });

        ListView listView = (ListView) findViewById(R.id.list);
        ArrayList<ExpenseItem> items = new ArrayList<ExpenseItem>();

        if (savedInstanceState != null) {
            ArrayList<String> labels = savedInstanceState.getStringArrayList("labels");
            ArrayList<String> prices = savedInstanceState.getStringArrayList("prices");
            for (int i = 0; i < labels.size(); i++) {
                ExpenseItem item = new ExpenseItem();
                item.label = labels.get(i);
                item.price = prices.get(i);
                items.add(item);
            }
        }

        mAdapter = new ExpenseListAdapter(this, items);
        listView.setAdapter(mAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        ArrayList<String> labels = new ArrayList<String>();
        ArrayList<String> prices = new ArrayList<String>();

        List<ExpenseItem> items = mAdapter.getItems();
        for (ExpenseItem item : items) {
            labels.add(item.label);
            prices.add(item.price);
        }

        outState.putStringArrayList("labels", labels);
        outState.putStringArrayList("prices", prices);

        super.onSaveInstanceState(outState);
    }

    public class ExpenseListAdapter extends ArrayAdapter<ExpenseItem> {

        private ArrayList<ExpenseItem> Items;

        public ExpenseListAdapter(Context context, ArrayList<ExpenseItem> items) {
            super(context, 0, items);
            Items = items;
        }
        
        private class ViewHolder {
            TextView label;
            TextView price;
            ImageButton delete;
        }

        public List<ExpenseItem> getItems() {
            return Items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ExpenseItem item = getItem(position);
            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list, parent, false);
                holder = new ViewHolder();
                holder.label = (TextView) convertView.findViewById(R.id.label);
                holder.price = (TextView) convertView.findViewById(R.id.price);
                holder.delete = (ImageButton) convertView.findViewById(R.id.delete);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.label.setText(item.label);
            holder.price.setText(item.price);

            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Delete");
                    builder.setMessage("Do you want to delete it ? ");
                    builder.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mAdapter.remove(item);
                            notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("No", null);
                    builder.show();
                }
            });

            return convertView;
        }
    }
}


