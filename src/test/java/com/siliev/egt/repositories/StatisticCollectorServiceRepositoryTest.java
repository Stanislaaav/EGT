package com.siliev.egt.repositories;

import static org.junit.jupiter.api.Assertions.*;

import com.siliev.egt.entities.LatestRateEntity;
import com.siliev.egt.entities.StatisiticCollectorEntity;
import java.sql.Timestamp;
import java.util.List;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class StatisticCollectorServiceRepositoryTest extends TestcontainersConfig{

    @Autowired
    private StatisticCollectorServiceRepository repository;

    @Test
    void test_save_read_one(){

        StatisiticCollectorEntity toSave = getStatisiticCollectorEntity();
        repository.save(toSave);

        StatisiticCollectorEntity taken = repository.findById(toSave.getRequestId()).get();

        assertEquals(toSave, taken);
        assertNotNull(taken.getRequestId());
    }

    private StatisiticCollectorEntity getStatisiticCollectorEntity(){
        String requestId = "1234-8785566777ffssggggdghdffgdfghgggssssf6665";
        Timestamp timestampToDatabase = null;
        String client = "13617162";
        String serviceName = "oooo";
        Long timestamp = null;

        StatisiticCollectorEntity toSave = StatisiticCollectorEntity.builder()
            .client(client)
            .requestId(requestId)
            .serviceName(serviceName)
            .timestamp(timestamp)
            .timestampToDatabase(timestampToDatabase)
            .build();

        return toSave;
    }
}