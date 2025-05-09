package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InventoryTests extends BaseTest {

    private InventoryPage inventoryPage;

    @BeforeClass
    public void navigateToInventory() {
        LoginPage loginPage = new LoginPage(driver);
        inventoryPage = loginPage.login("standard_user", "secret_sauce");
    }

    @Test(description = "Test adding product to cart", priority = 0)
    public void testAddProductToCart() {
        // Add Sauce Labs Backpack to cart
        inventoryPage.addProductToCart("Sauce Labs Backpack");

        // Navigate to cart
        CartPage cartPage = inventoryPage.goToCart();

        // Verify item is in cart
        Assert.assertTrue(cartPage.isItemInCart("Sauce Labs Backpack"),
                "Product should be in cart after adding");
    }

    @Test(description = "Test product sorting", priority = 1)
    public void testProductSorting() {
        // back to inventory
        inventoryPage.navigateInventoryPage();
        // Get default number of products
        int initialProductCount = inventoryPage.getNumberOfProducts();

        // Sort products by price (high to low)
        inventoryPage.sortProductsBy("Price (high to low)");

        // Verify product count remains the same after sorting
        int productCountAfterSorting = inventoryPage.getNumberOfProducts();
        Assert.assertEquals(productCountAfterSorting, initialProductCount,
                "Product count should remain the same after sorting");
    }

    @Test(description = "Test logout functionality", priority = 2)
    public void testLogout() {
        // Logout
        inventoryPage.logout();

        // Verify user is redirected to login page
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isOnLoginPage(),
                "User should be on login page after logout");
    }
}
