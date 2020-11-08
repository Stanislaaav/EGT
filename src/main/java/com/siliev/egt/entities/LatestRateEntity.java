package com.siliev.egt.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "latest_rates")
@Table(name = "latest_rates")
public class LatestRateEntity {

    @Id
    @Column(name = "pk_latest_rate")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "success")
    private boolean success;

    @Column(name = "timestamp")
    private Long timestamp;

    @Column(name = "base")
    private String base;

    @Column(name = "date")
    private Date date;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "latest_rates_map_of_rates", joinColumns = @JoinColumn(name = "pk_rates_id", referencedColumnName = "pk_latest_rate"))
    @MapKeyColumn(name = "currency_iso_code", length = 5)
    @Column(name = "currency_value")
    private Map<String, BigDecimal> rates;
}
