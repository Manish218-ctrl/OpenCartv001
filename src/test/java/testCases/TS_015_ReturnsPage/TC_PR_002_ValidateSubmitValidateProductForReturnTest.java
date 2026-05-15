package testCases.TS_015_ReturnsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.ProductReturnsPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_PR_002_ValidateSubmitValidateProductForReturnTest extends BaseClass {

    @Test
    public void validateSubmitProductForReturn() {

        logger.info(
                "Starting Test Case: TC_PR_002_ValidateSubmitValidateProductForReturnTest"
        );

        WebDriverWait wait =
                new WebDriverWait(getDriver(), Duration.ofSeconds(20));

        HomePage homepage =
                new HomePage(getDriver());

        LoginPage loginPage =
                new LoginPage(getDriver());

        logger.info(
                "Navigating to Login Page"
        );

        homepage.clickMyAccount();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']")
                )
        );

        logger.info(
                "Clicked My Account on homepage"
        );

        homepage.clickLogin();

        logger.info(
                "Clicked Login link"
        );

        logger.info(
                "Logging in with username: " + username
        );

        loginPage.login(username, password);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='content']//h2[text()='My Account']")
                )
        );

        logger.info(
                "Login action performed"
        );

        MyAccountPage myAccountPage =
                new MyAccountPage(getDriver());

        logger.info(
                "Navigating to My Account page"
        );

        homepage.clickMyAccount();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']")
                )
        );

        logger.info(
                "My Account clicked again to ensure navigation"
        );

        myAccountPage.clicklnkreturnfooterlink();

        logger.info(
                "Clicked on View your return requests link in My Account page"
        );

        ProductReturnsPage productReturnsPage =
                new ProductReturnsPage(getDriver());

        logger.info(
                "Filling the Product Return form"
        );

        logger.info(
                "Filling first name: John"
        );

        logger.info(
                "Filling last name: Doe"
        );

        logger.info(
                "Filling email: john.doe@example.com"
        );

        logger.info(
                "Filling telephone: 1234567890"
        );

        logger.info(
                "Filling order ID: ORD12345"
        );

        logger.info(
                "Filling order date: 2015-08-25"
        );

        logger.info(
                "Filling product name: Laptop"
        );

        logger.info(
                "Filling product code: LP12345"
        );

        logger.info(
                "Filling quantity: 1"
        );

        logger.info(
                "Selecting reason for return: Faulty"
        );

        logger.info(
                "Agreeing to terms and conditions: true"
        );

        logger.info(
                "Adding product details: Broken screen Faulty Product This is a Test Automation Project"
        );

        productReturnsPage.fillProductReturnForm(
                "ORD12345",
                "2015-08-25",
                "Laptop",
                "LP12345",
                "1",
                "Faulty",
                true,
                "Broken screen Faulty Product This is a Test Automation Project"
        );

        logger.info(
                "Submitting the Product Return form"
        );

        productReturnsPage.clickSubmit();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='content']/h1")
                )
        );

        logger.info(
                "Clicked Submit button for product return"
        );

        logger.info(
                "Validating return request submission"
        );

        String returnSuccessMsg =
                productReturnsPage.getReturnsucessmsg();

        logger.info(
                "Return success message displayed: "
                        + returnSuccessMsg
        );

        Assert.assertEquals(
                returnSuccessMsg,
                "Thank you for submitting your return request. Your request has been sent to the relevant department for processing."
        );

        logger.info(
                "Assertion passed: Product return submitted successfully."
        );

        logger.info(
                "Test Case TC_PR_002_ValidateSubmitValidateProductForReturnTest Completed"
        );
    }
}