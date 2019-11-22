package com.bw.movie.presenter;
/*
 *@auther:张恩
 *@Date: 2019/11/15
 *@Time:20:31
 *@Description:${DESCRIPTION}
 **/

import com.bawei.lizekai.mylibrary.base.BasePresenter;
import com.bw.movie.bean.XiangqingBean;
import com.bw.movie.contract.IXiangqingContract;
import com.bw.movie.model.IXiangqingModel;

import java.util.Map;

public class IXiangqingPresenter extends BasePresenter<IXiangqingContract.IXiangqingView>implements IXiangqingContract.IXiangqingPresenter {

    private IXiangqingModel model;

    @Override
    protected void initModel() {
        model = new IXiangqingModel();
    }

    @Override
    public void getXiangqingPresenter(Map<String, Object> map, String movieId) {
        model.getXiangqingData(map, movieId, new IXiangqingContract.IXiangqingModel.IXiangqingModelCallback() {
            @Override
            public void onXiangqingSuccess(XiangqingBean bean) {
                getView().onXiangqingSuccess(bean);
            }

            @Override
            public void onXiangqingFailure(Throwable e) {
                getView().onXiangqingFailure(e);
            }
        });
    }
}
