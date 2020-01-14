package com.training.teamseven.repository;


import com.training.teamseven.entity.Employee;
import com.training.teamseven.entity.EmployeeMongo;

public interface MongoRepository extends org.springframework.data.mongodb.repository.MongoRepository<EmployeeMongo,String>{

}


