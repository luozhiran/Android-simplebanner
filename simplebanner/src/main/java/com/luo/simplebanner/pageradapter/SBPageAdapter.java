package com.luo.simplebanner.pageradapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.luo.simplebanner.SBViewpager;
import com.luo.simplebanner.interfaces.Holder;

import java.util.List;

/**
 * Created by LawnLuo on 2/8/17.
 */

public class SBPageAdapter extends PagerAdapter {
    private List data;
    private Holder holder;
    private boolean canLoop;
    private SBViewpager viewPager;

    private static final int MULTIPLE_COUNT = 300;
    public SBPageAdapter(List data,Holder holder) {
        this.data = data;
        this.holder = holder;
    }

    @Override
    public int getCount() {
        return canLoop?MULTIPLE_COUNT*getRealCount():getRealCount();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position= toRealPosition(position);
        View view = holder.createView(container.getContext());
        container.addView(view);
        if (data!=null&&!data.isEmpty()){
            holder.undateUI(view,position,data.get(position));
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        int currentPositiion = viewPager.getCurrentItem();
        if (currentPositiion == 0 ){
           viewPager.setCurrentItem(getFirstItem(),false);
        }else if (currentPositiion==MULTIPLE_COUNT*data.size()-1){
           viewPager.setCurrentItem(getLastItem(),false);
        }
    }

    private int getFirstItem(){
        return canLoop?data.size():0;
    }

    private int getLastItem(){
        return data.size()-1;
    }

    private int toRealPosition(int position){
        if (getRealCount()==0){
            return 0;
        }
        return position%getRealCount();

    }

    public void setCanLoop(boolean canLoop) {
        this.canLoop = canLoop;
    }

    private int getRealCount(){
        if (data!=null){
            return data.size();
        }
        return 0;
    }

    public void setViewPager(SBViewpager viewPager) {
        this.viewPager = viewPager;
    }
}
