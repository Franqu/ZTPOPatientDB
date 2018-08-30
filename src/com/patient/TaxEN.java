package com.patient;

class TaxEN implements ITax{

    @Override
    public double count(double money) {
        return 0.15 * money;
    }

    @Override
    public String getId() {
        return "EN";
    }
}