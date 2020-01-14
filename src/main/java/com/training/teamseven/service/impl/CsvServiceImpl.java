package com.training.teamseven.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.teamseven.controller.dto.EmployeeDTO;
import com.training.teamseven.entity.Employee;
import com.training.teamseven.service.CsvService;
import com.training.teamseven.service.consumer.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CsvServiceImpl implements CsvService {
    private static final String Topic = "kafka_example";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void csvParser() {
        String csvFile = "/Users/prateekkoul/Downloads/team7-master/src/main/java/com/training/teamseven/files/employee.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] c = line.split(cvsSplitBy);
                double exp = new Double(c[3]);
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(c[2]);
                EmployeeDTO employee = new EmployeeDTO(c[0], c[1], date, exp);
                System.out.println("CSV");
                ObjectMapper objectMapper = new ObjectMapper();
                kafkaTemplate.send(Topic,objectMapper.writeValueAsString(employee));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
