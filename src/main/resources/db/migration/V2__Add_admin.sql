insert into usr (id, username, password, active)
values(
          1,
          'admin',
          '$2a$08$GH8a/9WeKc7mdnEJ/J7tFOb3kQxV4GMH3kjBZQINwKMj0cxD3YsZi',
          true
      );

insert into user_role (user_id, roles)
    values (1,'USER'), (1,'ADMIN');