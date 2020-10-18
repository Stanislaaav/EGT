package com.siliev.egt.schedulingtasks;

import com.siliev.egt.services.LatestRateService;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RatesCollectorScheduler {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final LatestRateService latestRateService;

    public RatesCollectorScheduler(LatestRateService latestRateService) {
        this.latestRateService = latestRateService;
    }

    //@Scheduled(cron = "0 0 * * * *") //Check tasks for running every hour
    @Scheduled(fixedRate = 7200000) //every two hours
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        //latestRateService.save(latestRateService.getCurrencyExchangeRate());
    }
}
