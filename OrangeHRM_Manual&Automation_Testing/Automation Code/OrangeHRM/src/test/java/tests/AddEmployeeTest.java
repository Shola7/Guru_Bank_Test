package tests;

import Base.BaseTest;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.AddEmployeePage;

public class AddEmployeeTest extends BaseTest {
    LoginPage loginPage;
    AddEmployeePage addEmployeePage;


    @Test
    public void testAddRandomEmployee() {
        loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        addEmployeePage = new AddEmployeePage(driver);
        addEmployeePage.addEmployee(firstName, lastName);

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("pim/viewPersonalDetails"), "لم يتم إضافة الموظف");
    }

}
