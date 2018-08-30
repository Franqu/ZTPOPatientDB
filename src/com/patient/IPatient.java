package com.patient;


public interface IPatient {
    void add(IPatient patient);
    void remove(IPatient patient);
    Integer getId();
    String getName();
    String getSurname();
    String getIdentification();
    String getCountry();
    Double getPhoneNumber();
    String getEmail();
    double getSalary();
    void print();
}