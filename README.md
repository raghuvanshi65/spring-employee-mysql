# Spring Employee MySQL 1 - This repository contains code for Assessment 1 -

##Technical Requrements -
1. linux - ubuntu 20.04
2. docker - docker engine, refer-[Installation Docker](https://docs.docker.com/engine/install/ubuntu/)
3. jenkins - docker image, refer-[Installation jenkins](https://www.knowledgehut.com/blog/devops/install-jenkins-ubuntu)
4. mysql - docker image, ``` docker pull mysql```

**Run the below command to start the mysql container and build the spring-app image and run it and link with mysql docker container**

```
docker run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=org -e MYSQL_USER=<user> -e MYSQL_PASSWORD=<mysql> -d mysql
docker exec -i mysql-standalone mysql -u raghu -p raghu <relpath>/complete/summative.sql
```
Run this inside /compete dir
``` 
docker build . -t spring-employee-mysql
```
Spin up the Spring Application Container.
```
docker run -p 8086:8086 --name spring-employee-mysql --link mysql-standalone:mysql -d spring-employee-mysql
```
