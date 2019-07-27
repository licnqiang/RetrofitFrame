package cn.piesat.retrofitframe.netWork.module;

import cn.piesat.retrofitframe.constant.UrlConfig;
import cn.piesat.retrofitframe.netWork.upLoadFile.UploadListener;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 类名称：RetrofitUtils
 * 创建人：lq
 * 创建时间：2016-5-18 14:57:11
 * 类描述：封装一个retrofit集成0kHttp3的抽象基类
 */
public abstract class RetrofitUtils {


    private  Retrofit mRetrofit;
    private  OkHttpClient.Builder mOkHttpClient;

    /**
     * 获取Retrofit对象
     *
     * @return
     */
    protected  Retrofit getRetrofit(UploadListener uploadListener) {

        if (null == mRetrofit) {

            if (null == mOkHttpClient) {
                mOkHttpClient = OkHttp3Utils.getOkHttpClient(uploadListener);

            }

            //Retrofit2后使用build设计模式
            mRetrofit = new Retrofit.Builder()
                    //设置服务器路径
                    .baseUrl(UrlConfig.getURLPreFix())
                    //添加转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加回调库，采用RxJava
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    //设置使用okhttp网络请求
                    .client(mOkHttpClient.build())
                    .build();

        }

        return mRetrofit;
    }

}
