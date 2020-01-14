package com.training.teamseven.service.impl.databaseimpl;

import com.training.teamseven.entity.Employee;
import com.training.teamseven.repository.PostgresRepository;
import com.training.teamseven.service.PostgresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostgresServiceImpl implements PostgresService {
    @Autowired
    PostgresRepository postgresRepository;
    @Override
    public void save(Employee employee) {
        postgresRepository.save(employee);
    }
}
