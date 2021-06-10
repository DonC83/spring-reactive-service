package com.donc.springreactiveservice.core

import reactor.core.publisher.BaseSubscriber

class AsyncResponse<T> : BaseSubscriber<T>() {

    private var result: T? = null
    private var error: RuntimeException? = null

    fun get() : T? {
        return result
    }

    fun getError() : RuntimeException? {
        return error
    }

    override fun hookOnNext(value: T) {
        result = value
    }

    override fun hookOnError(throwable: Throwable) {
        error = RuntimeException(throwable)
    }
}