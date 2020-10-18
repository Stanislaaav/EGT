package com.siliev.egt.controllers.impl;

import com.siliev.egt.controllers.JsonController;
import com.siliev.egt.dto.LatestRateDto;
import com.siliev.egt.dto.json.CurrentDto;
import com.siliev.egt.dto.json.HistoryDto;
import com.siliev.egt.entities.LatestRateEntity;
import com.siliev.egt.services.LatestRateService;
import com.siliev.egt.services.StatisticCollectorServiceService;
import com.siliev.egt.services.impl.StatisticCollectorServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonControllerImpl implements JsonController {

    private final StatisticCollectorServiceService statisticCollectorService;
    private final LatestRateService latestRateService;

    @Value("${service.name.one}")
    private String requestServiceNam;

    public JsonControllerImpl(StatisticCollectorServiceImpl statisticCollectorService,
        LatestRateService latestRateService) {
        this.statisticCollectorService = statisticCollectorService;
        this.latestRateService = latestRateService;
    }

    @Override
    public ResponseEntity<LatestRateDto> getCurrent(@RequestBody CurrentDto currentDto) {

        if (statisticCollectorService.findById((currentDto.getRequestId())).isPresent()) {
            throw new IllegalArgumentException("Request with the same id already exist.");
        }

        currentDto.setServiceName(requestServiceNam);
        statisticCollectorService.saveCurrent(currentDto);

        return ResponseEntity.ok().body(latestRateService.findLatest());
    }

    //TODO the response is not ritgh have to deal with utc datetime
    @Override
    public ResponseEntity<LatestRateDto> getHistory(@RequestBody HistoryDto historyDto) {

        if (statisticCollectorService.findById((historyDto.getRequestId())).isPresent()) {
            throw new IllegalArgumentException("Request with the same id already exist.");
        }

        historyDto.setServiceName(requestServiceNam);
        statisticCollectorService.saveHistory(historyDto);

        String timeInterval = String.format("%d HOURS", historyDto.getPeriod());

        //return ResponseEntity.ok().body(latestRateService.getHistory(timeInterval));

        return ResponseEntity.ok().body(latestRateService.findLatest());
    }

}
