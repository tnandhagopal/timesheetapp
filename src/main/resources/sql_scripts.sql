drop database timesheet;
 
create database timesheet;

use timesheet;

#drop Table employee;

create table employee
(
	emp_id bigint(20) not null auto_increment,
	emp_user_name varchar(30) not null,
	emp_first_name varchar(50) not null,
	emp_second_name varchar(50) not null,
	emp_password varchar(255) not null,
	emp_contact_no varchar(30),
	emp_mail_id varchar(50),
	emp_status varchar(10),
	emp_dob date,
	emp_is_enabled boolean,
	emp_created_by varchar(10),
	emp_created_date datetime,
	emp_updated_by varchar(10),
	emp_updated_date datetime,
	constraint employee_pk primary key (emp_id),
	constraint employee_uk unique (emp_user_name)
);

insert into employee (emp_user_name, emp_first_name, emp_second_name, emp_password, emp_contact_no, emp_mail_id, emp_status, emp_dob, emp_is_enabled, emp_created_by, emp_created_date) values('51314542', 'Nandhagopal', 'Thangavelu', '$2a$10$KuJfH14T7Zr7ZtyIhtBeluV/PxwFZnG7R76AwmUCTl152SyoInUAe', '93374742', 'tnandhagopal@gmail.com', 'NEW',DATE('1986-06-21'), true,'ADMIN',CURRENT_TIMESTAMP( ));

insert into employee (emp_user_name, emp_first_name, emp_second_name, emp_password, emp_contact_no, emp_mail_id, emp_status, emp_dob, emp_is_enabled, emp_created_by, emp_created_date) values('51314543', 'Adhavan', 'Nandhagopal', '$2a$10$KuJfH14T7Zr7ZtyIhtBeluV/PxwFZnG7R76AwmUCTl152SyoInUAe', '12345678', 'adhavannandhagopal@gmail.com', 'NEW',DATE('2017-01-01'), true,'ADMIN',CURRENT_TIMESTAMP( ));


select * from employee;


#drop table project;

create table project(
	pro_id bigint(20) not null auto_increment,
	pro_code varchar(10) not null,
	pro_name varchar(50)  not null,
	pro_created_by varchar(10),
	pro_created_date datetime,
	pro_updated_by varchar(10),
	pro_updated_date datetime,
	constraint project_pk primary key (pro_id),
	constraint project_uk unique (pro_code)
);

insert into project(pro_code, pro_name, pro_created_by, pro_created_date) values('ABC1001', 'JAVA Development', 'ADMIN', CURRENT_TIMESTAMP( ));

insert into project(pro_code, pro_name, pro_created_by, pro_created_date) values('ABC1002', 'JAVA Testing', 'ADMIN', CURRENT_TIMESTAMP( ));

insert into project(pro_code, pro_name, pro_created_by, pro_created_date) values('ABC1003', 'Oracle Development', 'ADMIN', CURRENT_TIMESTAMP( ));

insert into project(pro_code, pro_name, pro_created_by, pro_created_date) values('XYZ1001', 'Oracle Testing', 'ADMIN', CURRENT_TIMESTAMP( ));

insert into project(pro_code, pro_name, pro_created_by, pro_created_date) values('SQW1001', 'Perl Development', 'ADMIN', CURRENT_TIMESTAMP( ));

insert into project(pro_code, pro_name, pro_created_by, pro_created_date) values('RTS1001', 'Perl Testing', 'ADMIN', CURRENT_TIMESTAMP( ));



#drop table employee_project;

create table employee_project(
	ep_id bigint(20) not null auto_increment,
	ep_emp_id bigint(20) not null,
	ep_pro_id bigint(20) not null,
	ep_start_date date not null,
	ep_end_date date not null,
	ep_created_by varchar(10),
	ep_created_date datetime,
	ep_updated_by varchar(10),
	ep_updated_date datetime,
	constraint ep_pk primary key (ep_id),
	constraint ep_uk unique (ep_emp_id, ep_pro_id),
	constraint ep_emp_fk foreign key (ep_emp_id) references employee(emp_id),
	constraint ep_pro_fk foreign key (ep_pro_id) references project(pro_id)
);

