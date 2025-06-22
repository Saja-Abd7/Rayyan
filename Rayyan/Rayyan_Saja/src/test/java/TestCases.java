import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.CreateNewReviewDialog;
import test.HomePage;
import test.LogInPage;
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
        driver.close();
    }
}
