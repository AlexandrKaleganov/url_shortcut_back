package ru.akaleganov.url_shortcut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.akaleganov.url_shortcut.domain.Statistic;
import ru.akaleganov.url_shortcut.domain.Url;

import java.util.Optional;
@Transactional(isolation = Isolation.SERIALIZABLE)
public interface StatisticRepository extends JpaRepository<Statistic, Long>, JpaSpecificationExecutor<Url> {
    @Transactional
    Optional<Statistic> findFirstByUrlId(Long id);
}
