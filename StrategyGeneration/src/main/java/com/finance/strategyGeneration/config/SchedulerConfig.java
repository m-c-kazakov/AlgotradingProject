package com.finance.strategyGeneration.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        var shutdownHook = new Thread(scheduler::shutdown);
        Runtime.getRuntime().addShutdownHook(shutdownHook);
        taskRegistrar.setScheduler(scheduler);
    }
}
