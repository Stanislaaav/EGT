package com.siliev.egt.services;

import com.siliev.egt.dto.LatestRateDto;

public interface LatestRateService {

    LatestRateDto findLatest();

    LatestRateDto save(LatestRateDto latestRateDto);

    LatestRateDto getCurrencyExchangeRate();

}
