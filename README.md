# Todo App - Spring Boot Side

This side is server side of Todo App.<br>
Client Side : [ReactJs](https://github.com/eeness23/todoAppReactJs)

## Features 
- Session authenticate with JWT. Default 90 second. Every 90 second user must reauthenticate.You can change the time from Server side.
- User registration
- User login
- Create to-do list. Each user will be able to have multiple to-do lists. Each to-do list will have a name.
- Update to-do list
- List of to-do lists.
- Delete to-do list
- Add to-do item to existing to-do list.
- Add dependency between to-do items. To-do items which have dependency can not be completed if dependent to-do item is not completed.<br>
- Each to-do item have a Id, Name, Description, Create date, Start Date, Update Date, End Date and Status.

- Mark to-do item as "Completed or Not Completed".

- Order to-do items on a to-do list by Id, Name, End Date.

- Dynamic Filter to-do items by Id, Name , Status. 

### Installing
In this project used maven.Before run, import maven dependencies<br>
In this project used H2 database for easily run. If you want to look database. You can go `http://localhost:8080/h2-console`
Settings must be same.Settings are below.

```
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
```

If you want to change database, you just change settings in `application.properties` <br>
In this project used JWT. You can change expiration time in `Security/SecurityConstants` <br>
Default 90 second. Every 90 second user must reauthenticate.

You can login test account
```
email : test@gmail.com
password : test123
```
**The client side of Todo App must run. - [ReactJS](https://github.com/eeness23/todoAppReactJs)**

### A Important Note
This note is about dependency between to-do items.<br>
You can add more than one child-item. But each to-do item can have only one parent-item.<br>

_For Example_ <br>

List of to-do items : 1,2,3,4,5,6,7,8,9 <br>
Parent -> Childs <br>
<img src="https://cdn3.iconfinder.com/data/icons/basicolor-arrows-checks/24/149_check_ok-512.png" alt="success" width="16"/> 1 -> 2,3   <br>
<img src="https://cdn3.iconfinder.com/data/icons/basicolor-arrows-checks/24/149_check_ok-512.png" alt="success" width="16"/> 2 -> -   <br>
<img src="https://cdn3.iconfinder.com/data/icons/basicolor-arrows-checks/24/149_check_ok-512.png" alt="success" width="16"/> 3 -> 4   <br>
<img src="https://cdn3.iconfinder.com/data/icons/basicolor-arrows-checks/24/149_check_ok-512.png" alt="success" width="16"/> 6 -> 1,2 <br>

<img src="https://cdn1.iconfinder.com/data/icons/social-messaging-ui-color-round-1/254000/43-512.png" alt="wrong" width="15"/> 7 -> 3  You cant. Because item 3 has parent(1). 

### Pictures From Client Side

##### Login

![alt text](https://i.ibb.co/6bQSVqc/Screenshot-from-2019-07-29-02-48-08.png)

##### Homepage ( List to-do)

![alt text](https://i.ibb.co/JHLDLv7/Screenshot-from-2019-07-29-02-48-24.png)

##### Create Task - Update Task

![alt text](https://i.ibb.co/YkqMn9c/Screenshot-from-2019-07-29-02-48-44.png)

##### Filter items with "abc"

![alt text](https://i.ibb.co/V01wN3Y/Screenshot-from-2019-07-29-02-47-33.png)

##### Register

![alt text](https://i.ibb.co/yFFQ3Vq/Screenshot-from-2019-07-29-02-54-52.png)

##### Response Token ( JWT )

![alt text](https://i.ibb.co/ByzN86v/Screenshot-from-2019-07-29-03-10-34.png)

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - Java Framework
* [Spring Security](https://spring.io/projects/spring-security) - Spring Security
* [JWT](https://jwt.io/) - Json Web Token
* [Maven](https://maven.apache.org/) - Dependency Management
* [H2](https://www.h2database.com/html/main.html) - H2 in-memory database
* [Gson](https://github.com/google/gson) - Java library to serialize and deserialize Java objects to JSON

## Authors

* **Enes Demirbaş**  - [Linkedin](https://www.linkedin.com/in/enesdemrbas) - [Github](https://github.com/eeness23) <br>

    Email : enesdemirbas95@gmail.com
