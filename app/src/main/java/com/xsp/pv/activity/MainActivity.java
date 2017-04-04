package com.xsp.pv.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.xsp.library.activity.BaseActivity;
import com.xsp.library.view.NoScrollViewPager;
import com.xsp.pv.R;
import com.xsp.pv.fragment.CommonFragment;
import com.xsp.pv.fragment.MainFragmentB;
import com.xsp.pv.fragment.MainFragmentC;
import com.xsp.pv.view.ImageTextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private NoScrollViewPager mViewPager;
    private List<Fragment> mTabs = new ArrayList<>();
    private List<ImageTextView> mTabIndicator = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFragment();
        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
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
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setCanScroll(false);
    }

    private void initView() {
        mViewPager = (NoScrollViewPager) findViewById(R.id.id_viewpager);

        ImageTextView one = (ImageTextView) findViewById(R.id.id_indicator_one);
        ImageTextView two = (ImageTextView) findViewById(R.id.id_indicator_two);
        ImageTextView three = (ImageTextView) findViewById(R.id.id_indicator_three);
        ImageTextView four = (ImageTextView) findViewById(R.id.id_indicator_four);

        mTabIndicator.add(one);
        mTabIndicator.add(two);
        mTabIndicator.add(three);
        mTabIndicator.add(four);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);

        one.setIconAlpha(1.0f);        // 设置了默认的第一个颜色
    }

    private void initFragment() {
        // 底部四个Tab为ViewPager + Fragment
//        mTabs.add(CommonFragment.newInstance("Fragment A"));
//        mTabs.add(CommonFragment.newInstance("Fragment B"));
//        mTabs.add(CommonFragment.newInstance("Fragment C"));
//        mTabs.add(CommonFragment.newInstance("Fragment D"));

        // 底部四个Tab为ViewPager + Fragment， TabB 为FragmentTransaction + Fragment
//        mTabs.add(CommonFragment.newInstance("Fragment A"));
//        mTabs.add(MainFragmentB.newInstance("Fragment B"));
//        mTabs.add(CommonFragment.newInstance("Fragment C"));
//        mTabs.add(CommonFragment.newInstance("Fragment D"));

        // 底部四个Tab为ViewPager + Fragment， TabC 为ViewPager + Fragment
//        mTabs.add(CommonFragment.newInstance("Fragment A"));
//        mTabs.add(CommonFragment.newInstance("Fragment B"));
//        mTabs.add(MainFragmentC.newInstance("Fragment C"));
//        mTabs.add(CommonFragment.newInstance("Fragment D"));

        // 混合式测试
        mTabs.add(CommonFragment.newInstance("Fragment A"));
        mTabs.add(MainFragmentB.newInstance("Fragment B"));
        mTabs.add(MainFragmentC.newInstance("Fragment C"));
        mTabs.add(CommonFragment.newInstance("Fragment D"));
    }

    @Override
    public void onClick(View v) {
        resetOtherTabs();

        switch (v.getId()) {
            case R.id.id_indicator_one:
                mTabIndicator.get(0).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.id_indicator_two:
                mTabIndicator.get(1).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.id_indicator_three:
                mTabIndicator.get(2).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.id_indicator_four:
                mTabIndicator.get(3).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(3, false);
                break;
        }

    }

    /**
     * 这个方法是设置每个底部view的颜色为默认颜色
     */
    private void resetOtherTabs() {
        for (int i = 0; i < mTabIndicator.size(); i++) {
            mTabIndicator.get(i).setIconAlpha(0);
        }
    }

}
