package com.bw.movie.fragment.xiangqing;
/*
 *@auther:张恩
 *@Date: 2019/11/15
 *@Time:21:24
 *@Description:${DESCRIPTION}
 **/

import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.lizekai.mylibrary.base.BaseFragment;
import com.bawei.lizekai.mylibrary.utils.Logger;
import com.bw.movie.R;
import com.bw.movie.adapter.xiangqing.RecyclerYingPingAdapter;
import com.bw.movie.bean.PingLunBean;
import com.bw.movie.contract.IPingLunContract;
import com.bw.movie.presenter.IPingLunPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Frag_yp extends BaseFragment<IPingLunPresenter> implements IPingLunContract.IPingLunView {


    private static final String TAG = "Frag_yp";
    @BindView(R.id.recycler_yp)
    RecyclerView recyclerYp;

    @Override
    protected IPingLunPresenter providePresenter() {
        return new IPingLunPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_xiangqing_yp;
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getActivity().getIntent();
        String id = intent.getStringExtra("movieId");
        mPresenter.getPingLunPresenter(id,"1","5");
    }

    @Override
    public void onPingLunSuccess(PingLunBean bean) {
        Logger.i(TAG,bean.getMessage());
        if (bean.getMessage().equals("无数据")){
            return;
        }
        List<PingLunBean.ResultBean>list=new ArrayList<>();
        for (int i = 0; i < bean.getResult().size(); i++) {
            list.add(bean.getResult().get(i));
        }
        recyclerYp.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerYp.setAdapter(new RecyclerYingPingAdapter(list,getActivity()));
    }

    @Override
    public void onPingLunFailure(Throwable e) {

    }
}
