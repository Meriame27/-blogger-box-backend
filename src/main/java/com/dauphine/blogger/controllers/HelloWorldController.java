package com.dauphine.blogger.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HelloWorldController {
    @GetMapping("hello-world")
    public String helloWorld(){
        return "Hello World!";
    }
    @GetMapping("hello")
    public String helloBynName(@RequestParam String name){
        return "Hello " + name;
    }

    @GetMapping("hello/{name}")
    public String hello (@PathVariable String name){
        return "Hello " + name;
    }
}
