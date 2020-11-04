package ru.akaleganov.urlshortcut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;
import ru.akaleganov.urlshortcut.domain.Statistic;
import ru.akaleganov.urlshortcut.domain.Url;

import java.util.Optional;

/**
 * The interface Statistic repository.
 */
public interface StatisticRepository extends JpaRepository<Statistic, Long>, JpaSpecificationExecutor<Url> {
    /**
     * Find first by url id optional.
     *
     * @param id the id
     * @return the optional
     */
    @Transactional
    Optional<Statistic> findFirstByUrlId(Long id);
//    @Query("INSERT INTO statistic (url_id) VALUES (:urlId) ON DUPLICATE KEY UPDATE statistic.count = ctatistic.count +1")
//    @Modifying
//    Long updateStatistic(@Param("urlId")Long id);
}
