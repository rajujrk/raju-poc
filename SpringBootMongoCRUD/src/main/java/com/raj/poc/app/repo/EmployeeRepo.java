package com.raj.poc.app.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.raj.poc.app.model.Employee;

public interface EmployeeRepo extends MongoRepository<Employee, Long> {

	

}
