package com.patient;

public class Developer implements IPatient{

    private String name;
    private double salary;

    public Developer(String name,double salary){
        this.name = name;
        this.salary = salary;
    }
    public void remove(IPatient employee) {
        //this is leaf node so this method is not applicable to this class.
    }
    public void add(IPatient employee) {
        //this is leaf node so this method is not applicable to this class.
    }

    public IPatient getChild(int i) {
        //this is leaf node so this method is not applicable to this class.
        return null;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void print() {
        System.out.println("-------------");
        System.out.println("Name ="+getName());
        System.out.println("Salary ="+getSalary());
        System.out.println("-------------");
    }
}