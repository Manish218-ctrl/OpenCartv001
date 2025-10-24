package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import testCases.TS_015_ReturnsPage.TC_PR_001_ValidateProductductReturnsPageNavigationTest;

import java.time.Duration;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }
    private static final Logger logger = LogManager.getLogger(TC_PR_001_ValidateProductductReturnsPageNavigationTest.class);


    @FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
    public WebElement lnkLogout;

    @FindBy(xpath = "//a[normalize-space()='Newsletter']")
    public WebElement lnkNewsletter;

    @FindBy(xpath = "//h2[text()='My Account']")
    WebElement msgHeading;

    @FindBy(xpath = "/html/body/footer/div/div/div[4]/ul/li[4]/a")
    WebElement newsletterfooterlnk;



    public void clickLogout() {
        try {
            lnkLogout.click();
        } catch (Exception e) {
            System.out.println("Logout link not found.");
        }
    }

    public void clicknewsletterfooterlnk(){
        newsletterfooterlnk.click();
    }

    public boolean isUserLoggedIn() {
        try {
            return lnkLogout.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @FindBy(xpath = "//div[@class='list-group']//a[normalize-space()='Wish List']")
    private WebElement lnkRightColumnWishList;


    @FindBy(xpath = "/html/body/div[2]/div/aside/div/a[8]")
    private static WebElement lnkRecurringPayments;


    public void clickWishListFromMyAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement wishListLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Wish List')]")
        ));
        wishListLink.click();
    }



    public static void clickRecurringPayments() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement recurringLink = wait.until(ExpectedConditions.elementToBeClickable(lnkRecurringPayments));
            recurringLink.click();
            System.out.println("Clicked on Recurring Payments link.");
        } catch (Exception e) {
            System.out.println("Recurring Payments link not found.");
        }
    }

    public void clickNewsletter() {
        lnkNewsletter.click();
    }

@FindBy(xpath = "/html/body/footer/div/div/div[2]/ul/li[2]/a")
public WebElement lnkreturnfooterlink;


    @FindBy(xpath = "//a[normalize-space(text())='View your return requests']")
    private WebElement lnkViewYourReturnRequests;


    public void clickViewYourReturnRequests() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("Waiting for 'View your return requests' link to be visible.");
        try {
            WebElement returnRequestsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[contains(text(),'View your return requests')]")
            ));
            logger.info("Found 'View your return requests' link, scrolling into view.");

            // Scroll into view if necessary
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", returnRequestsLink);
            returnRequestsLink.click();
            logger.info("Successfully clicked 'View your return requests'.");
        } catch (Exception e) {
            logger.error("Failed to click 'View your return requests'.", e);
            Assert.fail("Failed to locate and click on 'View your return requests': " + e.getMessage());
        }
    }

    public void clicklnkreturnfooterlink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("Waiting for 'Return' footer link to be visible.");

        try {
            // Wait for the 'Return' footer link to be visible
            WebElement returnFooterLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/footer/div/div/div[2]/ul/li[2]/a")  // Replace with the actual XPath for the 'Return' link
            ));
            logger.info("Found 'Return' footer link, scrolling into view.");

            // Scroll into view if necessary
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", returnFooterLink);

            // Click on the 'Return' footer link
            returnFooterLink.click();
            logger.info("Successfully clicked 'Return' footer link.");
        } catch (Exception e) {
            // Log error and fail the test if the element is not found or click fails
            logger.error("Failed to click 'Return' footer link.", e);
            Assert.fail("Failed to locate and click on 'Return' footer link: " + e.getMessage());
        }
    }



    // Locate the 'Order History' link in the My Account page
    @FindBy(xpath = "/html/body/div[2]/div/aside/div/a[6]")
    public WebElement lnkOrderHistory;

    // Method to click the 'Order History' link from My Account page
    public void clickOrderHistory() {
        try {
            logger.info("Attempting to click the 'Order History' link in My Account page.");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(lnkOrderHistory)).click();
            logger.info("Successfully clicked the 'Order History' link.");
        } catch (Exception e) {
            logger.error("Failed to click the 'Order History' link in My Account page.", e);
        }
    }


    // Locate 'Modify your address book entries' link
    @FindBy(xpath = "/html/body/div[2]/div/div/ul[1]/li[3]/a")
    public WebElement lnkModifyAddressBook;


    @FindBy(xpath = "//*[@id=\"column-right\"]/div/a[2]")
    public WebElement lnkEditAccountInformation;

    // Method to click Edit Account Information
    public void clickEditAccountInformation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(lnkEditAccountInformation)).click();
        logger.info("Clicked on 'Edit your account information' link.");
    }

    // Method to validate if we are on 'My Account Information' page
    public boolean isMyAccountInformationPageDisplayed() {
        try {
            WebElement heading = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[@id=\"content\"]/h1")));
            return heading.isDisplayed();
        } catch (Exception e) {
            logger.error("My Account Information page not displayed: " + e.getMessage());
            return false;
        }
    }
    @FindBy(xpath = "//input[@value='Continue']")
    public WebElement btnContinue;



    @FindBy(id = "input-firstname")
    public WebElement txtFirstName;

    @FindBy(id = "input-lastname")
    public WebElement txtLastName;

    @FindBy(id = "input-email")
    public WebElement txtEmail;

    @FindBy(id = "input-telephone")
    public WebElement txtTelephone;



    @FindBy(css = ".text-danger") // Generic locator for validation messages
    private WebElement validationMessage;

    public void clearAllFields() {
        txtFirstName.clear();
        txtLastName.clear();
        txtEmail.clear();
        txtTelephone.clear();
        logger.info("Cleared all input fields.");
    }

    public void clickContinue() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
        logger.info("Clicked Continue button.");
    }

    public boolean isValidationMessageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(validationMessage));
            logger.info("Validation message displayed: " + validationMessage.getText());
            return validationMessage.isDisplayed();
        } catch (Exception e) {
            logger.error("Validation message not displayed.", e);
            return false;
        }
    }

    // Method to get placeholder text of input fields using XPath
    public String getFirstNamePlaceholder() {
        return driver.findElement(By.xpath("//input[@id='input-firstname']")).getAttribute("placeholder");
    }

    public String getLastNamePlaceholder() {
        return driver.findElement(By.xpath("//input[@id='input-lastname']")).getAttribute("placeholder");
    }

    public String getEmailPlaceholder() {
        return driver.findElement(By.xpath("//input[@id='input-email']")).getAttribute("placeholder");
    }

    public String getTelephonePlaceholder() {
        return driver.findElement(By.xpath("//input[@id='input-telephone']")).getAttribute("placeholder");
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//li[last()]")
    private WebElement breadcrumbElement;


    public String getBreadcrumb() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(breadcrumbElement));
        return breadcrumbElement.getText();
    }

    @FindBy(linkText = "Downloads")
    public WebElement lnkDownloads;


    public void clickDownloads() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkDownloads)).click();
        logger.info("Clicked on 'Downloads' link in My Account page.");
    }


    public String getPageTitle() {
        return driver.getTitle();
    }


    @FindBy(linkText = "Downloads")
    public WebElement lnkDownloadsRightColumn;

    // Click Downloads link from Right Column
    public void clickDownloadsFromRightColumn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(lnkDownloadsRightColumn)).click();
        logger.info("Clicked on 'Downloads' link from Right Column in My Account page.");
    }

