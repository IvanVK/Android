package com.example.ivan.fmihwvar3;

/**
 * Created by IVAN on 5.12.2014 г..
 */
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.widget.TextView;

public class AppComponents extends Activity {

    TextView appLabel, packageName;
    PackageInfo packageInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appcomponents);
        findViewsById();
        setValues();
    }

    private void findViewsById() {
        appLabel = (TextView) findViewById(R.id.applabel);
        packageName = (TextView) findViewById(R.id.package_name);
    }

    private void setValues() {
        appLabel.setText(getPackageManager().getApplicationLabel(
                packageInfo.applicationInfo));
        packageName.setText(packageInfo.packageName);
    }

}