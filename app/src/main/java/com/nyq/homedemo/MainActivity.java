package com.nyq.homedemo;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.next.easynavigition.view.EasyNavigitionBar;
import com.nyq.homedemo.fragments.FindFragment;
import com.nyq.homedemo.fragments.HomeFragment;
import com.nyq.homedemo.fragments.MeFragment;
import com.nyq.homedemo.fragments.MsgFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @package: com.nyq.homedemo
 * @author: niuyq
 * @date: 2019/2/18
 * Copyright © 2019 某某某公司. All rights reserved.
 * @description: <功能简述>
 */

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.navigitionBar)
    EasyNavigitionBar navigitionBar;
    private long exitTime;
    private String[] tabText = {"首页", "消息", "发现", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.index, R.mipmap.find, R.mipmap.message, R.mipmap.me};
    //选中时icon
    private int[] selectIcon = {R.mipmap.index1, R.mipmap.find1, R.mipmap.message1, R.mipmap.me1};

    private List<Fragment> fragments = new ArrayList<>();
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(null);
//        //判断SDK版本是否大于等于19，大于就让他显示，小于就要隐藏，不然低版本会多出来一个
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(true);
//        }

        fragments.add(HomeFragment.newInstance());
        fragments.add(MsgFragment.newInstance());
        fragments.add(FindFragment.newInstance());
        fragments.add(MeFragment.newInstance());

        View view = LayoutInflater.from(this).inflate(R.layout.custom_add_view, null);
        navigitionBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .canScroll(true)
                .addAsFragment(false)
                .mode(EasyNavigitionBar.MODE_ADD_VIEW)
                .addCustomView(view)
                .fragmentManager(getSupportFragmentManager())
                .onTabClickListener(new EasyNavigitionBar.OnTabClickListener() {
                    @Override
                    public boolean onTabClickEvent(View view, int position) {
                        return false;
                    }
                })
                .build();
    }

    @TargetApi(19)
    public void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
