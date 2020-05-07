package ru.akaleganov.job4j_url_shortcut.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
@CrossOrigin("http://localhost:4200")
public class HomeController {

    @GetMapping
    public ResponseEntity<String> getTest() {
        return ResponseEntity.ok("работает");
    }
}
