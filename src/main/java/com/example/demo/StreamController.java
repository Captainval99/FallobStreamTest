package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class StreamController {
    private final ExecutorService executor = Executors.newCachedThreadPool();


    @GetMapping("/stream")
    public ResponseEntity<ResponseBodyEmitter> handleStream() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        Runnable streamService = new StreamService(emitter);
        Thread streamServiceThread = new Thread(streamService);
        streamServiceThread.start();
        return new ResponseEntity<>(emitter, HttpStatus.OK);
    }
}
