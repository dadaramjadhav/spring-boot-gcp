package com.accenture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "employee")
public class EmployeeEntity {
	
	@Id
	@Column(name="empid")
	private int empId;
	@Column(name="empname")
	private String empName;
	@Column(name="departmentcode")
	private String departmentCode;
	@Column(name="salary")
	private int salary;
}