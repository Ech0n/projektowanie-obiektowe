package com.example.spring_boot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.CommandLineRunner



@SpringBootApplication
class SpringKotlinAuthApplication : CommandLineRunner {
	override fun run(vararg args: String?) {
		println("App started...")
	}
}

fun main(args: Array<String>) {
	runApplication<SpringKotlinAuthApplication>(*args)
}