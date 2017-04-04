package com.xsp.library.activity;

import android.support.v4.app.FragmentActivity;

import com.xsp.library.util.LogUtil;

public class BaseActivity extends FragmentActivity {
    private String mActivityName = this.getClass().getSimpleName();

    protected void setClassName(String name) {
        mActivityName = name;
    }


    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d("show : " + mActivityName);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d("hide : " + mActivityName);
    }

}
