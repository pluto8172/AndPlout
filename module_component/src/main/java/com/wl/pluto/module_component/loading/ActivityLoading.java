package com.wl.pluto.module_component.loading;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wl.pluto.module_component.R;
import com.wl.pluto.module_component.base.BaseActivity;
import com.wl.pluto.module_component.view.RxTitle;

/**
 * @author vondear
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class ActivityLoading extends BaseActivity {


    RxTitle mRxTitle;
    TabLayout mTabs;
    ViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        mRxTitle = findViewById(R.id.rx_title);
        mRxTitle.setLeftFinish(mContext);

        mTabs = findViewById(R.id.tabs);
        mViewpager = findViewById(R.id.viewpager);
        mViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            String[] titles = new String[]{"加载的方式", "加载的例子"};

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return FragmentLoadingWay.newInstance();
                } else {
                    return FragmentLoadingDemo.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        mTabs.setupWithViewPager(mViewpager);
    }
}
