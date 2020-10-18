package com.siliev.egt.dto.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentDto {

    private String requestId;
    private Long timestamp;
    private String client;
    private String currency;
    private String serviceName;
}
