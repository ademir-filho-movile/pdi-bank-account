package com.movilepdibank.movilepdibank

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class MovilePdiBankApplication

fun main(args: Array<String>) {
    SpringApplication.run(MovilePdiBankApplication::class.java, *args)
}
