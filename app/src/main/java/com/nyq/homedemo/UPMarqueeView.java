package com.nyq.homedemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import java.util.List;

/**
 * 仿淘宝首页的 淘宝头条滚动的自定义View
 * <p>
 * Created by mengwei on 2016/7/20.
 */
public class UPMarqueeView extends ViewFlipper {

    private boolean isSetAnimDuration = false;
    private int interval = 3000;//修改间隔时间
    /**
     * 动画时间
     */
    private int animDuration = 500;

    /**
     * 点击
     */
    private OnItemClickListener onItemClickListener;


    public UPMarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setFlipInterval(interval);
        Animation animIn = AnimationUtils.loadAnimation(getContext(), R.anim.anim_marquee_in);
        if (isSetAnimDuration) {
            animIn.setDuration(animDuration);
            setInAnimation(animIn);
        }
        Animation animOut = AnimationUtils.loadAnimation(getContext(), R.anim.anim_marquee_out);
        if (isSetAnimDuration) {
            animOut.setDuration(animDuration);
            setOutAnimation(animOut);
        }
    }


    /**
     * 设置循环滚动的View数组
     *
     * @param views
     */
    public void setViews(final List<View> views) {
        if (views == null || views.isEmpty()) {
            return;
        }
        removeAllViews();
        for (int i = 0; i < views.size(); i++) {
            final int position = i;
            //设置监听回调
            views.get(i).setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position, views.get(position));
                }
            });
            addView(views.get(i));
        }
        startFlipping();
    }

    /**
     * 设置监听接口
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * item_view的接口
     */
    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }
}
