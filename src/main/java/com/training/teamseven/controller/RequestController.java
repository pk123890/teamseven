package com.training.teamseven.controller;

import com.training.teamseven.service.threads.CsvThread;
import com.training.teamseven.service.threads.JsonThread;
import com.training.teamseven.service.threads.XmlThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class RequestController {

    @Autowired
    private JsonThread jsonThread;
    @Autowired
    private CsvThread csvThread;
    @Autowired
    private XmlThread xmlThread;


    @GetMapping("/file")
    public String execute() {
        jsonThread.start();
        xmlThread.start();
        csvThread.start();

//        try {
//            jsonThread.returnThread().join();
//            xmlThread.returnThread().join();
//            csvThread.returnThread().join();
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("threads are over");
        return "Ok";
    }


}
