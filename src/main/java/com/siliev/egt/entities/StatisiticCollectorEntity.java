package com.siliev.egt.entities;

import com.siliev.egt.dto.xml.CurrentXmlDto;
import com.siliev.egt.dto.xml.HistoryXmlDto;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "statistic_collectors")
@Table(name = "statistic_collectors")
public class StatisiticCollectorEntity extends JdkSerializationRedisSerializer implements Serializable {

    @Transient
    private static final long serialVersionUID = 1L;

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
