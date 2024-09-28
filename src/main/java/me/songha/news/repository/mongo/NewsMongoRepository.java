package me.songha.news.repository.mongo;

import me.songha.news.model.jpa.News;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsMongoRepository extends MongoRepository<News, Long> {
}