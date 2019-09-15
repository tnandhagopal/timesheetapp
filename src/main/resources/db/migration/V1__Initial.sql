CREATE SCHEMA IF NOT EXISTS ts;

CREATE SEQUENCE ts.emp_seq START 1;

CREATE TABLE ts.employee
(
	emp_id bigint(20) NOT NULL DEFAULT nextval('ts.emp_seq'),
	emp_user_name varchar(30) not null,
	emp_first_name varchar(50) not null,
	emp_second_name varchar(50) not null,
	emp_password varchar(255) not null,
	emp_contact_no varchar(30),
	emp_mail_id varchar(50),
	emp_status varchar(10),
	emp_dob date,
	emp_is_enabled boolean,
	emp_CREATEd_by varchar(10),
	emp_CREATEd_date datetime,
	emp_updated_by varchar(10),
	emp_updated_date datetime,
	constraint ts.employee_pk primary key (emp_id),
	constraint ts.employee_uk unique (emp_user_name)
);

insert into ts.employee (emp_user_name, emp_first_name, emp_second_name, emp_password, emp_contact_no, emp_mail_id, emp_status, emp_dob, emp_is_enabled, emp_CREATEd_by, emp_CREATEd_date) values('51314542', 'Nandhagopal', 'Thangavelu', '$2a$10$KuJfH14T7Zr7ZtyIhtBeluV/PxwFZnG7R76AwmUCTl152SyoInUAe', '93374742', 'tnandhagopal@gmail.com', 'NEW',to_date('21/06/1986','DD/MM/YYYY'),TRUE,'ADMIN',sysdate);

insert into ts.employee (emp_user_name, emp_first_name, emp_second_name, emp_password, emp_contact_no, emp_mail_id, emp_status, emp_dob, emp_is_enabled, emp_CREATEd_by, emp_CREATEd_date) values('51314543', 'Adhavan', 'Nandhagopal', '$2a$10$KuJfH14T7Zr7ZtyIhtBeluV/PxwFZnG7R76AwmUCTl152SyoInUAe', '12345678', 'adhavan.nandhagopal@gmail.com', 'NEW',to_date('01/01/2017','DD/MM/YYYY'), true,'ADMIN',sysdate);


select * from ts.employee;

--drop TABLE project;
CREATE SEQUENCE ts.pro_seq START 1;

CREATE TABLE ts.project(
	pro_id bigint(20) NOT NULL DEFAULT nextval('ts.pro_seq'),
	pro_code varchar(10) not null,
	pro_name varchar(50)  not null,
	pro_CREATEd_by varchar(10),
	pro_CREATEd_date datetime,
	pro_updated_by varchar(10),
	pro_updated_date datetime,
	constraint ts.project_pk primary key (pro_id),
	constraint ts.project_uk unique (pro_code)
);

insert into ts.project(pro_code, pro_name, pro_CREATEd_by, pro_CREATEd_date) values('ABC1001', 'JAVA Development', 'ADMIN', sysdate);

insert into ts.project(pro_code, pro_name, pro_CREATEd_by, pro_CREATEd_date) values('ABC1002', 'JAVA Testing', 'ADMIN', sysdate);

insert into ts.project(pro_code, pro_name, pro_CREATEd_by, pro_CREATEd_date) values('ABC1003', 'Oracle Development', 'ADMIN', sysdate);

insert into ts.project(pro_code, pro_name, pro_CREATEd_by, pro_CREATEd_date) values('XYZ1001', 'Oracle Testing', 'ADMIN', sysdate);

insert into ts.project(pro_code, pro_name, pro_CREATEd_by, pro_CREATEd_date) values('SQW1001', 'Perl Development', 'ADMIN', sysdate);

insert into ts.project(pro_code, pro_name, pro_CREATEd_by, pro_CREATEd_date) values('RTS1001', 'Perl Testing', 'ADMIN', sysdate);


--drop TABLE employee_project;
CREATE SEQUENCE ts.ep_seq START 1;

