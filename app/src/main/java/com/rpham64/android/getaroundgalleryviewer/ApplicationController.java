package com.rpham64.android.getaroundgalleryviewer;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.orhanobut.logger.Logger;
import com.rpham64.android.getaroundgalleryviewer.network.RestClient;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rudolf on 2/8/2017.
 */

public class ApplicationController extends Application {

    public static final String BASE_URL = "https://api.500px.com/";
    public static final String API_KEY = "UU6XQeziu01adhSANZo3J5gDsZD6gaFyJXomYlhz";
    public static final String API_SECRET = "5CZl9ZYnOwvOn35Jg3sKFFDR6nKZC4DK2NctRPUc";

    private static ApplicationController sInstance;

    private OkHttpClient mOkHttpClient;
    private RestClient mRestClient;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        Stetho.initializeWithDefaults(this);

        Logger.d("ApplicationController created");

        mOkHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl url = request.url().newBuilder()
                                .addQueryParameter("consumer_key", API_KEY)
                                .build();
                        request = request.newBuilder().url(url).build();
                        return chain.proceed(request);
                    }
                })
                .build();
    }

    public RestClient getRestClient() {

        if (mRestClient == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();

            mRestClient = retrofit.create(RestClient.class);
        }

        return mRestClient;
    }

    public static ApplicationController getInstance() {
        return sInstance;
    }
}
