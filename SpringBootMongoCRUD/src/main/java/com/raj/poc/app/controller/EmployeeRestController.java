package com.raj.poc.app.controller;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.raj.poc.app.model.Employee;
import com.raj.poc.app.model.MessageConstant;
import com.raj.poc.app.repo.impl.EmployeeRepoImpl;

@RestController
public class EmployeeRestController {

	@Autowired
	public EmployeeRepoImpl employeeRepoImpl;

	@Autowired
	MessageConstant messageService;

	@RequestMapping(value = "/createEmployee/", method = RequestMethod.POST)
	public ResponseEntity<MessageConstant> createEmployee(@RequestBody Employee emp, UriComponentsBuilder ucBuilder) {

		HttpStatus httpStatus = HttpStatus.CREATED;

		try {
			employeeRepoImpl.createEmployee(emp);
			messageService.setEmpDetails(emp);
			messageService.setMsg("Created Successfully");
			messageService.setStatus(true);
			messageService.setStatusMsg("Success");

		} catch (Exception ex) {
			messageService.setEmpDetails(null);
			messageService.setMsg(ex.getMessage());
			messageService.setStatus(false);
			messageService.setStatusMsg("Failed");
			httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
		}

		return new ResponseEntity<MessageConstant>(messageService, httpStatus);

	}

	@RequestMapping(value = "/updateEmployee/", method = RequestMethod.POST)
	public ResponseEntity<MessageConstant> updateEmployee(@RequestBody Employee emp, UriComponentsBuilder ucBuilder) {

		HttpStatus httpStatus = HttpStatus.ACCEPTED;

		try {
			employeeRepoImpl.updateEmployee(emp);
			messageService.setEmpDetails(emp);
			messageService.setMsg("Updated Successfully");
			messageService.setStatus(true);
			messageService.setStatusMsg("Success");

		} catch (Exception ex) {
			messageService.setEmpDetails(null);
			messageService.setMsg(ex.getMessage());
			messageService.setStatus(false);
			messageService.setStatusMsg("Failed");
			httpStatus = HttpStatus.NOT_MODIFIED;
		}

		return new ResponseEntity<MessageConstant>(messageService, httpStatus);

	}

	@RequestMapping(value = "/deleteEmployee/", method = RequestMethod.DELETE)
	public ResponseEntity<MessageConstant> deleteEmployee(@QueryParam("empId") Long empId,
			UriComponentsBuilder ucBuilder) {

		HttpStatus httpStatus = HttpStatus.ACCEPTED;

		try {
			employeeRepoImpl.deleteEmployee(empId);
			messageService.setEmpDetails(null);
			messageService.setMsg("Deleted Successfully");
			messageService.setStatus(true);
			messageService.setStatusMsg("Success");

		} catch (Exception ex) {
			messageService.setEmpDetails(null);
			messageService.setMsg(ex.getMessage());
			messageService.setStatus(false);
			messageService.setStatusMsg("Failed");
			httpStatus = HttpStatus.NOT_MODIFIED;
		}

		return new ResponseEntity<MessageConstant>(messageService, httpStatus);

	}

	@RequestMapping(value = "/listEmployees", method = RequestMethod.GET)
	public ResponseEntity<MessageConstant> listEmployees(UriComponentsBuilder ucBuilder) {
		HttpStatus httpStatus = HttpStatus.FOUND;

		try {
			List<Employee> alList = employeeRepoImpl.listAllEmployee();
			messageService.setEmpDetails(null);
			messageService.setEmployeeList(alList);
			messageService.setMsg("Deleted Successfully");
			messageService.setStatus(true);
			messageService.setStatusMsg("Success");

		} catch (Exception ex) {
			messageService.setEmpDetails(null);
			messageService.setMsg(ex.getMessage());
			messageService.setStatus(false);
			messageService.setStatusMsg("Failed");
			httpStatus = HttpStatus.NOT_FOUND;
		}

		return new ResponseEntity<MessageConstant>(messageService, httpStatus);
	}

	@RequestMapping(value = "/searchEmployee", method = RequestMethod.GET)
	public ResponseEntity<MessageConstant> searchEmployee(@QueryParam("empName") String empName,
			UriComponentsBuilder ucBuilder) {
		HttpStatus httpStatus = HttpStatus.FOUND;

		try {
			List<Employee> alList = employeeRepoImpl.findByEmpName(empName);
			messageService.setEmpDetails(null);
			messageService.setEmployeeList(alList);
			messageService.setMsg("Deleted Successfully");
			messageService.setStatus(true);
			messageService.setStatusMsg("Success");

		} catch (Exception ex) {
			messageService.setEmpDetails(null);
			messageService.setMsg(ex.getMessage());
			messageService.setStatus(false);
			messageService.setStatusMsg("Failed");
			httpStatus = HttpStatus.NOT_FOUND;
		}

		return new ResponseEntity<MessageConstant>(messageService, httpStatus);
	}

}
