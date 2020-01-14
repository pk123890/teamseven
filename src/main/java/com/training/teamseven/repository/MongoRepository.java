package com.training.teamseven.repository;


import com.training.teamseven.entity.Employee;

public interface MongoRepository extends org.springframework.data.mongodb.repository.MongoRepository<Employee,String>{

}