CREATE TABLE ts.employee_project(
	ep_id bigint(20)  NOT NULL DEFAULT nextval('ts.ep_seq'),
	ep_emp_id bigint(20) not null,
	ep_pro_id bigint(20) not null,
	ep_start_date date not null,
	ep_end_date date not null,
	ep_CREATEd_by varchar(10),
	ep_CREATEd_date datetime,
	ep_updated_by varchar(10),
	ep_updated_date datetime,
	constraint ts.ep_pk primary key (ep_id),
	constraint ts.ep_uk unique (ep_emp_id, ep_pro_id),
	constraint ts.ep_emp_fk foreign key (ep_emp_id) references ts.employee(emp_id),
	constraint ts.ep_pro_fk foreign key (ep_pro_id) references ts.project(pro_id)
);

insert into ts.employee_project(ep_emp_id, ep_pro_id, ep_start_date, ep_end_date, ep_CREATEd_by, ep_CREATEd_date) values((select emp_id from ts.employee where emp_user_name='51314542'), (select pro_id from ts.project where pro_code = 'ABC1001' ),to_date('21/06/2019','DD/MM/YYYY'), to_date('21/06/2020','DD/MM/YYYY'),  'ADMIN', sysdate);

insert into ts.employee_project(ep_emp_id, ep_pro_id, ep_start_date, ep_end_date, ep_CREATEd_by, ep_CREATEd_date) values((select emp_id from ts.employee where emp_user_name='51314542'), (select pro_id from ts.project where pro_code = 'ABC1003' ),to_date('21/06/2019','DD/MM/YYYY'), to_date('21/06/2020','DD/MM/YYYY'),  'ADMIN', sysdate);

insert into ts.employee_project(ep_emp_id, ep_pro_id, ep_start_date, ep_end_date, ep_CREATEd_by, ep_CREATEd_date) values((select emp_id from ts.employee where emp_user_name='51314542'), (select pro_id from ts.project where pro_code = 'ABC1002' ),to_date('21/06/2019','DD/MM/YYYY'), to_date('21/06/2020','DD/MM/YYYY'),  'ADMIN', sysdate);

insert into ts.employee_project(ep_emp_id, ep_pro_id, ep_start_date, ep_end_date, ep_CREATEd_by, ep_CREATEd_date) values((select emp_id from ts.employee where emp_user_name='51314543'), (select pro_id from ts.project where pro_code = 'RTS1001' ),to_date('21/06/2019','DD/MM/YYYY'), to_date('21/06/2020','DD/MM/YYYY'),  'ADMIN', sysdate);

insert into ts.employee_project(ep_emp_id, ep_pro_id, ep_start_date, ep_end_date, ep_CREATEd_by, ep_CREATEd_date) values((select emp_id from ts.employee where emp_user_name='51314543'), (select pro_id from ts.project where pro_code = 'XYZ1001' ),to_date('21/06/2019','DD/MM/YYYY'), to_date('21/06/2020','DD/MM/YYYY'),  'ADMIN', sysdate);

insert into ts.employee_project(ep_emp_id, ep_pro_id, ep_start_date, ep_end_date, ep_CREATEd_by, ep_CREATEd_date) values((select emp_id from ts.employee where emp_user_name='51314543'), (select pro_id from ts.project where pro_code = 'SQW1001' ),to_date('21/06/2019','DD/MM/YYYY'), to_date('21/06/2020','DD/MM/YYYY'),  'ADMIN', sysdate);

CREATE SEQUENCE ts.task_seq START 1;

CREATE TABLE ts.task(
	task_id bigint(20) NOT NULL DEFAULT nextval('ts.task_seq'),
	task_name varchar(50)  not null,
	task_CREATEd_by varchar(10),
	task_CREATEd_date datetime,
	task_updated_by varchar(10),
	task_updated_date datetime,
	constraint ts.task_pk primary key (task_id),
	constraint ts.task_uk unique (task_name)
);

insert into ts.task( task_name, task_CREATEd_by, task_CREATEd_date) values( 'Development', 'ADMIN', sysdate);

insert into ts.task( task_name, task_CREATEd_by, task_CREATEd_date) values( 'Testing', 'ADMIN', sysdate);

insert into ts.task( task_name, task_CREATEd_by, task_CREATEd_date) values( 'Support', 'ADMIN', sysdate);

insert into ts.task( task_name, task_CREATEd_by, task_CREATEd_date) values('Training', 'ADMIN', sysdate);


--drop TABLE employee_time_sheet;

