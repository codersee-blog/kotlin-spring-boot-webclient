package com.codersee.webclient.controller

import com.codersee.webclient.model.ExampleResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api")
class TestController {

    @GetMapping("/hello")
    fun getExampleResponse() = ExampleResponse(
        message = "Hello world",
        timestamp = LocalDateTime.now()
    )

    @GetMapping("/hello-list")
    fun getExampleResponseList() = listOf(
        ExampleResponse(
            message = "Hello world #1",
            timestamp = LocalDateTime.now()
        ),
        ExampleResponse(
            message = "Hello world #2",
            timestamp = LocalDateTime.now()
        )
    )
}

