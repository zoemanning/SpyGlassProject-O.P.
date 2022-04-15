package com.spyglass.optionalintl.domain.goal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String title;
    private Double targetSavingsAmount;
    private Double amountSaved;
    private Date savingsDateGoal;
    private String notes;
    private Double progressBar;
    private goalType goalType;


    public Goal() {
    }

    public Goal(String title, Double targetSavingsAmount, Double amountSaved,
                Date savingsDateGoal, String notes, Double progressBar, goalType goalType) {
        this.title = title;
        this.targetSavingsAmount = targetSavingsAmount;
        this.amountSaved = amountSaved;
        this.savingsDateGoal = savingsDateGoal;
        this.notes = notes;
        this.progressBar = progressBar;
        this.goalType = goalType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public goalType getGoalType() {
        return goalType;
    }

    public void setGoalType(goalType goalType) {
        this.goalType = goalType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getTargetSavingsAmount() {
        return targetSavingsAmount;
    }

    public void setTargetSavingsAmount(Double targetSavingsAmount) {
        this.targetSavingsAmount = targetSavingsAmount;
    }

    public Double getAmountSaved() {
        return amountSaved;
    }

    public void setAmountSaved(Double amountSaved) {
        this.amountSaved = amountSaved;
    }

    public Date getSavingsDateGoal() {
        return savingsDateGoal;
    }

    public void setSavingsDateGoal(Date savingsDateGoal) {
        this.savingsDateGoal = savingsDateGoal;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(Double progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "goalType"+ goalType + '\'' +
                "title='" + title + '\'' +
                "id=" + id +
                ", title='" + title + '\'' +
                ", targetSavingsAmount=" + targetSavingsAmount +
                ", amountSaved=" + amountSaved +
                ", savingsDateGoal=" + savingsDateGoal +
                ", notes='" + notes + '\'' +
                ", progressBar=" + progressBar +
                ", goalType=" + goalType +
                '}';
    }
}