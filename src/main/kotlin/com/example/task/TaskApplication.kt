package com.example.task

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
open class TaskApplication

fun main(args: Array<String>) {
	runApplication<TaskApplication>(*args)
}
