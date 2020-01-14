package com.training.teamseven.repository;

import com.training.teamseven.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface PostgresRepository extends CrudRepository<Employee,String> {
}
