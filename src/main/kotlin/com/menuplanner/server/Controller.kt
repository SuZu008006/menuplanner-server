package com.menuplanner.server

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/greeting")
@CrossOrigin(origins = ["http://localhost:3000", "https://menuplanner-web.herokuapp.com"])
class Controller {
    @GetMapping
    fun getMessage(): List<String> {
        return listOf("hello")
    }
}