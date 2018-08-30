package com.patient;

class TaxDE implements ITax{

    @Override
    public double count(double money) {
        return 0.30 * money;
    }

    @Override
    public String getId() {
        return "DE";
    }
}