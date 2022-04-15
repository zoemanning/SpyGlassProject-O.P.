package com.spyglass.optionalintl.domain.accountmanager.model;

import com.spyglass.optionalintl.domain.goal.model.Goal;
import com.spyglass.optionalintl.domain.user.model.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private List<User> usersList;
    private List<Goal> goalsList;
    private Integer accountNumber;
    private String description;
    private String name;

    public Account() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    public List<Goal> getGoalsList() {
        return goalsList;
    }

    public void setGoalsList(List<Goal> goalsList) {
        this.goalsList = goalsList;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", usersList=" + usersList +
                ", goalsList=" + goalsList +
                ", accountNumber=" + accountNumber +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
