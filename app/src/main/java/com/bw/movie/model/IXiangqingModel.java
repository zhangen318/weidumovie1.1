package com.bw.movie.model;
/*
 *@auther:张恩
 *@Date: 2019/11/15
 *@Time:20:29
 *@Description:${DESCRIPTION}
 **/

import com.bawei.lizekai.mylibrary.utils.CommonObserver;
import com.bawei.lizekai.mylibrary.utils.CommonSchedulers;
import com.bw.movie.bean.XiangqingBean;
import com.bw.movie.contract.IXiangqingContract;
import com.bw.movie.utils.RetrofitManager;

import java.util.Map;

public class IXiangqingModel implements IXiangqingContract.IXiangqingModel {
    @Override
    public void getXiangqingData(Map<String, Object> map, String movieId, IXiangqingModelCallback iXiangqingModelCallback) {
        RetrofitManager.getInstance().create().getXiangqing(map, movieId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<XiangqingBean>() {
                    @Override
                    public void onNext(XiangqingBean xiangqingBean) {
                        iXiangqingModelCallback.onXiangqingSuccess(xiangqingBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iXiangqingModelCallback.onXiangqingFailure(e);
                    }
                });
    }
}
