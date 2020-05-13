package ru.akaleganov.job4j_url_shortcut.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping
    public ResponseEntity<String> getTest() {
        return ResponseEntity.ok("работает");
    }
}
