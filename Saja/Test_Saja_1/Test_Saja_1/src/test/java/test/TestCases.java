package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;

import java.io.File;

import static java.lang.Thread.*;

public class TestCases {
        WebDriver driver;
        LogInPage logInPage;
        HomePage homePage;
        ActiveReviewsPage activeReviewsPage;
        CreateNewReviewDialog dialog;


        @Test
       public void beforeTest() {
            WebDriverManager.chromedriver ().setup ();
            driver = new ChromeDriver ();
            driver.manage ().window ().maximize ();
            driver.get ( "https://new.rayyan.ai/" );
            logInPage = new LogInPage ( driver );
            logInPage.verifySignInMessage ();
            logInPage.inputUserName ( "sajaabdelkhaliq@gmail.com" );
            logInPage.inputPassword ( "SajaTest123!" );
            logInPage.clickSignInButton ();
            homePage = new HomePage ( driver );
            homePage.checkVisibilityOfHomeIcon ();
            activeReviewsPage = new ActiveReviewsPage ( driver );
            activeReviewsPage.navigateToActiveReviewsPage ();
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
                public void testCases(String reviewTitle,String reviewTypeOption,String reviewDomain,String description,String invitationEmail , String userRole, String message){
                //here we can loop and do more than just 1 testcase
                activeReviewsPage.checkVisibilityOfActiveReviewsHeaderPage();
                activeReviewsPage.clickOnCreateActiveReviewButton ();
                dialog = new CreateNewReviewDialog(driver);
                dialog.checkActiveTabText ( "Add review info" );
                dialog.inputReviewTitle (reviewTitle);
                dialog.selectReviewTypeOption( reviewTypeOption );
                dialog.selectReviewDomainOption ( reviewDomain );
                dialog.inputDescription ( description);
                dialog.clickCreateNewReviewButton ();
                dialog.checkActiveTabText ( "Upload Articles" );
                dialog.clickSkipButton ();
                dialog.checkActiveTabText ( "Invite Members" );
                dialog.checkVisibilityOfInviteButton ();
                dialog.inputUserEmail ( invitationEmail );
                dialog.selectUserRole(userRole);
                dialog.inputReasonMessage ( message);
                dialog.clickInviteButton ();
                activeReviewsPage.navigateToActiveReviewsPage ();
                activeReviewsPage.checkVisibilityOfActiveReviewsHeaderPage();
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

        /*try {
            sleep ( 10000 );
        } catch (InterruptedException e) {
            throw new RuntimeException ( e );
        }*/
        driver.close ();
    }

}
