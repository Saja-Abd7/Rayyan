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
        wait = new WebDriverWait (driver, Duration.ofSeconds ( 10 ) );
    }


    By dialogLocater = By.cssSelector("div[role='dialog']");
    By activeTabLocater = By.xpath ("//button[.//div[contains(@class,'text-primary')]]//span[last()]");
    By reviewTitleLocater = By.cssSelector ( "input[name='review[title]']" );
    By reviewTypeLocator = By.xpath("//label[contains(text(), 'Review Type')]/following::button[1]");
    By reviewDomainLocater = By.xpath("//label[contains(text(), 'Review Domain')]/following::button[1]");
    By descriptionLocater = By.xpath ( "//label[contains(text(), 'Description')]/following::textarea[1]" );
    By cancelButtonLocater = By.xpath ( "//button[span[text()='Cancel']]" );
    By createNewReviewButtonLocator =  By.xpath ( "//button[span[text()='Create New Review']]" );
    By skipButtonLocater = By.xpath ( "//button[span[text()='Skip']]" );
    By selectFilesButtonLocater = By.xpath ( "//button[span[text()='Select Files']]" );
    By userEmailInputFieldLocater = By.xpath("//label[contains(text(),'User Email')]/following-sibling::div//input[@name='invite[emails]']");
    By userRoleFieldLocater = By.xpath ( "//label[contains(text(), 'User Role')]/following-sibling::div//button[text()='Select member role']" );
    By reasonMessageFieldLocater = By.name ( "invite[reason]" );
    By inviteButtonLocater = By.xpath ( "//button[span[text()='Invite']]" );





    public void checkVisibilityOfCreateReviewDialog(){
        WebElement homeIcon = wait.until ( ExpectedConditions.visibilityOfElementLocated (dialogLocater) );
        checkVisibilityOfElement ( homeIcon );
    }

    public void checkActiveTabText(String expectedText){
        try{
            Thread.sleep ( 2000 );
        } catch (InterruptedException e) {
            throw new RuntimeException ( e );
        }
        try {
            WebElement activeTab = wait.until ( ExpectedConditions.visibilityOfElementLocated ( activeTabLocater ) );
            checkVisibilityOfElement ( activeTab );
            String activeTabText = activeTab.getText ().trim ();
            Assert.assertEquals ( activeTabText, expectedText, "You are not in the correct Tab" );
        } catch (TimeoutException | NoSuchElementException e) {
            throw new NoSuchElementException ("Create New Review Dialog not found");
        }
    }


    public void inputReviewTitle(String title){
        WebElement reviewTitle = driver.findElement(reviewTitleLocater);
        checkVisibilityOfElement (  reviewTitle );
        reviewTitle.sendKeys ( title );

    }

    public void selectReviewTypeOption(String option){
        WebElement reviewType = driver.findElement(reviewTypeLocator);
        reviewType.click ();
        By reviewTypeOptionLocater = By.xpath( "//button[@data-testid='dropdown-option']//span[text()='" + option + "']" );
        WebElement optionElement = wait.until ( ExpectedConditions.visibilityOfElementLocated (reviewTypeOptionLocater) );
        optionElement.click ();

    }

    public void selectReviewDomainOption(String option){
        WebElement reviewDomain = driver.findElement ( reviewDomainLocater );
        checkVisibilityOfElement ( reviewDomain );
        reviewDomain.click ();
        By ReviewDomainOptionLocater = By.xpath ( "//button[@data-testid='dropdown-option']//span[text()='"+option+"']" );
        WebElement optionElement = wait.until (  ExpectedConditions.visibilityOfElementLocated (ReviewDomainOptionLocater) );
        checkVisibilityOfElement ( optionElement );
        optionElement.click ();
    }

    public void inputDescription(String description){
        WebElement descriptionElement = driver.findElement( descriptionLocater );
        checkVisibilityOfElement ( descriptionElement );
        descriptionElement.sendKeys ( description );
    }
    public void clickCreateNewReviewButton(){
        WebElement createNewReviewButton = wait.until ( ExpectedConditions.visibilityOfElementLocated ( createNewReviewButtonLocator) );
        checkVisibilityOfElement (  createNewReviewButton );
        createNewReviewButton.click ();

    }
    public void clickSkipButton(){
      WebElement skipButton = wait.until (  ExpectedConditions.visibilityOfElementLocated ( skipButtonLocater) );
      checkVisibilityOfElement (  skipButton );
      skipButton.click ();

    }

    public void checkVisibilityOfInviteButton(){
        WebElement inviteButton = wait.until ( ExpectedConditions.visibilityOfElementLocated ( inviteButtonLocater ) );
        checkVisibilityOfElement (  inviteButton );
    }


    public void inputUserEmail(String email){
        if(validateEmailAddress ( email )) {
          WebElement userEmailInputFeild = wait.until (  ExpectedConditions.visibilityOfElementLocated ( userEmailInputFieldLocater ) );
          userEmailInputFeild.sendKeys ( email );
        } else{
            throw new NoSuchElementException ("Invalid Email Address");
        }
    }

    public void selectUserRole(String role){
      WebElement userRoleFiled = driver.findElement (userRoleFieldLocater );
      checkVisibilityOfElement ( userRoleFiled );
      userRoleFiled.click ();
      By userRoleFieldOptionLocater = By.xpath("//div[@data-slot='menubar-item']//span[text()='"+role+"']");
     WebElement optionElement = wait.until (  ExpectedConditions.visibilityOfElementLocated ( userRoleFieldOptionLocater) );
     checkVisibilityOfElement ( optionElement );
     optionElement.click ();

    }

    public void inputReasonMessage(String messageText) {
        WebElement message = wait.until(ExpectedConditions.elementToBeClickable(reasonMessageFieldLocater));
        checkVisibilityOfElement (   message );
        message.sendKeys(messageText);
    }

    public void clickInviteButton() {
        WebElement inviteButton = wait.until(ExpectedConditions.elementToBeClickable(inviteButtonLocater));
        inviteButton.click();
    }




/// /////////////////////////////////////////////////////////////
    public void clickCancelButton(){
        WebElement cancelButton = wait.until ( ExpectedConditions.visibilityOfElementLocated (cancelButtonLocater) );
        cancelButton.click ();
    }
    public void clickSelectFilesButton(){
        try {
            WebElement elementToCheck = driver.findElement ( By.xpath ( "//div[@role='dialog']//a[contains(text(), 'Upgrade Now')]" ) );
            if (!elementToCheck.isDisplayed ()) {
                WebElement selectFileButton = wait.until ( ExpectedConditions.elementToBeClickable ( selectFilesButtonLocater ) );
                selectFileButton.click ();
            } else {
                this.clickSkipButton ();
            }
        }
        catch (NoSuchElementException e){
                WebElement selectFileButton = wait.until ( ExpectedConditions.elementToBeClickable ( selectFilesButtonLocater )  );
                selectFileButton.click ();
            }

    }
    /// //////////////////////////////////////////////////////////////


    public void checkVisibilityOfElement(WebElement element) {
        Assert.assertTrue(element.isDisplayed(), "Element is not displayed");
            }
    public boolean validateEmailAddress(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }
}
