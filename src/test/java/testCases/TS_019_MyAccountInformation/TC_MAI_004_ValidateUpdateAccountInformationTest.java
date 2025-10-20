package testCases.TS_019_MyAccountInformation;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_MAI_004_ValidateUpdateAccountInformationTest extends BaseClass {

    @Test
    public void TC_MAI_004_UpdateAccountInformationTest() throws InterruptedException {
        logger.info("Starting TS_014: Update Account Information Test");

        // Initialize Page Objects
        HomePage home = new HomePage(driver);
        MyAccountPage myAccount = new MyAccountPage(driver);
        LoginPage login = new LoginPage(driver);

        // Step 1: Login
        home.clickMyAccount();
        home.clickLogin();
        login.login(username, password);
        logger.info("Logged in with username: " + username);

        // Verify user is logged in
        Assert.assertTrue(myAccount.isUserLoggedIn(), "User should be logged in");

        // Step 2: Navigate to Edit Account Information
        myAccount.clickEditAccountInformation();
        Assert.assertTrue(myAccount.isMyAccountInformationPageDisplayed(),
                "My Account Information page should be displayed");

        //Step 3: Update Account Information
        String newFirstName = "AutoFirst" + randomString();
        String newLastName = "AutoLast" + randomString();
        String newEmail = "auto" + randomAlphaNumeric() + "example.com";
        String newTelephone = randomNumber();

        // Update Account Information using XPath
        driver.findElement(By.xpath("//input[@id='input-firstname']")).clear();
        driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(newFirstName);

        driver.findElement(By.xpath("//input[@id='input-lastname']")).clear();
        driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys(newLastName);

        driver.findElement(By.xpath("//input[@id='input-email']")).clear();
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(newEmail);

        driver.findElement(By.xpath("//input[@id='input-telephone']")).clear();
        driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys(newTelephone);

        myAccount.clickContinue();
        logger.info("Account details updated successfully");

        //  Step 4: Verify success message
        String successMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-success')]")).getText();
        Assert.assertTrue(successMessage.contains("Success: Your account has been successfully updated."),
                "Success message should be displayed");

        // Step 5: Logout and Login with new Email
        myAccount.clickLogout();
        home.clickMyAccount();
        home.clickLogin();

        login.login(newEmail, password);
        Assert.assertTrue(myAccount.isUserLoggedIn(), "User should be able to login with updated email");

        myAccount.clickLogout();

        //  Step 6: Verify login fails with old email
        home.clickMyAccount();
        home.clickLogin();
        login.login(username, password); // old email
        String warning = driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]")).getText();
        Assert.assertTrue(warning.contains("No match for E-Mail Address and/or Password"),
                "Login should fail with old email");

        logger.info("TS_014 executed successfully");
    }
}
