CREATE TABLE IF NOT EXISTS employee (
  empId int(11) unsigned NOT NULL,
  empName varchar(20) DEFAULT NULL,
  salary double DEFAULT NULL,
  departmentCode int(11),
  PRIMARY KEY (empId)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

INSERT INTO employee (empId, empName,departmentCode, salary) VALUES
	(501, 'John'   ,101 , 123),
	(502, 'Andrew' ,102 , 124),
	(503, 'Mitchell' ,103 , 125);
commit;
