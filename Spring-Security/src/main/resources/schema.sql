drop table if exists employee_info;
drop table if exists user_info;


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

create table user_info (
		id integer not null auto_increment,
        username varchar(50) not null,
        country varchar(30),
        enabled smallint,
        full_name varchar(100),
        password varchar(800) not null,
        role varchar(50),
        primary key (id)
    );
  
