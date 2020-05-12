insert into usr (id, username, password, fio)
values(
          1,
          'admin',
          '$2a$08$GH8a/9WeKc7mdnEJ/J7tFOb3kQxV4GMH3kjBZQINwKMj0cxD3YsZi',
          'sa'
      );

insert into user_role (user_id, roles)
    values (1,'ADMIN');