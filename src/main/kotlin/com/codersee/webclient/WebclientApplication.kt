package com.codersee.webclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebclientApplication

fun main(args: Array<String>) {
	runApplication<WebclientApplication>(*args)
}
