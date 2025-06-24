package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.regex.Pattern;

public class LogInPage {

    WebDriver driver;
    WebDriverWait wait;

    public LogInPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By signInMessageSelector = By.className("auth-form__heading");
    By userNameInputSelector = By.id("user_email");
    By passwordInputSelector = By.id("user_password");
    By signInButtonSelector = By.cssSelector("button[type='submit']");

    /**
     * Verifies that the "Sign In" header is visible and contains the expected text.
     */
    public void verifySignInMessage() {
        WebElement signInMessage = driver.findElement(signInMessageSelector);
        checkVisibilityOfElement(signInMessage);
        String signInMessageText = signInMessage.getText();
        Assert.assertEquals(signInMessageText, "Sign In", "Sign In Header not found");
    }

    /**
     * Inputs the given email into the username field after validating it.
     * @param userName The email address to input.
     */
    public void inputUserName(String userName) {
        if (validateEmailAddress(userName)) {
            WebElement userEmailInputField = wait.until(ExpectedConditions.elementToBeClickable(userNameInputSelector));
            userEmailInputField.sendKeys(userName);
        } else {
            throw new RuntimeException("Invalid email address");
        }
    }

    /**
     * Inputs the given password into the password field.
     * @param password The password to input.
     */
    public void inputPassword(String password) {
        WebElement passwordInput = driver.findElement(passwordInputSelector);
        checkVisibilityOfElement(passwordInput);
        passwordInput.sendKeys(password);
    }

    /**
     * Clicks the "Sign In" button to submit the login form.
     */
    public void clickSignInButton() {
        WebElement signInButton = driver.findElement(signInButtonSelector);
        checkVisibilityOfElement(signInButton);
        signInButton.click();
    }

    /**
     * Checks if a given WebElement is displayed on the page.
     * @param element The WebElement to verify.
     */
    public void checkVisibilityOfElement(WebElement element) {
        Assert.assertTrue(element.isDisplayed(), "Element is not displayed");
    }

    /**
     * Validates the format of an email address using a regular expression.
     * @param email The email to validate.
     * @return true if the email is valid, false otherwise.
     */
    public boolean validateEmailAddress(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }
}
