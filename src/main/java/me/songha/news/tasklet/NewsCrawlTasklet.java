package me.songha.news.tasklet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.songha.news.model.jpa.News;
import me.songha.news.repository.jpa.NewsJpaRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@Component
public class NewsCrawlTasklet implements Tasklet {
    private static final String NEWS_URL = "https://news.naver.com/section/102";
    private static final String HEADLINE_SELECTOR = ".sa_text_strong";
    private final NewsJpaRepository newsRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        log.info("Starting news crawling task");

        try {
            Document doc = Jsoup.connect(NEWS_URL).get();
            Elements headlines = doc.select(HEADLINE_SELECTOR);

            if (headlines.isEmpty()) {
                log.warn("No headlines found.");
            } else {
                log.info("Number of headlines : {}", headlines.size());
                for (Element headline : headlines) {
                    log.info("Headline : {}", headline.text());

                    News news = new News();
                    news.setTitle(headline.text());
                    newsRepository.save(news);
                }
            }
        } catch (IOException e) {
            log.error("An error occurred while the tasklet was in progress e.msg: {}", e.getMessage(), e);
        }

        log.info("News crawling task completed");
        return RepeatStatus.FINISHED;
    }
}