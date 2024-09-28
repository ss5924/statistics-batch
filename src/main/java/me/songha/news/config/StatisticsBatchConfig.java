package me.songha.news.config;

import me.songha.news.tasklet.StatisticsTasklet;
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
public class StatisticsBatchConfig {

    @Bean
    public Job statisticsJob(JobRepository jobRepository, @Qualifier("statisticsStep") Step statisticsStep) {
        return new JobBuilder("statisticsJob", jobRepository)
                .start(statisticsStep)
                .build();
    }

    @Bean
    public Step statisticsStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, StatisticsTasklet statisticsTasklet) {
        return new StepBuilder("statisticsStep", jobRepository)
                .tasklet(statisticsTasklet, transactionManager)
                .build();
    }
}