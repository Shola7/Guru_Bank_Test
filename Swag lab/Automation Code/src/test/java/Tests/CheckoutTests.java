package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {

    private CheckoutPage checkoutPage;

    @BeforeClass
    public void setupCheckoutTest() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        // Add a product to cart
        inventoryPage.addProductToCart("Sauce Labs Backpack");

        // Navigate to cart and checkout
        CartPage cartPage = inventoryPage.goToCart();
        checkoutPage = cartPage.proceedToCheckout();
    }

    @Test(description = "Test complete checkout process")
    public void testCompleteCheckout() {
        // Fill checkout information
        checkoutPage.fillCheckoutInfo("John", "Doe", "12345");

        // Complete order
        checkoutPage.clickFinish();

        // Verify order confirmation
        Assert.assertTrue(checkoutPage.isOrderConfirmed(),
                "Order confirmation message should be displayed");
    }
}
