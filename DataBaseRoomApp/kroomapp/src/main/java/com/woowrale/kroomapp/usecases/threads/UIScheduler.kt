package com.woowrale.kroomapp.usecases.threads

import io.reactivex.Scheduler

interface UIScheduler {
    val scheduler: Scheduler
}