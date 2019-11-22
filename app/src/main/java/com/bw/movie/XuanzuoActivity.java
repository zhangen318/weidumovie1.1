package com.bw.movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.lizekai.mylibrary.base.BaseActivity;
import com.bawei.lizekai.mylibrary.utils.Logger;
import com.bumptech.glide.Glide;
import com.bw.movie.adapter.xiangqing.RecyclerDypqAdapter;
import com.bw.movie.adapter.xiangqing.RecyclerXunZuoAdapter;
import com.bw.movie.bean.DypqBean;
import com.bw.movie.bean.XuanZuoBean;
import com.bw.movie.bean.XuanZuoEventBus;
import com.bw.movie.contract.IXuanZuoContract;
import com.bw.movie.presenter.IXuanZuoPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * @describe(描述)：XuanzuoActivity  选座页面
 * @data（日期）: 2019/11/21
 * @time（时间）: 10:39
 * @author（作者）: 张恩
 **/

public class XuanzuoActivity extends BaseActivity<IXuanZuoPresenter> implements IXuanZuoContract.IXuanZuoView {


    private static final String TAG = "XuanzuoActivity1";
    @BindView(R.id.text_xz_name)
    TextView textXzName;
    @BindView(R.id.jcvideoplayer_xuanzuo)
    JCVideoPlayer jcvideoplayerXuanzuo;
    @BindView(R.id.image_xz_fh)
    ImageView imageXzFh;
    @BindView(R.id.recycler_xz)
    RecyclerView recyclerXz;
    @BindView(R.id.recycler_dypq)
    RecyclerView recyclerDypq;
    @BindView(R.id.butt_xuanzuo_price)
    Button buttXuanzuoPrice;
    private String cinemaId;
    private String movieId;
    private double Fare;

    @Override
    protected IXuanZuoPresenter providePresenter() {
        return new IXuanZuoPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_xuanzuo;
    }

    @Override
    protected void initView() {
        super.initView();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        cinemaId = intent.getStringExtra("id");
        Logger.i(TAG, cinemaId);
        mPresenter.getDypqPresenter(movieId, cinemaId);
        Logger.i(TAG, movieId + "  " + cinemaId);
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onEvent(XuanZuoEventBus evBean) {
        String name = evBean.getName();
        String yugao = evBean.getYugao();
        movieId = evBean.getMovieId();
        textXzName.setText(name);
        Logger.i(TAG, name);
        Logger.i(TAG, yugao);
        jcvideoplayerXuanzuo.setUp(yugao, null);
        Glide.with(this).load(yugao).into(jcvideoplayerXuanzuo.ivThumb);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onXuanZuoSuccess(XuanZuoBean bean) {
        buttXuanzuoPrice.setVisibility(View.GONE);

        String message = bean.getMessage();
        Logger.i(TAG, "onXuanZuoSuccess" + message);
        List<XuanZuoBean.ResultBean> list = new ArrayList<>();
        for (int i = 0; i < bean.getResult().size(); i++) {
            list.add(bean.getResult().get(i));
        }
        RecyclerXunZuoAdapter adapter = new RecyclerXunZuoAdapter(list, XuanzuoActivity.this);
        recyclerXz.setLayoutManager(new GridLayoutManager(XuanzuoActivity.this, 7));
        recyclerXz.setAdapter(adapter);

        adapter.getoncheck(new RecyclerXunZuoAdapter.onCheck() {
            @Override
            public void setCheck() {
                int count = 0;
                for (int i = 0; i < bean.getResult().size(); i++) {
                    if (bean.getResult().get(i).isCheck()) {
                        count++;
                    }
                }
                if (count != 0) {
                    buttXuanzuoPrice.setVisibility(View.VISIBLE);
                    double v = Fare * count;
                    String.format("%.2f",v);
                    buttXuanzuoPrice.setText("￥"+ String.format("%.2f",v));
                }
                if (count==0){
                    buttXuanzuoPrice.setVisibility(View.GONE);

                }
            }
        });

    }

    @Override
    public void onDypqSuccess(DypqBean bean) {
        Logger.i(TAG, "onDypqSuccess" + bean.getMessage());
        List<DypqBean.ResultBean> list = new ArrayList<>();
        for (int i = 0; i < bean.getResult().size(); i++) {
            list.add(bean.getResult().get(i));
        }
        RecyclerDypqAdapter adapter = new RecyclerDypqAdapter(list, XuanzuoActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(XuanzuoActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerDypq.setLayoutManager(linearLayoutManager);
        recyclerDypq.setAdapter(adapter);
        adapter.getClick(new RecyclerDypqAdapter.onClick() {
            @Override
            public void setClick(int id, double fare) {
                mPresenter.getXuanZuoPresenter(id + "");
                Fare = fare;

            }
        });
    }

    @Override
    public void onFailure(Throwable e) {

    }


    @OnClick({R.id.image_xz_fh, R.id.butt_xuanzuo_price})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_xz_fh:
                finish();
                break;
            case R.id.butt_xuanzuo_price:
                break;
        }
    }
}
