package com.bw.movie.presenter;
/*
 *@auther:张恩
 *@Date: 2019/11/20
 *@Time:17:34
 *@Description:${DESCRIPTION}
 **/

import com.bawei.lizekai.mylibrary.base.BasePresenter;
import com.bw.movie.bean.DypqBean;
import com.bw.movie.bean.XuanZuoBean;
import com.bw.movie.contract.IXuanZuoContract;
import com.bw.movie.model.IXuanZuoModel;

public class IXuanZuoPresenter extends BasePresenter<IXuanZuoContract.IXuanZuoView> implements IXuanZuoContract.IXuanZuogPresenter {


    private IXuanZuoModel model;

    @Override
    protected void initModel() {
        model = new IXuanZuoModel();
    }


    @Override
    public void getXuanZuoPresenter(String hallId) {
        model.getXuanZuoData(hallId, new IXuanZuoContract.IXuanZuoModel.IXuanZuoModelCallback() {
            @Override
            public void onXuanZuoSuccess(XuanZuoBean bean) {
                getView().onXuanZuoSuccess(bean);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void getDypqPresenter(String movieId, String cinemaId) {
            model.getDypqData(movieId, cinemaId, new IXuanZuoContract.IXuanZuoModel.IDypqModelCallback() {
                @Override
                public void onDypqSuccess(DypqBean bean) {
                    getView().onDypqSuccess(bean);
                }

                @Override
                public void onFailure(Throwable e) {
                    getView().onFailure(e);
                }
            });
    }
}
