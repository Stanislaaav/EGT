package com.siliev.egt.dto.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDto {

    private String requestId;
    private Long timestamp;
    private String client;
    private String currency;
    private Integer period;
    private String serviceName;
}
