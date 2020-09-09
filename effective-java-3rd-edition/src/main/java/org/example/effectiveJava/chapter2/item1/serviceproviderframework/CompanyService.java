package org.example.effectiveJava.chapter2.item1.serviceproviderframework;

import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

public class CompanyService {

    private static final CompanyService COMPANY_SERVICE = new CompanyService();
    private ServiceLoader<Company> loader;

    private CompanyService() {
        loader = ServiceLoader.load(Company.class);
    }

    public static CompanyService getInstance() {
        return COMPANY_SERVICE;
    }

    public long getEmployees(String companyName) {
        long employees = 0;
        try {
            Iterator<Company> companyIterator = loader.iterator();
            while (employees == 0 && companyIterator.hasNext()) {
                Company c = companyIterator.next();
                if (c.getCompanyName().equals(companyName)) {
                    employees = c.getNumberOfEmployees();
                }
            }
        } catch (ServiceConfigurationError serviceError) {
            employees = 0;
            serviceError.printStackTrace();
        }
        return employees;
    }
}
