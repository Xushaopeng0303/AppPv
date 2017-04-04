package com.xsp.pv.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xsp.library.fragment.BaseFragment;
import com.xsp.pv.R;

public class MainFragmentB extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "tag";
    private CommonFragment mOneFragment;
    private CommonFragment mTwoFragment;
    private CommonFragment mThreeFragment;

    public MainFragmentB() {

    }

    public static MainFragmentB newInstance(String arg){
        MainFragmentB fragment = new MainFragmentB();
        Bundle bundle = new Bundle();
        bundle.putString(TAG, arg);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setClassName(getArguments().getString(TAG));

        View view = inflater.inflate(R.layout.tab_b_fragment, container, false);
        view.findViewById(R.id.id_fragment_b_01).setOnClickListener(this);
        view.findViewById(R.id.id_fragment_b_02).setOnClickListener(this);
        view.findViewById(R.id.id_fragment_b_03).setOnClickListener(this);
        initFragment();
        return view;
    }

    private void initFragment() {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mOneFragment   = CommonFragment.newInstance("Fragment B--A");
        mTwoFragment   = CommonFragment.newInstance("Fragment B--B");
        mThreeFragment = CommonFragment.newInstance("Fragment B--C");
        transaction.replace(R.id.id_b_content, mOneFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        switch (view.getId()) {
            case R.id.id_fragment_b_01:
                if (null == mOneFragment) {
                    mOneFragment = CommonFragment.newInstance("Fragment B--A");
                }
                transaction.replace(R.id.id_b_content, mOneFragment);
                transaction.commit();
                break;
            case R.id.id_fragment_b_02:
                if (null == mTwoFragment) {
                    mTwoFragment = CommonFragment.newInstance("Fragment B--B");
                }
                transaction.replace(R.id.id_b_content, mTwoFragment);
                transaction.commit();
                break;
            case R.id.id_fragment_b_03:
                if (null == mThreeFragment) {
                    mThreeFragment = CommonFragment.newInstance("Fragment B--C");
                }
                transaction.replace(R.id.id_b_content, mThreeFragment);
                transaction.commit();
                break;
            default:
                break;
        }

    }

}
