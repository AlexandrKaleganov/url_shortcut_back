package ru.akaleganov.urlshortcut.web.rest;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.akaleganov.urlshortcut.service.UrlService;
import ru.akaleganov.urlshortcut.service.dto.UrlDTO;
import ru.akaleganov.urlshortcut.service.url.UrlFilter;

import java.security.Principal;

/**
 * The type Url controller.
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UrlController {
    private final UrlService urlService;


    /**
     * добавить новый урл
     *
     * @param url       the url
     * @param principal the principal
     * @return получить назад урл
     */
    @PostMapping("/url")
    public ResponseEntity<UrlDTO> addUrl(@RequestBody String url, Principal principal) {
        return ResponseEntity.ok().body(this.urlService.addUrl(url, principal.getName()));
    }

    /**
     * добавить новый урл
     *
     * @param pageable  the pageable
     * @param urlFilter the url filter
     * @return получить назад урл
     */
    @GetMapping("/url")
    public ResponseEntity<Page<UrlDTO>> findAllUrl(Pageable pageable, UrlFilter urlFilter) {
        return ResponseEntity.ok().body(this.urlService.findAllURl(pageable, urlFilter));
    }

    /**
     * добавить новый урл
     *
     * @param shortCut the short cut
     * @return получить назад урл
     */
    @GetMapping("/getUrlByShortCut/{shortCut}")
    public ResponseEntity<UrlDTO> findAllUrl(@PathVariable String shortCut) {
        return ResponseEntity.ok().body(this.urlService.findUrlByShortCut(shortCut));
    }

}


