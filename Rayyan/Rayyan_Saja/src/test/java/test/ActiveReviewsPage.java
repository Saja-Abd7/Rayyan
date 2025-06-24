package test;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.NoSuchElementException;

public class ActiveReviewsPage {
    WebDriver driver;
    WebDriverWait wait;
    String pageUrl = "https://new.rayyan.ai/reviews";

    public ActiveReviewsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By activeReviewsHeaderPageLocater = By.xpath("(//div[text()='Active Reviews'])[1]");
    By createReviewButtonLocater = By.xpath("//button[text()='Create Review']");
    By verifyNumberOfReviewsInHeaderLocater = By.xpath("//div[contains(text(),'Showing') and contains(text(),'active reviews')]");

    /**
     * Checks the visibility of the "Active Reviews" header on the page.
     */
    public void checkVisibilityOfActiveReviewsHeaderPage() {
        WebElement homeIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(activeReviewsHeaderPageLocater));
        checkVisibilityOfElement(homeIcon);
    }

    /**
     * Waits for an element to be visible and asserts that it is displayed.
     *
     * @param element The WebElement to check.
     */
    public void checkVisibilityOfElement(WebElement element) {
        try {
            if (element == null) {
                throw new NullPointerException("The WebElement provided is null.");
            }
            wait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertTrue(element.isDisplayed(), "Element is not displayed");
        } catch (TimeoutException e) {
            throw new RuntimeException("Element not visible within wait time.");
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Element not found.");
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred.");
        }
    }

    /**
     * Navigates the driver to the Active Reviews page URL.
     */
    public void navigateToActiveReviewsPage() {
        driver.navigate().to(pageUrl);
    }

    /**
     * Waits for the "Create Review" button to be clickable and clicks it.
     */
    public void clickOnCreateReviewButton() {
        WebElement createReviewButton = wait.until(ExpectedConditions.elementToBeClickable(createReviewButtonLocater));
        checkVisibilityOfElement(createReviewButton);
        createReviewButton.click();
    }

    /**
     * Checks the visibility of the text showing the number of active reviews (e.g., "Showing 1/1 active reviews").
     */
    public void checkVisibilityOfNumberActiveReviewsHeaderPage() {
        WebElement numberOfActiveReviews = wait.until(ExpectedConditions.visibilityOfElementLocated(verifyNumberOfReviewsInHeaderLocater));
        checkVisibilityOfElement(numberOfActiveReviews);
    }
}