@FindBy(xpath = "/html/body/div[2]/div/aside/div/a[4]")
WebElement rightclmnmyaccount;

    public void clickrightclmnmyaccount(){
        rightclmnmyaccount.click();
    }



    public String getBreadcrumbText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(breadcrumbElement));
        return breadcrumbElement.getText();
    }


    @FindBy(xpath = "/html/body/div[2]/div/div/div/div/a\n")  // adjust based on actual HTML
    public WebElement btnContinue0;

    public void clickbtnContinue0() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(btnContinue0));
        btnContinue0.click();
    }


    @FindBy(xpath = "//ul[@class='breadcrumb']//li[last()]")
    public WebElement breadcrumbLast;


    public boolean isAt() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(headingMyAccount),
                    ExpectedConditions.visibilityOf(breadcrumbLast)
            ));
            String bc = breadcrumbLast.getText().trim();
            return bc.equalsIgnoreCase("My Account") || headingMyAccount.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Heading of My Account page
    @FindBy(xpath = "//div[@id='content']/h2[1]")  // Adjust locator if needed
    public WebElement headingMyAccount;

    public String getMyAccountHeading() {
        return headingMyAccount.getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }




    // Change Password link
    @FindBy(xpath = "//a[text()='Change your password']")
    @CacheLookup
    WebElement lnkChangeYourPassword;



    // Breadcrumb text (for navigation validation)
    @FindBy(xpath = "//ul[@class='breadcrumb']//li[last()]")
    @CacheLookup
    WebElement breadcrumb;


    public void clickChangeYourPassword() {
        lnkChangeYourPassword.click();
    }


    public boolean isMyAccountPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Wait until the main "My Account" heading is visible
            return wait.until(ExpectedConditions.visibilityOf(msgHeading)).isDisplayed();
        } catch (Exception e) {
            // Log the error using your logger instance
            logger.error("My Account page heading not found or displayed: " + e.getMessage());
            return false;
        }
    }



    public boolean isMyAccountPageExists() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Wait until either heading OR breadcrumb is visible
            boolean pageLoaded = wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("/html/body/div[2]/div/div/h2[1]")

                    )

            ));

            return pageLoaded;
        } catch (Exception e) {
            System.out.println("DEBUG: My Account not found. Current URL = " + driver.getCurrentUrl());
            return false;
        }
    }

    @FindBy(linkText = "Password")
    WebElement lnkPassword;


    public void clickPassword() {
        lnkPassword.click();
    }

    public String getHeadingText() {
        return headingMyAccount.getText();
    }



    public void clickAddressBookLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            WebElement addressBook = wait.until(
                    ExpectedConditions.elementToBeClickable(By.linkText("Modify your address book entries"))
            );
            addressBook.click();
            logger.info("Clicked on Address Book link successfully.");
        } catch (Exception e) {
            logger.error("Unable to click on Address Book link: " + e.getMessage());
            Assert.fail("Unable to click on Address Book link: " + e.getMessage());
        }
    }

}




