package com.luo.android.simplebanner;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.luo.simplebanner.interfaces.Holder;

import java.util.List;

/**
 * Created by LawnLuo on 2/8/17.
 */

public class BannerView implements Holder<String> {
    @Override
    public View createView(Context context) {
        ImageView imageView = new ImageView(context);
        return imageView;
    }

    @Override
    public void undateUI(View view, int position, String data) {
        ((ImageView)(view)).setImageResource(R.mipmap.ic_launcher);
    }

}
