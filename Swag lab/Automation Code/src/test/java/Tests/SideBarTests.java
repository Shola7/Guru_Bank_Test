package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Utils.Utility;

public class SideBarTests extends BaseTest {
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private Utility utility;

    @BeforeClass
    public void setup() {
        loginPage = new LoginPage(driver);
        inventoryPage = loginPage.login("standard_user", "secret_sauce");
        cartPage = new CartPage(driver);
        utility = new Utility(driver);
    }

    @Test(description = "Reset app state", priority = 1)
    public void testResetApp() {
        // Add item and verify
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.goToCart();
        Assert.assertTrue(cartPage.isItemInCart("Sauce Labs Backpack"),
                "Item should be in cart before reset");

        // Reset app state
        inventoryPage.clickMenuBtn();
        utility.waitForElementToBeClickable(inventoryPage.getResetAppBtn());
        inventoryPage.clickResetLink();

        // Verify cart is empty
        driver.navigate().refresh();
        Assert.assertEquals(cartPage.getNumberOfItemsInCart(), 0,
                "Cart should be empty after reset");
    }

    @Test(description = "Test Logout action", priority = 2)
    public void testLogout() {
        inventoryPage.clickMenuBtn();
        utility.waitForElementToBeClickable(inventoryPage.getLogoutLink());
        inventoryPage.clickLogoutBtn();
        Assert.assertTrue(loginPage.isOnLoginPage(),
                "Should be on login page after logout");
    }
}