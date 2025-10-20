package testCases.TS_017_OrderHistory;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

    public class TC_OH_009_ValidateOrderHistoryBreadcrumbTest extends BaseClass {

        @Test
        public void validateOrderHistoryBreadcrumb() {
            logger.info("====== TC_OH_009: Validate Order History Breadcrumb ======");

            // Step 1: Login to the application
            performLogin();
            logger.info("Logged in successfully");

            // Step 2: Navigate to Order History page
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount(); // Click My Account dropdown
            homePage.clickOrderHistory(); // Click 'Order History' link

            logger.info("Navigated to Order History page");

            // Step 3: Validate breadcrumb
            String actualBreadcrumb = homePage.getBreadcrumb();
            logger.info("Breadcrumb on Order History page: " + actualBreadcrumb);


            String expectedBreadcrumb = "Order History";

            Assert.assertEquals(actualBreadcrumb.trim(), expectedBreadcrumb, "Breadcrumb validation failed!");
            logger.info("Breadcrumb validated successfully");

            logger.info("====== TC_OH_009_ValidateOrderHistoryBreadcrumbTest Completed ======");
        }
    }




