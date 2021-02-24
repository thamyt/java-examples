insert into `users` (username,password,enabled) values ('admin1','{bcrypt}$2a$12$pYZ69INCupe8RlIx96tgt.Fvjb/YZoZHadFadcB7nt0vSe3jHWTqe',1);
insert into `users` (username,password,enabled) values ('admin2','{sha256}5b79fc59098c53c031b3f23df955c129c1a5528f88c7dbb3cea28c23691a9bef42bba4dfef910e62',1);
insert into `users` (username,password,enabled) values ('admin3','{SHA-256}1faf265e9a3ac988d23269ef187e919b6292151e0c8b05d493edc209d3ac0999',1);
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
