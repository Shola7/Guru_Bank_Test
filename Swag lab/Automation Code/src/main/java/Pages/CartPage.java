package Pages;

import Utils.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    protected WebDriver driver;
    protected String cartPageURL = "https://www.saucedemo.com/cart.html";
    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Actions
//    public void removeItemFromCart(String productName) {
//        for (WebElement item : cartItems) {
//            WebElement itemName = item.findElement(By.className("inventory_item_name"));
//            if (itemName.getText().equals(productName)) {
//                WebElement removeButton = item.findElement(By.cssSelector("button[id^='remove']"));
//                removeButton.click();
//                break;
//            }
//        }
//    }
    public void removeItemFromCart(String productName) {
        for (WebElement item : cartItems) {
            WebElement itemName = item.findElement(By.className("inventory_item_name"));
            if (itemName.getText().equals(productName)) {
                WebElement removeButton = item.findElement(By.cssSelector("button[id^='remove']"));
                new Utility(driver).waitForElementToBeClickable(removeButton); // Add this
                removeButton.click();
                break;
            }
        }
    }

    public CheckoutPage proceedToCheckout() {
        checkoutButton.click();
        return new CheckoutPage(driver);
    }

    public InventoryPage continueShopping() {
        continueShoppingButton.click();
        return new InventoryPage(driver);
    }

    public int getNumberOfItemsInCart() {
        return cartItems.size();
    }

    public boolean isItemInCart(String productName) {
        for (WebElement item : cartItems) {
            WebElement itemName = item.findElement(By.className("inventory_item_name"));
            if (itemName.getText().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public void navigateCartPage(){
        driver.get(cartPageURL);
    }
}
