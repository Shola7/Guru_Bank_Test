package Tests;

import Base.BaseTest;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import javax.swing.text.Utilities;

public class LoginTests extends BaseTest {
    LoginPage loginPage;
    @BeforeMethod
    public void setLoginPage() {
        loginPage = new LoginPage(driver);
        driver.navigate().refresh();
    }

    @Test(description = "Test successful login with standard user", priority = 1)
    public void testSuccessfulLogin() {
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "User should be redirected to inventory page after login");
    }

    @Test(description = "Login with invalid username", priority = 2)
    public void loginWithInvalidUsername() {
        loginPage.login("invalid_username", "secure_sauce");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"),
                "Correct error message should be displayed");
    }

    @Test(description = "Login with invalid password", priority = 3)
    public void loginWithInvalidPassword() {
        loginPage.login("standard_user", "invalid_password");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"),
                "Correct error message should be displayed");
    }

    @Test( description = "Login with Empty username and password", priority = 4)
    public void loginWithEmptyUsernameAndPassword(){
        loginPage.login("", "");
        loginPage.validateErrorMessage("Username is required");
    }

    @Test( description = "Login with Empty username", priority = 5)
    public void loginWithEmptyUsername(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "secret_sauce");
        loginPage.validateErrorMessage("Username is required");
    }

    @Test( description = "Login with Empty password", priority = 6)
    public void loginWithEmptyPassword(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "");
        loginPage.validateErrorMessage("Password is required");

    }

}