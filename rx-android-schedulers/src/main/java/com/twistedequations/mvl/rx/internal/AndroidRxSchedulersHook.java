package com.twistedequations.mvl.rx.internal;

import com.twistedequations.mvl.rx.AndroidRxSchedulers;

import rx.Scheduler;
import rx.plugins.RxJavaSchedulersHook;

public class AndroidRxSchedulersHook extends RxJavaSchedulersHook {

    private final AndroidRxSchedulers androidRxSchedulers;

    public AndroidRxSchedulersHook(AndroidRxSchedulers androidRxSchedulers) {
        this.androidRxSchedulers = androidRxSchedulers;
    }

    @Override
    public Scheduler getComputationScheduler() {
        return androidRxSchedulers.computation();
    }

    @Override
    public Scheduler getIOScheduler() {
        return androidRxSchedulers.io();
    }
}
