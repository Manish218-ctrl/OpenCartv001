package testCases.TS_015_ReturnsPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.ProductReturnsPage;
import testBase.BaseClass;

public class TC_PR_002_ValidateSubmitValidateProductductForReturnTest extends BaseClass {

    @Test
    public void validateSubmitProductForReturn() throws InterruptedException {
        logger.info("Starting Test Case: TC_PR_002_ValidateSubmitValidateProductductForReturnTest");

        // Step 1: Login to the application
        HomePage homepage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        logger.info("Step 1: Navigating to Login Page");
        homepage.clickMyAccount();
        logger.info("Clicked 'My Account' on homepage");

        homepage.clickLogin();
        logger.info("Clicked 'Login' link");

        logger.info("Logging in with username: " + username);
        loginPage.login(username, password);
        logger.info("Login action performed");

        // Step 2: Navigate to 'My Account' page and click on 'View your return requests'
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        logger.info("Step 2: Navigating to 'My Account' page");
        homepage.clickMyAccount();
        logger.info("'My Account' clicked again to ensure navigation");

        myAccountPage.clicklnkreturnfooterlink();
        logger.info("Clicked on 'View your return requests' link in My Account page");

        // Step 3: Fill in all the fields for product return
        ProductReturnsPage productReturnsPage = new ProductReturnsPage(driver);
        logger.info("Step 3: Filling the Product Return form");

        logger.info("Filling first name: John");
        logger.info("Filling last name: Doe");
        logger.info("Filling email: john.doe@example.com");
        logger.info("Filling telephone: 1234567890");
        logger.info("Filling order ID: ORD12345");
        logger.info("Filling order date: 2015-08-25");
        logger.info("Filling product name: Laptop");
        logger.info("Filling product code: LP12345");
        logger.info("Filling quantity: 1");
        logger.info("Selecting reason for return: Faulty");
        logger.info("Agreeing to terms and conditions: true");
        logger.info("Adding product details: 'Broken screen Faulty Product This is a Test Automation Project'");

        productReturnsPage.fillProductReturnForm("ORD12345",
                "2015-08-25", "Laptop", "LP12345", "1", "Faulty", true, "Broken screen Faulty Product This is a Test Automation Project"
        );

        // Step 4: Submit the return request
        logger.info("Step 4: Submitting the Product Return form");
        productReturnsPage.clickSubmit();
        logger.info("Clicked 'Submit' button for product return");

        Thread.sleep(20000);

        // Step 5: Validate if the return was successful
        logger.info("Step 5: Validating return request submission");
        String returnSuccessMsg = productReturnsPage.getReturnsucessmsg();
        logger.info("Return success message displayed: " + returnSuccessMsg);

        Assert.assertEquals(returnSuccessMsg, "Thank you for submitting your return request. Your request has been sent to the relevant department for processing.");
        logger.info("Assertion passed: Product return submitted successfully.");

        logger.info("Test Case TC_PR_002_ValidateSubmitValidateProductductForReturnTest Completed");
    }
}
