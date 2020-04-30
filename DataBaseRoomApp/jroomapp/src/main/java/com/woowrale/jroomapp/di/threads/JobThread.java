package com.woowrale.jroomapp.di.threads;

import com.woowrale.jroomapp.usecases.threads.JobScheduler;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * JobScheduler implementation based on a {@link Scheduler}
 * which will execute tasks in "an unbounded thread pool".
 */
@Singleton
public class JobThread implements JobScheduler {

    @Inject
    JobThread() {}

    @Override
    public Scheduler getScheduler() {
        return Schedulers.io();
    }

}
