package testCases.TS_015_ReturnsPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.OrderHistoryPage;
import pageObjects.ProductReturnsPage;
import testBase.BaseClass;

public class TC_PR_008_ValidateBreadcrumbTest extends BaseClass {

    @Test
    public void validateBreadcrumbTest() {

        try {

            logger.info(
                    "Test Started: Validate the Breadcrumb in the Product Returns page"
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
                    "Clicking on the View icon for the first order..."
            );

            OrderHistoryPage orderHistoryPage =
                    new OrderHistoryPage(getDriver());

            orderHistoryPage.clickFirstOrderViewIcon();

            ProductReturnsPage productReturnsPage =
                    new ProductReturnsPage(getDriver());

            logger.info(
                    "Clicking on the Return icon..."
            );

            productReturnsPage.clickReturnIcon();

            logger.info(
                    "Validating Breadcrumb..."
            );

            Assert.assertTrue(
                    productReturnsPage.isBreadcrumbDisplayed(),
                    "Breadcrumb is not displayed."
            );

            String breadcrumbText =
                    productReturnsPage.getBreadcrumbText();

            Assert.assertTrue(
                    breadcrumbText.contains("Product Returns"),
                    "Breadcrumb text is incorrect. Expected text: Product Returns, but got: "
                            + breadcrumbText
            );

            Assert.assertEquals(
                    productReturnsPage.getPageTitle(),
                    "Product Returns",
                    "Product Returns page title is incorrect."
            );

            logger.info(
                    "Breadcrumb validated successfully."
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