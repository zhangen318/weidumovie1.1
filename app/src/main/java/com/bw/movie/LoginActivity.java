package com.bw.movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.lizekai.mylibrary.base.BaseActivity;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.contract.IUserContract;
import com.bw.movie.md5.EncryptUtil;
import com.bw.movie.presenter.IUserPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 *@describe(描述)：LoginActivity
 *@data（日期）: 2019/11/12
 *@time（时间）: 8:45
 *@author（作者）: 张恩
 **/

public class LoginActivity extends BaseActivity<IUserPresenter> implements IUserContract.IUserlView {


    @BindView(R.id.edit_login_email)
    EditText editLoginEmail;
    @BindView(R.id.edit_login_pwd)
    EditText editLoginPwd;
    @BindView(R.id.butt_login_wjpwd)
    Button buttLoginWjpwd;
    @BindView(R.id.text_login_register)
    TextView textLoginRegister;
    @BindView(R.id.butt_login_login)
    Button buttLoginLogin;

    @Override
    protected IUserPresenter providePresenter() {
        return new IUserPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onEmailSuccess(EmailBean bean) {

    }

    @Override
    public void onRegisterSuccess(RegisterBean bean) {

    }

    @Override
    public void onLoginSuccess(LoginBean bean) {
        Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            if (bean.getStatus().equals("0000")){
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.butt_login_wjpwd, R.id.text_login_register, R.id.butt_login_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.butt_login_wjpwd:
                break;
            case R.id.text_login_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.butt_login_login:
                String email = editLoginEmail.getText().toString().trim();
                String pwd = editLoginPwd.getText().toString().trim();
                String MD5_pwd = EncryptUtil.encrypt(pwd);
                mPresenter.getLoginPresenter(email,MD5_pwd);
                break;
        }
    }
}
