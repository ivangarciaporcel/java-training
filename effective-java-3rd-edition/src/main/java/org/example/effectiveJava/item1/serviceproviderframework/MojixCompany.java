package org.example.effectiveJava.item1.serviceproviderframework;

public class MojixCompany implements Company {
    @Override
    public long getNumberOfEmployees() {
        return 800;
    }

    @Override
    public String getCompanyName() {
        return "Mojix";
    }
}