insert into employee_project(ep_emp_id, ep_pro_id, ep_start_date, ep_end_date, ep_created_by, ep_created_date) values((select emp_id from employee where emp_user_name='51314542'), (select pro_id from project where pro_code = 'ABC1001' ),DATE('2018-06-21'), DATE('2020-06-21'),  'ADMIN', CURRENT_TIMESTAMP());

insert into employee_project(ep_emp_id, ep_pro_id, ep_start_date, ep_end_date, ep_created_by, ep_created_date) values((select emp_id from employee where emp_user_name='51314542'), (select pro_id from project where pro_code = 'ABC1003' ),DATE('2018-06-21'), DATE('2020-06-21'),  'ADMIN', CURRENT_TIMESTAMP());

insert into employee_project(ep_emp_id, ep_pro_id, ep_start_date, ep_end_date, ep_created_by, ep_created_date) values((select emp_id from employee where emp_user_name='51314542'), (select pro_id from project where pro_code = 'ABC1002' ),DATE('2018-06-21'), DATE('2020-06-21'),  'ADMIN', CURRENT_TIMESTAMP());

insert into employee_project(ep_emp_id, ep_pro_id, ep_start_date, ep_end_date, ep_created_by, ep_created_date) values((select emp_id from employee where emp_user_name='51314543'), (select pro_id from project where pro_code = 'RTS1001' ),DATE('2018-06-21'), DATE('2020-06-21'),  'ADMIN', CURRENT_TIMESTAMP());

insert into employee_project(ep_emp_id, ep_pro_id, ep_start_date, ep_end_date, ep_created_by, ep_created_date) values((select emp_id from employee where emp_user_name='51314543'), (select pro_id from project where pro_code = 'XYZ1001' ),DATE('2018-06-21'), DATE('2020-06-21'),  'ADMIN', CURRENT_TIMESTAMP());

insert into employee_project(ep_emp_id, ep_pro_id, ep_start_date, ep_end_date, ep_created_by, ep_created_date) values((select emp_id from employee where emp_user_name='51314543'), (select pro_id from project where pro_code = 'SQW1001' ),DATE('2018-06-21'), DATE('2020-06-21'),  'ADMIN', CURRENT_TIMESTAMP());


create table task(
	task_id bigint(20) not null auto_increment,
	task_name varchar(50)  not null,
	task_created_by varchar(10),
	task_created_date datetime,
	task_updated_by varchar(10),
	task_updated_date datetime,
	constraint task_pk primary key (task_id),
	constraint task_uk unique (task_name)
);

insert into task( task_name, task_created_by, task_created_date) values( 'Development', 'ADMIN', CURRENT_TIMESTAMP( ));

insert into task( task_name, task_created_by, task_created_date) values( 'Testing', 'ADMIN', CURRENT_TIMESTAMP( ));

insert into task( task_name, task_created_by, task_created_date) values( 'Support', 'ADMIN', CURRENT_TIMESTAMP( ));

insert into task( task_name, task_created_by, task_created_date) values('Training', 'ADMIN', CURRENT_TIMESTAMP( ));


drop table employee_time_sheet;

create table employee_time_sheet(
	ets_id bigint(20) not null auto_increment,
	ets_ep_id bigint(20) not null,
	ets_task_id bigint(20) not null,
	ets_date date not null,
	ets_time int,
	ets_created_by varchar(10),
	ets_created_date datetime,
	ets_updated_by varchar(10),
	ets_updated_date datetime,
	constraint ets_pk primary key (ets_id),
	constraint ets_uk unique (ets_ep_id, ets_task_id, ets_date ),
	constraint ets_ep_fk foreign key (ets_ep_id) references employee_project(ep_id),
	constraint ets_task_fk foreign key (ets_task_id) references task(task_id)	

);


