package com.training.teamseven.controller;

import com.training.teamseven.service.CsvParser;
import com.training.teamseven.service.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class RequestController {

    @Autowired
    JsonParser jsonParser;
    @Autowired
    CsvParser csvParser;
    @GetMapping("/file")
    public String execute() {
//        jsonParser.jsonParser();
        csvParser.csvParser();
        return "Ok";
    }
}
