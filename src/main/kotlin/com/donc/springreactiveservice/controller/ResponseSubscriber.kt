package com.donc.springreactiveservice.controller

import reactor.core.publisher.BaseSubscriber

class ResponseSubscriber<T> : BaseSubscriber<T>() {

    private var result: T? = null

    fun get() : T? {
        return result
    }

    override fun hookOnNext(value: T) {
        result = value
    }

    override fun hookOnError(throwable: Throwable) {
        throw RuntimeException(throwable)
    }
}