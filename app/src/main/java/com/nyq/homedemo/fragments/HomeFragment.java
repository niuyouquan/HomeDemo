package com.nyq.homedemo.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nyq.homedemo.AppAdapter;
import com.nyq.homedemo.AppInfo;
import com.nyq.homedemo.FixedGridView;
import com.nyq.homedemo.InfoColumn;
import com.nyq.homedemo.R;
import com.nyq.homedemo.TopLine;
import com.nyq.homedemo.UPMarqueeView;
import com.nyq.homedemo.ViewPagerFragmentAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 首页，嵌套滑动
 * @author niuyq
 */
public class HomeFragment extends Fragment {
    @BindView(R.id.iv_topline)
    ImageView ivTopline;
    @BindView(R.id.up_line)
    TextView upLine;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.area_layout)
    LinearLayout areaLayout;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.search_ll)
    LinearLayout searchLl;
    @BindView(R.id.iv_qrcode)
    ImageView ivQrcode;
    @BindView(R.id.iv_tongzhi)
    ImageView ivTongzhi;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.tl_main_tabtop)
    TabLayout tlMainTabtop;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.nsv)
    NestedScrollView nsv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    @BindView(R.id.banner)
    ImageView banner;
    @BindView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    @BindView(R.id.fixedGridView)
    FixedGridView fixedGridView;
    @BindView(R.id.upview1)
    UPMarqueeView upview1;

    private View view;
    private AppAdapter appAdapter;
    private List<AppInfo> appList;

    private TagAdapter hotDeptAdapter;
    private List<TopLine> topLineList = new ArrayList<>();
    List<View> views = new ArrayList<>();

    private List<InfoColumn> infoColumnList;
    private List<Fragment> fragmentList;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        } else {
            view = inflater.inflate(R.layout.home_fra, container, false);
            unbinder = ButterKnife.bind(this, view);
            initView(inflater);
            getFragmentData();
        }
        return view;
    }

    public void initView(LayoutInflater inflater) {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getFragmentData();
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }

            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }
        });

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            //verticalOffset是当前appbarLayout的高度与最开始appbarlayout高度的差，向上滑动的话是负数
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //Y轴偏移量
                float scrollY = Math.abs(verticalOffset);
                //变化率 Toolbar与header高度的差值
                float headerBarOffsetY = appBarLayout.getHeight() - toolbar.getHeight();
                float offset = 1 - Math.max((headerBarOffsetY - scrollY) / headerBarOffsetY, 0f);
                //Toolbar背景色透明度
                toolbar.setBackgroundColor(Color.argb((int) (offset * 255), 63, 185, 255));
            }
        });
        //设置还没收缩时状态下字体颜色
        collapsingToolbar.setExpandedTitleColor(Color.parseColor("#00ffffff"));
        //设置收缩后Toolbar上字体的
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);

        appAdapter = new AppAdapter(getActivity(), getGridData());
        fixedGridView.setAdapter(appAdapter);

        ivTopline.setOnClickListener(view12 -> {
            Toast.makeText(getContext(),"头条",Toast.LENGTH_SHORT).show();
        });

        Map<String,String> map = new HashMap<>();
        List<Map<String, String>> hotDeptList = new ArrayList<>();
        map.put("CLASS_NAME","菜单");
        hotDeptList.add(map);
        hotDeptList.add(map);
        hotDeptList.add(map);
        hotDeptList.add(map);
        hotDeptList.add(map);
        hotDeptList.add(map);
        hotDeptList.add(map);

        hotDeptAdapter = new TagAdapter<Map<String, String>>(hotDeptList) {
            @Override
            public View getView(FlowLayout parent, int position, Map<String, String> s) {
                TextView tv = (TextView) inflater.inflate(R.layout.tag_hot_dept_tv, idFlowlayout, false);
                tv.setText(s.get("CLASS_NAME"));
                return tv;
            }
        };
        idFlowlayout.setAdapter(hotDeptAdapter);

        idFlowlayout.setOnTagClickListener((view1, position, parent) -> {
            Map<String, String> map1 = hotDeptList.get(position);
            Toast.makeText(getContext(),map1.get("CLASS_NAME"),Toast.LENGTH_SHORT).show();
            return true;
        });

        TopLine topLine1 = new TopLine();
        topLine1.setTopLineName("假如滚动的是三条或者一条");
        TopLine topLine2 = new TopLine();
        topLine2.setTopLineName("和这个方法稍微改改就可以了");
        topLineList.add(topLine1);
        topLineList.add(topLine2);
        /**
         * 初始化需要循环的View
         * 为了灵活的使用滚动的View，所以把滚动的内容让用户自定义
         * 假如滚动的是三条或者一条，或者是其他，只需要把对应的布局，和这个方法稍微改改就可以了，
         */
        for (int i = 0; i < topLineList.size(); i = i + 2) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_top_line_view, null);
            //初始化布局的控件
            TextView tv1 = (TextView) moreView.findViewById(R.id.tv1);
            TextView tv2 = (TextView) moreView.findViewById(R.id.tv2);

            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl).setOnClickListener(view1 -> {
                Toast.makeText(getContext(),topLineList.get(position).getTopLineName(),Toast.LENGTH_SHORT).show();
            });
            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl2).setOnClickListener(view12 -> {
                Toast.makeText(getContext(),topLineList.get(position + 1).getTopLineName(),Toast.LENGTH_SHORT).show();
            });
            //进行对控件赋值
            tv1.setText(topLineList.get(position).getTopLineName());
            if (topLineList.size() > i + 1) {
                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
                tv2.setText(topLineList.get(i + 1).getTopLineName());
            } else {
                moreView.findViewById(R.id.rl2).setVisibility(View.GONE);
            }
            //添加到循环滚动数组里面去
            views.add(moreView);
        }
        upview1.setViews(views);
    }

    public void getFragmentData() {
        infoColumnList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            InfoColumn infoColumn = new InfoColumn();
            infoColumn.setColumnCode("" + i);
            infoColumn.setColumnName("菜单" + i);
            infoColumn.setColumnType("0" + i);
            infoColumnList.add(infoColumn);
        }

        fragmentList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            fragmentList.add(JkzxFragment.newInstance());
        }
        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager());
        viewpager.setAdapter(viewPagerFragmentAdapter);
        tlMainTabtop.setupWithViewPager(viewpager);
        // 更新适配器数据
        viewPagerFragmentAdapter.setList(infoColumnList);
        viewPagerFragmentAdapter.setListData(fragmentList);
//        app:tabMode="fixed"
        tlMainTabtop.setTabMode(TabLayout.MODE_FIXED);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlMainTabtop));
        tlMainTabtop.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public List<AppInfo> getGridData() {
        appList = new ArrayList<>();
        appList.add(new AppInfo("侧滑菜单", R.mipmap.image_practice_repast_1));
        appList.add(new AppInfo("二维码扫描", R.mipmap.image_practice_repast_2));
        appList.add(new AppInfo("百度地图", R.mipmap.image_practice_repast_3));
        appList.add(new AppInfo("优雅的Adapter", R.mipmap.image_practice_repast_4));
        appList.add(new AppInfo("但家香酥鸭", R.mipmap.image_practice_repast_1));
        appList.add(new AppInfo("香菇蒸鸟蛋", R.mipmap.image_practice_repast_2));
        appList.add(new AppInfo("花溪牛肉粉", R.mipmap.image_practice_repast_3));
        appList.add(new AppInfo("破酥包", R.mipmap.image_practice_repast_4));
        appList.add(new AppInfo("但家香酥鸭", R.mipmap.image_practice_repast_1));
        return appList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
