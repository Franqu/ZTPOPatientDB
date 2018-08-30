package com.patient;

class TaxPL implements ITax{

    @Override
    public double count(double money) {
        return 0.23 * money;
    }

    @Override
    public String getId() {
        return "PL";
    }
}