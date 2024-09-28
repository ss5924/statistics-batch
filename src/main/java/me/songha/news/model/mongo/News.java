package me.songha.news.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "news")
public class News {
    @Id
    private Long id;
    private String title;
}