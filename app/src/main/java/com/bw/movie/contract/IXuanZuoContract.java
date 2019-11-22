package com.bw.movie.contract;
/*
 *@auther:张恩
 *@Date: 2019/11/20
 *@Time:17:27
 *@Description:${DESCRIPTION}
 **/

import com.bawei.lizekai.mylibrary.base.IBaseView;
import com.bw.movie.bean.DypqBean;
import com.bw.movie.bean.XuanZuoBean;

public interface IXuanZuoContract {
    interface IXuanZuoView extends IBaseView {
        void onXuanZuoSuccess(XuanZuoBean bean);
        void onDypqSuccess(DypqBean bean);
        void onFailure(Throwable e);
    }
    interface IXuanZuoModel{
        void getXuanZuoData(String hallId, IXuanZuoModelCallback iXuanZuoModelCallback) ;
        interface IXuanZuoModelCallback{
            void onXuanZuoSuccess(XuanZuoBean bean);
            void onFailure(Throwable e);
        }
        void getDypqData(String movieId,String cinemaId, IDypqModelCallback iDypqModelCallback) ;
        interface IDypqModelCallback{
            void onDypqSuccess(DypqBean bean);
            void onFailure(Throwable e);
        }
    }


    interface IXuanZuogPresenter{
        void getXuanZuoPresenter(String hallId);
        void getDypqPresenter(String movieId,String cinemaId);

    }
}
