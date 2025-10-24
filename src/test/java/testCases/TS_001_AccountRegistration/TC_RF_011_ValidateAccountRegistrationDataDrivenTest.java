package testCases.TS_001_AccountRegistration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.ExcelUtility;
import java.time.Duration;


public class TC_RF_011_ValidateAccountRegistrationDataDrivenTest extends BaseClass {

    @DataProvider(name="RegistrationDataFromExcel")
    public Object[][] getRegistrationData() {
        Object[][] rawData = ExcelUtility.getTestData("RegistrationData.xlsx", "Sheet1");

        int startIndex = 0;
        if (rawData.length > 0) {
            String firstCell = String.valueOf(rawData[0][0]);
            if (firstCell.contains("H0") || firstCell.equals("Data Set ID") ||
                    firstCell.equals("Column A")) {
                startIndex = 1;
            }
        }

        if (startIndex > 0 && rawData.length > startIndex) {
            Object[][] cleanData = new Object[rawData.length - startIndex][];
            System.arraycopy(rawData, startIndex, cleanData, 0, rawData.length - startIndex);
            System.out.println("Loaded " + cleanData.length + " test data sets.");
            return cleanData;
        }

        return rawData;
    }

    @Test(dataProvider = "RegistrationDataFromExcel",
            groups = {"Sanity", "Regression", "DataDriven"},
            description = "Data-driven test for account registration with various scenarios")
    public void verify_account_registration(
            String dataSetId,
            String testObjective,
            String fname,
            String lname,
            String telephone,
            String password,
            String expMsg)
    {
        logger.info("Data Set: {} | Objective: {}", dataSetId, testObjective);
        logger.info("User: {} {} | Tel: {} | Pwd: {}",
                fname, lname, telephone, password.replaceAll(".", "*"));

        boolean registrationSuccessful = false;

        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            homePage.clickRegister();
            logger.info("Navigated to Registration Page");

            AccountRegistrationPage regPage = new AccountRegistrationPage(driver);

            regPage.setFirstName(fname);
            regPage.setLastName(lname);

            String uniqueEmail = randomString().toLowerCase() + "@test.com";
            regPage.setEmail(uniqueEmail);
            logger.debug("Generated email: {}", uniqueEmail);

            regPage.setTelephone(telephone);

            regPage.setPassword(password);

            if (testObjective.contains("Password Mismatch") ||
                    testObjective.contains("Mismatch") ||
                    dataSetId.equals("2")) {
                String differentPassword = password + "X"; // Append "X" to make it different
                regPage.setConfirmPassword(differentPassword);
                logger.debug("Set Password: {} | Confirm Password: {} (MISMATCH)",
                        password.replaceAll(".", "*"),
                        differentPassword.replaceAll(".", "*"));
            } else {
                regPage.setConfirmPassword(password);
                logger.debug("Set Password: {} | Confirm Password: {} (MATCH)",
                        password.replaceAll(".", "*"),
                        password.replaceAll(".", "*"));
            }

            regPage.setPrivacyPolicy();
            regPage.clickContinue();

            logger.info("Registration form submitted");

            Thread.sleep(1500);

            String actualMsg = regPage.getConfirmationMsg();
            logger.info("Expected: '{}'", expMsg);
            logger.info("Actual:   '{}'", actualMsg);

            if (actualMsg.contains("Been Created") ||
                    actualMsg.contains("Your Account Has Been Created")) {
                registrationSuccessful = true;
                logger.info("Account created successfully");
            }

            Assert.assertEquals(actualMsg, expMsg,
                    String.format("Message mismatch for '%s %s' (Data Set: %s)",
                            fname, lname, dataSetId));

            logger.info("TEST PASSED - Data Set: {} | {}", dataSetId, testObjective);

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED - Data Set: {} | {}", dataSetId, testObjective);
            logger.error("Error: {}", ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("TEST FAILED - Data Set: {} | {}", dataSetId, testObjective);
            logger.error("Exception: {}", e.getMessage());
            Assert.fail("Test failed with exception: " + e.getMessage());
        } finally {
            if (registrationSuccessful) {
                logoutUser();
            }
        }
    }


    private void logoutUser() {
        try {
            logger.info("Logging out user for next test iteration...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            WebElement myAccountDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[text()='My Account']")));
            myAccountDropdown.click();

            WebElement logoutLink = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.linkText("Logout")));
            logoutLink.click();

            Thread.sleep(1000);

            logger.info("Successfully logged out");

        } catch (Exception e) {
            logger.warn("Logout failed (may not be logged in): {}", e.getMessage());
        }
    }
}
