package com.accenture.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accenture.entity.EmployeeEntity;
import com.accenture.model.EmployeeModel;

@Repository
public class EmployeeDaoWrapper {

	@Autowired
	EmployeeDao employeeDao;

	public int addEmployee(EmployeeModel employee) {

		EmployeeEntity employeeEntity = new EmployeeEntity();
		BeanUtils.copyProperties(employee, employeeEntity);
		EmployeeEntity emp = (EmployeeEntity) employeeDao.save(employeeEntity);
		return emp.getEmpId();
	}

	public List<EmployeeModel> getEmployeeDetails() {
		List<EmployeeEntity> empListEntity = employeeDao.findAll();
		List<EmployeeModel> empListModel = new ArrayList<EmployeeModel>();
		for (EmployeeEntity employeeEntity : empListEntity) {
			EmployeeModel employeeModel = new EmployeeModel();
			BeanUtils.copyProperties(employeeEntity, employeeModel);
			empListModel.add(employeeModel);
		}
		return empListModel;
	}

	public EmployeeModel getEmployeeDetailByEmployeeId(int employeeId) {
		EmployeeModel employeeModel = new EmployeeModel();
		System.out.println("empid " + employeeId);
		Optional<EmployeeEntity> employeeEntity = employeeDao.findById(employeeId);
		if (employeeEntity.isPresent()) {
			EmployeeEntity empEntity = employeeEntity.get();
			BeanUtils.copyProperties(empEntity, employeeModel);
			return employeeModel;
		} else {
			return null;
		}
	}

	public boolean deleteEmployee(int employeeId) {
		Optional<EmployeeEntity> employeeEntity = employeeDao.findById(employeeId);
		if (employeeEntity.isPresent()) {
			employeeDao.delete(employeeEntity.get());
			return true;
		}
		return false;
	}

	public boolean updateEmployee(int employeeId, EmployeeModel employeeModel) {
		Optional<EmployeeEntity> employeeEntity = employeeDao.findById(employeeId);
		if (employeeEntity.isPresent()) {
			EmployeeEntity empEntity = new EmployeeEntity();
			BeanUtils.copyProperties(employeeModel, empEntity);
			employeeDao.save(empEntity);
			return true;
		}
		return false;
	}
}