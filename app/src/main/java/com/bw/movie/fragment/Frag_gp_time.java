package com.bw.movie.fragment;
/*
 *@auther:张恩
 *@Date: 2019/11/20
 *@Time:14:59
 *@Description:${DESCRIPTION}
 **/

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.lizekai.mylibrary.base.BaseFragment;
import com.bawei.lizekai.mylibrary.utils.Logger;
import com.bw.movie.R;
import com.bw.movie.XuanzuoActivity;
import com.bw.movie.adapter.xiangqing.RecyclerGpTimeAdapter;
import com.bw.movie.bean.IdTimeYingYuanBean;
import com.bw.movie.bean.MovieIdEventBus;
import com.bw.movie.contract.IIdTimeYingYuanContract;
import com.bw.movie.presenter.IIdTimeYingPresenter;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Frag_gp_time extends BaseFragment<IIdTimeYingPresenter> implements IIdTimeYingYuanContract.IIdTimeYingView {


    private static final String TAG = "Frag_gp_time";
    @BindView(R.id.recycler_gp_time)
    RecyclerView recyclerGpTime;

    @Override
    protected IIdTimeYingPresenter providePresenter() {
        return new IIdTimeYingPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_goupiao_idtime;
    }

    @Override
    protected void initView() {
        super.initView();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)//true
    public void onMovieIdEventBus(MovieIdEventBus movieIdEventBus) {
        String movieId = movieIdEventBus.getMovieId();
        Bundle arguments = getArguments();
        String time = arguments.getString("time").toString();
        mPresenter.getIdTimeYingPresenter(movieId,time,"1","9");
    }

    @Override
    public void onIdTimeYingSuccess(IdTimeYingYuanBean bean) {
        if (bean != null) {
            Logger.i(TAG, bean.getMessage());
            if (bean.getMessage().equals("无数据")){
                return;
            }
            List<IdTimeYingYuanBean.ResultBean> list = new ArrayList<>();
            for (int i = 0; i < bean.getResult().size(); i++) {
                list.add(bean.getResult().get(i));
            }
            RecyclerGpTimeAdapter adapter = new RecyclerGpTimeAdapter(list, getActivity());
            recyclerGpTime.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerGpTime.setAdapter(adapter);
            adapter.setClickId(new RecyclerGpTimeAdapter.onClickId() {
                @Override
                public void getClickId(int id) {
                    Intent intent = new Intent(getActivity(), XuanzuoActivity.class);
                    intent.putExtra("id", id + "");
                    getActivity().startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onIdTimeYingFailure(Throwable e) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
