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
public class CurrentXmlDto {

    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private String requestId;
    @JacksonXmlProperty(isAttribute = true, localName = "get")
    private CurrentXmlInnerAttributeInfo currentXmlInnerAttributeInfo;
    @JsonIgnore
    private String serviceName;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CurrentXmlInnerAttributeInfo {

        @JacksonXmlProperty(isAttribute = true)
        private String client;
        private String currency;

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
    }
}
