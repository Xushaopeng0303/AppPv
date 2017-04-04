package com.xsp.pv.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xsp.library.fragment.BaseFragment;
import com.xsp.library.view.NoScrollViewPager;
import com.xsp.pv.R;

import java.util.ArrayList;
import java.util.List;

public class MainFragmentC extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "tag";
    private NoScrollViewPager mViewPager;
    private List<Fragment> mTabs = new ArrayList<>();

    public MainFragmentC() {

    }

    public static MainFragmentC newInstance(String arg){
        MainFragmentC fragment = new MainFragmentC();
        Bundle bundle = new Bundle();
        bundle.putString(TAG, arg);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setClassName(getArguments().getString(TAG));

        View view = inflater.inflate(R.layout.tab_c_fragment, container, false);
        view.findViewById(R.id.id_fragment_c_01).setOnClickListener(this);
        view.findViewById(R.id.id_fragment_c_02).setOnClickListener(this);
        view.findViewById(R.id.id_fragment_c_03).setOnClickListener(this);

        mViewPager = (NoScrollViewPager) view.findViewById(R.id.id_viewpager);
        mTabs.add(CommonFragment.newInstance("Fragment C--A"));
        mTabs.add(CommonFragment.newInstance("Fragment C--B"));
        mTabs.add(CommonFragment.newInstance("Fragment C--C"));
        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mTabs.get(position);
            }
        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCanScroll(true);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_fragment_c_01:
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.id_fragment_c_02:
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.id_fragment_c_03:
                mViewPager.setCurrentItem(2, false);
                break;
        }

    }

}
