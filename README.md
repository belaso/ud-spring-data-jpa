# Demo Anwendung für den Udemy Kurs *Spring Data JPA*

## Docker Container mit Oracle 11g

```
docker run --shm-size=1g -p 1521:1521 -e ORACLE_PWD=testpw --name oracle oracle/database:11.2.0.2-xe
``