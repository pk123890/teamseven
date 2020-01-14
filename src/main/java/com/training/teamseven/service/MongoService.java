package com.training.teamseven.service;

import com.training.teamseven.entity.Employee;
import com.training.teamseven.entity.EmployeeMongo;

public interface MongoService {
    void save(EmployeeMongo employee);
}
