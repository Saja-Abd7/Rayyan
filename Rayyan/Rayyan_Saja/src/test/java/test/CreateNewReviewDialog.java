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
    By cancelButtonLocater = By.xpath("//button[span[text()='Cancel']]");
    By closeButtonLocater = By.xpath("//button[text()='Close']");
    By createNewReviewButtonLocater = By.xpath("//button[span[text()='Create New Review']]");
    By skipButtonLocater = By.xpath("//button[span[text()='Skip']]");
    By selectFilesButtonLocater = By.xpath("//button[@aria-label='Select Files']");
    By inviteButtonLocater = By.xpath("//button[span[text()='Invite']]");
    By userEmailInputFieldLocater = By.xpath("//label[contains(text(),'User Email')]/following-sibling::div//input[@name='invite[emails]']");
    By userRoleFieldLocater = By.xpath("//label[contains(text(), 'User Role')]/following::button[1]");
    By reasonMessageFieldLocater = By.name("invite[reason]");
    By upgradeButtonLocater = By.xpath("//div[@role='dialog']//a[contains(text(), 'Upgrade Now')]");

    /**
     * Verifies the visibility of the 'Create New Review' dialog.
     */
    public void checkVisibilityOfCreateNewReviewDialog() {
        WebElement homeIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(dialogLocater));
        checkVisibilityOfElement(homeIcon);
    }

    /**
     * Checks if the currently active tab matches the expected tab text.
     * @param expectedText The expected text of the active tab.
     */
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

        } catch (TimeoutException | NoSuchElementException e) {
            throw new NoSuchElementException("Create New Review Dialog not found");
        }
    }

    /**
     * Inputs the given title into the review title field.
     * @param title The review title.
     */
    public void inputReviewTitle(String title) {
        WebElement reviewTitle = driver.findElement(reviewTitleLocater);
        checkVisibilityOfElement(reviewTitle);
        reviewTitle.sendKeys(title);
    }

    /**
     * Selects an option from the review type dropdown.
     * @param option The option to select.
     */
    public void selectReviewTypeOption(String option) {
        WebElement reviewType = driver.findElement(reviewTypeLocater);
        reviewType.click();
        By reviewTypeOptionLocator = By.xpath("//button[@data-testid='dropdown-option']//span[text()='" + option + "']");
        WebElement optionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(reviewTypeOptionLocator));
        optionElement.click();
    }

    /**
     * Selects an option from the review domain dropdown.
     * @param option The option to select.
     */
    public void selectReviewDomainOption(String option) {
        WebElement reviewDomain = driver.findElement(reviewDomainLocater);
        reviewDomain.click();
        By reviewDomainOptionLocater = By.xpath("//button[@data-testid='dropdown-option']//span[text()='" + option + "']");
        WebElement optionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(reviewDomainOptionLocater));
        optionElement.click();
    }

    /**
     * Inputs the given description into the description field.
     * @param description The review description.
     */
    public void inputDescription(String description) {
        WebElement descriptionElement = driver.findElement(descriptionLocater);
        checkVisibilityOfElement(descriptionElement);
        descriptionElement.sendKeys(description);
    }

    /**
     * Clicks the Cancel button in the dialog.
     */
    public void clickCancelButton() {
        WebElement cancelButton = wait.until(ExpectedConditions.elementToBeClickable(cancelButtonLocater));
        cancelButton.click();
    }

    /**
     * Clicks the Close button in the dialog.
     */
    public void clickCloseButton() {
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(closeButtonLocater));
        closeButton.click();
    }

    /**
     * Clicks the Create New Review button.
     */
    public void clickCreateNewReviewButton() {
        WebElement createNewReviewButton = wait.until(ExpectedConditions.elementToBeClickable(createNewReviewButtonLocater));
        createNewReviewButton.click();
    }

    /**
     * Clicks the Skip button in the wizard.
     */
    public void clickSkipButton() {
        WebElement skipButton = wait.until(ExpectedConditions.elementToBeClickable(skipButtonLocater));
        skipButton.click();
    }

    /**
     * Clicks the Select Files button unless the upgrade prompt is shown.
     */
    public void clickSelectFilesButton() {
        try {
            WebElement elementToCheck = driver.findElement(upgradeButtonLocater);
            if (!elementToCheck.isDisplayed()) {
                WebElement selectFileButton = wait.until(ExpectedConditions.elementToBeClickable(selectFilesButtonLocater));
                selectFileButton.click();
            } else {
                this.clickCancelButton();
            }
        } catch (NoSuchElementException e) {
            WebElement selectFileButton = wait.until(ExpectedConditions.elementToBeClickable(selectFilesButtonLocater));
            selectFileButton.click();
        }
    }

    /**
     * Verifies that the Invite button is visible.
     */
    public void checkVisibilityOfInviteButton() {
        WebElement inviteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(inviteButtonLocater));
        checkVisibilityOfElement(inviteButton);
    }

    /**
     * Clicks the Invite button.
     */
    public void clickInviteButton() {
        WebElement inviteButton = wait.until(ExpectedConditions.elementToBeClickable(inviteButtonLocater));
        inviteButton.click();
    }

    /**
     * Inputs a user email into the invite field after validating the format.
     * @param email The email address to input.
     */
    public void inputUserEmail(String email) {
        if (validateEmailAddress(email)) {
            WebElement userEmailInputField = wait.until(ExpectedConditions.elementToBeClickable(userEmailInputFieldLocater));
            userEmailInputField.sendKeys(email);
        } else {
            throw new RuntimeException("Invalid email address");
        }
    }

    /**
     * Validates that the given element is visible.
     * @param element The WebElement to validate.
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
     * Selects a user role from the dropdown.
     * @param role The user role to select.
     */
    public void selectUserRole(String role) {
        WebElement reviewDomain = driver.findElement(userRoleFieldLocater);
        reviewDomain.click();
        By reviewDomainOptionLocater = By.xpath("//button[@data-testid='dropdown-option']//span[text()='" + role + "']");
        WebElement optionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(reviewDomainOptionLocater));
        optionElement.click();
    }

    /**
     * Inputs a message in the reason message field.
     * @param messageText The reason message text.
     */
    public void inputReasonMessage(String messageText) {
        WebElement message = wait.until(ExpectedConditions.elementToBeClickable(reasonMessageFieldLocater));
        message.sendKeys(messageText);
    }

    /**
     * Validates the format of the given email address.
     * @param email The email to validate.
     * @return True if the email is valid, false otherwise.
     */
    public boolean validateEmailAddress(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }
}
