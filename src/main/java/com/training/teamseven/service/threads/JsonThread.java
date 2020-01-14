package com.training.teamseven.service.threads;

import com.training.teamseven.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonThread extends  Thread{
    @Autowired
    JsonService jsonService;

    @Override
    public void run() {
        jsonService.jsonParser();
    }

    public Thread returnThread(){
        return currentThread();
    }
}
