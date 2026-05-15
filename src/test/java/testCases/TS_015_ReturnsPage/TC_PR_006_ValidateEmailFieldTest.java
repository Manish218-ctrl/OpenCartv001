package testCases.TS_015_ReturnsPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.OrderHistoryPage;
import pageObjects.ProductReturnsPage;
import testBase.BaseClass;

public class TC_PR_006_ValidateEmailFieldTest extends BaseClass {

    @Test
    public void validateEmailFieldTest() {

        try {

            logger.info(
                    "Test started: validateEmailFieldTest"
            );

            logger.info(
                    "Opening the Application URL: https://tutorialsninja.com/demo/index.php?route=common/home"
            );

            getDriver().get(
                    "https://tutorialsninja.com/demo/index.php?route=common/home"
            );

            performLogin();

            logger.info(
                    "Navigating to Order History page..."
            );

            HomePage homepage =
                    new HomePage(getDriver());

            homepage.clickMyAccount();

            homepage.clickOrderHistory();

            logger.info(
                    "Clicking on the first order view icon..."
            );

            OrderHistoryPage orderHistoryPage =
                    new OrderHistoryPage(getDriver());

            orderHistoryPage.clickFirstOrderViewIcon();

            logger.info(
                    "Clicking on the Return icon for the selected order..."
            );

            ProductReturnsPage productReturnsPage =
                    new ProductReturnsPage(getDriver());

            productReturnsPage.clickReturnIcon();

            String[] invalidEmails = {
                    "testauto@.com",
                    "automation@gmail",
                    "testautomation@gmail."
            };

            for (String email : invalidEmails) {

                logger.info(
                        "Testing with invalid email: " + email
                );

                productReturnsPage.enterEmail(email);

                logger.info(
                        "Clicking the Submit button..."
                );

                productReturnsPage.clickSubmit();

                String errorMessage =
                        productReturnsPage.getEmailValidationMessage();

                logger.info(
                        "Error message displayed: " + errorMessage
                );

                Assert.assertTrue(
                        productReturnsPage.isEmailValidationMessageDisplayed(),
                        "Error message is not displayed for invalid email: "
                                + email
                );

                Assert.assertTrue(
                        errorMessage.contains(
                                "E-Mail Address does not appear to be valid!"
                        ),
                        "Error message does not contain Invalid email address for email: "
                                + email
                );
            }

            logger.info(
                    "Test completed successfully."
            );

        } catch (Exception e) {

            logger.error(
                    "Test failed due to an exception: "
                            + e.getMessage(),
                    e
            );

            Assert.fail(
                    "Test failed due to an exception: "
                            + e.getMessage()
            );
        }
    }
}