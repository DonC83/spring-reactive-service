package com.donc.springreactiveservice.processor

import com.donc.springreactiveservice.domain.SumObject
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.function.Function

@Service
class SumLogic : Function<SumObject, Mono<Int>> {

    override fun apply(t: SumObject): Mono<Int> {
        return Mono.just(t.num1.plus(t.num2))
    }

}