insert into employee_time_sheet(ets_ep_id, ets_task_id, ets_date, ets_time, ets_created_by, ets_created_date) values((select ep_id from employee, employee_project, project where emp_user_name='51314542' and ep_emp_id = emp_id and pro_id = ep_pro_id and pro_code ='ABC1001'), (select task_id from task where task_name='Development'),CURRENT_TIMESTAMP(),8, 'ADMIN', CURRENT_TIMESTAMP());


insert into employee_time_sheet(ets_ep_id, ets_task_id, ets_date, ets_time, ets_created_by, ets_created_date) values((select ep_id from employee, employee_project, project where emp_user_name='51314542' and ep_emp_id = emp_id and pro_id = ep_pro_id and pro_code ='ABC1001'), (select task_id from task where task_name='Testing'),CURRENT_TIMESTAMP(),8, 'ADMIN', CURRENT_TIMESTAMP());


#drop table employee_leave;

create table employee_leave(
	el_id bigint(20) not null auto_increment,
	el_emp_id bigint(20) not null,
	el_date date not null,
	el_status varchar(10),
	el_created_by varchar(10),
	el_created_date datetime,
	el_updated_by varchar(10),
	el_updated_date datetime,
	constraint el_pk primary key (el_id),
	constraint el_uk unique (el_emp_id, el_date),
	constraint el_emp_fk foreign key (el_emp_id) references employee(emp_id)
);

#drop table role;

create table role(
	role_id bigint(20) not null auto_increment,
	role_name varchar(10),
	role_created_by varchar(10),
	role_created_date datetime,
	role_updated_by varchar(10),
	role_updated_date datetime,
	constraint role_pk primary key (role_id),
	constraint role_uk unique (role_name)
);

insert into role (role_name, role_created_by, role_created_date) values('USER','ADMIN',CURRENT_TIMESTAMP());

insert into role (role_name, role_created_by, role_created_date) values('ADMIN','ADMIN',CURRENT_TIMESTAMP());

#drop table employee_role;

create table employee_role(
	er_id bigint(20) not null auto_increment,
	er_emp_id bigint(20) not null,
	er_role_id bigint(20) not null,
	er_created_by varchar(10),
	er_created_date datetime,
	er_updated_by varchar(10),
	er_updated_date datetime,
	constraint er_pk primary key (er_id),
	constraint er_uk unique (er_emp_id, er_role_id),
	constraint er_emp_fk foreign key (er_emp_id) references employee(emp_id),
	constraint er_role_fk foreign key (er_role_id) references role(role_id)
);

insert into employee_role (er_emp_id, er_role_id, er_created_by, er_created_date) values((select emp_id from employee where emp_user_name='51314542'), (select role_id from role where role_name='USER'),'ADMIN',CURRENT_TIMESTAMP());

insert into employee_role (er_emp_id, er_role_id, er_created_by, er_created_date) values((select emp_id from employee where emp_user_name='51314542'), (select role_id from role where role_name='ADMIN'),'ADMIN',CURRENT_TIMESTAMP());

insert into employee_role (er_emp_id, er_role_id, er_created_by, er_created_date) values((select emp_id from employee where emp_user_name='51314543'), (select role_id from role where role_name='USER'),'ADMIN',CURRENT_TIMESTAMP());


#drop table task;



#spring.datasource.url = jdbc:mysql://aagpv7jjakutzi.co4sfgsv7sfr.us-east-2.rds.amazonaws.com:3306/timesheet?useSSL=false&serverTimezone=Asia/Singapore
#spring.datasource.username = root
#spring.datasource.password = 6TnG6qcDtJK4Wjb

#spring.datasource.username = admin
#spring.datasource.password = admin@12345


CREATE USER 'admin'@'timesheet' IDENTIFIED BY 'admin@12345';

GRANT ALL PRIVILEGES ON timesheet.* TO 'admin'@'timesheet';
