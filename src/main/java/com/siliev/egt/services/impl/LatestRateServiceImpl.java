package com.siliev.egt.services.impl;

import com.siliev.egt.dto.LatestRateDto;
import com.siliev.egt.entities.LatestRateEntity;
import com.siliev.egt.repositories.LatestRateRepository;
import com.siliev.egt.services.LatestRateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LatestRateServiceImpl implements LatestRateService {

    private final ModelMapper modelMapper;
    private final LatestRateRepository latestRateRepository;
    private final RestTemplate restTemplate;

    @Value("${currency.exchange.rate.url}")
    private String url;

    public LatestRateServiceImpl(ModelMapper modelMapper, LatestRateRepository latestRateRepository, RestTemplate restTemplate) {
        this.modelMapper = modelMapper;
        this.latestRateRepository = latestRateRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public LatestRateDto getCurrencyExchangeRate() {
        ResponseEntity<LatestRateDto> latestRate = restTemplate.getForEntity(url, LatestRateDto.class);
        return latestRate.getBody();
    }

    @Override
    public LatestRateDto findLatest() {
        return modelMapper.map(latestRateRepository.findLatest(), LatestRateDto.class);
    }

    @Override
    public LatestRateDto save(LatestRateDto latestRateDto) {
        LatestRateEntity latestRateEntity = latestRateRepository.save(modelMapper.map(latestRateDto, LatestRateEntity.class));
        return modelMapper.map(latestRateEntity, LatestRateDto.class);
    }

}
