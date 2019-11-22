package com.bw.movie.contract;
/*
 *@auther:张恩
 *@Date: 2019/11/12
 *@Time:20:41
 *@Description:${DESCRIPTION}
 **/

import com.bawei.lizekai.mylibrary.base.IBaseView;
import com.bw.movie.bean.HotBean;
import com.bw.movie.bean.QuYuBean;
import com.bw.movie.bean.RegionBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.ShowBean;
import com.bw.movie.bean.ShowingBean;
import com.bw.movie.bean.XbannerBean;

import java.util.Map;

public interface IRegionContract {
    interface IRegionView extends IBaseView {
        void onRegionSuccess(RegionBean bean);
        void oQuYuSuccess(QuYuBean  bean);
        void onRegionFailure(Throwable e);
    }
    interface IRegionModel{
        void getRegionData(String regionId,IRegionModelCallback iRegionModelCallback) ;
        void getQuYuData(IRegionModelCallback iRegionModelCallback);
        interface IRegionModelCallback{
            void onRegionSuccess(RegionBean bean);
            void oQuYuSuccess(QuYuBean  bean);
            void onRegionFailure(Throwable e);
        }
        }


    interface IRegionPresenter{
        void getRegionPresenter(String regionId);
        void getQuYuPresenter();
    }
}
