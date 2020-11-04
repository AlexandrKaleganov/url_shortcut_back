package ru.akaleganov.url_shortcut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.akaleganov.url_shortcut.domain.Statistic;
import ru.akaleganov.url_shortcut.domain.Url;

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
