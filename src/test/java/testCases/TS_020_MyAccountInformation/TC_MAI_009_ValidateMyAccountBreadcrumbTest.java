package testCases.TS_020_MyAccountInformation;


import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.BaseClass;
import pageObjects.MyAccountPage;

    public class TC_MAI_009_ValidateMyAccountBreadcrumbTest extends BaseClass {

        @Test
        public void validateMyAccountBreadcrumb() {
            logger.info("Starting Test Case: Validate Breadcrumb on 'My Account Information' page.");

            // Step 1: Perform Login
            performLogin();

            // Step 2: Navigate to 'My Account Information' page
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            myAccountPage.clickEditAccountInformation();
            logger.info("Navigated to 'Edit Account Information' page.");

            // Step 3: Validate breadcrumb
            String breadcrumb = myAccountPage.getBreadcrumb();
            logger.info("Breadcrumb on page: " + breadcrumb);

            // Step 4: Assertion
            String expectedBreadcrumb = "Edit Information"; // Update if needed
            Assert.assertEquals(breadcrumb, expectedBreadcrumb, "Breadcrumb validation failed!");

            logger.info("Test Case Passed: Breadcrumb is displayed correctly.");
        }
    }

