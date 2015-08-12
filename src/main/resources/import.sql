-- OscarWeb SQL

INSERT INTO ROLE (ID, ROLE_NAME, USER_CREATED, DATE_CREATED) VALUES (1, 'ROLE_USER', 'Admin.Oscar', CURRENT_TIMESTAMP)
INSERT INTO ROLE(ID, ROLE_NAME, USER_CREATED, DATE_CREATED) VALUES(2, 'ROLE_MANAGER', 'Admin.Oscar', CURRENT_TIMESTAMP)
INSERT INTO ROLE(ID, ROLE_NAME, USER_CREATED, DATE_CREATED) VALUES(3, 'ROLE_ADMIN', 'Admin.Oscar', CURRENT_TIMESTAMP)
INSERT INTO ROLE(ID, ROLE_NAME, USER_CREATED, DATE_CREATED) VALUES(10, 'ROLE_ANONYMOUS', 'Admin.Oscar', CURRENT_TIMESTAMP)

-- Password=password
INSERT INTO ACCOUNT(ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, GENDER, BIRTHDAY, SALARY, EMAIL, ENABLED,ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, USER_CREATED, DATE_CREATED) VALUES(1, 'oscarwei168','$2a$10$xHRdgnOU4mXHbKxx0DkL2OdY280UNY7CrpBEnRVCCtfKjWUmmrew6', 'Oscar', 'Wei', 0, STR_TO_DATE('10/23/2000','%m/%d/%Y'), 100000, 'oscarwei168@msn.com', TRUE, TRUE, TRUE, TRUE, 'Admin.Oscar', CURRENT_TIMESTAMP)
INSERT INTO ACCOUNT(ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, GENDER, BIRTHDAY, SALARY, EMAIL, ENABLED,ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, USER_CREATED, DATE_CREATED) VALUES(2, 'somebody','$2a$10$xHRdgnOU4mXHbKxx0DkL2OdY280UNY7CrpBEnRVCCtfKjWUmmrew6', 'Some', 'Body', 1, STR_TO_DATE('10/23/2000','%m/%d/%Y'), 50000, 'somebody@msn.com', TRUE, TRUE, TRUE, TRUE, 'Admin.Oscar', CURRENT_TIMESTAMP)

INSERT INTO ACCOUNT_ROLE(ID_ACCOUNT, ID_ROLE) VALUES(1, 1)
INSERT INTO ACCOUNT_ROLE(ID_ACCOUNT, ID_ROLE) VALUES(1, 2)
INSERT INTO ACCOUNT_ROLE(ID_ACCOUNT, ID_ROLE) VALUES(1, 3)

INSERT INTO ACCOUNT_ROLE(ID_ACCOUNT, ID_ROLE) VALUES(2, 1)
