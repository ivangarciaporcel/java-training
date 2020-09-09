package org.example.effectivejava.chapter2.item1.serviceproviderframework;

public class AnyCompany implements Company {
    @Override
    public long getNumberOfEmployees() {
        return 500;
    }

    @Override
    public String getCompanyName() {
        return "AnyCompany";
    }
}
