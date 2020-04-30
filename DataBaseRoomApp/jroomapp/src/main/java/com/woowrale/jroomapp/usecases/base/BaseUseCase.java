package com.woowrale.jroomapp.usecases.base;

import com.woowrale.jroomapp.usecases.threads.JobScheduler;
import com.woowrale.jroomapp.usecases.threads.UIScheduler;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public abstract class BaseUseCase<Observer, Params> {

    private final UIScheduler uiScheduler;
    private final JobScheduler jobScheduler;

    public BaseUseCase(UIScheduler uiScheduler, JobScheduler jobScheduler) {
        this.uiScheduler = uiScheduler;
        this.jobScheduler = jobScheduler;
    }

    protected abstract Single<Observer> buildUseCaseObservable(Params params);

    public Disposable execute(DisposableSingleObserver<Observer> observer, Params params) {
        final Single<Observer> observable = this.buildUseCaseObservable(params)
                .observeOn(uiScheduler.getScheduler())
                .subscribeOn(jobScheduler.getScheduler());
        return observable.subscribeWith(observer);
    }

}
