package com.example.restapi.entity

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {

    @GetMapping("/test")
    fun aaa():String{
        return "aaa";
    }
}