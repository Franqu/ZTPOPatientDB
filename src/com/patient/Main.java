package com.patient;

public class Main {
	final static String fileName = "PatientDB";
	private static SQLiteDB sqLiteDB = new SQLiteDB();
    public static void main(String[] args) {
    	
    	
    	sqLiteDB.createNewDatabase(fileName);
        Context tax = new Context();

        double money = 50;

        tax.setTax(new TaxPL());
        System.out.println("TAX " + tax.getTaxId() + ": " + tax.getTaxCount(money) );

        tax.setTax(new TaxDE());
        System.out.println("TAX " + tax.getTaxId() + ": " + tax.getTaxCount(money) );

        tax.setTax(new TaxEN());
        System.out.println("TAX " + tax.getTaxId() + ": " + tax.getTaxCount(money) );
    }
}
