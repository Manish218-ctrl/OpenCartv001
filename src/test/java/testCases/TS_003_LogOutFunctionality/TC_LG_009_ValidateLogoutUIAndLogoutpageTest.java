package testCases.TS_003_LogOutFunctionality;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountSuccessPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_LG_009_ValidateLogoutUIAndLogoutpageTest extends BaseClass {

    @Test(groups = {"sanity", "regression", "master"})
    public void test_validate_logout_ui_and_logout_page() {

        logger.info(
                "Starting TC_LG_009_ValidateLogoutUIAndLogoutpageTest: Validate UI of Logout option and Account Logout page."
        );

        try {

            HomePage hp = new HomePage(getDriver());

            hp.clickMyAccount();

            logger.info(
                    "Clicked My Account dropdown."
            );

            hp.clickLogin();

            logger.info(
                    "Clicked Login link to navigate to login page."
            );

            LoginPage lp = new LoginPage(getDriver());

            lp.setEmail(p.getProperty("email"));

            logger.info(
                    "Entering login email: "
                            + p.getProperty("email")
            );

            lp.setPassword(p.getProperty("password"));

            logger.info(
                    "Entering login password."
            );

            lp.clickLogin();

            logger.info(
                    "Attempting login."
            );

            MyAccountPage macc =
                    new MyAccountPage(getDriver());

            Assert.assertTrue(
                    macc.isMyAccountPageExists(),
                    "Login failed: My Account page not displayed."
            );

            logger.info(
                    "Login successful. User is on My Account page."
            );

            hp.clickMyAccount();

            logger.info(
                    "Re-opened My Account dropdown to check for Logout link UI."
            );

            boolean isDropdownLogoutDisplayed = false;

            try {

                isDropdownLogoutDisplayed =
                        hp.lnkLogoutFromDropdown.isDisplayed();

                Assert.assertTrue(
                        isDropdownLogoutDisplayed,
                        "Logout link in My Account dropdown is not displayed after login."
                );

                logger.info(
                        "Verified: Logout link is displayed in My Account dropdown."
                );

            } catch (NoSuchElementException e) {

                Assert.fail(
                        "Logout link in My Account dropdown was not found after login."
                );
            }

            boolean isRightColumnLogoutDisplayed = false;

            try {

                isRightColumnLogoutDisplayed =
                        macc.lnkLogout.isDisplayed();

                Assert.assertTrue(
                        isRightColumnLogoutDisplayed,
                        "Logout link in Right Column is not displayed after login."
                );

                logger.info(
                        "Verified: Logout link is displayed in the Right Column."
                );

            } catch (NoSuchElementException e) {

                Assert.fail(
                        "Logout link in Right Column was not found after login."
                );
            }

            hp.clickLogoutFromDropdown();

            logger.info(
                    "Selected Logout from the dropdown menu to navigate to logout page."
            );

            AccountSuccessPage accSuccess =
                    new AccountSuccessPage(getDriver());

            String pageHeading =
                    accSuccess.getConfirmationMsg();

            Assert.assertTrue(
                    pageHeading.contains("Account Logout"),
                    "Logout Page Heading Account Logout is not displayed or incorrect. Actual: "
                            + pageHeading
            );

            logger.info(
                    "Verified Logout Page Heading: " + pageHeading + ""
            );

            String pageTitle =
                    getDriver().getTitle();

            Assert.assertTrue(
                    pageTitle.contains("Account Logout"),
                    "Logout Page Title is not Account Logout. Actual: "
                            + pageTitle
            );

            logger.info(
                    "Verified Logout Page Title: " + pageTitle + ""
            );

            String currentUrl =
                    getDriver().getCurrentUrl();

            Assert.assertTrue(
                    currentUrl.contains("account/logout"),
                    "Logout Page URL does not contain account/logout. Actual: "
                            + currentUrl
            );

            logger.info(
                    "Verified Logout Page URL: " + currentUrl + ""
            );

            LoginPage logoutPage =
                    new LoginPage(getDriver());

            String breadcrumbText =
                    logoutPage.getBreadcrumb();

            Assert.assertTrue(
                    breadcrumbText.contains("Logout"),
                    "Logout Page Breadcrumb does not contain Logout. Actual: "
                            + breadcrumbText
            );

            logger.info(
                    "Verified Logout Page Breadcrumb: " + breadcrumbText + ""
            );

        } catch (Exception e) {

            logger.error(
                    "Test execution failed for TC_LG_009_ValidateLogoutUIAndLogoutpageTest: "
                            + e.getMessage()
            );

            Assert.fail(
                    "Test failed due to an exception: "
                            + e.getMessage()
            );

        } finally {

            logger.info(
                    "Finished TC_LG_009_ValidateLogoutUIAndLogoutpageTest."
            );
        }
    }
}