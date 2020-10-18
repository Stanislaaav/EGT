package com.siliev.egt.controllers;


import com.siliev.egt.dto.LatestRateDto;
import com.siliev.egt.dto.xml.CurrentXmlDto;
import com.siliev.egt.dto.xml.HistoryXmlDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("xml_api/command")
public interface XmlController {

    @PostMapping(value = "/current", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<LatestRateDto> getCurrent(@RequestBody CurrentXmlDto currentXmlDto);

    @PostMapping(value = "/history", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<LatestRateDto> getHistory(@RequestBody HistoryXmlDto historyXmlDto);
}
