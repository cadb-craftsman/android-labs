package com.woowrale.kroomapp.usecases.threads

import io.reactivex.Scheduler

interface JobScheduler {
    val scheduler: Scheduler
}
