package com.bw.movie.contract;
/*
 *@auther:张恩
 *@Date: 2019/11/12
 *@Time:20:41
 *@Description:${DESCRIPTION}
 **/

import com.bawei.lizekai.mylibrary.base.IBaseView;
import com.bw.movie.bean.XiangqingBean;

import java.util.Map;

public interface IXiangqingContract {
    interface IXiangqingView extends IBaseView {
        void onXiangqingSuccess(XiangqingBean bean);
        void onXiangqingFailure(Throwable e);
    }
    interface IXiangqingModel{
        void getXiangqingData(Map<String,Object> map,String movieId, IXiangqingModelCallback iXiangqingModelCallback) ;
        interface IXiangqingModelCallback{
            void onXiangqingSuccess(XiangqingBean bean);
            void onXiangqingFailure(Throwable e);
        }
        }


    interface IXiangqingPresenter{
        void getXiangqingPresenter(Map<String,Object> map,String movieId);
    }
}
