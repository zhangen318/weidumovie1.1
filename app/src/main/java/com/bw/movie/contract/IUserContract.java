package com.bw.movie.contract;
/*
 *@auther:张恩
 *@Date: 2019/11/11
 *@Time:16:40
 *@Description:${DESCRIPTION}
 **/

import com.bawei.lizekai.mylibrary.base.IBaseView;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.RegisterBean;

public interface IUserContract  {
        interface IUserlView extends IBaseView {
            void onEmailSuccess(EmailBean bean);
            void onRegisterSuccess(RegisterBean bean);
            void onLoginSuccess(LoginBean bean);
            void onFailure(Throwable e);
        }
        interface IUserlModel{
            void getEmailData(String email,IEmailModelCallback iEmailModelCallback) ;
            interface IEmailModelCallback{
                void onEmailSuccess(EmailBean bean);
                void onFailure(Throwable e);

            }
            void getRegisterData(String nickName,String pwd,String email,String code,IRegisterModelCallback iRegisterModelCallback);
            interface IRegisterModelCallback{
                void onRegisterSuccess(RegisterBean bean);
                void onFailure(Throwable e);

            }
            void getLoginData(String email,String pwd,ILoginModelCallback iLoginModelCallback);
            interface ILoginModelCallback{
                void onLoginSuccess(LoginBean bean);
                void onFailure(Throwable e);

            }
        }
        interface IUserPresenter{
                void getEmailPresenter(String email);
                void getRegisterPresenter(String nickName,String pwd,String email,String code);
                void getLoginPresenter(String email,String pwd);
        }
}
