package com.altrevo.consultancy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JobPingScheduler {
    private static final Logger logger = LoggerFactory.getLogger(JobPingScheduler.class);
    private static final String URL = "https://consultancy-backend-q0a9.onrender.com/api/v1/public/careers";
    private final RestTemplate restTemplate = new RestTemplate();

    // Cron: Every 12 minutes
    @Scheduled(cron = "0 */12 * * * *")
    public void pingCareersEndpoint() {
        try {
            restTemplate.getForObject(URL, String.class);
//            logger.info("Pinged {} successfully", URL);
        } catch (Exception e) {
//            logger.error("Failed to ping {}: {}", URL, e.getMessage());
        }
    }
}

