package com.twistedequations.reddit.rsvp.app.dagger;

import com.google.gson.Gson;
import com.twistedequations.rx.AndroidRxSchedulers;
import com.twistedequations.reddit.rsvp.network.reddit.RedditService;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RestServiceModule {

    @AppScope
    @Provides
    @Named("RedditServiceRetrofit")
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson, AndroidRxSchedulers androidRxSchedulers) {
        return new Retrofit.Builder()
                .baseUrl("https://www.reddit.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(androidRxSchedulers.network()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    @AppScope
    @Provides
    public RedditService redditService(@Named("RedditServiceRetrofit") Retrofit retrofit) {
        return retrofit.create(RedditService.class);
    }
}
