package com.woowrale.kroomapp.di.threads

import com.woowrale.kroomapp.usecases.threads.JobScheduler
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

class JobThread @Inject internal constructor() : JobScheduler {
    override val scheduler: Scheduler
        get() = Schedulers.io()
}