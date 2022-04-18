package com.spyglass.optionalintl.domain.account.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Long accountNumber;
    private String description;
    private String name;
    
    private String eMail;

    public Account() {
    }

    public Account(List<Account> accounts, Date parse) {
    }

    public Account(String name) {
    }

    public Account(String test_account, List<Account> accounts) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
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

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber=" + accountNumber +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", eMail='" + eMail + '\'' +
                '}';
    }
}
