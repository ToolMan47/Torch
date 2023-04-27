package com.toolman.torch.controller;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class HelloController {

    @GetMapping("/randomNumber")
    public Flux<ServerSentEvent<Long>> randomNumber() {

        return Flux.interval(Duration.ofSeconds(1))
                .map(tick -> Tuples.of(tick, ThreadLocalRandom.current().nextLong()))
                .map(this::randomNumberEvent)
                .take(5);

    }
    @GetMapping("/servertime")
    public Flux<ServerSentEvent<LocalDateTime>> servertime() {

        return Flux.interval(Duration.ofSeconds(1))
                .map(tick -> LocalDateTime.now())
                .map(time -> ServerSentEvent.<LocalDateTime>builder().event("servertime").data(time).build());
    }

    private ServerSentEvent<Long>  randomNumberEvent(Tuple2<Long, Long> data) {
        return ServerSentEvent.<Long>builder()
                .event("randomNumber")
                .id(Long.toString(data.getT1()))
                .data(data.getT2())
                .build();
    }


}
