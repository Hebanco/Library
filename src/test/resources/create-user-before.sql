SET FOREIGN_KEY_CHECKS = 0;
delete from user_role;
delete from usr;
SET FOREIGN_KEY_CHECKS = 1;

insert into usr(id, password, username, fio) values
(1, '$2a$08$DUMkBeogrqhRHELIxJhlZeV9msruT9l.JUNGC6aXC7ESSBTwttn3m', 'admin', 'fio'),
(2, '$2a$08$DUMkBeogrqhRHELIxJhlZeV9msruT9l.JUNGC6aXC7ESSBTwttn3m', 'u', 'fio');

insert into user_role(user_id, roles) values
(1, 'ADMIN'), (1,'TEACHER');
