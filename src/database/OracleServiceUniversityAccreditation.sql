SELECT banner FROM v$version WHERE ROWNUM = 1;
show con_name
show pdbs
CREATE USER skhole IDENTIFIED BY Mantarlele;

CREATE USER University_Accreditation IDENTIFIED BY Mantarlele;
GRANT CREATE TABLE TO University_Accreditation;
GRANT create session TO University_Accreditation;
GRANT create view TO University_Accreditation;
GRANT create any trigger TO University_Accreditation;
GRANT create any procedure TO University_Accreditation;
GRANT create sequence TO University_Accreditation;
GRANT create synonym TO University_Accreditation;
GRANT ALL ON University_Accreditation.UNIVERSITY_USER TO University_Accreditation;
SELECT username, account_status FROM dba_users
WHERE username = 'University_Accreditation';

SELECT username, account_status FROM dba_users;
select * from dba_users where account_status = 'OPEN';
CREATE DATABASE Universityaccreditation;

CREATE TABLE University_Accreditation.UNIVERSITY_USER 
(
  USER_ID NUMBER(10) NOT NULL
, USER_NAME VARCHAR2(20) NOT NULL
, USER_TYPE VARCHAR2(20) NOT NULL
, USER_PASSWORD VARCHAR2(20),
CONSTRAINT USER_ID_PK PRIMARY KEY (USER_ID)
);

COMMENT ON COLUMN UNIVERSITYUSER.USERID IS 'Unique User Id';

COMMENT ON COLUMN UNIVERSITYUSER.TYPE IS 'UserType like admin or teacher or student';

COMMENT ON COLUMN UNIVERSITYUSER.PASSWORD IS 'password for the profile';


ALTER SESSION SET CONTAINER=XEPDB1;
select * from UNIVERSITY_USER;
select * from UNIVER
delete from UNIVERSITY_USER 
alter table UNIVERSITY_USER modify USERID number(10) not null;
constraint USER_ID_PK PRIMARY KEY(USERID);