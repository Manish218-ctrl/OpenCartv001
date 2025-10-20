package testCases.TS_001_AccountRegistration;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.ExcelUtility;


public class TC_RF_001_ValidateAccountRegistrationDataDrivenTest extends BaseClass {




    @DataProvider(name="RegistrationDataFromExcel")
    public Object[][] getRegistrationData()
    {
        Object[][] data = ExcelUtility.getTestData("RegistrationData.xlsx", "Sheet1");
        return data;
    }

    @Test(dataProvider = "RegistrationDataFromExcel")
    public void verify_account_registration(String fname, String lname, String telephone, String password, String expMsg)
    {
        logger.info("***** Starting Data-Driven TC_RF_001_ValidateAccountRegistrationTest ****");
        logger.debug("Testing with data: FName=" + fname + ", LName=" + lname);

        try
        {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickRegister();
            logger.info("Navigated to Registration Page. Providing customer details...");

            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

            regpage.setFirstName(fname);
            regpage.setLastName(lname);

            String uniqueEmail = randomString().toLowerCase() + "@datadriven.com";
            regpage.setEmail(uniqueEmail);
            logger.debug("Using unique email: " + uniqueEmail);

            regpage.setTelephone(telephone);

            regpage.setPassword(password);
            regpage.setConfirmPassword(password); // Password and Confirm Password must match

            regpage.setPrivacyPolicy();
            regpage.clickContinue();

            logger.info("Validating expected confirmation message..");

            String confmsg = regpage.getConfirmationMsg();
            Assert.assertEquals(confmsg, expMsg, "Confirmation message mismatch for user: " + fname + " " + lname);

            logger.info("Test case passed for user: " + fname + " " + lname);
        }
        catch (Exception e)
        {
            logger.error("Test failed for user: " + fname + " " + lname + ". Error: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
        finally
        {
            logger.info("***** Finished a run of TC_RF_001_ValidateAccountRegistrationTest *****");
        }
    }
}