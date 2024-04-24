/*package com.dauphine.blogger.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @Operation(
            summary = "Hello by name endpoint",
            description = "Returns 'Hello {name}' by path variable"
    )
    public String hello (
            @Parameter(description = "Name to greet")
            @PathVariable String name
    ){
        return "Hello " + name;
    }
}
*/