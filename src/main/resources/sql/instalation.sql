drop table lst_users;
drop table lst_groups;
drop table lst_roles;
drop table lst_user_groups;
drop table lst_group_roles;
drop table persistent_logins;
create table lst_users(
	user_id int not null auto_increment,
	username varchar(300) not null,
	pass varchar(300) not null,
	email varchar(300) not null,
	active int not null default 0,
	photo varchar(200) not null default 'notavailable.png',
	primary key(user_id)	
);

create table lst_groups(
	group_id int not null auto_increment,
	group_name varchar(200) not null,
	description varchar(300) not null default '',
	active int not null default 0,
	primary key(group_id)
);

create table lst_roles(
	role_id int not null auto_increment,
	role_name varchar(200) not null,
	description varchar(300) not null default '',
	active int not null default 0,
	primary key(role_id)
);

create table lst_group_roles(
	group_id int not null,
	role_id int not null,
	primary key(group_id,role_id)
);

create table lst_user_groups(
	user_id int not null,
	group_id int not null,
	primary key(user_id,group_id),
	foreign key(user_id) references lst_users(user_id),
	foreign key(group_id) references lst_groups(group_id)
);

create table persistent_logins ( 
  username varchar(100) not null, 
  series varchar(64) primary key, 
  token varchar(64) not null, 
  last_used timestamp not null 
);


/** 
 * insert user admin
 * **/

insert into lst_users(username,pass,email,active) values ('admin','admin','patartimotiustambunan@gmail.com',1);
insert into lst_groups(group_name,description,active) values ('Administrator','Administrator',1);
insert into lst_roles(role_name,description,active) values ('ROLE_USER','ROLE USER for used application',1);
insert into lst_group_roles(group_id,role_id) values(1,1);
insert into lst_user_groups(user_id,group_id) values(1,1);
