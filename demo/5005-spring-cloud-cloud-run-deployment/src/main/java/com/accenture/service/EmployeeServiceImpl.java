package com.accenture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.dao.EmployeeDaoWrapper;
import com.accenture.model.EmployeeModel;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeDaoWrapper empDaoWrapper;

	@Override
	public int addEmployee(EmployeeModel employee) {
		return empDaoWrapper.addEmployee(employee);
	}

	@Override
	public List<EmployeeModel> getAllEmployee() {
		return empDaoWrapper.getEmployeeDetails();
	}

	@Override
	public EmployeeModel getEmployeeId(int employeeId) {
		return empDaoWrapper.getEmployeeDetailByEmployeeId(employeeId);
	}

	@Override
	public boolean deleteEmployee(int employeeId) {
		return empDaoWrapper.deleteEmployee(employeeId);
	}

	@Override
	public boolean updateEmployee(int employeeId, EmployeeModel empModel) {
		return empDaoWrapper.updateEmployee(employeeId, empModel);
	}

}
