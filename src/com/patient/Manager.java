package com.patient;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Manager implements IPatient{

    private String name;
    private double salary;

    public Manager(String name,double salary){
        this.name = name;
        this.salary = salary;
    }

    List <IPatient> employees = new ArrayList<IPatient>();
    public void add(IPatient employee) {
        employees.add(employee);
    }

    public IPatient getChild(int i) {
        return  employees.get(i);
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

        Iterator employeeIterator = employees.iterator();
        while(employeeIterator.hasNext()){
            IPatient employee = (IPatient) employeeIterator.next();
            employee.print();
        }
    }

    public void remove(IPatient employee) {
        employees.remove(employee);
    }
}