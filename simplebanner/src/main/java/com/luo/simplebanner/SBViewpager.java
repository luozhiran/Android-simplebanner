package com.luo.simplebanner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.luo.simplebanner.pageradapter.SBPageAdapter;

/**
 * Created by LawnLuo on 2/8/17.
 */

public class SBViewpager extends ViewPager {


    public SBViewpager(Context context) {
        super(context);
    }

    public SBViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
    }

    public void setAdapter(SBPageAdapter adapter, boolean canloop) {
        adapter.setViewPager(this);
        adapter.setCanLoop(canloop);
        super.setAdapter(adapter);

    }
}
