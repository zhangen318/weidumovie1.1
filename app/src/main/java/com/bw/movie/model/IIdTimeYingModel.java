package com.bw.movie.model;
/*
 *@auther:张恩
 *@Date: 2019/11/20
 *@Time:14:28
 *@Description:${DESCRIPTION}
 **/

import com.bawei.lizekai.mylibrary.utils.CommonObserver;
import com.bawei.lizekai.mylibrary.utils.CommonSchedulers;
import com.bw.movie.bean.IdTimeYingYuanBean;
import com.bw.movie.contract.IIdTimeYingYuanContract;
import com.bw.movie.utils.RetrofitManager;

public class IIdTimeYingModel implements IIdTimeYingYuanContract.IIdTimeYingModel {
    @Override
    public void getIdTimeYingData(String movieId, String date, String page, String count, IIdTimeYingModelCallback iIdTimeYingModelCallback) {
        RetrofitManager.getInstance().create().getIdTimeYingYuan(movieId, date, page, count)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<IdTimeYingYuanBean>() {
                    @Override
                    public void onNext(IdTimeYingYuanBean idTimeYingYuanBean) {
                        iIdTimeYingModelCallback.onIdTimeYingSuccess(idTimeYingYuanBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iIdTimeYingModelCallback.onIdTimeYingFailure(e);
                    }
                });
    }
}
