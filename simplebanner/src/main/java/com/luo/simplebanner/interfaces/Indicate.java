package com.luo.simplebanner.interfaces;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LawnLuo on 2/15/17.
 */

public abstract class Indicate {
    protected List<View> views;

    public Indicate() {
        views = new ArrayList<>();
    }

    public abstract void changeSelectedStatus(int position);

    public abstract void createView(Context context, int count);

    public int getCount() {
        if (views!=null){
            return views.size();
        }else {
            return 0;
        }
    }

    public List<View> getViews() {
        return views;
    }

    public int getRealPosition(int position){
        return position%getCount();
    }

}
