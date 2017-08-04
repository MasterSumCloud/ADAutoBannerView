package com.autobanner.peng.adautobannerview;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/8/4 16:26
 *
 * @author pengyan
 * @Des
 */

public class AdAutoBannerView extends RelativeLayout {

    private Context mContext;
    private int mBottomMargin = 10;
    private int mLeftMargin   = 20;
    private int pointWith     = 24;
    private int pointHigh     = 24;
    private ArrayList<String> mImages;
    private PagerAdapter      mPagerAdapter;
    private LinearLayout      mLinearLayout;


    public AdAutoBannerView(Context context) {
        super(context, null);
        Log.d("AdAutoBannerView", "11111111113");
        this.mContext = context;
        init();
    }

    public AdAutoBannerView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        Log.d("AdAutoBannerView", "11111111112");
        this.mContext = context;
        init();
    }


    public AdAutoBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("AdAutoBannerView", "1111111111");
        this.mContext = context;
        init();
    }

    private void prepareViewpager() {
        ViewPager viewPager = new ViewPager(mContext);
        viewPager.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mPagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return mImages.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(mContext);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(40,40));
                imageView.setBackgroundColor(Color.parseColor(mImages.get(position)));
                container.addView(imageView);
                return imageView;
            }
        };
        viewPager.setAdapter(mPagerAdapter);
        this.setGravity(Gravity.BOTTOM);
        this.addView(viewPager);
    }


    public void init() {
        if (mImages == null) {
            mImages = new ArrayList<>();
        }
        prepareViewpager();
        setPoint();
    }


    private void setPoint() {
        mLinearLayout = new LinearLayout(mContext);
        mLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mLinearLayout.setGravity(Gravity.CENTER);

        addPoint();
    }

    private void addPoint() {
        mLinearLayout.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pointWith, pointHigh);
        ImageView point = new ImageView(mContext);
        params.bottomMargin = mBottomMargin;
        for (int i = 0; i < mImages.size(); i++) {
            if (i != 0) {
                params.leftMargin = mLeftMargin;
                point.setEnabled(false);
            }
            point.setLayoutParams(params);
            point.setBackgroundResource(R.drawable.rd_point_isselect);
            ViewGroup parent = (ViewGroup) point.getParent();
            if (parent != null) {
                parent.removeAllViews();
            }
            mLinearLayout.addView(point);
        }
        ViewGroup parent = (ViewGroup) mLinearLayout.getParent();
        if (parent != null) {
            parent.removeAllViews();
        }
        this.addView(mLinearLayout);
    }

    public void setData(List<String> imageList) {
        if (mImages == null) {
            mImages = new ArrayList<>();
        }
        if (imageList != null) {
            mImages.addAll(imageList);
        }
        addPoint();
        mPagerAdapter.notifyDataSetChanged();
    }
}
