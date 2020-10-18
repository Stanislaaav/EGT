package com.siliev.egt.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement
public class LatestRateDto {

    private boolean success;
    private Long timestamp;
    private String base;
    private Date date;
    private Map<String, BigDecimal> rates;
}
