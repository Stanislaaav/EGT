package com.siliev.egt.services.impl;

import com.siliev.egt.dto.UnifiedStatisticalInformationDto;
import com.siliev.egt.dto.json.CurrentDto;
import com.siliev.egt.dto.json.HistoryDto;
import com.siliev.egt.dto.xml.CurrentXmlDto;
import com.siliev.egt.dto.xml.HistoryXmlDto;
import com.siliev.egt.entities.StatisiticCollectorEntity;
import com.siliev.egt.repositories.StatisticCollectorServiceRepository;
import com.siliev.egt.services.EventService;
import com.siliev.egt.services.StatisticCollectorServiceService;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class StatisticCollectorServiceImpl implements StatisticCollectorServiceService {

    private final StatisticCollectorServiceRepository statisticCollectorServiceRepository;
    private final ModelMapper modelMapper;
    private final EventService eventService;

    public StatisticCollectorServiceImpl(StatisticCollectorServiceRepository statisticCollectorServiceRepository, ModelMapper modelMapper,
        EventService eventService) {
        this.statisticCollectorServiceRepository = statisticCollectorServiceRepository;
        this.modelMapper = modelMapper;
        this.eventService = eventService;
    }

    @Override
    public UnifiedStatisticalInformationDto saveCurrent(CurrentDto dto) {
        StatisiticCollectorEntity entity =
            statisticCollectorServiceRepository.save(modelMapper.map(dto, StatisiticCollectorEntity.class));
        eventService.sendMessageToBroker(entity);
        return modelMapper.map(entity, UnifiedStatisticalInformationDto.class);
    }

    @Override
    public UnifiedStatisticalInformationDto saveCurrent(CurrentXmlDto dto) {
        StatisiticCollectorEntity entity = modelMapper.map(dto, StatisiticCollectorEntity.class);
        entity.populateFiled(dto);
        statisticCollectorServiceRepository.save(entity);
        eventService.sendMessageToBroker(entity);
        return modelMapper.map(entity, UnifiedStatisticalInformationDto.class);
    }

    @Override
    public UnifiedStatisticalInformationDto saveHistory(HistoryDto dto) {
        StatisiticCollectorEntity entity = modelMapper.map(dto, StatisiticCollectorEntity.class);
        statisticCollectorServiceRepository.save(entity);
        eventService.sendMessageToBroker(entity);
        return modelMapper.map(entity, UnifiedStatisticalInformationDto.class);
    }

    @Override
    public UnifiedStatisticalInformationDto saveHistory(HistoryXmlDto dto) {
        StatisiticCollectorEntity entity = modelMapper.map(dto, StatisiticCollectorEntity.class);
        entity.populateFiled(dto);
        statisticCollectorServiceRepository.save(entity);
        eventService.sendMessageToBroker(entity);
        return modelMapper.map(entity, UnifiedStatisticalInformationDto.class);
    }

    @Override
    //TODO to check why it doesnt work
    //@Cacheable(value = "currency", key="#timestamp")  //value - the name of the cache, key - name of the key
    public Optional<StatisiticCollectorEntity> findById(String id) {
        return statisticCollectorServiceRepository.findById(id);
    }
}
