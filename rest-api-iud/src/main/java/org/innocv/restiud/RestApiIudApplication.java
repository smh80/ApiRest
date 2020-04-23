package org.innocv.restiud;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class RestApiIudApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiIudApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RestApiIudApplication.class, args);
	}
	
	@Bean(name = "asyncExecutor")
    public Executor asyncExecutor() 
    {
		LOGGER.info("Creating Async Executor.");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("AsynchThread-");
        executor.initialize();
        return executor;
    }

}
