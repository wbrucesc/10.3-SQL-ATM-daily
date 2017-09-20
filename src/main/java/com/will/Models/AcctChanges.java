package com.will.Models;

public class AcctChanges {
    private int id;
    private String name;
    private double transAmount;

    public AcctChanges(int id, String name, double transAmount) {
        this.id = id;
        this.name = name;
        this.transAmount = transAmount;
    }

    public AcctChanges(String name, double transAmount) {
        this.name = name;
        this.transAmount = transAmount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getTransAmount() {
        return transAmount;
    }

    @Override
    public String toString() {
        return "AcctChanges{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", transAmount=" + transAmount +
                '}';
    }
}
