package com.luo.simplebanner.defaultview;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.luo.simplebanner.R;
import com.luo.simplebanner.interfaces.Indicate;

import java.util.ArrayList;

/**
 * Created by LawnLuo on 2/15/17.
 */

public class PointIndicateView extends Indicate {

    public PointIndicateView() {
        views = new ArrayList<>();
    }

    @Override
    public void changeSelectedStatus(int position) {
        if (views!=null){

            position = getRealPosition(position);
            Log.d("listener:","监听："+position);
            for (int i=0;i<getCount();i++) {
                if (i==position) {
                    ((ImageView) views.get(i)).setImageResource(R.drawable.ic_page_indicator_focused);
                }else {
                    ((ImageView) views.get(i)).setImageResource(R.drawable.ic_page_indicator);
                }
            }
        }
    }

    @Override
    public void createView(Context context,int count) {
        views.clear();
        for (int i=0;i<count;i++){
            ImageView imageView = new ImageView(context);
            imageView.setPadding(10,0,10,0);
            views.add(imageView);
        }
    }

}
