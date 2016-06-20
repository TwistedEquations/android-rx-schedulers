package com.twistedequations.reddit.rsvp.rx;

import java.util.concurrent.Executors;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DefaultSchedulers implements RxSchedulers {

  //Limit the threads to prevent cpu thread overloading and to help with context switching
  private static int PROCESSOR_THREADS = Math.min(2, Runtime.getRuntime().availableProcessors()); //Limit to availableProcessors cores
  private static int NETWORK_THREADS = 4; //Prevent network overloading
  private static int IO_THREADS = 6; //Prevent overloading the disk IO

  private static final Scheduler NETWORK_SCHEDULER = Schedulers.from(Executors.newFixedThreadPool(NETWORK_THREADS, new AndroidThreadFactory("Network")));
  private static final Scheduler IO_SCHEDULER = Schedulers.from(Executors.newFixedThreadPool(IO_THREADS, new AndroidThreadFactory("I/O")));
  private static final Scheduler COMPUTATION_SCHEDULER = Schedulers.from(Executors.newFixedThreadPool(PROCESSOR_THREADS, new AndroidThreadFactory("Computation")));

  @Override
  public Scheduler network() {
    return NETWORK_SCHEDULER;
  }

  @Override
  public Scheduler io() {
    return IO_SCHEDULER;
  }

  @Override
  public Scheduler computation() {
    return COMPUTATION_SCHEDULER;
  }

  @Override
  public Scheduler immediate() {
    return Schedulers.immediate();
  }

  @Override
  public Scheduler mainThread() {
    return AndroidSchedulers.mainThread();
  }
}
