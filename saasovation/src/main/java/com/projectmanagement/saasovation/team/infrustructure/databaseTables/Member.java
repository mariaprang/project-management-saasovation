//package com.projectmanagement.saasovation.team.infrustructure.databaseTables;
//
//import com.projectmanagement.saasovation.team.domain.Role;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "team")
//public class Member {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE)
//    private Long databaseId;
//
//    @Column(name = "first_name", nullable = false, unique = false)
//    private String firstName;
//
//    @Column(name = "last_name", nullable = false, unique = false)
//    private String lastName;
//
//    @Column(name = "email", nullable = false, unique = false)
//    private String email;
//
//    @Column(name = "username", nullable = false, unique = false)
//    private String username;
//
//    @Column(name = "password", nullable = false)
//    private String password;
//
//    @Column(name = "role", nullable = false)
//    private Role role;
//
//    public Member(String firstName, String lastName, String email, String username, String password, Role role){
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.username = username;
//        this.password = password;
//        this.role = role;
//    }
//
//
//
//
//
//}
