package com.patient;

class Context{
    private ITax tax;

    public ITax getTax() {
        return tax;
    }

    public void setTax(ITax tax) {
        this.tax = tax;
    }

    public double getTaxCount(double money) {
        return tax.count(money);
    }

    public String getTaxId() {
        return tax.getId();
    }
}