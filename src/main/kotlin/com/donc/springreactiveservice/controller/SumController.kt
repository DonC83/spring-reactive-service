package com.donc.springreactiveservice.controller

import com.donc.springreactiveservice.dto.SumRequest
import com.donc.springreactiveservice.dto.SumResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class SumController(
    val processor : (SumRequest) -> Mono<SumResponse>) {

    @GetMapping(path = ["/sum"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun sumNumbers(@RequestBody request: SumRequest) : SumResponse? {

        val responseSubscriber = ResponseSubscriber<SumResponse>()
        processor.invoke(request).subscribe(responseSubscriber)

        return responseSubscriber.get()
    }


}

