package com.siliev.egt.services;

import com.siliev.egt.dto.LatestRateDto;
import com.siliev.egt.entities.LatestRateEntity;
import java.util.List;

public interface LatestRateService {

    LatestRateDto findLatest();

    LatestRateDto save(LatestRateDto latestRateDto);

    LatestRateDto getCurrencyExchangeRate();

    List<LatestRateEntity> getHistory(String timeInterval);
}
