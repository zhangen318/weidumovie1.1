package com.bw.movie.fragment.xiangqing;
/*
 *@auther:张恩
 *@Date: 2019/11/15
 *@Time:21:24
 *@Description:${DESCRIPTION}
 **/

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bawei.lizekai.mylibrary.base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.adapter.xiangqing.RecyclerJuzhaoAdapter;
import com.bw.movie.bean.XiangqingBean;
import com.bw.movie.contract.IXiangqingContract;
import com.bw.movie.presenter.IXiangqingPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class Frag_jz extends BaseFragment<IXiangqingPresenter> implements IXiangqingContract.IXiangqingView {


    @BindView(R.id.recycler_jz)
    RecyclerView recyclerJz;

    @Override
    protected IXiangqingPresenter providePresenter() {
        return new IXiangqingPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_xiangqing_jz;
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getActivity().getIntent();
        String movieId = intent.getStringExtra("movieId");
        Map<String, Object> map = new HashMap<>();
        map.put("userId", "13798");
        map.put("sessionId", "157356295970613798");
        mPresenter.getXiangqingPresenter(map, movieId);
    }

    @Override
    public void onXiangqingSuccess(XiangqingBean bean) {
        if (bean!=null) {
            RecyclerJuzhaoAdapter recyclerJuzhaoAdapter = new RecyclerJuzhaoAdapter(bean.getResult().getPosterList(), getActivity());
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
            recyclerJz.setLayoutManager(staggeredGridLayoutManager);
            recyclerJz.setAdapter(recyclerJuzhaoAdapter);
        }
    }

    @Override
    public void onXiangqingFailure(Throwable e) {

    }
}
