package com.donc.springreactiveservice.controller

import com.donc.springreactiveservice.dto.SumRequest
import com.donc.springreactiveservice.dto.SumResponse
import com.donc.springreactiveservice.core.AsyncResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SumController(
    val processor : (SumRequest) -> AsyncResponse<SumResponse>
) {

    @GetMapping(path = ["/sum"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun sumNumbers(@RequestBody request: SumRequest) : SumResponse? {

        val response = processor.invoke(request)

        if (response.getError() != null)
            throw response.getError()!!

        return response.get()
    }


}

