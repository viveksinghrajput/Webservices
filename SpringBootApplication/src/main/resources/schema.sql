drop table if exists employee_info;


create table employee_info (
	employee_Id integer not null auto_increment,
	employee_Name varchar(250),
	employee_Role varchar(250),
	employee_Emailid varchar(250),
	employee_Gender varchar(50),
	employee_Hobbies varchar(50),
	employee_City varchar(100),
	employee_State varchar(100),
	primary key(employee_Id)
);
