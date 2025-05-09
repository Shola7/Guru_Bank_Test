package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class InventoryPage {
    protected WebDriver driver;
    protected String inventoryPageURL = "https://www.saucedemo.com/inventory.html";
    // Elements
    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(id = "react-burger-menu-btn")
    public WebElement menuButton;

    @FindBy(id = "reset_sidebar_link")
    public WebElement resetAppBtn;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    // Constructor
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Actions
    public void sortProductsBy(String sortOption) {
        Select dropdown = new Select(sortDropdown);
        dropdown.selectByVisibleText(sortOption);
    }

    public void addProductToCart(String productName) {
        for (WebElement item : inventoryItems) {
            WebElement itemName = item.findElement(By.className("inventory_item_name"));
            if (itemName.getText().equals(productName)) {
                WebElement addButton = item.findElement(By.cssSelector("button[id^='add-to-cart']"));
                addButton.click();
                break;
            }
        }
    }

    public CartPage goToCart() {
        cartLink.click();
        return new CartPage(driver);
    }

    public void logout() {
        menuButton.click();
        // Wait for menu to appear
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logoutLink.click();
    }

    public void clickMenuBtn(){
        menuButton.click();
    }

    public void clickResetLink(){
        resetAppBtn.click();
    }

    public void clickLogoutBtn(){
        logoutLink.click();
    }

    public int getNumberOfProducts() {
        return inventoryItems.size();
    }

    public boolean isOnInventoryPage() {
        return driver.getCurrentUrl().contains("inventory.html");
    }

    public void navigateInventoryPage(){
        driver.get(inventoryPageURL);
    }

    public WebElement getResetAppBtn() {
        return this.resetAppBtn; // Make sure resetAppBtn is defined with @FindBy
    }

    public WebElement getLogoutLink() {
        return this.logoutLink; // Make sure logoutLink is defined with @FindBy
    }
}