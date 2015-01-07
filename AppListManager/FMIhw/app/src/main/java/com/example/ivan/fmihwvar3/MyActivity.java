package com.example.ivan.fmihwvar3;

import java.util.ArrayList;
import java.util.List;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

public class MyActivity extends Activity implements OnItemClickListener,TextWatcher {

    PackageManager packageManager;
    ListView listApp;
    EditText search;
    private Adapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        search = (EditText) findViewById(R.id.searchbox);
        EditText e = new EditText(this);
        packageManager = getPackageManager();
        List<PackageInfo> packageList = getPackageManager().getInstalledPackages(PackageManager.GET_PERMISSIONS);

//        adapter = new Adapter(this,packageList,packageManager);

//        e.addTextChangedListener(new CustomTextWatcher(e));
 //       List<PackageInfo> packageList = packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS);
        List<PackageInfo> packageList1 = new ArrayList<PackageInfo>();
        for(PackageInfo p : packageList) {
            boolean isSys = isSystemPackage(p);
            if(!isSys) {
                packageList1.add(p);
            }
        }
        listApp = (ListView) findViewById(R.id.applist);
        adapter = new Adapter(this, packageList1, packageManager);
        listApp.setAdapter(adapter);

        listApp.setOnItemClickListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence text, int start, int before, int count) {
        adapter.getFilter().filter(text, (android.widget.Filter.FilterListener) this);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


private boolean isSystemPackage(PackageInfo pkgInfo) {
    if ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0)
        return true;
    else return false;
}


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long row) {
        PackageInfo packageInfo = (PackageInfo) adapter.getItem(position);

        Intent i;
        try {
            i = packageManager.getLaunchIntentForPackage(packageInfo.packageName);
            if (i == null)
                throw new PackageManager.NameNotFoundException();
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(i);
        } catch (PackageManager.NameNotFoundException e) {

        }
    }


}


