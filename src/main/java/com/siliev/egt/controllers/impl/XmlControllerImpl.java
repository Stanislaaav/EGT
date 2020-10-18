package com.siliev.egt.controllers.impl;

import com.siliev.egt.controllers.XmlController;
import com.siliev.egt.dto.LatestRateDto;
import com.siliev.egt.dto.xml.CurrentXmlDto;
import com.siliev.egt.dto.xml.HistoryXmlDto;
import com.siliev.egt.services.LatestRateService;
import com.siliev.egt.services.impl.StatisticCollectorServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XmlControllerImpl implements XmlController {

    private final StatisticCollectorServiceImpl unifiedStatisticalInformationService;
    private final LatestRateService latestRateService;

    @Value("${service.name.two}")
    private String requestServiceName;

    public XmlControllerImpl(StatisticCollectorServiceImpl unifiedStatisticalInformationService,
        LatestRateService latestRateService) {
        this.unifiedStatisticalInformationService = unifiedStatisticalInformationService;
        this.latestRateService = latestRateService;
    }

    @Override
    public ResponseEntity<LatestRateDto> getCurrent(@RequestBody CurrentXmlDto currentXmlDto) {

        if (unifiedStatisticalInformationService.findById((currentXmlDto.getRequestId())).isPresent()) {
            throw new IllegalArgumentException("Request with the same id already exist.");
        }

        currentXmlDto.setServiceName(requestServiceName);
        unifiedStatisticalInformationService.saveCurrent(currentXmlDto);

        return ResponseEntity.ok().body(latestRateService.findLatest());
    }

    //TODO the response is not ritgh have to deal with utc datetime
    @Override
    public ResponseEntity<LatestRateDto> getHistory(@RequestBody HistoryXmlDto historyXmlDto) {

        if (unifiedStatisticalInformationService.findById((historyXmlDto.getRequestId())).isPresent()) {
            throw new IllegalArgumentException("Request with the same id already exist.");
        }

        historyXmlDto.setServiceName(requestServiceName);
        unifiedStatisticalInformationService.saveHistory(historyXmlDto);

        return ResponseEntity.ok().body(latestRateService.findLatest());
    }
}
