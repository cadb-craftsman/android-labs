package com.woowrale.jroomapp.di.threads;

import com.woowrale.jroomapp.usecases.threads.UIScheduler;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * MainThread (UI Thread) implementation based on a {@link Scheduler}
 * which will execute actions on the Android UI thread.
 */
@Singleton
public class UIThread implements UIScheduler {

  @Inject
  UIThread() {}

  @Override
  public Scheduler getScheduler() {
    return AndroidSchedulers.mainThread();
  }

}
