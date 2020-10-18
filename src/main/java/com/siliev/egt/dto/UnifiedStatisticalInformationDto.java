package com.siliev.egt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnifiedStatisticalInformationDto {

    private String requestId;
    private Long timestamp;
    private String client;
    private String serviceName;
}
