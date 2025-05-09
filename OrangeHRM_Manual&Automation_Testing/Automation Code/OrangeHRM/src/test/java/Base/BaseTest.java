package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginpage;

    @BeforeMethod
    public void setUp() {
        // إعداد خيارات المتصفح
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--user-data-dir=C:/temp/selenium_chrome_profile");
        options.addArguments("--incognito");
        options.addArguments("--disable-password-manager");
        options.addArguments("--safebrowsing-disable-download-protection");

        // إنشاء WebDriver وتهيئته
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Run Test Case");

        // تهيئة صفحة تسجيل الدخول
        loginpage = new LoginPage(driver);

        // تنفيذ خطوات تسجيل الدخول
        loginpage.navigateToLoginPage();
        loginpage.enterUsername("tomsmith");
        loginpage.enterPassword("SuperSecretPassword!");
        loginpage.clickLoginButton();

        System.out.println("Run Test Case");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
