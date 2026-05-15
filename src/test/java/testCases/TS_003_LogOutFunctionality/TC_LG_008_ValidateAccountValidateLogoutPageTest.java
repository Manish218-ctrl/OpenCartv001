package testCases.TS_003_LogOutFunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountSuccessPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_LG_008_ValidateAccountValidateLogoutPageTest extends BaseClass {

    @Test(groups = {"sanity", "regression", "master"})
    public void test_validate_account_logout_page() {

        logger.info(
                "Starting TC_LG_008_ValidateAccountValidateLogoutPageTest: Validate Account Logout page elements."
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

            hp.clickMyAccount();

            logger.info(
                    "Re-clicked My Account to open dropdown for logout."
            );

            hp.clickLogoutFromDropdown();

            logger.info(
                    "Selected Logout from the dropdown menu."
            );

            AccountSuccessPage accSuccess =
                    new AccountSuccessPage(getDriver());

            String pageHeading =
                    accSuccess.getConfirmationMsg();

            Assert.assertTrue(
                    pageHeading.contains("Account Logout"),
                    "Page Heading Account Logout is not displayed."
            );

            logger.info(
                    "Verified Page Heading: " + pageHeading + ""
            );

            String pageTitle =
                    getDriver().getTitle();

            Assert.assertTrue(
                    pageTitle.contains("Account Logout"),
                    "Page Title is not Account Logout. Actual: "
                            + pageTitle
            );

            logger.info(
                    "Verified Page Title: " + pageTitle + ""
            );

            String currentUrl =
                    getDriver().getCurrentUrl();

            Assert.assertTrue(
                    currentUrl.contains("account/logout"),
                    "Page URL does not contain account/logout. Actual: "
                            + currentUrl
            );

            logger.info(
                    "Verified Page URL: " + currentUrl + ""
            );

            LoginPage logoutPage =
                    new LoginPage(getDriver());

            String breadcrumbText =
                    logoutPage.getBreadcrumb();

            Assert.assertTrue(
                    breadcrumbText.contains("Logout"),
                    "Breadcrumb does not contain Logout. Actual: "
                            + breadcrumbText
            );

            logger.info(
                    "Verified Breadcrumb: " + breadcrumbText + ""
            );

        } catch (Exception e) {

            logger.error(
                    "Test execution failed for TC_LG_008_ValidateAccountValidateLogoutPageTest: "
                            + e.getMessage()
            );

            Assert.fail(
                    "Test failed due to an exception: "
                            + e.getMessage()
            );

        } finally {

            logger.info(
                    "Finished TC_LG_008_ValidateAccountValidateLogoutPageTest."
            );
        }
    }
}