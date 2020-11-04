package ru.akaleganov.urlshortcut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.akaleganov.urlshortcut.domain.Url;

import java.util.List;
import java.util.Optional;

/**
 * The interface Url repository.
 */
public interface UrlRepository extends JpaRepository<Url, Long>, JpaSpecificationExecutor<Url> {
    /**
     * Find all by origin list.
     *
     * @param origin the origin
     * @return the list
     */
    List<Url> findAllByOrigin(String origin);

    /**
     * Find first by short cut optional.
     *
     * @param shortCut the short cut
     * @return the optional
     */
    Optional<Url> findFirstByShortCut(String shortCut);

}
