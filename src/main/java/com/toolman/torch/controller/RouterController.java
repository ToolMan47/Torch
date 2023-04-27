package com.toolman.torch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class RouterController {

    @GetMapping("/")
    public String defaultPage() {
        return "index";
    }

    @GetMapping("/client")
    public Mono<String> client() {
        return Mono.just("client");
    }


}
