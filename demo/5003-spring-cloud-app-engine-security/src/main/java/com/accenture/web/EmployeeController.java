package com.accenture.web;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.accenture.model.EmployeeModel;
import com.accenture.service.EmployeeService;

import lombok.extern.apachecommons.CommonsLog;

@RestController
@CommonsLog
public class EmployeeController {

	@Value("${PORT}")
	private int port;
	
	@Value("${GAE_INSTANCE}")
	private String gaeInstanceId;
	
	@Value("${GOOGLE_CLOUD_PROJECT}")
	private String projectId;
	
	@Value("${GAE_APPLICATION}")
	private String gaeApplication;

	@Autowired
	EmployeeService empService;

	@GetMapping(value = { "/", "/welcome" }, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> welcome() throws UnknownHostException {
		
		RestTemplate rt = new RestTemplate();
		
		
		log.debug("called welcome endpoint");
		log.info("called welcome endpoint");
		return new ResponseEntity<String>("Welcome to app engine security using GCP running on , port "+ port +
				" instance id "+ gaeInstanceId.substring(95) + "  project id : " + projectId + "     gae application : "+ gaeApplication
				, HttpStatus.OK);
	}

	@PostMapping(value = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addEmployee(@RequestBody EmployeeModel employee) {
		int savedEmployeeId = empService.addEmployee(employee);
		log.debug("successfully added employee with id: " + savedEmployeeId);
		log.info("successfully added employee with id: " + savedEmployeeId);
		return new ResponseEntity<String>("successfully added employee with id: " + savedEmployeeId,
				HttpStatus.CREATED);
	}

	@GetMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllEmployees() {
		List<EmployeeModel> employeeList = empService.getAllEmployee();
		if (employeeList.isEmpty()) {
			log.debug("no record found in database ");
			log.info("no record found in database ");
			return new ResponseEntity<>("no record found in database ", HttpStatus.NOT_FOUND);
		} else {
			log.debug("total number of record fetched from database: " + employeeList.size());
			log.info("total number of record fetched from database: " + employeeList.size());
			return new ResponseEntity<List<EmployeeModel>>(employeeList, HttpStatus.FOUND);
		}
	}

	@GetMapping(value = "/employee/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmployeeId(@PathVariable("empId") int employeeId) {
		EmployeeModel employee = empService.getEmployeeId(employeeId);
		if (employee == null) {
			log.debug("no record found in database ");
			log.info("no record found in database ");
			return new ResponseEntity<>("no record found in database ", HttpStatus.NOT_FOUND);
		} else {
			log.debug("Employee detail is : " + employee);
			log.info("Employee detail is: " + employee);
			return new ResponseEntity<>("Employee detail is: " + employee, HttpStatus.FOUND);
		}
	}

	@DeleteMapping(value = "/employee/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteEmployeeById(@PathVariable("empId") int employeeId) {
		boolean isEmpDeleted = empService.deleteEmployee(employeeId);
		if (isEmpDeleted) {
			log.debug("Employee deleted with id : " + employeeId);
			log.info("Employee deleted with id : " + employeeId);
			return new ResponseEntity<>("Employee deleted with id : " + employeeId, HttpStatus.OK);
		} else {
			log.debug("no record found in database ");
			log.info("no record found in database ");
			return new ResponseEntity<>("no record found in database ", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/employee/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateEmployee(@PathVariable("empId") int employeeId,
			@RequestBody EmployeeModel empModel) {
		boolean isEmpUpdated = empService.updateEmployee(employeeId, empModel);
		if (isEmpUpdated) {
			log.debug("Employee updated with id : " + employeeId);
			log.info("Employee updated with id : " + employeeId);
			return new ResponseEntity<>("Employee updated with id : " + employeeId, HttpStatus.OK);
		} else {
			log.debug("no record found in database ");
			log.info("no record found in database ");
			return new ResponseEntity<>("no record found in database ", HttpStatus.NOT_FOUND);
		}
	}
}
