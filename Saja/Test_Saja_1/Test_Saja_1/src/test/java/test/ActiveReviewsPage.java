package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ActiveReviewsPage {
    WebDriver driver;
    WebDriverWait wait;
    String pageUrl = "https://new.rayyan.ai/reviews";

    public ActiveReviewsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait (driver, Duration.ofSeconds (10));
    }

    By activeReviewsHeaderPageLocater = By.xpath ("//div[text()='Active Reviews']");
    By createReviewButtonLocater = By.xpath ("//button[text()='Create Review']");


    public void navigateToActiveReviewsPage() {
        driver.navigate().to(pageUrl);
    }


    public void checkVisibilityOfActiveReviewsHeaderPage(){
        WebElement homeIcon = wait.until ( ExpectedConditions.visibilityOfElementLocated ( activeReviewsHeaderPageLocater ));
        checkVisibilityOfElement (homeIcon);

    }

    public void clickOnCreateActiveReviewButton() {
        WebElement createReviewButton = wait.until ( ExpectedConditions.visibilityOfElementLocated (createReviewButtonLocater) );
        checkVisibilityOfElement(createReviewButton);
        createReviewButton.click();
    }

    public void checkVisibilityOfElement(WebElement element) {
        Assert.assertTrue(element.isDisplayed(), "Element is not displayed");
    }
}
