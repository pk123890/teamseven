package com.training.teamseven.service.threads;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.training.teamseven.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonThread extends  Thread{
    @Autowired
    JsonService jsonService;

    @Override
    public void run() {
        try {
            jsonService.jsonParser();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public Thread returnThread(){
        return currentThread();
    }
}
