package me.songha.news.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Component
public class NewsCrawlScheduler {
    private final JobLauncher jobLauncher;
    @Qualifier("newsCrawlJob")
    private final Job newsCrawlJob;

    @Scheduled(cron = "*/30 * * * * *")
    public void runJob() {
        JobParameters params = new JobParametersBuilder()
                .addLocalDateTime("startAt", LocalDateTime.now())
                .toJobParameters();
        try {
            jobLauncher.run(newsCrawlJob, params);
        } catch (Exception e) {
            log.error("An error occurred while the job was in progress.", e);
        }
    }
}