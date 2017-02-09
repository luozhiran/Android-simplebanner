package com.luo.android.simplebanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.luo.simplebanner.SBContainLayout;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };


    private SBContainLayout sbContainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sbContainLayout = (SBContainLayout) findViewById(R.id.sb);
        sbContainLayout.setCanLoop(true);
        sbContainLayout.setPager(new BannerView(), Arrays.asList(images));

    }

    @Override
    protected void onResume() {
        super.onResume();
        sbContainLayout.startLoop(5000);
    }
}
