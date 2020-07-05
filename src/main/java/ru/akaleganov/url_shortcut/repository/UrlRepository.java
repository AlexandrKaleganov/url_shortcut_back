package ru.akaleganov.url_shortcut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.akaleganov.url_shortcut.domain.Url;

import java.util.List;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long>, JpaSpecificationExecutor<Url> {
    List<Url> findAllByOrigin(String origin);
    Optional<Url> findFirstByShortCut(String shortCut);

}
