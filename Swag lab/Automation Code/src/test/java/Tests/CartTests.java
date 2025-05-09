package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {

    private CartPage cartPage;
    private InventoryPage inventoryPage;

    @BeforeClass
    public void setupCartTest() {
        LoginPage loginPage = new LoginPage(driver);
        inventoryPage = loginPage.login("standard_user", "secret_sauce");
        cartPage = new CartPage(driver);
    }


    @Test(description = "Test add item to cart", priority = 1)
    public void testAddItemToCart(){
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.goToCart();
        Assert.assertTrue(cartPage.isItemInCart("Sauce Labs Backpack"));

    }
    @Test(description = "Test remove item from cart", priority = 2)
    public void testRemoveItemFromCart() {
        // go to cart
        cartPage.navigateCartPage();

        // Remove one item
        cartPage.removeItemFromCart("Sauce Labs Backpack");

        // Verify item is removed
        Assert.assertFalse(cartPage.isItemInCart("Sauce Labs Backpack"),
                "Removed item should not be in cart");
    }

    @Test(description = "Test continue shopping button", priority = 3)
    public void testContinueShopping() {
        // Click continue shopping
        inventoryPage = cartPage.continueShopping();

        // Verify user is redirected to inventory page
        Assert.assertTrue(inventoryPage.isOnInventoryPage(),
                "User should be redirected to inventory page");
    }

//    @Test(description = "Test remove all items from the cart", priority = 4)
//    public void testRemoveAllItemFromCart(){
//        // adding products
//        inventoryPage.addProductToCart("Sauce Labs Backpack");
//        inventoryPage.addProductToCart("Sauce Labs Bike Light");
//        // go to cart
//        inventoryPage.goToCart();
//        // remove items
//        cartPage.removeItemFromCart("Sauce Labs Backpack");
//        cartPage.removeItemFromCart("Sauce Labs Bike Light");
//        Assert.assertEquals(cartPage.getNumberOfItemsInCart(), 0);
//    }
    @Test(description = "Test remove all items from the cart", priority = 4)
    public void testRemoveAllItemFromCart(){
        // Reset state first
        inventoryPage.clickMenuBtn();
        inventoryPage.clickResetLink();

        // adding products
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.addProductToCart("Sauce Labs Bike Light");

        // Verify items were added
        Assert.assertEquals(inventoryPage.goToCart().getNumberOfItemsInCart(), 2);

        // remove items
        cartPage.removeItemFromCart("Sauce Labs Backpack");
        cartPage.removeItemFromCart("Sauce Labs Bike Light");
        Assert.assertEquals(cartPage.getNumberOfItemsInCart(), 0);
    }

}
