package testCases.TS_012_HeaderMenuFooterOptions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import testBase.BaseClass;

    public class TC_HMF_001_ValidatePhoneNumberForContactUsHeaderOptionTest extends BaseClass {

        private static final Logger logger = LogManager.getLogger(TC_HMF_001_ValidatePhoneNumberForContactUsHeaderOptionTest.class);

        @Test
        public void validatePhoneNumberForContactUs() {
            logger.info("Test Case TC_HMF_001 - Validate correct Phone number for 'Contact Us' Header option Started");

            try {
                // Open the application URL
                logger.info("Opening the application URL: " + rb.getString("appURL"));
                driver.get(rb.getString("appURL"));

                // Initialize the Homepage object
                Homepage homepage = new Homepage(driver);

                // Step 1: Click on 'Contact Us' header option
                logger.info("Clicking on 'Contact Us' header option");
                homepage.clickContactUsHeaderOption();

                // Step 2: Get the phone number displayed next to the phone icon
                logger.info("Getting the phone number displayed in the header");
                String phoneNumber = homepage.getPhoneNumber();

                // Step 3: Validate that the phone number is displayed in the correct format (for example: +1 (800) 123-4567)
                String expectedPhoneNumberFormat = "123456789";  // Adjust according to the correct phone number format expected
                Assert.assertEquals(phoneNumber, expectedPhoneNumberFormat, "Phone number displayed is incorrect.");

                logger.info("Test Case TC_HMF_001 - Validate correct Phone number for 'Contact Us' Header option Completed Successfully");

            } catch (Exception e) {
                // Handle any errors and log the exception
                logger.error("Test Case TC_HMF_001 - Error occurred during Phone Number Validation: " + e.getMessage());
                Assert.fail("Test failed due to an error: " + e.getMessage());
            }
        }
    }



