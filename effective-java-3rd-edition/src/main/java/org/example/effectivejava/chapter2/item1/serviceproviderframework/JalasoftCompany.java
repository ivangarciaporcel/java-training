package org.example.effectivejava.chapter2.item1.serviceproviderframework;

public class JalasoftCompany implements Company {
    @Override
    public long getNumberOfEmployees() {
        return 1000;
    }

    @Override
    public String getCompanyName() {
        return "Jalasoft";
    }
}
