package testCases.TS_014_NewsLetter;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.NewsletterPage;
import testBase.BaseClass;

import java.io.IOException;

public class TC_NLT_002_ValidateNewsletterPageNavigationFromFooterTest extends BaseClass {



        @Test
        public void validateNewsletterSubscriptionPageNavigation() {
            // Step 1: Perform Login
            performLogin();

            // Step 2: Click on 'Newsletter' link in Right Column
            HomePage homepage = new HomePage(driver);
            homepage.clickRightColumnNewsletter();

            // Step 3: Wait for the 'Newsletter Subscription' page and verify the page heading
            NewsletterPage newsletterPage = new NewsletterPage(driver);
            String actualPageTitle = newsletterPage.getPageTitle();
            String expectedPageTitle = "Newsletter Subscription";  // Modify if actual title differs

            Assert.assertEquals(actualPageTitle, expectedPageTitle, "Page title mismatch - Failed to navigate to the Newsletter Subscription page.");
        }


    }



