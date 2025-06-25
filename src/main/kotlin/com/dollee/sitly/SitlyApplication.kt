package com.dollee.sitly

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DolleeMarketApplication

fun main(args: Array<String>) {
    try {
        println(org.springframework.web.method.ControllerAdviceBean::class.java.protectionDomain.codeSource.location)
        runApplication<DolleeMarketApplication>(*args)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
