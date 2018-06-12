package com.abing.entity;

import lombok.Data;
//import org.springframework.data.annotation.Id;

import javax.persistence.*;



@Entity
@Table(name="USERS")
@Data
public class User {

    @Id
    @GeneratedValue     //(strategy = GenerationType.IDENTITY)    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;
}
