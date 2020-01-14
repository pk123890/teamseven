package com.training.teamseven.service.threads;

import com.training.teamseven.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsvThread extends Thread {
    @Autowired
    CsvService csvService;

    @Override
    public void run() {
        csvService.csvParser();
    }

    public Thread returnThread(){
        return currentThread();
    }
}
