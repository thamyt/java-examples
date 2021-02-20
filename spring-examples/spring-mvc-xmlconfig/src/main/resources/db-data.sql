insert into `users` (username,password,enabled) values ('admin1','{bcrypt}$2a$12$pYZ69INCupe8RlIx96tgt.Fvjb/YZoZHadFadcB7nt0vSe3jHWTqe',1);
insert into `users` (username,password,enabled) values ('admin2','{SHA256}Ix7MfReNpfIpg7xXlZk5bWwTmkV5h64e4AJtiEMtanI=',1);
insert into `users` (username,password,enabled) values ('user1','{bcrypt}$2a$12$Xtk9Oh4utoObopLhgmzFIeRK5QK05U0mhu2cPhXBjRMlm.7r/Cb4m',1);
insert into `users` (username,password,enabled) values ('user2','{bcrypt}$2a$12$Rvd/XqX5a3OBxi2kO1shFOZgbtkQo78Uql7/iId0Wqg8o59YGPFSq',1);
	
insert into authorities values ('admin1','ROLE_ADMIN');
insert into authorities values ('admin2','ROLE_ADMIN');
insert into authorities values ('user1','ROLE_USER');
insert into authorities values ('user2','ROLE_USER'); 

insert into `groups`(group_name) values ('Users');
insert into `groups`(group_name) values ('Administrators');

insert into `group_authorities`(group_id, authority) 
	select id,'ROLE_USER' from `groups` where group_name='Users';
insert into `group_authorities`(group_id, authority) 
	select id,'ROLE_USER' from `groups` where group_name='Administrators';
insert into `group_authorities`(group_id, authority) 
	select id,'ROLE_ADMIN' from `groups` where group_name='Administrators';

insert into `group_members`(group_id, username)
	select id,'user1' from `groups` where group_name='Users';
insert into `group_members`(group_id, username)
	select id,'user2' from `groups` where group_name='Users';
insert into `group_members`(group_id, username)
	select id,'admin1' from `groups` where group_name='Users';
insert into `group_members`(group_id, username)
	select id,'admin2' from `groups` where group_name='Users';
insert into `group_members`(group_id, username) 
 	select id,'admin1' from `groups` where group_name='Administrators';
insert into `group_members`(group_id, username) 
 	select id,'admin2' from `groups` where group_name='Administrators';
