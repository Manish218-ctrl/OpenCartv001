package testCases.TS_011_HeaderMenuFooterOptions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

    public class TC_HMF_006_ValidateDeliveryInformationFooterLinkTest extends BaseClass {

        private static final Logger logger = LogManager.getLogger(TC_HMF_006_ValidateDeliveryInformationFooterLinkTest.class);

        @Test
        public void validateDeliveryInformationFooterLink() {
            logger.info("Test Case TC_HMF_006 - Validate 'Delivery Information' Footer Link Started");

            try {
                // Open the application URL
                logger.info("Opening the application URL: " + rb.getString("appURL"));
                driver.get(rb.getString("appURL"));

                // Initialize the HomePage object
                HomePage homepage = new HomePage(driver);

                // Step 1: Click on the 'Delivery Information' footer link
                logger.info("Clicking on the 'Delivery Information' footer link");
                homepage.clickDeliveryInfoFooterLink();

                // Step 2: Verify that the page redirects to the 'Delivery Information' page and displays the correct information
                logger.info("Verifying the 'Delivery Information' page title...");
                String pageTitle = homepage.getPageTitle();

                // Expected title of the 'Delivery Information' page (adjust as per actual title of the page)

                String expectedPageTitle = "Delivery Information";  // Replace this with the actual title

                Assert.assertEquals(pageTitle, expectedPageTitle, "The 'Delivery Information' page did not load correctly.");

                logger.info("Test Case TC_HMF_006 - Validate 'Delivery Information' Footer Link Completed Successfully");

            } catch (Exception e) {
                logger.error("Test Case TC_HMF_006 - Error occurred during validation of 'Delivery Information' Footer link: " + e.getMessage());
                Assert.fail("Test failed due to an error: " + e.getMessage());
            }
        }
    }



