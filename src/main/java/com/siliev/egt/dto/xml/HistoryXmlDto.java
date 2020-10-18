package com.siliev.egt.dto.xml;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "command")
public class HistoryXmlDto {

    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private String requestId;
    @JacksonXmlProperty(isAttribute = true, localName = "history")
    private HystoryXmlInnerAttributeInfo hystoryXmlInnerAttributeInfo;
    @JsonIgnore
    private String serviceName;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HystoryXmlInnerAttributeInfo {

        @JacksonXmlProperty(isAttribute = true)
        private String client;
        private String currency;
        private Integer period;

        public String getConsumer() {
            return client;
        }

        public void setConsumer(String consumer) {
            this.client = consumer;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public Integer getPeriod() {
            return period;
        }

        public void setPeriod(Integer period) {
            this.period = period;
        }
    }
}
