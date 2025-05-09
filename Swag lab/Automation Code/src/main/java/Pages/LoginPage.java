package Pages;

import Utils.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {
    protected WebDriver driver;

    // Elements
    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    @FindBy(css = "h3[data-test='error'] button[class='error-button']")
    private WebElement errorMessageCloseBtn;
    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Actions
    public void enterUsername(String username) {
        usernameInput.clear();
        // Only send keys if username is not empty
        if (!username.isEmpty()) {
            usernameInput.sendKeys(username);
        }
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        // Only send keys if password is not empty
        if (!password.isEmpty()) {
            passwordInput.sendKeys(password);
        }
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickCloseMessageErrorBtn(){errorMessageCloseBtn.click();}

//    public InventoryPage login(String username, String password) {
//        enterUsername(username);
//        enterPassword(password);
//        clickLoginButton();
//        return new InventoryPage(driver);
//    }
    public InventoryPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        new Utility(driver).waitForUrlToContain("inventory.html"); // Add this
        return new InventoryPage(driver);
    }

    public String getErrorMessage() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return errorMessage.getText();
    }

    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("saucedemo.com") &&
                loginButton.isDisplayed();
    }

    // Assertions
    public void validateSuccessfulLogin(){
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    public void validateErrorMessage(String message){
        Assert.assertTrue(errorMessage.getText().contains(message));
    }
}
