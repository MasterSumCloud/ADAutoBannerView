package com.autobanner.peng.adautobannerview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AdAutoBannerView viewById = (AdAutoBannerView) findViewById(R.id.aabv);
        final ArrayList<String> strings = new ArrayList<>();
        strings.add("#ff00ff");
        strings.add("#ffffff");
        strings.add("#ffeeff");
        strings.add("#ffee00");
        strings.add("#ffddff");
        strings.add("#00ffff");
        viewById.setData(new PagerAdapter() {
            @Override
            public int getCount() {
                return strings.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(MainActivity.this);
                container.addView(imageView);
                imageView.setBackgroundColor(Color.parseColor(strings.get(position)));
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

    }


}
