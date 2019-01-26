create user test identified by testpw;
grant connect,resource,dba to test;
grant unlimited tablespace to test;

select * from person;
