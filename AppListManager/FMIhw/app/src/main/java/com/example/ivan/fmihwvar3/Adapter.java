package com.example.ivan.fmihwvar3;

/**
 * Created by IVAN on 5.12.2014 г..
 */
import java.util.ArrayList;
import java.util.List;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filterable;
import android.widget.TextView;
//import android.widget.Filter;
//import android.widget.Filter.FilterResults;
import android.widget.Filter;



public class Adapter extends BaseAdapter implements Filterable {

    List<PackageInfo> packageList;
    ArrayList<PackageInfo> mFullList;
    Activity context;
    PackageManager packageManager;

    ArrayList<PackageInfo> filteredList;

    public Adapter(Activity context, List<PackageInfo> packageList,
                      PackageManager packageManager) {
        super();
        this.context = context;
        this.packageList = packageList;
        this.filteredList = new ArrayList<PackageInfo>(packageList.size()) ;
        this.packageManager = packageManager;
    }

    @Override
    public Filter getFilter() {
        return new ApplicationFilter();
    }


    private class ViewHolder {
        TextView apkName;
    }

    public int getCount() {
        return packageList.size();
    }

    public Object getItem(int position) {
        return packageList.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }





    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.applist, null);
            holder = new ViewHolder();

            holder.apkName = (TextView) convertView.findViewById(R.id.appname);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PackageInfo packageInfo = (PackageInfo) getItem(position);
        Drawable appIcon = packageManager
                .getApplicationIcon(packageInfo.applicationInfo);
        String appName = packageManager.getApplicationLabel(
                packageInfo.applicationInfo).toString();
        appIcon.setBounds(0, 0, 90, 90);
        holder.apkName.setCompoundDrawables(appIcon, null, null, null);
        holder.apkName.setCompoundDrawablePadding(30);
        holder.apkName.setText(appName);

        return convertView;
    }

    @SuppressLint("DefaultLocale")
    private class ApplicationFilter extends  Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            final ArrayList<PackageInfo> filteredApps = new ArrayList<PackageInfo>(
                            packageList.size());

                for (PackageInfo packageInfo : packageList) {
                        if (packageInfo.packageName.toString().toLowerCase().startsWith(charSequence.toString().toLowerCase()))


                            filteredApps.add(packageInfo);
                    }
                    final FilterResults results = new FilterResults();
                    results.values = filteredApps;
                    results.count = filteredApps.size();
                    return results;
                }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            filteredList = (ArrayList<PackageInfo>) filterResults.values;
            notifyDataSetChanged();
        }


    }



}