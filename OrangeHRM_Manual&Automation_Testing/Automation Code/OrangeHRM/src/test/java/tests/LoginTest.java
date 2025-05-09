package tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @Test(priority = 1)
    public void testSuccessfulLogin() {
        loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        String pageTitle = loginPage.getPageTitle();
        Assert.assertEquals(pageTitle, "OrangeHRM", "✅ تسجيل الدخول ناجح");
    }

    @Test(priority = 2)
    public void testLoginWithInvalidPassword() {
        loginPage = new LoginPage(driver);
        loginPage.login("Admin", "wrongpass");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "❌ لم تظهر رسالة الخطأ");
        Assert.assertEquals(loginPage.getErrorMessageText(), "Invalid credentials", "✅ تم التحقق من رسالة الخطأ");
    }

    @Test(priority = 3)
    public void testLoginWithInvalidUsername() {
        loginPage = new LoginPage(driver);
        loginPage.login("InvalidUser", "admin123");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "❌ لم تظهر رسالة الخطأ");
        Assert.assertEquals(loginPage.getErrorMessageText(), "Invalid credentials", "✅ تم التحقق من رسالة الخطأ");
    }

    @Test(priority = 4)
    public void testLoginWithEmptyFields() {
        loginPage = new LoginPage(driver);
        loginPage.login("", "");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "❌ لم تظهر رسالة الخطأ");
        Assert.assertEquals(loginPage.getErrorMessageText(), "Required", "✅ تحقق من الحقول الفارغة");
    }

    @Test(priority = 5)
    public void testLoginWithOnlyUsername() {
        loginPage = new LoginPage(driver);
        loginPage.login("Admin", "");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "❌ لم تظهر رسالة الخطأ");
        Assert.assertEquals(loginPage.getErrorMessageText(), "Required", "✅ تحقق من نقص كلمة المرور");
    }

    @Test(priority = 6)
    public void testLoginWithOnlyPassword() {
        loginPage = new LoginPage(driver);
        loginPage.login("", "admin123");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "❌ لم تظهر رسالة الخطأ");
        Assert.assertEquals(loginPage.getErrorMessageText(), "Required", "✅ تحقق من نقص اسم المستخدم");
    }
}
