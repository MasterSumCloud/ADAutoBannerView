package com.autobanner.peng.adautobannerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("AdAutoBannerView", "..000000000");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("AdAutoBannerView", "..22222222222");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AdAutoBannerView", "..3333333333333");
        AdAutoBannerView viewById = (AdAutoBannerView) findViewById(R.id.aabv);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("#ff00ff");
        strings.add("#ffffff");
        strings.add("#ffeeff");
        strings.add("#ffee00");
        strings.add("#ffddff");
        strings.add("#00ffff");
        viewById.setData(strings);

    }
}
