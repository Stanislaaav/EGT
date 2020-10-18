package com.siliev.egt.services;

import com.siliev.egt.dto.UnifiedStatisticalInformationDto;
import com.siliev.egt.dto.json.CurrentDto;
import com.siliev.egt.dto.json.HistoryDto;
import com.siliev.egt.dto.xml.CurrentXmlDto;
import com.siliev.egt.dto.xml.HistoryXmlDto;
import com.siliev.egt.entities.StatisiticCollectorEntity;
import java.util.Optional;

public interface StatisticCollectorServiceService {

    UnifiedStatisticalInformationDto saveCurrent(CurrentDto dto);

    UnifiedStatisticalInformationDto saveCurrent(CurrentXmlDto dto);

    UnifiedStatisticalInformationDto saveHistory(HistoryDto dto);

    UnifiedStatisticalInformationDto saveHistory(HistoryXmlDto dto);

    Optional<StatisiticCollectorEntity> findById(String id);
}
