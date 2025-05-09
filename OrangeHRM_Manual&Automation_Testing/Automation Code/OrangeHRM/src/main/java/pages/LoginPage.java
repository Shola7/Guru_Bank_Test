package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    // Locators
    private By usernameTextbox = By.name("username");
    private By passwordTextbox = By.name("password");
    private By loginButton = By.cssSelector(".oxd-button.oxd-button--medium.oxd-button--main.orangehrm-login-button");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void navigateToLoginPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    public void enterUsername(String username) {
        driver.findElement(usernameTextbox).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordTextbox).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void login(String username, String password) {
        navigateToLoginPage();
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    // Optional: Get page title
    public String getPageTitle() {
        return driver.getTitle();
    }

    private By errorMessage = By.cssSelector(".oxd-alert-content-text");

    public boolean isErrorMessageDisplayed() {
        return driver.findElements(errorMessage).size() > 0;
    }

    public String getErrorMessageText() {
        if (isErrorMessageDisplayed()) {
            return driver.findElement(errorMessage).getText();
        }
        return "";
    }

}
