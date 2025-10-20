package testCases.TS_011_HeaderMenuFooterOptions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

    public class TC_HMF_007_ValidatePrivacyPolicyFooterLinkTest extends BaseClass {

        private static final Logger logger = LogManager.getLogger(TC_HMF_007_ValidatePrivacyPolicyFooterLinkTest.class);

        @Test
        public void validatePrivacyPolicyFooterLink() {
            logger.info("Test Case TC_HMF_007 - Validate 'Privacy Policy' Footer Link Started");

            try {
                // Open the application URL
                logger.info("Opening the application URL: " + rb.getString("appURL"));
                driver.get(rb.getString("appURL"));

                // Initialize the HomePage object
                HomePage homepage = new HomePage(driver);

                // Step 1: Click on the 'Privacy Policy' footer link
                logger.info("Clicking on the 'Privacy Policy' footer link");
                homepage.clickPrivacyPolicyFooterLink();

                // Step 2: Verify that the page redirects to the 'Privacy Policy' page and displays the correct information
                logger.info("Verifying the 'Privacy Policy' page title...");
                String pageTitle = homepage.getPageTitle();

                // Expected title of the 'Privacy Policy' page (adjust as per actual title of the page)

                String expectedPageTitle = "Privacy Policy";

                Assert.assertEquals(pageTitle, expectedPageTitle, "The 'Privacy Policy' page did not load correctly.");

                logger.info("Test Case TC_HMF_007 - Validate 'Privacy Policy' Footer Link Completed Successfully");

            } catch (Exception e) {
                logger.error("Test Case TC_HMF_007 - Error occurred during validation of 'Privacy Policy' Footer link: " + e.getMessage());
                Assert.fail("Test failed due to an error: " + e.getMessage());
            }
        }
    }



