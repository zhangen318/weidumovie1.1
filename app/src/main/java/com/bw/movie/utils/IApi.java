package com.bw.movie.utils;


import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.HotBean;
import com.bw.movie.bean.IdTimeYingYuanBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.PingLunBean;
import com.bw.movie.bean.QuYuBean;
import com.bw.movie.bean.RegionBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.ShowBean;
import com.bw.movie.bean.ShowingBean;
import com.bw.movie.bean.TimeBean;
import com.bw.movie.bean.TjyyBean;
import com.bw.movie.bean.XbannerBean;
import com.bw.movie.bean.XiangqingBean;
import com.bw.movie.bean.XuanZuoBean;
import com.bw.movie.bean.DypqBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Retrofit默认只使用这一个IApi
 * <p>
 * <p>
 * 使用规范：
 * 一个http请求对应该接口中一个方法
 */
public interface IApi {
    //登录
    @FormUrlEncoded
    @POST("user/v2/login")
    Observable<LoginBean> getLogin(@Field("email")String email,@Field("pwd")String pwd);
    //注册
    @FormUrlEncoded
    @POST("user/v2/register")
    Observable<RegisterBean> getRegister(@Field("nickName")String nickName,@Field("pwd")String pwd,@Field("email")String email,@Field("code")String code);
    @FormUrlEncoded
    @POST("user/v2/sendOutEmailCode")
    Observable<EmailBean> getEmail(@Field("email")String email);

    @GET("movie/v2/findReleaseMovieList")
    Observable<ShowingBean> getShowing(@Query("page")String page, @Query("count")String count);



    @GET("movie/v2/findComingSoonMovieList")
    Observable<ShowBean> getShow(@Query("page")String page, @Query("count")String count, @QueryMap()Map<String,String> map);

    @GET("movie/v2/findHotMovieList")
    Observable<HotBean> getHot(@Query("page")String page, @Query("count")String count);
    //轮播图
    @GET("tool/v2/banner")
    Observable<XbannerBean> getXbanner();

    //影院地址
    @GET("cinema/v2/findCinemaByRegion")
    Observable<RegionBean>getRegion(@Query("regionId")String regionId);
    //推荐影院
    @GET("cinema/v1/findRecommendCinemas")
    Observable<TjyyBean>getTjyy(@Query("page")String page, @Query("count")String count);
    //详情
    @GET("movie/v2/findMoviesDetail")
    Observable<XiangqingBean> getXiangqing(@HeaderMap Map<String,Object> map,@Query("movieId")String movieId);
    //评论
    @GET("movie/v2/findAllMovieComment")
    Observable<PingLunBean>getPingLun(@Query("movieId") String movieId,@Query("page")String page,@Query("count")String count);
    //根据电影id，时间 查询播放影院信息
    @GET("movie/v2/findCinemasInfoByDate")
    Observable<IdTimeYingYuanBean>getIdTimeYingYuan(@Query("movieId") String movieId,@Query("date") String date,@Query("page") String page,@Query("count") String count);
    //时间
    @GET("tool/v2/findDateList")
    Observable<TimeBean>gettime();
    //选座
    @GET("movie/v2/findSeatInfo")
    Observable<XuanZuoBean>getXuanZuo(@Query("hallId")String hallId);
    //根据电影ID和影院ID查询电影排期列表
    @GET("movie/v2/findMovieSchedule")
    Observable<DypqBean>getDypq(@Query("movieId")String movieId, @Query("cinemaId")String cinemaId);

    //根据地区查询
    @GET("tool/v2/findRegionList")
    Observable<QuYuBean> getQuYu();

}
