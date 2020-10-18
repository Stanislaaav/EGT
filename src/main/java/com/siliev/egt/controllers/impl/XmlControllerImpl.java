package com.siliev.egt.controllers.impl;

import com.siliev.egt.controllers.XmlController;
import com.siliev.egt.dto.LatestRateDto;
import com.siliev.egt.dto.json.HistoryDto;
import com.siliev.egt.dto.xml.CurrentXmlDto;
import com.siliev.egt.dto.xml.HistoryXmlDto;
import com.siliev.egt.entities.StatisiticCollectorEntity;
import com.siliev.egt.services.LatestRateService;
import com.siliev.egt.services.impl.StatisticCollectorServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XmlControllerImpl implements XmlController {

    private final StatisticCollectorServiceImpl statisticCollectorService;
    private final LatestRateService latestRateService;

    @Value("${service.name.two}")
    private String requestServiceName;

    public XmlControllerImpl(StatisticCollectorServiceImpl statisticCollectorService,
        LatestRateService latestRateService) {
        this.statisticCollectorService = statisticCollectorService;
        this.latestRateService = latestRateService;
    }

    @Override
    public ResponseEntity<LatestRateDto> getCurrent(@RequestBody CurrentXmlDto currentXmlDto) {

        if (statisticCollectorService.findById((currentXmlDto.getRequestId())).isPresent()) {
            throw new IllegalArgumentException("Request with the same id already exist.");
        }

        currentXmlDto.setServiceName(requestServiceName);
        statisticCollectorService.saveCurrent(currentXmlDto);

        return ResponseEntity.ok().body(latestRateService.findLatest());
    }

    @Override
    public ResponseEntity<List<StatisiticCollectorEntity>> getHistory(@RequestBody HistoryXmlDto historyXmlDto) {

        if (statisticCollectorService.findById((historyXmlDto.getRequestId())).isPresent()) {
            throw new IllegalArgumentException("Request with the same id already exist.");
        }

        historyXmlDto.setServiceName(requestServiceName);
        statisticCollectorService.saveHistory(historyXmlDto);

        String timeInterval = String.format("%d HOURS", historyXmlDto.getHystoryXmlInnerAttributeInfo().getPeriod());

        return ResponseEntity.ok().body(statisticCollectorService.getHistory(timeInterval));
    }
}
