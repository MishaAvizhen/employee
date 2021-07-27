
 create table employee
  (id integer not null auto_increment,
  date_of_birth date,
  first_name varchar(255),
  gender varchar(255),
  job_title varchar(255),
  last_name varchar(255),
  department_id integer,
  primary key (id)
  );

   create table department
 (id integer not null auto_increment,
 name varchar(255),
 primary key (id)
 );

 ALTER TABLE employee
 ADD CONSTRAINT department_id_fk
  FOREIGN KEY (department_id)
  REFERENCES department(id);