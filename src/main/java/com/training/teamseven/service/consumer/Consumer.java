package com.training.teamseven.service.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.teamseven.controller.dto.EmployeeDTO;
import com.training.teamseven.entity.Employee;
import com.training.teamseven.entity.EmployeeMongo;
import com.training.teamseven.service.MongoService;
import com.training.teamseven.service.PostgresService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    @Autowired
    PostgresService postgresService;
    @Autowired
    MongoService mongoService;
    private static int count =0;
    EmployeeDTO employeeDTO;
    @KafkaListener(topics = "kafka_example", groupId = "group_id")
    public void consume(String message){
        System.out.println("In Consumer");
        ObjectMapper mapper = new ObjectMapper();
        try {
            employeeDTO=mapper.readValue(message,EmployeeDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if(count<150){

            EmployeeMongo employee = new EmployeeMongo();
            BeanUtils.copyProperties(employeeDTO,employee);
            mongoService.save(employee);
        }
        else{
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDTO,employee);
            postgresService.save(employee);

        }
        count++;

    }
}
