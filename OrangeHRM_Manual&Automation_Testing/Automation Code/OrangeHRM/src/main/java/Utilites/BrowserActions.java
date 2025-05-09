package Utilites;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserActions {

    public static void acceptAlerts(WebDriver driver) {
        if (isAlertPresent(driver)) {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
    }

    public static String getAlertText(WebDriver driver) {
        if (isAlertPresent(driver)) {
            Alert alert = driver.switchTo().alert();
            return alert.getText();
        }
        return null;
    }

    public static boolean isAlertPresent(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void waitForElementVisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
