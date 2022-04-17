use org;
create table employee(id varchar(160) , email varchar(160) unique, firstname varchar(160) , lastname varchar(160) , contact bigint , pass varchar(280) , primary key (id));
create table login_details(id varchar(160) , logged_in BIT , last_logged_in timestamp default current_timestamp() , employee_id varchar(160), 
constraint fk_login_details_employee foreign key(employee_id) references employee(id) on delete cascade);


select * from employee;
delete from employee;

select * from login_details;
delete from login_details;
