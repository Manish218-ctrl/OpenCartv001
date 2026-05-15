package testCases.TS_020_ProductDisplayPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDisplayPage;
import testBase.BaseClass;

public class TC_PDP_008_ValidateSubmitProductReviewTest extends BaseClass {

    @Test
    public void submitProductReview() {

        logger.info("========== STARTING TC_PDP_009: Submit Product Review Test ==========");

        try {

            HomePage home = new HomePage(getDriver());
            ProductDisplayPage pdp = new ProductDisplayPage(getDriver());

            logger.info("Entering product name in search box: {}", productName);

            home.enterSearchText(productName);
            home.clickSearchButton();

            logger.info("Clicked on Search button");

            home.clickProductByName(productName);

            logger.info("Clicked on product link: {}", productName);

            pdp.closePopupIfPresent();

            pdp.clickReviewsTab();

            String reviewName = "TestUser_" + randomString();
            String reviewText = "This is a test review for automation " + randomString();

            pdp.enterReviewName(reviewName);
            pdp.enterReviewText(reviewText);
            pdp.selectFiveStarRating();
            pdp.clickSubmitReview();

            logger.info("Submitted product review successfully");

            String actualMessage = pdp.getReviewSuccessMessage();

            String expectedMessage =
                    "Thank you for your review. It has been submitted to the webmaster for approval.";

            logger.info("Validating review success message");

            Assert.assertTrue(actualMessage.contains(expectedMessage),
                    "Review submission message validation failed!");

            logger.info("Review submission success message validated successfully");
            logger.info("TC_PDP_009 COMPLETED SUCCESSFULLY");

        } catch (Exception e) {

            logger.error("ERROR occurred while submitting review: {}", e.getMessage(), e);

            Assert.fail("Test case failed due to exception: " + e.getMessage());
        }
    }
}