package com.example.aisa.controller;

import com.example.aisa.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;

    @PutMapping("/update/{container}")
    public ResponseEntity<Void> updateContainer(@PathVariable String container) {
        workService.updateContainer(container);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return new ResponseEntity<>(workService.getStatus(), HttpStatus.OK);
    }

    @PostMapping("/make/{coffee}")
    public ResponseEntity<String> makeCoffee(@PathVariable String coffee) {
        workService.makeCoffee(coffee);
        return getStatus();
    }
}
