package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

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
    By signInButtonSelector = By.xpath ( "//button[@type='submit']" );


    /*public void getTargetPageToStart(){
        driver.get("https://new.rayyan.ai/");
    }*/

    public void verifySignInMessage() {
        WebElement signInMessage = driver.findElement(signInMessageSelector);
        checkVisibilityOfElement(signInMessage);
        String signInMessageText = signInMessage.getText();
        Assert.assertEquals(signInMessageText, "Sign In","Sign In Header not found");
    }

    public void inputUserName(String userName) {
        if(validateEmailAdress(userName)){
            WebElement userEmailInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(userNameInputSelector));
            userEmailInputField.sendKeys(userName);
        } else {
            throw new RuntimeException("Invalid email address");
        }

    }

    public void inputPassword(String password) {
        WebElement passwordInput = driver.findElement(passwordInputSelector);
        checkVisibilityOfElement (passwordInput);
        passwordInput.sendKeys(password);
    }

    public void clickSignInButton(){
        WebElement signInButton = driver.findElement(signInButtonSelector);
        checkVisibilityOfElement(signInButton);
        signInButton.click();
    }

    public boolean validateEmailAdress(String emailAdress) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return emailAdress != null && emailAdress.matches(emailRegex);
    }


    public void checkVisibilityOfElement(WebElement element) {
        Assert.assertTrue(element.isDisplayed(), "Element is not displayed");
    }





}
