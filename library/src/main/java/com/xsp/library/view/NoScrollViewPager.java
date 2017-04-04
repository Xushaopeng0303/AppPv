package com.xsp.library.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class NoScrollViewPager extends ViewPager {
    private boolean mCanScroll = true;

    public NoScrollViewPager(Context context) {
        super(context, null);
    }
    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCanScroll(boolean canScroll) {
        mCanScroll = canScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return mCanScroll && super.onTouchEvent(arg0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return mCanScroll && super.onInterceptTouchEvent(arg0);
    }

}
