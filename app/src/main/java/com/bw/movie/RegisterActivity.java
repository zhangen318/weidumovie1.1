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
 *@describe(描述)：RegisterActivity
 *@data（日期）: 2019/11/12
 *@time（时间）: 8:45
 *@author（作者）: 张恩
 **/

public class RegisterActivity extends BaseActivity<IUserPresenter> implements IUserContract.IUserlView {


    @BindView(R.id.edit_register_name)
    EditText editRegisterName;
    @BindView(R.id.edit_register_email)
    EditText editRegisterEmail;
    @BindView(R.id.edit_register_pwd)
    EditText editRegisterPwd;

    @BindView(R.id.butt_register_register)
    Button buttRegisterRegister;
    @BindView(R.id.butt_register_email)
    Button buttRegisterEmail;
    @BindView(R.id.text_register_login)
    TextView textRegisterLogin;
    @BindView(R.id.edit_register_yzm)
    EditText editRegisterYzm;

    @Override
    protected IUserPresenter providePresenter() {
        return new IUserPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void onEmailSuccess(EmailBean bean) {
        String message = bean.getMessage();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegisterSuccess(RegisterBean bean) {
        String message = bean.getMessage();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginSuccess(LoginBean bean) {

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


    @OnClick({R.id.butt_register_email, R.id.butt_register_register, R.id.text_register_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.butt_register_email:
                String email = editRegisterEmail.getText().toString().trim();
                mPresenter.getEmailPresenter(email);
                break;
            case R.id.butt_register_register:
                String email2 = editRegisterEmail.getText().toString().trim();
                String name = editRegisterName.getText().toString().trim();
                String yzm = editRegisterYzm.getText().toString().trim();
                String pwd = editRegisterPwd.getText().toString().trim();
                String MD5_pwd = EncryptUtil.encrypt(pwd);
                mPresenter.getRegisterPresenter(name, MD5_pwd,email2,yzm );
                break;
            case R.id.text_register_login:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                break;
        }
    }
}

