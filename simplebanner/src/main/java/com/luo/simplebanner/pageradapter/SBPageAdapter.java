package com.luo.simplebanner.pageradapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.luo.simplebanner.SBViewpager;
import com.luo.simplebanner.controller.ObserveScrollState;
import com.luo.simplebanner.interfaces.Holder;

import java.util.List;

/**
 * Created by LawnLuo on 2/8/17.
 */

public class SBPageAdapter extends PagerAdapter {
    public static final int MULTIPLE_COUNT = 300;
    private List data;
    private Holder holder;
    private boolean isCanloop;
    private ObserveScrollState observeScrollState;
    public SBPageAdapter(List data,Holder holder) {
        this.data = data;
        this.holder = holder;
    }

    @Override
    public int getCount() {
        return isCanloop?MULTIPLE_COUNT*getRealCount():getRealCount();
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
        if (observeScrollState!=null){
            observeScrollState.finishUpdate();
        }
    }


    private int toRealPosition(int position){
        if (getRealCount()==0){
            return 0;
        }
        return position%getRealCount();

    }

    public int getRealCount(){
        if (data!=null){
            return data.size();
        }
        return 0;
    }


    public void setObserveScrollState(ObserveScrollState observeScrollState) {
        this.observeScrollState = observeScrollState;
    }


    public int getFirstItem(){
        return isCanloop?getRealCount():0;
    }

    public int getLastItem(){
        return getRealCount()-1;
    }

    public boolean isCanloop() {
        return isCanloop;
    }

    public void setCanloop(boolean canloop) {
        isCanloop = canloop;
    }
}
