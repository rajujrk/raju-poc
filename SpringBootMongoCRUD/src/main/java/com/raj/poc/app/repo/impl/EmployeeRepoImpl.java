package com.raj.poc.app.repo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.WriteResult;
import com.raj.poc.app.model.Employee;

@Service(value = "employeeRepoImpl")
public class EmployeeRepoImpl extends EmployeeRepoAbstract {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<Employee> findByEmpName(String empName) {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(Employee.class, empName);
	}

	@SuppressWarnings("static-access")
	@Override
	public WriteResult updateEmployee(Employee emp) {
		// TODO Auto-generated method stub
		Update updateEmp = new Update();

		Long empId = emp.getEmpId();

		updateEmp.update("empName", emp.getEmpName());
		updateEmp.update("doj", emp.getDoj());
		updateEmp.update("jobTitle", emp.getJobTitle());
		updateEmp.update("department", emp.getDepartment());
		updateEmp.update("company", emp.getCompany());
		updateEmp.update("city", emp.getCity());
		updateEmp.update("country", emp.getCountry());
		updateEmp.update("email", emp.getEmail());
		updateEmp.update("phone", emp.getPhone());

		return mongoTemplate.updateFirst(new Query(Criteria.where("empId").is(empId)), updateEmp, Employee.class);
	}

	@Override
	public void createEmployee(Employee emp) {
		// TODO Auto-generated method stub
		mongoTemplate.insert(emp);
	}

	@Override
	public void deleteEmployee(Long empId) {
		// TODO Auto-generated method stub

		mongoTemplate.remove(new Query(Criteria.where("empId").is(empId)), Employee.class);
	}

	@Override
	public List<Employee> listAllEmployee() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(Employee.class);
	}

}
