package com.training.teamseven.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.teamseven.controller.dto.EmployeeDTO;
import com.training.teamseven.entity.Employee;
import com.training.teamseven.service.JsonService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

@Service
public class JsonServiceImpl implements JsonService {
    private static final String Topic="kafka_example";

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    @Override
    public void jsonParser() throws JsonProcessingException {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = null;
        Object obj = null;
        try {
            reader = new FileReader("/Users/prateekkoul/Downloads/team7-master/src/main/java/com/training/teamseven/files/employee.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            obj = jsonParser.parse(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = (JSONArray) obj;
        EmployeeDTO employee = null;
        ObjectMapper objectMapper = new ObjectMapper();
        int counter = 0;
        for (Object ob : jsonArray) {
            employee = new EmployeeDTO();
            JSONObject object = (JSONObject) ob;
            employee.setFirstName((String) object.get("firstName"));
            employee.setLastName((String) object.get("lastName"));
            employee.setExperience(Long.valueOf((Long) object.get("experience")).doubleValue());
            Date date = new Date((String) object.get("dateOfBirth"));
            employee.setDateOfBirth(date);
            System.out.println("Json");

            kafkaTemplate.send(Topic,objectMapper.writeValueAsString(employee));
            System.out.println(counter + " Counter");
            counter++;
        }

        System.out.println(employee.getExperience());
    }

}

