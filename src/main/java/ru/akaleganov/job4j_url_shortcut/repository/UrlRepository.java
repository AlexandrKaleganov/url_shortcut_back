package ru.akaleganov.job4j_url_shortcut.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.akaleganov.job4j_url_shortcut.domain.Url;
import ru.akaleganov.job4j_url_shortcut.service.dto.UrlDTO;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long>, JpaSpecificationExecutor<Url> {
    List<Url> findAllByOrigin(String origin);
    Optional<Url> findFirstByShortCut(String shortCut);

}
