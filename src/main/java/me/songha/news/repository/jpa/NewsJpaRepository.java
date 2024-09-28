package me.songha.news.repository.jpa;

import me.songha.news.model.jpa.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsJpaRepository extends JpaRepository<News, Long> {
}