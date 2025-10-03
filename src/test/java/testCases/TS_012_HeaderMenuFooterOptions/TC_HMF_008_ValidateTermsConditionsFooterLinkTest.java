package testCases.TS_012_HeaderMenuFooterOptions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import testBase.BaseClass;

    public class TC_HMF_008_ValidateTermsConditionsFooterLinkTest extends BaseClass {

        private static final Logger logger = LogManager.getLogger(TC_HMF_008_ValidateTermsConditionsFooterLinkTest.class);

        @Test
        public void validateTermsConditionsFooterLink() {
            logger.info("Test Case TC_HMF_008 - Validate 'Terms & Conditions' Footer Link Started");

            try {
                // Open the application URL
                logger.info("Opening the application URL: " + rb.getString("appURL"));
                driver.get(rb.getString("appURL"));

                // Initialize the Homepage object
                Homepage homepage = new Homepage(driver);

                // Step 1: Click on the 'Terms & Conditions' footer link
                logger.info("Clicking on the 'Terms & Conditions' footer link");
                homepage.clickTermsConditionsFooterLink();

                // Step 2: Verify that the page redirects to the 'Terms & Conditions' page and displays the correct information
                logger.info("Verifying the 'Terms & Conditions' page title...");
                String pageTitle = homepage.getPageTitle();

                // Expected title of the 'Terms & Conditions' page (adjust as per actual title of the page)
                String expectedPageTitle = "Terms & Conditions";  // Replace this with the actual title of the page

                Assert.assertEquals(pageTitle, expectedPageTitle, "The 'Terms & Conditions' page did not load correctly.");

                logger.info("Test Case TC_HMF_008 - Validate 'Terms & Conditions' Footer Link Completed Successfully");

            } catch (Exception e) {
                logger.error("Test Case TC_HMF_008 - Error occurred during validation of 'Terms & Conditions' Footer link: " + e.getMessage());
                Assert.fail("Test failed due to an error: " + e.getMessage());
            }
        }
    }



