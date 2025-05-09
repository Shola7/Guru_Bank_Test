package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddEmployeePage {
    private WebDriver driver;

    // Locators
    private By pimMenu = By.xpath("//span[text()='PIM']");
    private By addEmployeeButton = By.linkText("Add Employee");
    private By firstNameInput = By.name("firstName");
    private By lastNameInput = By.name("lastName");
    private By saveButton = By.cssSelector("button[type='submit']");

    // Constructor
    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void navigateToAddEmployeePage() {
        driver.findElement(pimMenu).click();
        driver.findElement(addEmployeeButton).click();
    }

    public void enterEmployeeDetails(String firstName, String lastName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void clickSaveButton() {
        driver.findElement(saveButton).click();
    }

    public void addEmployee(String firstName, String lastName) {
        navigateToAddEmployeePage();
        enterEmployeeDetails(firstName, lastName);
        clickSaveButton();
    }
}
