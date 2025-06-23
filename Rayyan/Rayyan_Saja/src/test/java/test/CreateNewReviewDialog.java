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
import java.util.regex.Pattern;

public class CreateNewReviewDialog {
    WebDriver driver;
    WebDriverWait wait;


    public CreateNewReviewDialog(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    By dialogLocater = By.cssSelector("div[role='dialog']");
    By activeTabLocater = By.xpath("//button[.//div[contains(@class, 'text-primary')]]//span[last()]");
    By reviewTitleLocater = By.cssSelector("input[name='review[title]']");
    By reviewTypeLocater = By.xpath("//label[contains(text(), 'Review Type')]/following::button[1]");
    By reviewDomainLocater = By.xpath("//label[contains(text(), 'Review Domain')]/following::button[1]");
    By descriptionLocater = By.xpath("//label[contains(text(), 'Description')]/following::textarea[1]");
    By cancelButtonLocater  = By.xpath("//button[span[text()='Cancel']]");
    By closeButtonLocater  = By.xpath("//button[text()='Close']");
    By createNewReviewButtonLocater = By.xpath("//button[span[text()='Create New Review']]");
    By skipButtonLocater = By.xpath("//button[span[text()='Skip']]");
    By selectFilesButtonLocater = By.xpath("//button[@aria-label='Select Files']");
    By inviteButtonLocater = By.xpath("//button[span[text()='Invite']]");
    By userEmailInputFieldLocater = By.xpath("//label[contains(text(),'User Email')]/following-sibling::div//input[@name='invite[emails]']");
    By userRoleFieldLocater = By.xpath("//label[contains(text(), 'User Role')]/following::button[1]");
    By reasonMessageFieldLocater = By.name("invite[reason]");
    By upgradeButtonLocater = By.xpath("//div[@role='dialog']//a[contains(text(), 'Upgrade Now')]");

    public void checkVisibilityOfCreateNewReviewDialog() {
        WebElement homeIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(dialogLocater));
        checkVisibilityOfElement(homeIcon);
    }
    public void checkActiveTabText(String expectedText) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        try {
            WebElement activeTab = wait.until(ExpectedConditions.visibilityOfElementLocated(activeTabLocater));
            checkVisibilityOfElement(activeTab);
            String activeTabText = activeTab.getText().trim();
            Assert.assertEquals(activeTabText, expectedText, "You are not in the correct Tab");

        }
        catch (TimeoutException | NoSuchElementException e) {
            throw new NoSuchElementException("Create New Review Dialog not found");
        }
    }

    public void inputReviewTitle(String title) {
        WebElement reviewTitle = driver.findElement(reviewTitleLocater);
        checkVisibilityOfElement(reviewTitle);
        reviewTitle.sendKeys(title);
    }

    public void selectReviewTypeOption(String option){
        WebElement reviewType = driver.findElement(reviewTypeLocater);
        reviewType.click();
        By reviewTypeOptionLocator = By.xpath("//button[@data-testid='dropdown-option']//span[text()='" + option + "']");

        WebElement optionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(reviewTypeOptionLocator));
        optionElement.click();
    }

    public void selectReviewDomainOption(String option){
        WebElement reviewDomain = driver.findElement(reviewDomainLocater);
        reviewDomain.click();
        By reviewDomainOptionLocater = By.xpath("//button[@data-testid='dropdown-option']//span[text()='" + option + "']");
        WebElement optionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(reviewDomainOptionLocater));
        optionElement.click();
    }
    public void inputDescription(String description) {
        WebElement descriptionElement = driver.findElement(descriptionLocater);
        checkVisibilityOfElement(descriptionElement);
        descriptionElement.sendKeys(description);
    }
    public void clickCancelButton() {
        WebElement cancelButton = wait.until(ExpectedConditions.elementToBeClickable(cancelButtonLocater));
        cancelButton.click();
    }

    public void clickCloseButton() {
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(closeButtonLocater));
        closeButton.click();
    }
    public  void clickCreateNewReviewButton() {
        WebElement createNewReviewButton = wait.until(ExpectedConditions.elementToBeClickable(createNewReviewButtonLocater));
        createNewReviewButton.click();
    }

    public void clickSkipButton() {
        WebElement skipButton = wait.until(ExpectedConditions.elementToBeClickable(skipButtonLocater));
        skipButton.click();
    }

    public void clickSelectFilesButton() {
        try {
            WebElement elementToCheck = driver.findElement(upgradeButtonLocater);
            if (!elementToCheck.isDisplayed()) {
                WebElement selectFileButton = wait.until(ExpectedConditions.elementToBeClickable(selectFilesButtonLocater));
                selectFileButton.click();
            }
            else
                this.clickCancelButton();
        } catch (NoSuchElementException e) {
            WebElement selectFileButton = wait.until(ExpectedConditions.elementToBeClickable(selectFilesButtonLocater));
            selectFileButton.click();
        }
    }

    public void checkVisibilityOfInviteButton() {
        WebElement inviteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(inviteButtonLocater));
        checkVisibilityOfElement(inviteButton);
    }
    public void clickInviteButton() {
        WebElement inviteButton= wait.until(ExpectedConditions.elementToBeClickable(inviteButtonLocater));
        inviteButton.click();
    }

    public void inputUserEmail(String email) {
        if(validateEmailAddress(email)) {
            WebElement userEmailInputField = wait.until(ExpectedConditions.elementToBeClickable(userEmailInputFieldLocater));
            userEmailInputField.sendKeys(email);
        }
        else
            throw new RuntimeException("Invalid email address");
    }

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
    public void selectUserRole(String role) {
        WebElement reviewDomain = driver.findElement(userRoleFieldLocater);
        reviewDomain.click();
        By reviewDomainOptionLocater = By.xpath("//button[@data-testid='dropdown-option']//span[text()='" + role + "']");
        WebElement optionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(reviewDomainOptionLocater));
        optionElement.click();
    }
    public void inputReasonMessage(String messageText) {
        WebElement message = wait.until(ExpectedConditions.elementToBeClickable(reasonMessageFieldLocater));
        message.sendKeys(messageText);
    }
    public boolean validateEmailAddress(String email) {
        String emailRegex =  "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }

}
