package com.siliev.egt.entities;

import com.siliev.egt.dto.xml.CurrentXmlDto;
import com.siliev.egt.dto.xml.HistoryXmlDto;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "statistic_collectors")
@Table(name = "statistic_collectors")
public class StatisiticCollectorEntity {

    @Id
    @Column(name = "pk_request_id")
    private String requestId;
    @Column(name = "timestamp")
    private Timestamp timestampToDatabase;
    @Column(name = "client")
    private String client;
    @Column(name = "service_name")
    private String serviceName;
    @Transient
    private Long timestamp;

    public void populateFiled(CurrentXmlDto dto) {
        this.client = dto.getCurrentXmlInnerAttributeInfo().getClient();
        this.timestampToDatabase = TimestampCreator.cteateTimestamp();
    }

    public void populateFiled(HistoryXmlDto dto) {
        this.client = dto.getHystoryXmlInnerAttributeInfo().getClient();
        this.timestampToDatabase = TimestampCreator.cteateTimestamp();
    }

    public void setTimestamp(Long timestamp) {
        this.timestampToDatabase = TimestampCreator.cteateTimestamp(timestamp);
        this.timestamp = timestamp;
    }
}
