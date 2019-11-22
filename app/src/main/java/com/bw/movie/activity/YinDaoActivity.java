package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bw.movie.MainActivity;
import com.bw.movie.R;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 *@describe(描述)：YinDaoActivity
 *@data（日期）: 2019/11/22   引导页
 *@time（时间）: 9:24
 *@author（作者）: 张恩
 **/
public class YinDaoActivity extends AppCompatActivity {

    int count=3;
    @BindView(R.id.tv)
    TextView tv;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage( Message msg) {
            super.handleMessage(msg);
            if (count<0){
                startActivity(new Intent(YinDaoActivity.this, MainActivity.class));
                finish();
            }else {
                tv.setText(count+"S");
                count--;
                handler.sendEmptyMessageDelayed(99, 1000);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yin_dao);
        ButterKnife.bind(this);

        handler.sendEmptyMessageDelayed(99, 1000);
    }
}
