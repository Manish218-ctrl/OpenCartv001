package testCases.TS_001_AccountRegistration;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.Homepage;
import testBase.BaseClass;
import utilities.ExcelUtility;


public class TC_RF_001_ValidateAccountRegistrationDataDrivenTest extends BaseClass {

    /**
     * Data Provider method to supply multiple sets of registration data read from an Excel sheet.
     * The utility method handles the reading of data from the specified file and sheet.
     */
    @DataProvider(name="RegistrationDataFromExcel")
    public Object[][] getRegistrationData()
    {
        // Calling the ExcelUtility to fetch the data.
        // Replace "YourExcelFile.xlsx" and "RegistrationSheet" with your actual file details.
        Object[][] data = ExcelUtility.getTestData("RegistrationData.xlsx", "Sheet1");
        return data;
    }

    // The @Test annotation is updated to use the new data provider name.
    @Test(dataProvider = "RegistrationDataFromExcel")
    public void verify_account_registration(String fname, String lname, String telephone, String password, String expMsg)
    {
        logger.info("***** Starting Data-Driven TC_RF_001_ValidateAccountRegistrationTest ****");
        logger.debug("Testing with data: FName=" + fname + ", LName=" + lname);

        try
        {
            Homepage hp = new Homepage(driver);
            hp.clickMyAccount();
            hp.clickRegister();
            logger.info("Navigated to Registration Page. Providing customer details...");

            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

            // Using the data-driven parameters from Excel
            regpage.setFirstName(fname);
            regpage.setLastName(lname);

            // Critical Step: Still generating a unique email, combined with a unique string from the test
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
            // Asserting the confirmation message using the expected message parameter
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