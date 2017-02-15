package com.luo.simplebanner.listener;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.luo.simplebanner.interfaces.Indicate;

import java.util.List;

/**
 * Created by LawnLuo on 2/15/17.
 */

public class SBOnChangePagerListener implements ViewPager.OnPageChangeListener {

    private Indicate indicate;

    public SBOnChangePagerListener(Indicate indicate) {
        this.indicate = indicate;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (indicate!=null){
            indicate.changeSelectedStatus(position);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
