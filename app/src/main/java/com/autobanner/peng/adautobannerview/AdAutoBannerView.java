package com.autobanner.peng.adautobannerview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Timer;

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
    private int delayTiem = 3000;
    private int currentItemPosition = 0;
    private int lastCurrentItemPositon = 0;
    private LinearLayout mLinearLayout;
    private ViewPager mViewPager;
    private Handler mMHandler;
    private Runnable mTimeRun;
    private int mAdapterCount;
    private Timer mTimer;


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
        mMHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                currentItemPosition++;
                mViewPager.setCurrentItem(currentItemPosition);
                //                setOtherPointFalse();
                mMHandler.postDelayed(mTimeRun, delayTiem);
                super.handleMessage(msg);
            }
        };
        prepareViewpager();
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentItemPosition = position;
                setOtherPointFalse();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mTimeRun = new Runnable() {
            @Override
            public void run() {
                mMHandler.sendEmptyMessage(0);
            }
        };
        mViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mMHandler.removeCallbacks(mTimeRun);
                        break;
                    case MotionEvent.ACTION_UP:
                        mMHandler.postDelayed(mTimeRun, delayTiem);
                        break;
                }
                return false;
            }
        });
    }


    public void setOtherPointFalse() {
        for (int i = 0; i < mAdapterCount; i++) {
            if (i != currentItemPosition % mAdapterCount) {
                mLinearLayout.getChildAt(i).setEnabled(false);
            } else {
                mLinearLayout.getChildAt(i).setEnabled(true);
            }
        }
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
        this.addView(mLinearLayout);
    }

    /**
     * 设置数据
     */
    public <T extends PagerAdapter> void setData(final T adapter, int size) {
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(mAdapterCount / 2);
        mAdapterCount = size;
        prepareLlPoint(mAdapterCount);
        adapter.notifyDataSetChanged();
        mMHandler.postDelayed(mTimeRun, delayTiem);
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


    public void cacelTheTiemr() {
        mMHandler.removeCallbacks(mTimeRun);
    }
}
