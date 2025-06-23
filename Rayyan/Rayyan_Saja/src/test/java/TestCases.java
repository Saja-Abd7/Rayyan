import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.ActiveReviewsPage;
import org.openqa.selenium.io.FileHandler;
import test.CreateNewReviewDialog;
import test.HomePage;
import test.LogInPage;
import java.io.File;
public class TestCases {
    WebDriver driver;
    LogInPage logInPage;
    HomePage homePage;
    CreateNewReviewDialog dialog;
    ActiveReviewsPage activeReviewsPage;

    @BeforeTest
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://new.rayyan.ai/");
        logInPage = new LogInPage(driver);
        logInPage.verifySignInMessage();
        logInPage.inputUserName("sajaabdelkhaliq@gmail.com");
        logInPage.inputPassword("SajaTest123!");
        logInPage.clickSignInButton();
        homePage = new HomePage(driver);
        homePage.checkVisibilityOfHomeIcon();
        activeReviewsPage = new ActiveReviewsPage(driver);
        activeReviewsPage.navigateToActiveReviewsPage();
    }

    @DataProvider(name = "reviewDataProvider")
    public Object[][] reviewData() {
        return new Object[][]{
                {"Review Example - 1","Not Applicable","Biomedical","This is my review description.","sajamuneer@gmail.com","Collaborator","Collaboration"},
                {"Review Example - 2","Systematic Review","Electrical Engineering","This is my review description.","muneer_ahmad1111@gmail.com","Reviewer","Review"},
                {"Review Example - 3","Other","Materials","This is my review description.","sajaabd_qa@gmail.com","Viewer","Access"},
        };
    }

    @Test(dataProvider = "reviewDataProvider")
    public void testCases(String reviewTitle,String reviewTypeOption,String reviewDomain,String description,String invitationEmail , String userRole, String message) {
        activeReviewsPage.checkVisibilityOfActiveReviewsHeaderPage();
        activeReviewsPage.clickOnCreateReviewButton();
        dialog = new CreateNewReviewDialog(driver);
        dialog.checkVisibilityOfCreateNewReviewDialog();
        dialog.checkActiveTabText("Add review info");
        dialog.inputReviewTitle(reviewTitle);
        dialog.selectReviewTypeOption(reviewTypeOption);
        dialog.selectReviewDomainOption(reviewDomain);
        dialog.inputDescription(description);
        dialog.clickCreateNewReviewButton();
        dialog.checkActiveTabText("Upload Articles");
        dialog.clickSkipButton();
        dialog.checkVisibilityOfInviteButton();
        dialog.checkActiveTabText("Invite Members");
        dialog.inputUserEmail(invitationEmail);
        dialog.selectUserRole(userRole);
        dialog.inputReasonMessage(message);
        dialog.clickInviteButton();
        activeReviewsPage.navigateToActiveReviewsPage();
        activeReviewsPage.checkVisibilityOfNumberActiveReviewsHeaderPage();
    }

    @AfterTest
    public void afterTest() {
        TakesScreenshot reviewsScreenshot = (TakesScreenshot) driver;
        File screenshotAsFile = reviewsScreenshot.getScreenshotAs(OutputType.FILE);
        String savePath = "screenshot.png";
        File savedFile = new File(savePath);
        try {
            FileHandler.copy(screenshotAsFile,savedFile);
        }catch (Exception e){
            e.printStackTrace();
        }
        driver.close();
    }
}
