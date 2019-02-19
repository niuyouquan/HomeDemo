package com.nyq.homedemo.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nyq.homedemo.DividerItemDecoration;
import com.nyq.homedemo.HealthInfo;
import com.nyq.homedemo.MLImageView;
import com.nyq.homedemo.R;
import com.nyq.homedemo.StringHelper;
import com.nyq.homedemo.TimeHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author 健康资讯fragment
 * @time 2016-07-18 16:49
 */

// JUMP 
@SuppressLint("ValidFragment")
public class JkzxFragment extends Fragment {
    RecyclerView mRecyclerView;
    private static int pageSize = 10;
    private int pageNum = 1;
    JkzxAdapter mAdapter;

    private List<HealthInfo> healthInfos;
    private List<HealthInfo> healthInfosMore = new ArrayList<>();

    public static JkzxFragment newInstance() {
        JkzxFragment tripFragment = new JkzxFragment();
        return tripFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        healthInfosMore.clear();
        getInfoList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jkzx, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new JkzxAdapter(getActivity(), R.layout.item_listview_jkzx, healthInfos);
//        //打开或关闭加载
        mAdapter.setEnableLoadMore(true);
        mAdapter.openLoadAnimation();
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (mAdapter.getData().size() > 10) {
                    Toast.makeText(getContext(), "数据全部加载完毕", Toast.LENGTH_SHORT).show();
                    mAdapter.setEnableLoadMore(false);
                } else {
                    getInfoList();
                    mAdapter.addData(healthInfos);
                    mAdapter.setEnableLoadMore(true);
                    mAdapter.loadMoreComplete();
                }
            }
        }, mRecyclerView);
        //设置adapter
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.replaceData(healthInfos);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getContext(),"Activity沉浸式",Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }

    //从网络获取数据
    private void getInfoList() {
        healthInfos = new ArrayList<>();
        healthInfos.add(new HealthInfo("Activity沉浸式", new Date().toString(),"Activity内容入嵌沉浸式","https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1607779262,1632598626&fm=26&gp=0.jpg"));
        healthInfos.add(new HealthInfo("默认主题", new Date().toString(),"Activity内容入嵌沉浸式","https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4017085655,264997454&fm=26&gp=0.jpg"));
        healthInfos.add(new HealthInfo("橙色主题", new Date().toString(),"Activity内容入嵌沉浸式","https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3182596561,138363622&fm=26&gp=0.jpg"));
        healthInfos.add(new HealthInfo("红色主题", new Date().toString(),"Activity内容入嵌沉浸式","https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=659044909,4042106041&fm=26&gp=0.jpg"));
        healthInfos.add(new HealthInfo("绿色主题", new Date().toString(),"Activity内容入嵌沉浸式","https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4111176407,2620705746&fm=26&gp=0.jpg"));
        healthInfos.add(new HealthInfo("蓝色主题", new Date().toString(),"Activity内容入嵌沉浸式","https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2739505509,237691169&fm=26&gp=0.jpg"));
        healthInfosMore.addAll(healthInfos);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    /**
     * adapter
     */
    class JkzxAdapter extends BaseQuickAdapter<HealthInfo, BaseViewHolder> {
        Context context;
        String code;

        public JkzxAdapter(Context context, int layoutId, List<HealthInfo> str) {
            super(layoutId, str);
            this.context = context;
        }

        @Override
        public void convert(BaseViewHolder viewHolder, HealthInfo healthInfo) {
            viewHolder.setText(R.id.item_tv1, healthInfo.getTitle());
            viewHolder.setText(R.id.msg_time, TimeHelper.formatStringToDate(new Date().toString(), "yyyy-M-d"));
            viewHolder.setText(R.id.source, StringHelper.isBlank(healthInfo.getSource()) ? "未知" : healthInfo.getSource());
            Glide.with(mContext).load(healthInfo.getImg()).into((MLImageView) viewHolder.getView(R.id.item_image));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