CREATE SEQUENCE ts.ets_seq START 1;

CREATE TABLE ts.employee_time_sheet(
	ets_id bigint(20) NOT NULL DEFAULT nextval('ts.ets_seq'),
	ets_ep_id bigint(20) not null,
	ets_task_id bigint(20) not null,
	ets_date date not null,
	ets_time int,
	ets_CREATEd_by varchar(10),
	ets_CREATEd_date datetime,
	ets_updated_by varchar(10),
	ets_updated_date datetime,
	constraint ts.ets_pk primary key (ets_id),
	constraint ts.ets_uk unique (ets_ep_id, ets_task_id, ets_date ),
	constraint ts.ets_ep_fk foreign key (ets_ep_id) references ts.employee_project(ep_id),
	constraint ts.ets_task_fk foreign key (ets_task_id) references ts.task(task_id)

);


insert into ts.employee_time_sheet(ets_ep_id, ets_task_id, ets_date, ets_time, ets_CREATEd_by, ets_CREATEd_date) values((select ep_id from ts.employee, ts.employee_project, ts.project where emp_user_name='51314542' and ep_emp_id = emp_id and pro_id = ep_pro_id and pro_code ='ABC1001'), (select task_id from ts.task where task_name='Development'), sysdate,8, 'ADMIN', sysdate);


--insert into employee_time_sheet(ets_ep_id, ets_task_id, ets_date, ets_time, ets_CREATEd_by, ets_CREATEd_date) values((select ep_id from ts.employee, employee_project, project where emp_user_name='51314542' and ep_emp_id = emp_id and pro_id = ep_pro_id and pro_code ='ABC1001'), (select task_id from task where task_name='Testing'), sysdate,8, 'ADMIN', sysdate);


--insert into employee_time_sheet(ets_ep_id, ets_task_id, ets_date, ets_time, ets_CREATEd_by, ets_CREATEd_date) values((select ep_id from ts.employee, employee_project, project where emp_user_name='51314542' and ep_emp_id = emp_id and pro_id = ep_pro_id and pro_code ='ABC1001'), (select task_id from task where task_name='Development'), sysdate,8, 'ADMIN', sysdate);


--insert into employee_time_sheet(ets_ep_id, ets_task_id, ets_date, ets_time, ets_CREATEd_by, ets_CREATEd_date) values((select ep_id from ts.employee, employee_project, project where emp_user_name='51314542' and ep_emp_id = emp_id and pro_id = ep_pro_id and pro_code ='ABC1001'), (select task_id from task where task_name='Testing'), sysdate,8, 'ADMIN', sysdate);


--insert into employee_time_sheet(ets_ep_id, ets_task_id, ets_date, ets_time, ets_CREATEd_by, ets_CREATEd_date) values((select ep_id from ts.employee, employee_project, project where emp_user_name='51314542' and ep_emp_id = emp_id and pro_id = ep_pro_id and pro_code ='ABC1001'), (select task_id from task where task_name='Development'), sysdate,8,  'ADMIN', sysdate);


--insert into employee_time_sheet(ets_ep_id, ets_task_id, ets_date, ets_time, ets_CREATEd_by, ets_CREATEd_date) values((select ep_id from ts.employee, employee_project, project where emp_user_name='51314542' and ep_emp_id = emp_id and pro_id = ep_pro_id and pro_code ='ABC1001'), (select task_id from task where task_name='Testing'), sysdate,8, 'ADMIN', sysdate);



--drop TABLE employee_leave;

CREATE SEQUENCE ts.el_seq START 1;

CREATE TABLE ts.employee_leave(
	el_id bigint(20) NOT NULL DEFAULT nextval('ts.el_seq'),
	el_emp_id bigint(20) not null,
	el_date date not null,
	el_status varchar(10),
	el_CREATEd_by varchar(10),
	el_CREATEd_date datetime,
	el_updated_by varchar(10),
	el_updated_date datetime,
	constraint ts.el_pk primary key (el_id),
	constraint ts.el_uk unique (el_emp_id, el_date),
	constraint ts.el_emp_fk foreign key (el_emp_id) references ts.employee(emp_id)
);

--drop TABLE role;

CREATE SEQUENCE ts.role_seq START 1;

