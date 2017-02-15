package com.luo.simplebanner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.luo.simplebanner.controller.ObserveScrollState;
import com.luo.simplebanner.listener.OnitemClickListener;
import com.luo.simplebanner.pageradapter.SBPageAdapter;

/**
 * Created by LawnLuo on 2/8/17.
 */

public class SBViewpager extends ViewPager {

    private SBPageAdapter sbPageAdapter;
    private OnitemClickListener onItemClickListener;
    final float sen = 5;
    float oldx = 0;
    float newx = 0;
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

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (onItemClickListener!=null) {
            int action = ev.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                oldx = ev.getX();
            } else if (action == MotionEvent.ACTION_UP) {
                newx = ev.getX();
                if (Math.abs(oldx - newx) < sen) {
                    onItemClickListener.onItemClick(sbPageAdapter.toRealPosition(getCurrentItem()));
                }
            }
        }
        return super.onTouchEvent(ev);
    }

    public void setOnItemClickListener(OnitemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
