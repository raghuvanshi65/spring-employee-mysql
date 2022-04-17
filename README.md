# Spring Employee MySQL 1 - This repository contains code for Assessment 1 -

### Technical Requrements -
1. linux - ubuntu 20.04
2. docker - docker engine, refer-[Installation Docker](https://docs.docker.com/engine/install/ubuntu/)
3. jenkins - docker image, refer-[Installation jenkins](https://www.knowledgehut.com/blog/devops/install-jenkins-ubuntu)
4. mysql - docker image, ``` docker pull mysql```



**Run the below command to start the mysql container and build the spring-app image and run it and link with mysql docker container**

```
docker run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=org -e MYSQL_USER=<user> -e MYSQL_PASSWORD=<mysql> -d mysql
docker exec -i mysql-standalone mysql -u raghu -p raghu <relpath>/complete/summative.sql
```
**Run this inside /compete dir**
``` 
docker build . -t spring-employee-mysql
```
**Spin up the Spring Application Container.**
```
docker run -p 8086:8086 --name spring-employee-mysql --link mysql-standalone:mysql -d spring-employee-mysql
```



### Setting up the jenkins pipeline to automate the deployment - (Using jenkins on localhost) - unstable due to changing ip address

1. jenkins will be available on [localhost](http://localhost:8080) by default.
2. Create a new Freestyle project.
3. add git url.
4. Enable GitHub hook trigger for GITScm polling.
5. Genarate a Webhook to connect to Jenkins for event triggeing.
6. Add below script in Build Step - shell execution ```mvn clean install```
7. Add below scripts in Post Build Actions - 
```
docker stop spring-employee-mysql
docker rm spring-employee-mysql
docker build <relative_path>/complete/ -t spring-employee-mysql
docker run -p 8086:8086 --name spring-employee-mysql --link mysql-standalone:mysql -d spring-employee-mysql
```



**The above pipeline does the following**

1. Pulls the code from git, when pushed to master.
2. Runs Build script to generate new .jar file with **embedded tomcat server**
3. Rebuild Docker Image and spin us the container again.



![Jenkins Dashboard Pipeline Job](https://raw.githubusercontent.com/raghuvanshi65/spring-employee-mysql/master/images/Screenshot%20from%202022-04-18%2000-46-30.png)

![Jenkins Build for Pipeline Job](https://raw.githubusercontent.com/raghuvanshi65/spring-employee-mysql/master/images/Screenshot%20from%202022-04-18%2000-46-39.png)

![Github Webhook Connection](https://raw.githubusercontent.com/raghuvanshi65/spring-employee-mysql/master/images/Screenshot%20from%202022-04-18%2000-47-04.png)
