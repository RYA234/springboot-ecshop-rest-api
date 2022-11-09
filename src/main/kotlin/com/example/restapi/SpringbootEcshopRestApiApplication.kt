package com.example.restapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

//@EnableWebMvc
@SpringBootApplication
class SpringbootEcshopRestApiApplication

fun main(args: Array<String>) {
	runApplication<SpringbootEcshopRestApiApplication>(*args)
}
