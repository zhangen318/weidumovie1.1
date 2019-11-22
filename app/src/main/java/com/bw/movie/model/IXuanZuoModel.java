package com.bw.movie.model;
/*
 *@auther:张恩
 *@Date: 2019/11/20
 *@Time:17:34
 *@Description:${DESCRIPTION}
 **/

import com.bawei.lizekai.mylibrary.utils.CommonObserver;
import com.bawei.lizekai.mylibrary.utils.CommonSchedulers;
import com.bw.movie.bean.DypqBean;
import com.bw.movie.bean.XuanZuoBean;
import com.bw.movie.contract.IXuanZuoContract;
import com.bw.movie.utils.RetrofitManager;

public class IXuanZuoModel implements IXuanZuoContract.IXuanZuoModel {


    @Override
    public void getXuanZuoData(String hallId, IXuanZuoModelCallback iXuanZuoModelCallback) {
        RetrofitManager.getInstance().create().getXuanZuo(hallId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<XuanZuoBean>() {
                    @Override
                    public void onNext(XuanZuoBean xuanZuoBean) {
                        iXuanZuoModelCallback.onXuanZuoSuccess(xuanZuoBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iXuanZuoModelCallback.onFailure(e);
                    }
                });
    }

    @Override
    public void getDypqData(String movieId, String cinemaId, IDypqModelCallback iDypqModelCallback) {
            RetrofitManager.getInstance().create().getDypq(movieId, cinemaId)
                    .compose(CommonSchedulers.io2main())
                    .subscribe(new CommonObserver<DypqBean>() {
                        @Override
                        public void onNext(DypqBean dypqBean) {
                            iDypqModelCallback.onDypqSuccess(dypqBean);
                        }

                        @Override
                        public void onError(Throwable e) {
                            iDypqModelCallback.onFailure(e);
                        }
                    });
    }
}
