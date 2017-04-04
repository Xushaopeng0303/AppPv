package com.xsp.library.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.xsp.library.util.FragmentVisibleMgr;
import com.xsp.library.util.LogUtil;

public class BaseFragment extends Fragment implements FragmentVisibleMgr.VisibleCallback {
    private String mActivityName = this.getClass().getSimpleName();

    protected void setClassName(String name) {
        mActivityName = name;
    }

    private FragmentVisibleMgr userVisibleMgr;
    public BaseFragment() {
        userVisibleMgr = new FragmentVisibleMgr(this, this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (null != userVisibleMgr) {
            userVisibleMgr.activityCreated();
        }
    }

    @Override
    public void onResume() {
     super.onResume();
        if (null != userVisibleMgr) {
            userVisibleMgr.resume();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (null != userVisibleMgr) {
            userVisibleMgr.pause();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (null != userVisibleMgr) {
            userVisibleMgr.setUserVisibleHint(isVisibleToUser);
        }
    }

    @Override
    public void setWaitingShowToUser(boolean waitingShowToUser) {
        if (null != userVisibleMgr) {
            userVisibleMgr.setWaitingShowToUser(waitingShowToUser);
        }
    }

    @Override
    public boolean isWaitingShowToUser() {
        return null != userVisibleMgr && userVisibleMgr.isWaitingShowToUser();
    }

    @Override
    public boolean isVisibleToUser() {
        return null != userVisibleMgr && userVisibleMgr.isVisibleToUser();
    }

    @Override
    public void callSuperSetUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onVisibleToUserChanged(boolean isVisibleToUser, boolean invokeInResumeOrPause) {
        if (isVisibleToUser) {
            LogUtil.d("show : " + mActivityName);
        } else {
            LogUtil.d("hide : " + mActivityName);
        }
    }
}
