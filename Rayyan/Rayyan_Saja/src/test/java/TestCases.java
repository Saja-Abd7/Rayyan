<<<<<<< HEAD
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.LogInPage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class TestCases {
    WebDriver driver;

    @BeforeTest
    public void beforeTest()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://new.rayyan.ai/");
    }

    @Test
    public void testCases()
    {
        LogInPage logInPage = new LogInPage(driver);
        logInPage.verifySignInMessage();
        logInPage.inputUserName("sajaabdelkhaliq@gmail.com");
        logInPage.inputPassword("SajaTest123!");
    }


    //public static Properties property ;
    //private static String configPath = "config.properties";
//    public static void myain(String[] args) throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement homeIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[aria-label='Home']")));
//
//        WebElement createReviewButton = driver.findElement(By.xpath("//button[text()='Create Review']"));
//        createReviewButton.click();
//
//        WebElement createNewReviewDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[role='dialog']")));
//        Assert.assertTrue(createNewReviewDialog.isDisplayed(), "Create New Review Dialog not displayed");
//
//        WebElement activeTabTextElement = driver.findElement(By.xpath(
//                "//button[.//div[contains(@class, 'text-primary')]]//span[last()]"
//        ));
//        String activeTabText = activeTabTextElement.getText().trim();
//        Assert.assertEquals(activeTabText, "Add review info", "You are in the wrong Tab");
//
//
//        // fill review title
//        WebElement reviewTitle = driver.findElement(By.cssSelector("input[name=\"review[title]\"]"));
//
//        reviewTitle.sendKeys("Review Example - 2");
//
//        // select review type and review domain
//        String[] selectFields = {"Review Type", "Review Domain"};
//        String[] options = {"Other", "Other"};
//        for (int i = 0; i < selectFields.length; i++) {
//            String xpath = "//label[contains(text(), '" + selectFields[i] + "')]/following::button[1]";
//            WebElement dropdownButton = wait.until(ExpectedConditions.elementToBeClickable(
//                    By.xpath(xpath)
//            ));
//            dropdownButton.click();
//            String optionText = options[i];
//            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
//                    By.xpath("//button[@data-testid='dropdown-option']//span[text()='" + optionText + "']"))
//            );
//            option.click();
//
//        }
//        // fill description
//        WebElement descriptionTextarea = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//label[contains(text(), 'Description')]/following::textarea[1]")
//        ));
//
//        descriptionTextarea.sendKeys("This is my review description.");
//
//
//        WebElement createNewReviewButton = driver.findElement(By.cssSelector("button[aria-label='Create New Review']"));
//        createNewReviewButton.click();
//
//        WebElement selectFilesButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//button[@aria-label='Select Files']")
//        ));
//        Assert.assertTrue(selectFilesButton.isDisplayed(), "Element is not visible");
//
//
//
//        WebElement skipButton = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//button[span[text()='Skip']]")
//        ));
//
//        skipButton.click();
//
//        WebElement inviteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//button[span[text()='Invite']]")
//        ));
//        Assert.assertTrue(inviteButton.isDisplayed(), "Element is not visible");
//        inviteButton.click();
//
//
//
//
//        WebElement inviteUserEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//label[contains(text(),'User Email')]/following-sibling::div//input[@name='invite[emails]']\n")
//        ));
//        inviteUserEmail.sendKeys("saje1@gmail.com");
//
//
//
//
//        String xpath = "//label[contains(text(), 'User Role')]/following::button[1]";
//        WebElement dropdownButton = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath(xpath)
//        ));
//        dropdownButton.click();
//        String optionText = "Viewer";
//        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//button[@data-testid='dropdown-option']//span[text()='" + optionText + "']"))
//        );
//        option.click();
//
//
//
//
//        Assert.assertTrue(inviteButton.isEnabled(), "Element is not enabled");
//        inviteButton.click();
//
//
//
//
//
//
//
//
//
//
//
//
////        driver.close();
//
//
//    }

    @AfterTest
    public void afterTest()
    {
        driver.close();
    }
}
=======
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.CreateNewReviewDialog;
import test.HomePage;
import test.LogInPage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class TestCases {
    WebDriver driver;

    @BeforeTest
    public void beforeTest()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://new.rayyan.ai/");
    }

    @Test
    public void testCases()
    {
        LogInPage logInPage = new LogInPage(driver);
        logInPage.verifySignInMessage();
        logInPage.inputUserName("sajaabdelkhaliq@gmail.com");
        logInPage.inputPassword("SajaTest123!");
        logInPage.clickSignInButton();
        HomePage homePage = new HomePage(driver);
        homePage.checkVisibilityOfHomeIcon();
        homePage.clickOnCreateReviewButton();

        CreateNewReviewDialog dialog = new CreateNewReviewDialog(driver);
        dialog.checkVisibilityOfCreateNewReviewDialog();
        dialog.checkActiveTabText("Add review info");
        dialog.inputReviewTitle("Review Example - 1");
        dialog.selectReviewTypeOption("Other");
        dialog.selectReviewDomainOption("Other");
        dialog.inputDescription("This is my review description.");
        dialog.clickCreateNewReviewButton();
        dialog.checkVisibilityOfSelectFilesButton();
        dialog.checkActiveTabText("Upload Articles");
        dialog.clickSkipButton();
        dialog.checkVisibilityOfInviteButton();
        dialog.checkActiveTabText("Invite Members");
        dialog.inputUserEmail("saje1@gmail.com");
        dialog.selectUserRole("Viewer");
        dialog.inputReasonMessage("inputReasonMessage");
        dialog.clickInviteButton();
    }

    @AfterTest
    public void afterTest()
    {
       // driver.close();
    }
}
>>>>>>> 106094e (add pages and methods)
