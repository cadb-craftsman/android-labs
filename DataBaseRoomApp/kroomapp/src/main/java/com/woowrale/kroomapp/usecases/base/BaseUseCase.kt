package com.woowrale.kroomapp.usecases.base

import com.woowrale.kroomapp.usecases.threads.JobScheduler
import com.woowrale.kroomapp.usecases.threads.UIScheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver

abstract class BaseUseCase<Observer, Params> constructor(
        private val uiScheduler: UIScheduler,
        private val jobScheduler: JobScheduler
) {

    protected abstract fun buildUseCaseObservable(params: Params): Single<Observer>

    fun execute(observer: DisposableSingleObserver<Observer>, params: Params): Disposable {
        val observable = buildUseCaseObservable(params)
            .observeOn(uiScheduler.scheduler)
            .subscribeOn(jobScheduler.scheduler)
        return observable.subscribeWith(observer)
    }
}