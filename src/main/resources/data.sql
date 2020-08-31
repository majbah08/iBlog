-- user table ; pass='password' in plaintext
insert into app_users values (1,'sa','$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm','Super Admin','sa@iblog,com',1)

-- Roles
INSERT INTO ROLE (role_id, role)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO ROLE (role_id, role)
VALUES (2, 'ROLE_BLOGGER');

-- User Roles
INSERT INTO USER_ROLE (user_id, role_id)
VALUES (1, 1);

