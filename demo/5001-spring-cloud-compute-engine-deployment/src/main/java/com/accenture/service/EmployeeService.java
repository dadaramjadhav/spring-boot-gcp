package com.accenture.service;

import java.util.List;

import com.accenture.model.EmployeeModel;

public interface EmployeeService {
	public int addEmployee(EmployeeModel employee);
	public List<EmployeeModel> getAllEmployee();
	public EmployeeModel getEmployeeId(int employeeId);
	public boolean deleteEmployee(int employeeId);
	public boolean updateEmployee(int employeeId, EmployeeModel empModel);
}
