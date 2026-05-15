package testCases.TS_019_MyAccountInformation;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_MAI_004_ValidateUpdateAccountInformationTest extends BaseClass {

    @Test
    public void TC_MAI_004_UpdateAccountInformationTest() {

        logger.info("Starting TS_014: Update Account Information Test");

        HomePage home =
                new HomePage(getDriver());

        MyAccountPage myAccount =
                new MyAccountPage(getDriver());

        LoginPage login =
                new LoginPage(getDriver());

        home.clickMyAccount();

        home.clickLogin();

        login.login(username, password);

        logger.info(
                "Logged in with username: {}",
                username
        );

        Assert.assertTrue(
                myAccount.isUserLoggedIn(),
                "User should be logged in"
        );

        myAccount.clickEditAccountInformation();

        Assert.assertTrue(
                myAccount.isMyAccountInformationPageDisplayed(),
                "My Account Information page should be displayed"
        );

        String newFirstName =
                "AutoFirst" + randomString();

        String newLastName =
                "AutoLast" + randomString();

        String newEmail =
                "auto" + randomAlphaNumeric() + "@example.com";

        String newTelephone =
                randomNumber();

        myAccount.updateAccountInformation(
                newFirstName,
                newLastName,
                newEmail,
                newTelephone
        );

        myAccount.clickContinue();

        logger.info("Account details updated successfully");

        String successMessage =
                myAccount.getAccountUpdateSuccessMessage();

        Assert.assertTrue(
                successMessage.contains(
                        "Success: Your account has been successfully updated."
                ),
                "Success message should be displayed"
        );

        myAccount.clickLogout();

        home.clickMyAccount();

        home.clickLogin();

        login.login(newEmail, password);

        Assert.assertTrue(
                myAccount.isUserLoggedIn(),
                "User should be able to login with updated email"
        );

        myAccount.clickLogout();

        home.clickMyAccount();

        home.clickLogin();

        login.login(username, password);

        String warning =
                login.getWarningMessage();

        Assert.assertTrue(
                warning.contains("No match for E-Mail Address and/or Password"),
                "Login should fail with old email"
        );

        logger.info("TS_014 executed successfully");
    }
}