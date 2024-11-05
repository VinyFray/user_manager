package br.com.zup.user_manager.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/test")
public class ControllerTest {

    @GetMapping
    public String test(){
        return LocalDateTime.now().toString();
    }
}
