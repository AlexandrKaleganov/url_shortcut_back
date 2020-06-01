package ru.akaleganov.job4j_url_shortcut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.akaleganov.job4j_url_shortcut.domain.Url;

import java.util.List;

public interface UrlRepository extends JpaRepository<Url, Long>, JpaSpecificationExecutor<Url> {
    List<Url> findAllByOrigin(String origin);
    List<Url> findAllByNewOrigin(String newOrigin);
}
