package com.raj.poc.app.repo.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

	public void insertFromFile(File flUpFile) throws IOException {

		FileInputStream fis = new FileInputStream(flUpFile);

		XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

		XSSFSheet mySheet = myWorkBook.getSheetAt(0);

		Iterator<Row> rowIterator = mySheet.iterator();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Employee emp = new Employee();
			emp.setEmpId(Long.parseLong(row.getCell(0).getStringCellValue()));
			emp.setEmpName(row.getCell(1).getStringCellValue());
			emp.setJobTitle(row.getCell(2).getStringCellValue());
			emp.setDepartment(row.getCell(3).getStringCellValue());
			emp.setCompany(row.getCell(4).getStringCellValue());
			emp.setEmail(row.getCell(5).getStringCellValue());
			emp.setPhone(row.getCell(6).getStringCellValue());
			emp.setCity(row.getCell(7).getStringCellValue());
			emp.setCountry(row.getCell(8).getStringCellValue());
			emp.setDoj(row.getCell(9).getStringCellValue());
			createEmployee(emp);
		}
		myWorkBook.close();
	}

	@Override
	public Employee findByEmpById(String id) {
		// TODO Auto-generated method stub
		Employee emp = mongoTemplate.findById(id, Employee.class);

		return emp;
	}
}
