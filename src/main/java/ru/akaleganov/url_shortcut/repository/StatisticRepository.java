package ru.akaleganov.url_shortcut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.akaleganov.url_shortcut.domain.Statistic;
import ru.akaleganov.url_shortcut.domain.Url;

import java.util.Optional;

/**
 * The interface Statistic repository.
 */
@Transactional(isolation = Isolation.SERIALIZABLE)
public interface StatisticRepository extends JpaRepository<Statistic, Long>, JpaSpecificationExecutor<Url> {
    /**
     * Find first by url id optional.
     *
     * @param id the id
     * @return the optional
     */
    @Transactional
    Optional<Statistic> findFirstByUrlId(Long id);
}
