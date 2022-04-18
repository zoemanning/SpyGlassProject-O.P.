package com.spyglass.optionalintl.domain.user.model;

import com.spyglass.optionalintl.domain.goal.model.Goal;

import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String emailAddress;
    @OneToMany(targetEntity = Goal.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private List<Goal> goalsList;


    public User() {
    }

    public User(String firstName, String lastName, Date dateOfBirth, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
    }

    public User(Long id, String firstName, String lastName, Date dateOfBirth, String emailAddress, List<Goal> goalsList) {
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(goalsList, user.goalsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, goalsList);
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
