package com.luo.simplebanner.interfaces;

import android.content.Context;
import android.view.View;

import java.util.List;

/**
 * Created by LawnLuo on 2/8/17.
 */

public interface Holder<T> {
    View createView(Context context);
    void undateUI(View view, int position, T data);
}
