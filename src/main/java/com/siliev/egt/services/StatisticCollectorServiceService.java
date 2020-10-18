package com.siliev.egt.services;

import com.siliev.egt.dto.StatisticCollectorDto;
import com.siliev.egt.dto.json.CurrentDto;
import com.siliev.egt.dto.json.HistoryDto;
import com.siliev.egt.dto.xml.CurrentXmlDto;
import com.siliev.egt.dto.xml.HistoryXmlDto;
import com.siliev.egt.entities.StatisiticCollectorEntity;
import java.util.List;
import java.util.Optional;

public interface StatisticCollectorServiceService {

    StatisticCollectorDto saveCurrent(CurrentDto dto);

    StatisticCollectorDto saveCurrent(CurrentXmlDto dto);

    StatisticCollectorDto saveHistory(HistoryDto dto);

    StatisticCollectorDto saveHistory(HistoryXmlDto dto);

    Optional<StatisiticCollectorEntity> findById(String id);

    List<StatisiticCollectorEntity> getHistory(String timeInterval);
}
