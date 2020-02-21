# "Saasovation" - a "Jira"-like project management system

This project implements features required for a project management system. It enables company members to register, login, create new project boards, new tickets, add members to the task and to the team.

## Built With

* [SpringBoot](https://spring.io/projects/spring-boot) - an open source Java-based framework.
* [Hibernate](https://hibernate.org/) -  an object-relational mapping tool for the Java programming language.
* [Bootstrap](https://getbootstrap.com/) -  an open source toolkit for developing with HTML, CSS, and JS.
* [Thymeleaf](https://www.thymeleaf.org/) -  a modern server-side Java template engine for both web and standalone environments.


## Getting Started
Spring Boot can be used with “classic” Java development tools or installed as a command line tool. Either way, you need Java SDK v1.8 or higher. Before you begin, you should check your current Java installation by using the following command:

```
$ java -version
```

## Initial instructions
Here are the credentials of initial "pre-created/registered" users (it might be beneficial to use these at start, as for demo purpose each member has certain project assigned to it initially): 
1)	Email: test@gmail.com
Password: test
2)	Email: test2@gmail.com
Password: test2

## More details
An authenticated user is initially exposed to the following dashboard page: 
![dashboard image here](/dashboard.png)

If user/member have created any project they are entitled to edit the project. Search can be used to quickly find a needed project based on a certain keyword. To submit search query either “pressing Enter” or clicking on the icon works. The page is updated with the search inquiry then.

![add member here](/add-member.png)

The members are then visible in the dropdown: 
![members](/members.png)

Members that were added to the project are now able to add boards and tickets to the boards:
![board image here](/board.png)


Initially there are no project members visible, project owner has to add them to the project. This is doable via the “add member” button that triggers the popup window that displays all members of the company and lets the project owner decide which member to add 

