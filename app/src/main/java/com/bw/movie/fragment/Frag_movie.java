package com.bw.movie.fragment;
/*
 *@auther:张恩
 *@Date: 2019/11/12
 *@Time:16:47
 *@Description:${DESCRIPTION}
 **/

import android.util.Log;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bawei.lizekai.mylibrary.base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.adapter.RecyclerMovieAdapter;
import com.bw.movie.bean.HotBean;
import com.bw.movie.bean.ShowBean;
import com.bw.movie.bean.ShowingBean;
import com.bw.movie.bean.XbannerBean;
import com.bw.movie.contract.IMovieContract;
import com.bw.movie.presenter.IMoviePresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class Frag_movie extends BaseFragment<IMoviePresenter> implements IMovieContract.IMovieView {
    private static final String TAG = "Frag_movie";

    public List<Object> list = new ArrayList<>();

    @BindView(R.id.xrecycler_movie)
    XRecyclerView xrecyclerMovie;

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_movie;
    }


    @Override
    protected IMoviePresenter providePresenter() {
        return new IMoviePresenter();
    }

    @Override
    protected void initView() {
        super.initView();

        mPresenter.getXbannerPresenter();

    }


    @Override
    protected void initData() {
        super.initData();
        xrecyclerMovie.setPullRefreshEnabled(true);
        xrecyclerMovie.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xrecyclerMovie.refreshComplete();
            }

            @Override
            public void onLoadMore() {
            xrecyclerMovie.loadMoreComplete();
            }
        });
    }


    @Override
    public void onXbannerSuccess(XbannerBean bean) {
        Log.i(TAG, "onXbannerSuccess: " + bean.getMessage());
        list.add(bean);
        mPresenter.getShowingrPresenter("1", "5");

      /*   List<String>xbannerList=new ArrayList<>();
        for (int i = 0; i < bean.getResult().size(); i++) {
            xbannerList.add(bean.getResult().get(i).getImageUrl());
        }
       xbanderMovie.setBannerData(R.layout.image_fresco, new AbstractList<SimpleBannerInfo>() {
            @Override
            public SimpleBannerInfo get(int i) {
                return null;
            }

            @Override
            public int size() {
                return xbannerList.size();
            }
        });
        xbanderMovie.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                SimpleDraweeView simpleDraweeView=view.findViewById(R.id.my_image_view);
                Uri uri=Uri.parse(xbannerList.get(position));
//                Log.i(TAG, "loadBanner: "+xbannerList.get(position));
                simpleDraweeView.setImageURI(uri);
            }
        });*/
    }

    @Override
    public void onShowingSuccess(ShowingBean bean) {
        Log.i(TAG, "onShowingSuccess: " + bean.getMessage());
        list.add(bean);
        Map<String, String> map = new HashMap<>();
        map.put("userId", "13798");
        map.put("sessionId", "157356295970613798");
        mPresenter.getShowPresenter("1", "5", map);


      /*  List<ShowingBean.ResultBean>showingList=new ArrayList<>();
        for (int i = 0; i < bean.getResult().size(); i++) {
                    showingList.add(bean.getResult().get(i));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        RecyclorShowingAdapter recyclorShowingAdapter=new RecyclorShowingAdapter(showingList,getActivity());
        recyclerShowing.setLayoutManager(linearLayoutManager);
        recyclerShowing.setAdapter(recyclorShowingAdapter);*/

    }

    @Override
    public void onShowSuccess(ShowBean bean) {
        Log.i(TAG, "onShowSuccess: " + bean.getMessage());
        list.add(bean);

        mPresenter.getHotPresenter("1", "5");
     /*   List<ShowBean.ResultBean>showList=new ArrayList<>();
        for (int i = 0; i < bean.getResult().size(); i++) {
            showList.add(bean.getResult().get(i));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        RecyclorShowAdapter recyclorShowingAdapter=new RecyclorShowAdapter(showList,getActivity());
        recyclerShow.setLayoutManager(linearLayoutManager);
        recyclerShow.setAdapter(recyclorShowingAdapter);*/
    }

    @Override
    public void onHotSuccess(HotBean bean) {
        Log.i(TAG, "onHotSuccess: " + bean.getMessage());
        list.add(bean);
        RecyclerMovieAdapter adapter = new RecyclerMovieAdapter(list, getContext());
        xrecyclerMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrecyclerMovie.setAdapter(adapter);
    /*    List<HotBean.ResultBean>hotList=new ArrayList<>();
        for (int i = 0; i < bean.getResult().size(); i++) {
            hotList.add(bean.getResult().get(i));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        RecyclorHotAdapter recyclorHotAdapter=new RecyclorHotAdapter(hotList,getActivity());
        recyclerHot.setLayoutManager(linearLayoutManager);
        recyclerHot.setAdapter(recyclorHotAdapter);*/
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
