package com.training.teamseven.service.threads;

import com.training.teamseven.service.XmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XmlThread extends Thread {

    @Autowired
    XmlService xmlService;

    @Override
    public void run() {
        xmlService.xmlParser();
    }

    public Thread returnThread(){
        return currentThread();
    }
}
