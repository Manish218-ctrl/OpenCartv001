package testCases.TS_020_ProductDisplayPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

public class TC_PDP_009_ValidateNoReviewsTest extends BaseClass {

    @Test
    public void validateNoReviewsMessage() {

        logger.info("========== STARTING TC_PDP_011: Validate No Reviews Tab ==========");

        try {

            HomePage home = new HomePage(getDriver());
            ProductDisplayPage pdp = new ProductDisplayPage(getDriver());

            String productWithoutReviews = "iMac";

            logger.info("Searching for product: {}", productWithoutReviews);

            home.enterSearchText(productWithoutReviews);
            home.clickSearchButton();
            home.clickProductByName(productWithoutReviews);

            logger.info("Clicked on product link: {}", productWithoutReviews);

            pdp.closePopupIfPresent();
            pdp.clickReviewsTab();

            logger.info("Clicked on Reviews tab");

            String actualMessage = pdp.getNoReviewsMessage();

            String expectedMessage =
                    "There are no reviews for this product.";

            logger.info("Expected message: {}", expectedMessage);
            logger.info("Actual message: {}", actualMessage);

            Assert.assertEquals(actualMessage, expectedMessage,
                    "Validation of No reviews message failed!");

            logger.info("No reviews message is displayed correctly");

            logger.info("========== TC_PDP_011 COMPLETED SUCCESSFULLY ==========");

        } catch (Exception e) {

            logger.error("ERROR occurred in TC_PDP_011: {}", e.getMessage(), e);

            Assert.fail("Test case failed due to exception: " + e.getMessage());
        }
    }
}