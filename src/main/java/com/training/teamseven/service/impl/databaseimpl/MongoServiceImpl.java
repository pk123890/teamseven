package com.training.teamseven.service.impl.databaseimpl;

import com.training.teamseven.entity.Employee;
import com.training.teamseven.repository.MongoRepository;
import com.training.teamseven.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoServiceImpl implements MongoService {
    @Autowired
    MongoRepository mongoRepository;
    @Override
    public void save(Employee employee) {
        mongoRepository.save(employee);
    }
}
