package com.softserve.mosquito.enitities;

import java.util.Set;

public class User {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Set<Specialization> specializations;

    public User() {
    }

    public User(String email, String password, String firstName, String lastName, Set<Specialization> specializations) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specializations = specializations;
    }

    //Constructor for sign-in
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //Constructor for sign-up
    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(Long id, String email, String password,
                String firstName, String lastName, Set<Specialization> specializations) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specializations = specializations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specializations=" + specializations +
                '}';
    }
}