package com.autobanner.peng.adautobannerview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created on 2017/8/4 16:26
 *
 * @author pengyan
 * @Des 一款非常简单的自动滚动布局
 */

public class AdAutoBannerView extends RelativeLayout {

    private Context mContext;
    private int mBottomMargin = 10;
    private int mLeftMargin = 20;
    private int pointWith = 24;
    private LinearLayout mLinearLayout;
    private ViewPager mViewPager;


    public AdAutoBannerView(Context context) {
        super(context, null);
        this.mContext = context;
        init();
    }

    public AdAutoBannerView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        this.mContext = context;
        init();
    }


    public AdAutoBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void prepareViewpager() {
        mViewPager = new ViewPager(mContext);
        mViewPager.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.addView(mViewPager);
    }


    private void init() {
        prepareViewpager();
    }

    /**
     * 准备点的包裹布局
     *
     * @param count 点的数量
     */
    private void prepareLlPoint(int count) {
        mLinearLayout = new LinearLayout(mContext);
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mLinearLayout.setLayoutParams(layoutParams);
        mLinearLayout.setGravity(Gravity.CENTER);
        addPoint(count);
    }

    /**
     * 设置点
     */
    private void addPoint(int count) {
        mLinearLayout.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < count; i++) {
            ImageView point = new ImageView(mContext);
            params.width = pointWith;
            params.height = pointWith;
            if (i != 0) {
                params.leftMargin = mLeftMargin;
                point.setEnabled(false);
            }
            point.setLayoutParams(params);
            point.setBackgroundResource(R.drawable.rd_point_isselect);
            mLinearLayout.addView(point);
        }
        setMargins(mLinearLayout, 0, 0, 0, mBottomMargin);
        this.removeAllViews();
        this.addView(mLinearLayout);
    }

    /**
     * 设置数据
     */
    public <T extends PagerAdapter> void setData(T adapter) {
        mViewPager.setAdapter(adapter);
        prepareLlPoint(adapter.getCount());
        adapter.notifyDataSetChanged();
    }

    private static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    public void setPointWith(int newPintwith) {
        if (newPintwith < 0) {
            return;
        }
        this.pointWith = newPintwith;
    }

    public void setBottomMargin(int newBottomMargin) {
        this.mBottomMargin = newBottomMargin;
    }

    public void setLayoutPointMargin(int newPointMargin) {
        this.mLeftMargin = newPointMargin;

    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
}
