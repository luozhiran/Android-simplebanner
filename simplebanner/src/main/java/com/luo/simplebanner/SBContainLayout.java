package com.luo.simplebanner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.luo.simplebanner.interfaces.Holder;
import com.luo.simplebanner.pageradapter.SBPageAdapter;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by LawnLuo on 2/8/17.
 */

public class SBContainLayout extends LinearLayout {
    private SBPageAdapter sbPageAdapter;
    private boolean canLoop;
    private SwitchRunnable switchRunnable;
    private static  int LOOP_TIME;
    /**
     * viewpager容器
     */
    private RelativeLayout viewpagerContainer;
    private SBViewpager viewPager;
    /**
     * 指示器容器
     */
    private LinearLayout indicatorContainer;

    public SBContainLayout(Context context) {
        super(context);
        init();
    }

    public SBContainLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SBContainLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.banner_main_view,this,true);
        viewpagerContainer = (RelativeLayout) view.findViewById(R.id.viewpagerContainer);
        viewPager = (SBViewpager) view.findViewById(R.id.viewpager);
        indicatorContainer = (LinearLayout) view.findViewById(R.id.indicatorContainer);
//        switchRunnable = new SwitchRunnable();
    }

    public void setPager(Holder holder,List data){
        sbPageAdapter = new SBPageAdapter(data,holder);
        viewPager.setAdapter(sbPageAdapter,canLoop);
        switchRunnable = new SwitchRunnable(this);
    }

    public boolean isCanLoop() {
        return canLoop;
    }

    public void setCanLoop(boolean canLoop) {
        this.canLoop = canLoop;
    }


    public void startLoop(int delayTime){
        LOOP_TIME=delayTime;
        postDelayed(switchRunnable,delayTime);
    }

    public void stopLoop(){
        canLoop = false;
        removeCallbacks(switchRunnable);
    }


    static class SwitchRunnable implements Runnable{
        private final WeakReference<SBContainLayout> reference ;
        public SwitchRunnable(SBContainLayout sbContainLayout) {
            this.reference = new WeakReference<SBContainLayout>(sbContainLayout);
        }
        @Override
        public void run() {
            if (reference.get().canLoop) {
                if (reference != null && reference.get() != null && reference.get().viewPager != null) {
                    reference.get().viewPager.setCurrentItem(reference.get().viewPager.getCurrentItem() + 1);
                    reference.get().postDelayed(reference.get().switchRunnable, LOOP_TIME);
                }
            }
        }
    }
}
