package testCases.TS_019_MyAccountInformation;


import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.BaseClass;
import pageObjects.MyAccountPage;

    public class TC_MAI_009_ValidateMyAccountBreadcrumbTest extends BaseClass {

        @Test
        public void validateMyAccountBreadcrumb() {
            logger.info("Starting Test Case: Validate Breadcrumb on My Account Information page.");

            //Perform Login
            performLogin();

            //Navigate to My Account Information page
            MyAccountPage myAccountPage = new MyAccountPage(getDriver());
            myAccountPage.clickEditAccountInformation();
            logger.info("Navigated to Edit Account Information page.");

            //Validate breadcrumb
            String breadcrumb = myAccountPage.getBreadcrumb();
            logger.info("Breadcrumb on page: " + breadcrumb);

            //Assertion
            String expectedBreadcrumb = "Edit Information"; // Update if needed
            Assert.assertEquals(breadcrumb, expectedBreadcrumb, "Breadcrumb validation failed!");

            logger.info("Test Case Passed: Breadcrumb is displayed correctly.");
        }
    }