CREATE TABLE ts.role(
	role_id bigint(20) NOT NULL DEFAULT nextval('ts.role_seq'),
	role_name varchar(10),
	role_CREATEd_by varchar(10),
	role_CREATEd_date datetime,
	role_updated_by varchar(10),
	role_updated_date datetime,
	constraint ts.role_pk primary key (role_id),
	constraint ts.role_uk unique (role_name)
);

insert into ts.role (role_name, role_CREATEd_by, role_CREATEd_date) values('USER','ADMIN',sysdate);

insert into ts.role (role_name, role_CREATEd_by, role_CREATEd_date) values('ADMIN','ADMIN',sysdate);

--drop TABLE employee_role;

CREATE SEQUENCE ts.er_seq START 1;

CREATE TABLE ts.employee_role(
	er_id bigint(20) NOT NULL DEFAULT nextval('ts.er_seq'),
	er_emp_id bigint(20) not null,
	er_role_id bigint(20) not null,
	er_CREATEd_by varchar(10),
	er_CREATEd_date datetime,
	er_updated_by varchar(10),
	er_updated_date datetime,
	constraint ts.er_pk primary key (er_id),
	constraint ts.er_uk unique (er_emp_id, er_role_id),
	constraint ts.er_emp_fk foreign key (er_emp_id) references ts.employee(emp_id),
	constraint ts.er_role_fk foreign key (er_role_id) references ts.role(role_id)
);

insert into ts.employee_role (er_emp_id, er_role_id, er_CREATEd_by, er_CREATEd_date) values((select emp_id from ts.employee where emp_user_name='51314542'), (select role_id from ts.role where role_name='USER'),'ADMIN',sysdate);

insert into ts.employee_role (er_emp_id, er_role_id, er_CREATEd_by, er_CREATEd_date) values((select emp_id from ts.employee where emp_user_name='51314542'), (select role_id from ts.role where role_name='ADMIN'),'ADMIN',sysdate);

insert into ts.employee_role (er_emp_id, er_role_id, er_CREATEd_by, er_CREATEd_date) values((select emp_id from ts.employee where emp_user_name='51314543'), (select role_id from ts.role where role_name='USER'),'ADMIN',sysdate);


--drop TABLE approval_status;

CREATE SEQUENCE ts.as_seq START 1;

CREATE TABLE ts.approval_status(
	as_id bigint(20) NOT NULL DEFAULT nextval('ts.as_seq'),
	as_name varchar(10),
	as_CREATEd_by varchar(10),
	as_CREATEd_date datetime,
	as_updated_by varchar(10),
	as_updated_date datetime,
	constraint ts.as_pk primary key (as_id),
	constraint ts.as_uk unique (as_name)
);

insert into ts.approval_status (as_name, as_CREATEd_by, as_CREATEd_date) values('APPROVED','ADMIN',sysdate);

insert into ts.approval_status (as_name, as_CREATEd_by, as_CREATEd_date) values('REJECTED','ADMIN',sysdate);

insert into ts.approval_status (as_name, as_CREATEd_by, as_CREATEd_date) values('PENDING','ADMIN',sysdate);

--drop TABLE employee_time_sheet_approval;

CREATE SEQUENCE ts.etsa_seq START 1;

CREATE TABLE ts.employee_time_sheet_approval(
	etsa_id bigint(20) NOT NULL DEFAULT nextval('ts.etsa_seq'),
	etsa_emp_id bigint(20) not null,
	etsa_as_id bigint(20) not null,
	etsa_date date not null,
	etsa_CREATEd_by varchar(10),
	etsa_CREATEd_date datetime,
	etsa_updated_by varchar(10),
	etsa_updated_date datetime,
	constraint ts.etsa_pk primary key (etsa_id),
	constraint ts.etsa_uk unique (etsa_emp_id, etsa_date),
	constraint ts.etsa_emp_fk foreign key (etsa_emp_id) references ts.employee(emp_id),
	constraint ts.etsa_as_fk foreign key (etsa_as_id) references ts.approval_status(as_id)
);


--CREATE USER 'admin'@'timesheet' IDENTIFIED BY 'admin@12345';

--GRANT ALL PRIVILEGES ON timesheet.* TO 'admin'@'timesheet';
