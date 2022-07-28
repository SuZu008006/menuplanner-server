package com.example.demo2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/greeting")
class Controller {
    @GetMapping
    fun getMessage(): List<String> {
        return listOf("hello")
    }
}