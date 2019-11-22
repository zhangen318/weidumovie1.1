package com.bw.movie;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bawei.lizekai.mylibrary.base.BaseActivity;
import com.bw.movie.bean.MovieIdEventBus;
import com.bw.movie.bean.XiangqingBean;
import com.bw.movie.bean.XuanZuoEventBus;
import com.bw.movie.contract.IXiangqingContract;
import com.bw.movie.fragment.xiangqing.Frag_js;
import com.bw.movie.fragment.xiangqing.Frag_jz;
import com.bw.movie.fragment.xiangqing.Frag_yg;
import com.bw.movie.fragment.xiangqing.Frag_yp;
import com.bw.movie.presenter.IXiangqingPresenter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @describe(描述)：XiangqingActivity   详情页
 * @data（日期）: 2019/11/15
 * @time（时间）: 21:02
 * @author（作者）: 张恩
 **/

public class XiangqingActivity extends BaseActivity<IXiangqingPresenter> implements IXiangqingContract.IXiangqingView {


    private static final String TAG = "XiangqingActivity";
    @BindView(R.id.tab_xiangqing)
    TabLayout tabXiangqing;
    @BindView(R.id.viewpager_xiangqing)
    ViewPager viewpagerXiangqing;
    @BindView(R.id.simp_xiangqing)
    SimpleDraweeView simpXiangqing;
    @BindView(R.id.text_xiangqing_name)
    TextView textXiangqingName;
    @BindView(R.id.text_xiangqing_leixing)
    TextView textXiangqingLeixing;
    @BindView(R.id.text_xiangqing_time)
    TextView textXiangqingTime;
    @BindView(R.id.butt_xiangqing_xz)
    Button buttXiangqingXz;
    private String movieId;
    private XiangqingBean bean1;


    @Override
    protected IXiangqingPresenter providePresenter() {
        return new IXiangqingPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_xiangqing;
    }

    @Override
    protected void initView() {
        super.initView();
        Fresco.initialize(this);
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        movieId = intent.getStringExtra("movieId");
        Map<String, Object> map = new HashMap<>();
        map.put("userId", "13798");
        map.put("sessionId", "157356295970613798");
        mPresenter.getXiangqingPresenter(map, movieId);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Frag_js());
        fragmentList.add(new Frag_yg());
        fragmentList.add(new Frag_jz());
        fragmentList.add(new Frag_yp());
        List<String> stringList = new ArrayList<>();
        stringList.add("介绍");
        stringList.add("预告");
        stringList.add("剧照");
        stringList.add("影评");
        viewpagerXiangqing.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return stringList.get(position);
            }
        });
        tabXiangqing.setupWithViewPager(viewpagerXiangqing);
        viewpagerXiangqing.setOffscreenPageLimit(4);


    }

    @Override
    public void onXiangqingSuccess(XiangqingBean bean) {
        bean1 = bean;
        textXiangqingName.setText(bean.getResult().getName());
        simpXiangqing.setImageURI(bean.getResult().getImageUrl());
        textXiangqingLeixing.setText(bean.getResult().getMovieType());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//转成的时间的格式
        String sd = sdf.format(new Date(bean.getResult().getReleaseTime())); // 时间戳转换成时间
        textXiangqingTime.setText(sd);

    }

    @Override
    public void onXiangqingFailure(Throwable e) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick(R.id.butt_xiangqing_xz)
    public void onClick() {
        EventBus.getDefault().postSticky(new MovieIdEventBus(movieId));
        EventBus.getDefault().postSticky(new XuanZuoEventBus(bean1.getResult().getName(),bean1.getResult().getShortFilmList().get(0).getVideoUrl(),bean1.getResult().getMovieId()+""));
        startActivity(new Intent(this,GouPiaoYingYuanActivity.class));
    }
}
