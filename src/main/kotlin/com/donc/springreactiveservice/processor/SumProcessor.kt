package com.donc.springreactiveservice.processor

import com.donc.springreactiveservice.core.AsyncResponse
import com.donc.springreactiveservice.domain.SumObject
import com.donc.springreactiveservice.dto.SumRequest
import com.donc.springreactiveservice.dto.SumResponse
import lombok.AllArgsConstructor
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@AllArgsConstructor
@Service
class SumProcessor
constructor(
    private val validate : SumValidator,
    private val process : SumLogic) : (SumRequest) -> AsyncResponse<SumResponse> {

    override fun invoke(request: SumRequest): AsyncResponse<SumResponse> {
        val response = AsyncResponse<SumResponse>()

        Mono.just(request)
            .doOnNext(validate)
            .map { SumObject(it.num1.toInt(), it.num2.toInt()) }
            .flatMap(process)
            .map { SumResponse(it) }
            .doOnError { throw RuntimeException(it) }
            .subscribe(response)

        return response
    }
}