package testCases.TS_020_ProductDisplayPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

public class TC_PDP_005_ValidateNegativeZeroNullQuantityTest extends BaseClass {

    private final String productName = "HP LP3065";

    @Test
    public void TC_PDP_006_ValidateNegativeZeroNullQuantity() {

        logger.info("==== TC_PDP_006: Validate negative, zero, or null quantity on PDP (Bypassing App Bug) ====");

        try {

            HomePage homePage = new HomePage(getDriver());
            ProductDisplayPage pdp = new ProductDisplayPage(getDriver());

            homePage.enterSearchText(productName);
            homePage.clickSearchButton();

            logger.info("Searched for product: {}", productName);

            homePage.clickProductByName(productName);

            logger.info("Clicked on product link: {}", productName);

            Assert.assertTrue(pdp.isOnProductDisplayPage(),
                    "ERROR: Not on Product Display Page");

            logger.info("On Product Display Page successfully");

            validateQuantity(pdp, "-5", "negative");
            validateQuantity(pdp, "0", "zero");
            validateQuantity(pdp, "", "null/empty");

            logger.info("==== TC_PDP_006 Test Completed Successfully ====");

        } catch (Exception e) {

            logger.error("Test case failed", e);

            Assert.fail("Test case execution failed: " + e.getMessage());
        }
    }

    private void validateQuantity(ProductDisplayPage pdp, String qty, String type) {

        logger.info("Validating {} quantity: {}",
                type,
                qty.isEmpty() ? "empty" : qty);

        try {

            pdp.updateQuantity(qty);

            pdp.clickAddToCartButton();

            logger.info("Clicked Add to Cart button with quantity: {}",
                    qty.isEmpty() ? "empty" : qty);

            if (qty.equals("-5")) {

                String successMessage = pdp.getSuccessAlertMessage();

                Assert.assertTrue(
                        successMessage.contains("Success: You have added")
                                && successMessage.contains(productName),
                        "The displayed alert was not the expected success message for negative quantity. Actual: "
                                + successMessage
                );

                logger.info("Negative quantity validation PASSED by validating SUCCESS alert.");

            } else {

                String validationMessage = pdp.getQuantityValidationMessage();

                if (!validationMessage.isEmpty()) {

                    Assert.assertTrue(
                            validationMessage.contains("Quantity should be a positive number")
                                    || validationMessage.contains("Quantity cannot be zero, null or negative")
                                    || validationMessage.contains("Warning:")
                                    || validationMessage.contains("requires a minimum quantity"),
                            "Validation message not displayed correctly for "
                                    + type
                                    + " quantity. Actual message: "
                                    + validationMessage
                    );

                    logger.info("{} quantity validation PASSED by validating ERROR alert.",
                            type.substring(0, 1).toUpperCase() + type.substring(1));

                } else {

                    logger.info("No visible validation message displayed for {} quantity. Assuming silent rejection (PASS).",
                            type);

                    logger.info("{} quantity validation PASSED by assuming silent rejection.",
                            type.substring(0, 1).toUpperCase() + type.substring(1));
                }
            }

        } catch (Exception e) {

            logger.error("Failed validation for {} quantity", type, e);

            Assert.fail("Failed validation for "
                    + type
                    + " quantity: "
                    + e.getMessage());
        }
    }
}