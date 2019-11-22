package com.bw.movie.contract;
/*
 *@auther:张恩
 *@Date: 2019/11/20
 *@Time:14:23
 *@Description:${DESCRIPTION}
 **/

import com.bawei.lizekai.mylibrary.base.IBaseView;
import com.bw.movie.bean.IdTimeYingYuanBean;

public interface IIdTimeYingYuanContract {
    interface IIdTimeYingView extends IBaseView {
        void onIdTimeYingSuccess(IdTimeYingYuanBean bean);
        void onIdTimeYingFailure(Throwable e);
    }
    interface IIdTimeYingModel{
        void getIdTimeYingData(String movieId,String date,String page,String count, IIdTimeYingModelCallback iIdTimeYingModelCallback) ;
        interface IIdTimeYingModelCallback{
            void onIdTimeYingSuccess(IdTimeYingYuanBean bean);
            void onIdTimeYingFailure(Throwable e);
        }
    }


    interface IIdTimeYingPresenter{
        void getIdTimeYingPresenter(String movieId,String date,String page,String count);
    }
}
