package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LogInPage {

    WebDriver driver;

    public LogInPage(WebDriver driver) {

        this.driver = driver;
    }

    By signInMessageSelector = By.className("auth-form__heading");
    By userNameInputSelector = By.id("user_email");
    By passwordInputSelector = By.id("user_password");
    By signInButtonSelector = By.cssSelector("button[type='submit']");


    public void verifySignInMessage() {

        WebElement signInMessage = driver.findElement(signInMessageSelector);
        checkVisibilityOfElement(signInMessage);
        String signInMessageText = signInMessage.getText();
        Assert.assertEquals(signInMessageText, "Sign In", "Sign In Header not found");
    }

    public void inputUserName(String userName) {
        WebElement userNameInput = driver.findElement(userNameInputSelector);
        checkVisibilityOfElement(userNameInput);
        userNameInput.sendKeys(userName);
    }
    public void inputPassword(String password) {
        WebElement passwordInput = driver.findElement(passwordInputSelector);
        checkVisibilityOfElement(passwordInput);
        passwordInput.sendKeys(password);
    }
    public void clickSignInButton() {
        WebElement signInButton = driver.findElement(signInButtonSelector);
        checkVisibilityOfElement(signInButton);
        signInButton.click();
    }
    public void checkVisibilityOfElement(WebElement element) {
        Assert.assertTrue(element.isDisplayed(), "Element is not displayed");
    }





}
