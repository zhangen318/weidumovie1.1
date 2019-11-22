package com.bw.movie.presenter;
/*
 *@auther:张恩
 *@Date: 2019/11/20
 *@Time:14:28
 *@Description:${DESCRIPTION}
 **/

import com.bawei.lizekai.mylibrary.base.BasePresenter;
import com.bw.movie.bean.IdTimeYingYuanBean;
import com.bw.movie.contract.IIdTimeYingYuanContract;
import com.bw.movie.model.IIdTimeYingModel;

public class IIdTimeYingPresenter extends BasePresenter<IIdTimeYingYuanContract.IIdTimeYingView> implements IIdTimeYingYuanContract.IIdTimeYingPresenter {

    private IIdTimeYingModel model;

    @Override
    protected void initModel() {
        model = new IIdTimeYingModel();
    }

    @Override
    public void getIdTimeYingPresenter(String movieId, String date, String page, String count) {
        model.getIdTimeYingData(movieId, date, page, count, new IIdTimeYingYuanContract.IIdTimeYingModel.IIdTimeYingModelCallback() {
            @Override
            public void onIdTimeYingSuccess(IdTimeYingYuanBean bean) {
                getView().onIdTimeYingSuccess(bean);
            }

            @Override
            public void onIdTimeYingFailure(Throwable e) {
                getView().onIdTimeYingFailure(e);
            }
        });
    }
}
