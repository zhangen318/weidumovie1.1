package com.bw.movie.adapter.xiangqing;
/*
 *@auther:张恩
 *@Date: 2019/11/18
 *@Time:20:20
 *@Description:${DESCRIPTION}
 **/

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.XiangqingBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class RecyclerYuGaoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<XiangqingBean.ResultBean.ShortFilmListBean>list;
        private Context context;

    public RecyclerYuGaoAdapter(List<XiangqingBean.ResultBean.ShortFilmListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = View.inflate(context, R.layout.layout_yugao, null);
            return new MyViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MyViewHolder){
                ((MyViewHolder) holder).jcVideoPlayer.setUp(list.get(position).getVideoUrl(),null);
                Glide.with(context).load(list.get(position).getVideoUrl()).into(((MyViewHolder) holder).jcVideoPlayer.ivThumb);
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
        class MyViewHolder extends RecyclerView.ViewHolder {
            private final JCVideoPlayer jcVideoPlayer;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                jcVideoPlayer=itemView.findViewById(R.id.jcvideoplayer_yugao);
            }
        }
}
