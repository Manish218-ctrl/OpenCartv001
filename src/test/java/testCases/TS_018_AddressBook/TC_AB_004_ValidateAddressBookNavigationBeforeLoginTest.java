package testCases.TS_018_AddressBook;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;

import java.time.Duration;

public class TC_AB_004_ValidateAddressBookNavigationBeforeLoginTest extends BaseClass {

    @Test
    public void validateAddressBookNavigationBeforeLogin() {
        logger.info("=== TC_AB_004: Start Test ===");

        try {
            getDriver().get(appURL);
            logger.info("Opened Application URL: " + appURL);

            HomePage homePage = new HomePage(getDriver());

            homePage.clickMyAccount();
            logger.info("Clicked on My Account dropdown.");

            homePage.clickRegister();
            logger.info("Clicked on Register option.");

            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h1[normalize-space()='Register Account']")));

            RegisterPage regPage = new RegisterPage(getDriver());
            regPage.clickAddressBook();
            logger.info("Navigated to Address Book from Registration page.");

            logger.info("Clicked on Address Book option from the Right Column.");

            LoginPage loginPage = new LoginPage(getDriver());
            loginPage.login(username, password);
            logger.info("Performed login with username: " + username);

            AddressBookPage addressBookPage = new AddressBookPage(getDriver());
            addressBookPage.verifyAddressBookPage();
            logger.info("Address Book page verified successfully.");

        } catch (Exception e) {
            logger.error("Test TC_AB_004 Failed due to exception: " + e.getMessage());
            throw e;
        }

        logger.info("=== TC_AB_004: End Test ===");
    }
}