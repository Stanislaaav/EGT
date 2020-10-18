package com.siliev.egt.controllers;


import com.siliev.egt.dto.LatestRateDto;
import com.siliev.egt.dto.json.CurrentDto;
import com.siliev.egt.dto.json.HistoryDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/json_api")
public interface JsonController {

    @PostMapping(value = "/current", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LatestRateDto> getCurrent(@RequestBody CurrentDto currentDto);

    @PostMapping(value = "/history", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LatestRateDto> getHistory(@RequestBody HistoryDto historyDto);
}
