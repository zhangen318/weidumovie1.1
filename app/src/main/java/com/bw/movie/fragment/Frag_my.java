package com.bw.movie.fragment;
/*
 *@auther:张恩
 *@Date: 2019/11/12
 *@Time:16:47
 *@Description:${DESCRIPTION}
 **/

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bawei.lizekai.mylibrary.base.BaseFragment;
import com.bawei.lizekai.mylibrary.base.BasePresenter;
import com.bw.movie.GxActivity;
import com.bw.movie.LoginActivity;
import com.bw.movie.R;
import com.facebook.drawee.backends.pipeline.Fresco;

import butterknife.BindView;

public class Frag_my extends BaseFragment {
    @BindView(R.id.mine_)
    TextView mine;
    @BindView(R.id.mine_xinxi)
    ImageView mineXinxi;
    @BindView(R.id.mine_img)
    ImageView mineImg;
    @BindView(R.id.mine_name_)
    TextView mineName;
    @BindView(R.id.mine_Setting)
    RelativeLayout mineSetting;

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        super.initView();
      //  mineImg.setImageResource(R.mipmap.f1e0b756bc17d19);
        mineImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mineSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),GxActivity.class));
            }
        });
    }
}
