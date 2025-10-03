package testCases.TS_015_NewsLetter;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.NewsletterPage;
import testBase.BaseClass;

import java.io.IOException;

public class TC_NLT_002_ValidateNewsletterPageNavigationFromFooterTest extends BaseClass {

        @BeforeClass
        public void setupTestCase() throws IOException {
            // Initialize the WebDriver and perform the setup
            setup("windows", "chrome");  // Modify this as needed for the OS/browser.
        }

        @Test
        public void validateNewsletterSubscriptionPageNavigation() {
            // Step 1: Perform Login
            performLogin();

            // Step 2: Click on 'Newsletter' link in Right Column
            Homepage homepage = new Homepage(driver);
            homepage.clickRightColumnNewsletter();  // Method for clicking the 'Newsletter' in the right column

            // Step 3: Wait for the 'Newsletter Subscription' page and verify the page heading
            NewsletterPage newsletterPage = new NewsletterPage(driver);
            String actualPageTitle = newsletterPage.getPageTitle();
            String expectedPageTitle = "Newsletter Subscription";  // Modify if actual title differs

            Assert.assertEquals(actualPageTitle, expectedPageTitle, "Page title mismatch - Failed to navigate to the Newsletter Subscription page.");
        }


    }



