package com.luo.simplebanner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.luo.simplebanner.controller.SBViewpagerScroller;
import com.luo.simplebanner.interfaces.Holder;
import com.luo.simplebanner.interfaces.Indicate;
import com.luo.simplebanner.listener.OnitemClickListener;
import com.luo.simplebanner.listener.SBOnChangePagerListener;
import com.luo.simplebanner.pageradapter.SBPageAdapter;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by LawnLuo on 2/8/17.
 */

public class SBContainLayout extends LinearLayout {
    private SBPageAdapter sbPageAdapter;
    private boolean canLoop;
    private boolean isTurning;
    private SwitchRunnable switchRunnable;
    private static  int LOOP_TIME;
    public final static int ALIGN_LEFT = 0;
    public final static int ALIGN_RIGHT =1;
    public final static int ALIGN_CENTER = 2;
    private boolean fingerAway ;

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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.banner_main_view,null,false);
        viewpagerContainer = (RelativeLayout) view.findViewById(R.id.viewpagerContainer);
        viewPager = (SBViewpager) view.findViewById(R.id.viewpager);
        indicatorContainer = (LinearLayout) view.findViewById(R.id.indicatorContainer);
        initViewPagerScroller();
        this.addView(view);
    }

    private void initViewPagerScroller(){
        SBViewpagerScroller scroller = new SBViewpagerScroller(getContext());
        try {
            Field fieldScroller = ViewPager.class.getDeclaredField("mScroller");
            fieldScroller.setAccessible(true);
            fieldScroller.set(viewPager,scroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    public SBContainLayout setPager(Holder holder,List data){
        sbPageAdapter = new SBPageAdapter(data,holder);
        viewPager.setAdapter(sbPageAdapter,canLoop);

        switchRunnable = new SwitchRunnable(this);
        return this;
    }

    public SBContainLayout setIndicator(Indicate indicator){
            indicator.createView(getContext(), sbPageAdapter.getRealCount());
            joinIndicateToContainer(indicator);
            SBOnChangePagerListener sbOnChangePagerListener = new SBOnChangePagerListener(indicator);
            viewPager.addOnPageChangeListener(sbOnChangePagerListener);


        return this;
    }


    public void setContentAlign(int align){
       RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) indicatorContainer.getLayoutParams();
        if (align == ALIGN_CENTER){
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        }else if (align == ALIGN_LEFT){
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.TRUE);

        }else if (align == ALIGN_RIGHT){
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);

        }
    }
    public void joinIndicateToContainer(Indicate indicate){
        indicatorContainer.removeAllViews();
        for (int i=0;i<indicate.getCount();i++){
            indicatorContainer.addView(indicate.getViews().get(i));
        }

        Log.d("ddddd"," == "+sbPageAdapter.getFirstItem());
        viewPager.setCurrentItem(sbPageAdapter.getFirstItem(),false);


    }

    public void setCanLoop(boolean canLoop) {
        this.canLoop = canLoop;
    }


    public void startLoop(int delayTime){
        if (isTurning)stopLoop();
        isTurning = true;
        LOOP_TIME=delayTime;
        postDelayed(switchRunnable,delayTime);
    }

    public void stopLoop(){
            isTurning = false;
            removeCallbacks(switchRunnable);
    }


    static class SwitchRunnable implements Runnable{
        private final WeakReference<SBContainLayout> reference ;
        public SwitchRunnable(SBContainLayout sbContainLayout) {
            this.reference = new WeakReference<SBContainLayout>(sbContainLayout);
        }
        @Override
        public void run() {
            if (reference.get()!=null&&reference.get().isTurning) {
                if (reference != null && reference.get() != null && reference.get().viewPager != null) {
                    reference.get().viewPager.setCurrentItem(reference.get().viewPager.getCurrentItem() + 1);
                    reference.get().postDelayed(reference.get().switchRunnable, LOOP_TIME);
                }
            }
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_UP||MotionEvent.ACTION_CANCEL==action||MotionEvent.ACTION_OUTSIDE==action){
            if (isTurning)startLoop(LOOP_TIME);

        }else if (MotionEvent.ACTION_DOWN == action){
            if (!isTurning)stopLoop();
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setOnitemClickListener(OnitemClickListener onitemClickListener) {
        if (onitemClickListener==null){
            viewPager.setOnItemClickListener(null);
        }else {
            viewPager.setOnItemClickListener(onitemClickListener);
        }

    }
}
