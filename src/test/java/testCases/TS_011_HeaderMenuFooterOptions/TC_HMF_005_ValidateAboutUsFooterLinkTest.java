package testCases.TS_011_HeaderMenuFooterOptions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

    public class TC_HMF_005_ValidateAboutUsFooterLinkTest extends BaseClass {

        private static final Logger logger = LogManager.getLogger(TC_HMF_005_ValidateAboutUsFooterLinkTest.class);

        @Test
        public void validateAboutUsFooterLink() {
            logger.info("Test Case TC_HMF_005 - Validate 'About Us' Footer Link Started");

            try {
                // Open the application URL
                logger.info("Opening the application URL: " + rb.getString("appURL"));
                driver.get(rb.getString("appURL"));

                // Initialize the HomePage object
                HomePage homepage = new HomePage(driver);

                // Step 1: Click on the 'About Us' footer link
                logger.info("Clicking on the 'About Us' footer link");
                homepage.clickAboutUsFooterLink();

                // Step 2: Verify that the page redirects to the 'About Us' page and displays the correct information
                logger.info("Verifying the 'About Us' page title...");
                String pageTitle = homepage.getPageTitle();

                // Expected title of the 'About Us' page (adjust as per actual title of the page)
                String expectedPageTitle = "About Us";  // Replace this with the actual title

                Assert.assertEquals(pageTitle, expectedPageTitle, "The 'About Us' page did not load correctly.");

                logger.info("Test Case TC_HMF_005 - Validate 'About Us' Footer Link Completed Successfully");

            } catch (Exception e) {
                logger.error("Test Case TC_HMF_005 - Error occurred during validation of 'About Us' Footer link: " + e.getMessage());
                Assert.fail("Test failed due to an error: " + e.getMessage());
            }
        }
    }



