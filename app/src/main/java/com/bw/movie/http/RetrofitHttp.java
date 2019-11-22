package com.bw.movie.http;

import android.util.Log;


import com.bw.movie.app.Constant;
import com.bw.movie.callback.DownloadCallBack;
import com.bw.movie.service.DownloadIntentService;
import com.bw.movie.utils.SPDownloadUtil;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Describe：RetrofitHttp
 * Author：fan
 * Data：2019/11/7
 * Time:9:07
 */
public class RetrofitHttp {

    private static final int DEFAULT_TIMEOUT = 10;
    private static final String TAG = "RetrofitClient";

    private ApiService apiService;

    private OkHttpClient okHttpClient;

    public static String baseUrl = ApiService.BASE_URL;

    private static RetrofitHttp sIsntance;

    public static RetrofitHttp getInstance() {
        if (sIsntance == null) {
            synchronized (RetrofitHttp.class) {
                if (sIsntance == null) {
                    sIsntance = new RetrofitHttp();
                }
            }
        }
        return sIsntance;
    }

    private RetrofitHttp() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        apiService = retrofit.create(ApiService.class);
    }




    public void downloadFile(final long range, final String url, final String fileName, final DownloadCallBack downloadCallback) {
        //断点续传时请求的总长度
        File file = new File(com.bw.movie.utils.Constant.APP_ROOT_PATH + com.bw.movie.utils.Constant.DOWNLOAD_DIR, fileName);
        String totalLength = "-";
        if (file.exists()) {
            //读取文件总长度
            totalLength += file.length();
        }
        //获取下载 地址
        apiService.executeDownload("bytes=" + Long.toString(range) + totalLength, url)
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        DownloadIntentService.compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        RandomAccessFile randomAccessFile = null;
                        InputStream inputStream = null;
                        long total = range;
                        long responseLength = 0;
                        try {
                            byte[] buf = new byte[2048];
                            int len = 0;
                            responseLength = responseBody.contentLength();
                            inputStream = responseBody.byteStream();
                            String filePath = com.bw.movie.utils.Constant.APP_ROOT_PATH + com.bw.movie.utils.Constant.DOWNLOAD_DIR;
                            File file = new File(filePath, fileName);
                            File dir = new File(filePath);
                            if (!dir.exists()) {
                                dir.mkdirs();
                            }
                            randomAccessFile = new RandomAccessFile(file, "rwd");
                            if (range == 0) {
                                randomAccessFile.setLength(responseLength);
                            }
                            randomAccessFile.seek(range);

                            int progress = 0;
                            int lastProgress = 0;

                            while ((len = inputStream.read(buf)) != -1) {
                                randomAccessFile.write(buf, 0, len);
                                total += len;
                                SPDownloadUtil.getInstance().save(url, total);
                                lastProgress = progress;
                                progress = (int) (total * 100 / randomAccessFile.length());
                                if (progress > 0 && progress != lastProgress) {
                                    downloadCallback.onProgress(progress);
                                }
                            }
                            downloadCallback.onCompleted();
                        } catch (Exception e) {
                            Log.d(TAG, e.getMessage());
                            downloadCallback.onError(e.getMessage());
                            e.printStackTrace();
                        } finally {
                            try {
                                if (randomAccessFile != null) {
                                    randomAccessFile.close();
                                }

                                if (inputStream != null) {
                                    inputStream.close();
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        downloadCallback.onError(e.toString());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}