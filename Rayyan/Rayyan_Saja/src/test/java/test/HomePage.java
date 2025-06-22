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

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By homeIconLocater = By.cssSelector("a[aria-label='Home']");
    By createReviewButtonLocater = By.xpath("//button[text()='Create Review']");

    public void checkVisibilityOfHomeIcon() {
        WebElement homeIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(homeIconLocater));
        checkVisibilityOfElement(homeIcon);
    }

    public void clickOnCreateReviewButton() {
        WebElement createReviewButton = driver.findElement(createReviewButtonLocater);
        checkVisibilityOfElement(createReviewButton);
        createReviewButton.click();
    }

    public void checkVisibilityOfElement(WebElement element) {
        Assert.assertTrue(element.isDisplayed(), "Element is not displayed");
    }

}
