package com.luo.simplebanner.controller;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by LawnLuo on 2/10/17.
 */

public class SBViewpagerScroller extends Scroller {
    private int scrollTime = 800;
    public SBViewpagerScroller(Context context) {
        super(context);
    }

    public SBViewpagerScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, scrollTime);
    }

    public int getScrollTime() {
        return scrollTime;
    }

    public void setScrollTime(int scrollTime) {
        this.scrollTime = scrollTime;
    }
}
