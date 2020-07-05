package ru.akaleganov.url_shortcut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.akaleganov.url_shortcut.domain.Statistic;
import ru.akaleganov.url_shortcut.domain.Url;

import java.util.Optional;

public interface StatisticRepository extends JpaRepository<Statistic, Long>, JpaSpecificationExecutor<Url> {
    Optional<Statistic> findFirstByUrlId(Long id);
}
