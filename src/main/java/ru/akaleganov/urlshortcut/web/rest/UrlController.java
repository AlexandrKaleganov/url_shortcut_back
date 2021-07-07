package ru.akaleganov.urlshortcut.web.rest;

import java.security.Principal;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.akaleganov.urlshortcut.service.UrlService;
import ru.akaleganov.urlshortcut.service.dto.UrlDTO;
import ru.akaleganov.urlshortcut.service.url.UrlFilter;

/**
 * The type Url controller.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    /**
     * добавить новый урл
     *
     * @param url
     *         the url
     * @param principal
     *         the principal
     *
     * @return получить назад урл
     */
    @PostMapping("/url")
    public ResponseEntity<UrlDTO> addUrl(@RequestBody String url, Principal principal) {
        return ResponseEntity.ok().body(this.urlService.addUrl(url, principal.getName()));
    }

    /**
     * добавить новый урл
     *
     * @param pageable
     *         the pageable
     * @param urlFilter
     *         the url filter
     *
     * @return получить назад урл
     */
    @GetMapping("/url")
    public ResponseEntity<Page<UrlDTO>> findAllUrl(Pageable pageable, UrlFilter urlFilter) {
        return ResponseEntity.ok().body(this.urlService.findAllURl(pageable, urlFilter));
    }

    /**
     * добавить новый урл
     *
     * @param shortCut
     *         the short cut
     *
     * @return получить назад урл
     */
    @GetMapping("/getUrlByShortCut/{shortCut}")
    public ResponseEntity<UrlDTO> findAllUrl(@PathVariable String shortCut) {
        return ResponseEntity.ok().body(this.urlService.findUrlByShortCut(shortCut));
    }

}


