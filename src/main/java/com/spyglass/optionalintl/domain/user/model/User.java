package com.spyglass.optionalintl.domain.user.model;

import com.spyglass.optionalintl.domain.goal.model.Goal;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private SimpleDateFormat dateOfBirth;
    private String emailAddress;
    @OneToMany(targetEntity = Goal.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private List<Goal> goalsList;


    public User() {
    }

    public User(String firstName, String lastName, SimpleDateFormat dateOfBirth, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
    }

    public User(Long id, String firstName, String lastName, SimpleDateFormat dateOfBirth, String emailAddress, List<Goal> goalsList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.goalsList = goalsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SimpleDateFormat getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(SimpleDateFormat dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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

    public List<Goal> getGoalsList() {
        return goalsList;
    }

    public void setGoalsList(List<Goal> goalsList) {
        this.goalsList = goalsList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", dateOfBirth=" + dateOfBirth +
                ", emailAddress='" + emailAddress + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
