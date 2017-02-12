package com.luo.simplebanner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.luo.simplebanner.controller.ObserveScrollState;
import com.luo.simplebanner.pageradapter.SBPageAdapter;

/**
 * Created by LawnLuo on 2/8/17.
 */

public class SBViewpager extends ViewPager {

    private SBPageAdapter sbPageAdapter;
    private ObserveScrollState observeScrollState = new ObserveScrollState() {
        @Override
        public void finishUpdate() {
            int currentPositiion = getCurrentItem();
            if (currentPositiion == 0 ){
                setCurrentItem(sbPageAdapter.getFirstItem(),false);
            }else if (currentPositiion==SBPageAdapter.MULTIPLE_COUNT*sbPageAdapter.getRealCount()-1){
                setCurrentItem(sbPageAdapter.getLastItem(),false);
            }
        }
    };


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
        sbPageAdapter = adapter;
        sbPageAdapter.setObserveScrollState(observeScrollState);
        sbPageAdapter.setCanloop(canloop);
        super.setAdapter(sbPageAdapter);

    }

    public SBPageAdapter getAdapter() {
        return sbPageAdapter;
    }

}
