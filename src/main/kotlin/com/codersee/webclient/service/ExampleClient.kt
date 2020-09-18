package com.codersee.webclient.service

import com.codersee.webclient.model.ExampleRequest
import com.codersee.webclient.model.ExampleResponse
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

@Component
class ExampleClient(
        private val webClient: WebClient
) {

    fun retrieveExampleResponse(): ExampleResponse? =
            webClient.get()
                    .uri { it.pathSegment("api", "hello").build() }
                    .retrieve()
                    .bodyToMono(ExampleResponse::class.java)
                    .block()

    fun exchangeExampleResponse(): ResponseEntity<ExampleResponse>? =
            webClient.get()
                    .uri { it.pathSegment("api", "hello").build() }
                    .exchange()
                    .flatMap {
                        println("Raw status code: ${it.rawStatusCode()}")
                        it.toEntity(ExampleResponse::class.java)
                    }
                    .block()

    fun retrieveExampleResponseList(): List<ExampleResponse>? =
            webClient.get()
                    .uri { it.pathSegment("api", "hello-list").build() }
                    .retrieve()
                    .bodyToMono(typeReference<List<ExampleResponse>>())
                    .block()

    fun retrieveExampleResponseFromBearerSecuredEndpoint(token: String): ExampleResponse? =
            webClient.get()
                    .uri { it.pathSegment("api", "secured", "hello").build() }
                    .headers { it.setBearerAuth(token) }
                    .retrieve()
                    .bodyToMono(ExampleResponse::class.java)
                    .block()

    fun retrieveExampleResponseFromBasicSecuredEndpoint(username: String, password: String): ExampleResponse? =
            webClient.get()
                    .uri { it.pathSegment("api", "secured", "bearer", "hello").build() }
                    .headers { it.setBasicAuth(username, password) }
                    .retrieve()
                    .bodyToMono(ExampleResponse::class.java)
                    .block()

    fun sendJsonRequestBody(requestBody: ExampleRequest): ExampleResponse? =
            webClient.post()
                    .uri { it.pathSegment("api", "hello").build() }
                    .body(BodyInserters.fromValue(requestBody))
                    .retrieve()
                    .bodyToMono(ExampleResponse::class.java)
                    .block()

    fun sendFormEncodedRequestBody(name: String, value: String): ExampleResponse? =
            webClient.post()
                    .uri { it.pathSegment("api", "hello").build() }
                    .body(BodyInserters.fromFormData(name, value))
                    .retrieve()
                    .bodyToMono(ExampleResponse::class.java)
                    .block()
}

inline fun <reified T> typeReference() = object : ParameterizedTypeReference<T>() {}
