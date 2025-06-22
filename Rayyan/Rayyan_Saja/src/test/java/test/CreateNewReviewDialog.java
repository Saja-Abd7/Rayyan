package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

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
    By createNewReviewButtonLocater = By.xpath("//button[span[text()='Create New Review']]");
    By skipButtonLocater = By.xpath("//button[span[text()='Skip']]");
    By selectFilesButtonLocater = By.xpath("//button[@aria-label='Select Files']");
    By inviteButtonLocater = By.xpath("//button[span[text()='Invite']]");
    By userEmailInputFieldLocater = By.xpath("//label[contains(text(),'User Email')]/following-sibling::div//input[@name='invite[emails]']");
    By userRoleFieldLocater = By.xpath("//label[contains(text(), 'User Role')]/following::button[1]");
    By reasonMessageFieldLocater = By.name("invite[reason]");

    public void checkVisibilityOfCreateNewReviewDialog() {
        WebElement homeIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(dialogLocater));
        checkVisibilityOfElement(homeIcon);
    }
    public void checkActiveTabText(String expectedText) {
        WebElement activeTab = wait.until(ExpectedConditions.visibilityOfElementLocated(activeTabLocater));
        checkVisibilityOfElement(activeTab);
        String activeTabText = activeTab.getText().trim();
        Assert.assertEquals(activeTabText, expectedText, "You are in the wrong Tab");
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
    public  void clickCreateNewReviewButton() {
        WebElement createNewReviewButton = wait.until(ExpectedConditions.elementToBeClickable(createNewReviewButtonLocater));
        createNewReviewButton.click();
    }

    public void clickSkipButton() {
        WebElement skipButton = wait.until(ExpectedConditions.elementToBeClickable(skipButtonLocater));
        skipButton.click();
    }

    public void clickSelectFilesButton() {
        WebElement selectFileButton = wait.until(ExpectedConditions.elementToBeClickable(selectFilesButtonLocater));
        selectFileButton.click();
    }

    public void checkVisibilityOfSelectFilesButton() {
        WebElement selectFilesButton = wait.until(ExpectedConditions.visibilityOfElementLocated(selectFilesButtonLocater));
        checkVisibilityOfElement(selectFilesButton);
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
        WebElement userEmailInputField = wait.until(ExpectedConditions.elementToBeClickable(userEmailInputFieldLocater));
        userEmailInputField.sendKeys(email);
    }

    public void checkVisibilityOfElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        Assert.assertTrue(element.isDisplayed(), "Element is not displayed");
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

}
