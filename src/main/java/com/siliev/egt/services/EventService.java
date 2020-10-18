package com.siliev.egt.services;

import com.siliev.egt.entities.StatisiticCollectorEntity;

public interface EventService {

    void sendMessageToBroker(StatisiticCollectorEntity event);
}
