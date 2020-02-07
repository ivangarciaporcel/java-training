package org.example.effectiveJava;

import org.example.effectiveJava.item1.Building;
import org.example.effectiveJava.item1.Buildings;
import org.example.effectiveJava.item1.CustomObject;
import org.example.effectiveJava.item1.serviceproviderframework.CompanyService;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class Item1Test {

    /**
     * Item 1. Consider static factory methods instead of constructors
     */

    /**
     * One advantage of static factory methods is that, unlike constructors, they
     * have names, i.e. CustomObject.getInstance.
     */
    @Test
    public void Sum_TwoNumber_CustomObjectWithResult() {
        int a = 3, b = 5;
        CustomObject customObject = CustomObject.getInstance();
        assertNotNull(customObject);
        assertThat(customObject.sumOf(a, b), CoreMatchers.is(a + b));
        assertThat(customObject.getNumberOfOperations(), CoreMatchers.is(1));
    }

    /**
     * A second advantage of static factory methods is that, unlike constructors,
     * they are not required to create a new object each time they’re invoked, i.e.
     * calling getInstance method will always return same instance
     */
    @Test
    public void Multiply_TwoNumber_CustomObjectWithResult() {
        int a = 3, b = 5;
        CustomObject customObject = CustomObject.getInstance();
        assertNotNull(customObject);
        assertThat(customObject.multiplicationOf(a, b), CoreMatchers.is(a * b));
        assertThat(customObject.getNumberOfOperations(), CoreMatchers.is(2));
    }

    /**
     * A third advantage of static factory methods is that, unlike constructors,
     * they can return an object of any subtype of their return type.
     * In the example an instance of House subclass is returned.
     */
    @Test
    public void Get_HouseArea_HouseInstanceAndPrice() {
        double houseArea = 150.0;
        Building house = Buildings.getHouseInstance(houseArea);
        assertNotNull(house);
        double price = house.calculatePrice();
        assertTrue(house.getPricePerSquareMeter() < 1600);
        assertEquals(houseArea * house.getPricePerSquareMeter(), price, 0.0);
    }

    /**
     * A fourth advantage of static factories is that the class of the returned
     * object can vary from call to call as a function of the input parameters.
     * In the example as the input is 2300, then a Mansion instance will be created
     * in {@code Buildings.getHouseInstance} method
     */
    @Test
    public void Get_MansionArea_MansionInstanceAndPrice() {
        double mansionArea = 2300.0;
        Building mansion = Buildings.getHouseInstance(mansionArea);
        assertNotNull(mansion);
        double price = mansion.calculatePrice();
        assertTrue(mansion.getPricePerSquareMeter() >= 1600);
        assertEquals(mansionArea * mansion.getPricePerSquareMeter(), price, 0.0);
    }

    /**
     * A fifth advantage of static factories is that the class of the returned object
     * need not exist when the class containing the method is written.
     * Such flexible static factory methods form the basis of service provider frameworks, which was implemented
     * as part of the following example with Companies:
     * - Service Interface: JalasoftCompany, MojixCompany, etc
     * - Provider Registration API: META-INF/services/org.example.effectiveJava.item1.serviceproviderframework.company
     * - Service Access API: CompanyService
     * - Service Provider Interface: Company
     */
    @Test
    public void Get_ServiceLoaderAndImplementations_DataOfImplementations() {
        CompanyService companyService = CompanyService.getInstance();
        long employees = companyService.getEmployees("Jalasoft");
        assertEquals(1000, employees);
        employees = companyService.getEmployees("Mojix");
        assertEquals(800, employees);
        employees = companyService.getEmployees("AnyCompany");
        assertEquals(500, employees);
        employees = companyService.getEmployees("NotExistentCompany");
        assertEquals(0, employees);
    }
}
