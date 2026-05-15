package pageObjects;

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

import java.time.Duration;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver) {
        super(driver);
        
    }

    // LOCATORS

    @FindBy(xpath = "//div[@id='content']//h2[normalize-space()='My Account']")
    WebElement msgHeading;

    @FindBy(xpath = "//div[@id='content']//h2[1]")
    public WebElement headingMyAccount;

    @FindBy(xpath = "//aside[@id='column-right']//a[normalize-space()='Logout']")
    public WebElement lnkLogout;

    @FindBy(xpath = "//a[contains(normalize-space(),'Newsletter')]")
    public WebElement lnkNewsletter;

    @FindBy(xpath = "//aside[@id='column-right']//a[normalize-space()='Wish List']")
    private WebElement lnkRightColumnWishList;

    @FindBy(xpath = "//aside[@id='column-right']//a[contains(normalize-space(),'Recurring payments')]")
    private WebElement lnkRecurringPayments;

    @FindBy(xpath = "//aside[@id='column-right']//a[normalize-space()='Order History']")
    public WebElement lnkOrderHistory;

    @FindBy(xpath = "//div[@id='content']//a[contains(normalize-space(),'Modify your address book entries')]")
    public WebElement lnkModifyAddressBook;

    @FindBy(xpath = "//aside[@id='column-right']//a[contains(normalize-space(),'Edit Account')]")
    public WebElement lnkEditAccountInformation;

    @FindBy(xpath = "//a[normalize-space()='Downloads']")
    public WebElement lnkDownloads;

    @FindBy(xpath = "//aside[@id='column-right']//a[normalize-space()='Downloads']")
    public WebElement lnkDownloadsRightColumn;

    @FindBy(xpath = "//aside[@id='column-right']//a[normalize-space()='Address Book']")
    WebElement rightclmnmyaccount;

    @FindBy(xpath = "//a[contains(normalize-space(),'Change your password')]")
    @CacheLookup
    WebElement lnkChangeYourPassword;

    @FindBy(xpath = "//aside[@id='column-right']//a[normalize-space()='Password']")
    WebElement lnkPassword;

    @FindBy(xpath = "//a[contains(normalize-space(),'View your return requests')]")
    private WebElement lnkViewYourReturnRequests;

    @FindBy(xpath = "//footer//a[normalize-space()='Newsletter']")
    WebElement newsletterfooterlnk;

    @FindBy(xpath = "//footer//a[normalize-space()='Returns']")
    public WebElement lnkreturnfooterlink;

    @FindBy(id = "input-firstname")
    public WebElement txtFirstName;

    @FindBy(id = "input-lastname")
    public WebElement txtLastName;

    @FindBy(id = "input-email")
    public WebElement txtEmail;

    @FindBy(id = "input-telephone")
    public WebElement txtTelephone;

    @FindBy(xpath = "//input[@value='Continue']")
    public WebElement btnContinue;

    @FindBy(xpath = "//div[@id='content']//a[contains(@class,'btn-primary')]")
    public WebElement btnContinue0;

    @FindBy(css = ".text-danger")
    private WebElement validationMessage;

    @FindBy(xpath = "//ul[contains(@class,'breadcrumb')]//li[last()]")
    private WebElement breadcrumbElement;

    @FindBy(xpath = "//ul[contains(@class,'breadcrumb')]//li[last()]")
    public WebElement breadcrumbLast;

    @FindBy(id = "input-email")
    WebElement txtEmailAddress;

    @FindBy(id = "input-password")
    WebElement txtPassword;

    @FindBy(xpath = "//form[contains(@action,'account/login')]//input[@value='Login']")
    WebElement btnLogin;

    @FindBy(xpath = "//div[@id='content']//a[contains(@class,'btn-primary') and normalize-space()='Continue']")
    public WebElement btnContinueNewCustomer;

    @FindBy(xpath = "//div[contains(@class,'alert-danger') or contains(@class,'alert-dismissible')]")
    WebElement warningMessage;

    @FindBy(xpath = "//form[contains(@action,'account/login')]//a[contains(normalize-space(),'Forgotten Password')]")
    WebElement lnkForgotPassword;

    @FindBy(xpath = "//input[@value='Login' and contains(@class,'btn-primary')]")
    private WebElement loginButton;

    @FindBy(xpath = "//aside[@id='column-right']//a[normalize-space()='Login']")
    WebElement rightColumnLogin;

    @FindBy(xpath = "//ul[contains(@class,'breadcrumb')]//li[last()]")
    WebElement breadcrumb;

    @FindBy(xpath = "//div[@id='content']//h2[normalize-space()='Returning Customer']")
    WebElement pageHeading;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    private WebElement successAlertMessage;

    // ACTION METHODS

    public void clickLogout() {

        try {

            lnkLogout.click();

        } catch (Exception e) {

            System.out.println("Logout link not found.");
        }
    }

    public boolean isUserLoggedIn() {

        try {

            return lnkLogout.isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public void clickNewsletter() {
        lnkNewsletter.click();
    }

    public void clicknewsletterfooterlnk() {
        newsletterfooterlnk.click();
    }

    public void clickWishListFromMyAccount() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[contains(normalize-space(),'Wish List')]")
                )
        ).click();
    }

    public void clickRecurringPayments() {

        try {

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            lnkRecurringPayments
                    )
            ).click();

        } catch (Exception e) {

            System.out.println("Recurring Payments link not found.");
        }
    }

    public void clickViewYourReturnRequests() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            WebElement returnRequestsLink =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.xpath("//a[contains(normalize-space(),'View your return requests')]")
                            )
                    );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);",
                    returnRequestsLink
            );

            returnRequestsLink.click();

        } catch (Exception e) {

            logger.error(
                    "Failed to click View your return requests.",
                    e
            );

            Assert.fail(
                    "Failed to locate and click on View your return requests: "
                            + e.getMessage()
            );
        }
    }

    public void clicklnkreturnfooterlink() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            WebElement returnFooterLink =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.xpath("//footer//a[normalize-space()='Returns']")
                            )
                    );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);",
                    returnFooterLink
            );

            returnFooterLink.click();

        } catch (Exception e) {

            logger.error(
                    "Failed to click Return footer link.",
                    e
            );

            Assert.fail(
                    "Failed to locate and click on Return footer link: "
                            + e.getMessage()
            );
        }
    }

    public void clickOrderHistory() {

        try {

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            lnkOrderHistory
                    )
            ).click();

        } catch (Exception e) {

            logger.error(
                    "Failed to click the Order History link in My Account page.",
                    e
            );
        }
    }

    public void clickEditAccountInformation() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        lnkEditAccountInformation
                )
        ).click();
    }

    public boolean isMyAccountInformationPageDisplayed() {

        try {

            WebElement heading =
                    new WebDriverWait(driver, Duration.ofSeconds(10))
                            .until(
                                    ExpectedConditions.visibilityOfElementLocated(
                                            By.xpath("//div[@id='content']//h1")
                                    )
                            );

            return heading.isDisplayed();

        } catch (Exception e) {

            logger.error(
                    "My Account Information page not displayed: "
                            + e.getMessage()
            );

            return false;
        }
    }

    public void clearAllFields() {

        txtFirstName.clear();
        txtLastName.clear();
        txtEmail.clear();
        txtTelephone.clear();
    }

    public void clickContinue() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        btnContinue
                )
        ).click();
    }

    public void clickbtnContinue0() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        btnContinue0
                )
        );

        btnContinue0.click();
    }

    public boolean isValidationMessageDisplayed() {

        try {

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(5));

            wait.until(
                    ExpectedConditions.visibilityOf(
                            validationMessage
                    )
            );

            return validationMessage.isDisplayed();

        } catch (Exception e) {

            logger.error(
                    "Validation message not displayed.",
                    e
            );

            return false;
        }
    }

    public String getFirstNamePlaceholder() {

        return driver.findElement(
                By.id("input-firstname")
        ).getAttribute("placeholder");
    }

    public String getLastNamePlaceholder() {

        return driver.findElement(
                By.id("input-lastname")
        ).getAttribute("placeholder");
    }

    public String getEmailPlaceholder() {

        return driver.findElement(
                By.id("input-email")
        ).getAttribute("placeholder");
    }

    public String getTelephonePlaceholder() {

        return driver.findElement(
                By.id("input-telephone")
        ).getAttribute("placeholder");
    }

    public void clickDownloads() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        lnkDownloads
                )
        ).click();
    }

    public void clickDownloadsFromRightColumn() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        lnkDownloadsRightColumn
                )
        ).click();
    }

    public void clickrightclmnmyaccount() {
        rightclmnmyaccount.click();
    }

    public void clickChangeYourPassword() {
        lnkChangeYourPassword.click();
    }

    public void clickPassword() {
        lnkPassword.click();
    }

    public void clickAddressBookLink() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        try {

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.linkText("Modify your address book entries")
                    )
            ).click();

        } catch (Exception e) {

            logger.error(
                    "Unable to click on Address Book link: "
                            + e.getMessage()
            );

            Assert.fail(
                    "Unable to click on Address Book link: "
                            + e.getMessage()
            );
        }
    }

    public boolean isMyAccountPageDisplayed() {

        try {

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            return wait.until(
                    ExpectedConditions.visibilityOf(
                            msgHeading
                    )
            ).isDisplayed();

        } catch (Exception e) {

            logger.error(
                    "My Account page heading not found or displayed: "
                            + e.getMessage()
            );

            return false;
        }
    }

    public boolean isMyAccountPageExists() {

        try {

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@id='content']//h2[normalize-space()='My Account']")
                    )
            ).isDisplayed();

        } catch (Exception e) {

            System.out.println(
                    "DEBUG: My Account not found. Current URL = "
                            + driver.getCurrentUrl()
            );

            return false;
        }
    }

    public boolean isAt() {

        try {

            wait.until(
                    ExpectedConditions.or(
                            ExpectedConditions.visibilityOf(
                                    headingMyAccount
                            ),
                            ExpectedConditions.visibilityOf(
                                    breadcrumbLast
                            )
                    )
            );

            String bc =
                    breadcrumbLast.getText().trim();

            return bc.equalsIgnoreCase("My Account")
                    || headingMyAccount.isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public String getBreadcrumbText() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.visibilityOf(
                        breadcrumbElement
                )
        );

        return breadcrumbElement.getText();
    }

    public String getMyAccountHeading() {
        return headingMyAccount.getText();
    }

    public String getHeadingText() {
        return headingMyAccount.getText();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void login(String email, String password) {

        setEmail(email);

        setPassword(password);

        clickLogin();
    }

    public void clickRightColumnLogin() {

        clickElement(rightColumnLogin);
    }

    public String getBreadcrumb() {

        try {

            return getElementText(breadcrumb)
                    .replaceAll("\\s+", " ");

        } catch (Exception e) {

            return "";
        }
    }

    public String getPageHeading() {

        try {

            return getElementText(pageHeading);

        } catch (Exception e) {

            return "";
        }
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getEmailField() {
        return txtEmailAddress;
    }

    public WebElement getPasswordField() {
        return txtPassword;
    }

    public RegisterPage clickContinueButtonNewCustomer() {

        clickElement(btnContinueNewCustomer);

        return new RegisterPage(driver);
    }

    public void clickForgotPassword() {

        clickElement(lnkForgotPassword);
    }

    public boolean isLoginPageDisplayed() {

        try {

            return txtEmailAddress.isDisplayed()
                    && txtPassword.isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public void setEmail(String email) {

        typeText(txtEmailAddress, email);
    }

    public void setPassword(String pwd) {

        typeText(txtPassword, pwd);
    }

    public void clickLogin() {

        clickElement(btnLogin);
    }

    public String getWarningMessage() {

        try {

            return getElementText(warningMessage);

        } catch (Exception e) {

            return "";
        }
    }

    public void updateAccountInformation(
            String firstName,
            String lastName,
            String email,
            String telephone) {

        typeText(txtFirstName, firstName);

        typeText(txtLastName, lastName);

        typeText(txtEmail, email);

        typeText(txtTelephone, telephone);

        logger.info("Updated account information fields.");
    }

    public String getAccountUpdateSuccessMessage() {

        try {

            return getElementText(successAlertMessage);

        } catch (Exception e) {

            logger.error(
                    "Unable to fetch account update success message.",
                    e
            );

            return "";
        }
    }

    public void enterFirstName(String firstName) {

        typeText(txtFirstName, firstName);
    }

    public void enterLastName(String lastName) {

        typeText(txtLastName, lastName);
    }

    public void enterEmail(String email) {

        typeText(txtEmail, email);
    }

    public void enterTelephone(String telephone) {

        typeText(txtTelephone, telephone);
    }

    public String getEmailFieldValue() {

        return wait.until(
                ExpectedConditions.visibilityOf(txtEmail)
        ).getAttribute("value").trim();
    }

    public String getTelephoneFieldValue() {

        return wait.until(
                ExpectedConditions.visibilityOf(txtTelephone)
        ).getAttribute("value").trim();
    }

}