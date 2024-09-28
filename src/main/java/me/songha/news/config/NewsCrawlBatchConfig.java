package me.songha.news.config;

import me.songha.news.tasklet.NewsCrawlTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class NewsCrawlBatchConfig {

    @Bean
    public Job newsCrawlJob(JobRepository jobRepository, @Qualifier("newsCrawlStep") Step newsCrawlStep) {
        return new JobBuilder("newsCrawlJob", jobRepository)
                .start(newsCrawlStep)
                .build();
    }

    @Bean
    public Step newsCrawlStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, NewsCrawlTasklet newsCrawlTasklet) {
        return new StepBuilder("newsCrawlStep", jobRepository)
                .tasklet(newsCrawlTasklet, transactionManager)
                .build();
    }
}