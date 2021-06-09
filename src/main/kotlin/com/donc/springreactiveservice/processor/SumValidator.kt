package com.donc.springreactiveservice.processor

import com.donc.springreactiveservice.dto.SumRequest
import org.springframework.stereotype.Component
import java.lang.RuntimeException
import java.util.function.Consumer

@Component
class SumValidator : Consumer<SumRequest> {

    override fun accept(t: SumRequest) {
        try {
            t.num1.toInt()
            t.num2.toInt()
        } catch (e : NumberFormatException) {
            throw RuntimeException("Invalid number ")
        }

    }
}