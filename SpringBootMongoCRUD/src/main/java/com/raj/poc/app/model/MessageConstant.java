package com.raj.poc.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageConstant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7933889527240171119L;

	private Boolean status;
	private String msg;
	private String statusMsg;

	private Boolean otpFlg = new Boolean(false);

	private Employee EmpDetails = new Employee();

	private List<Employee> employeeList = new ArrayList<>();

	public MessageConstant() {
		// TODO Auto-generated constructor stub
		this.msg = "";
		this.status = false;
		this.statusMsg = "";
	}

	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the statusMsg
	 */
	public String getStatusMsg() {
		return statusMsg;
	}

	/**
	 * @param statusMsg
	 *            the statusMsg to set
	 */
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	/**
	 * @return the otpFlg
	 */
	public Boolean getOtpFlg() {
		return otpFlg;
	}

	/**
	 * @param otpFlg
	 *            the otpFlg to set
	 */
	public void setOtpFlg(Boolean otpFlg) {
		this.otpFlg = otpFlg;
	}

	public Employee getEmpDetails() {
		return EmpDetails;
	}

	public void setEmpDetails(Employee empDetails) {
		EmpDetails = empDetails;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	

}
