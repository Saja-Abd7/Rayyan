package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    /**
     * Constructor to initialize the HomePage with a WebDriver instance.
     *
     * @param driver The WebDriver instance to interact with the browser.
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By homeIconLocater = By.cssSelector("a[aria-label='Home']");

    /**
     * Verifies that the Home icon is visible on the page.
     */
    public void checkVisibilityOfHomeIcon() {
        WebElement homeIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(homeIconLocater));
        checkVisibilityOfElement(homeIcon);
    }

    /**
     * Asserts that the given WebElement is displayed on the page.
     *
     * @param element The WebElement to verify visibility for.
     */
    public void checkVisibilityOfElement(WebElement element) {
        Assert.assertTrue(element.isDisplayed(), "Element is not displayed");
    }
}
