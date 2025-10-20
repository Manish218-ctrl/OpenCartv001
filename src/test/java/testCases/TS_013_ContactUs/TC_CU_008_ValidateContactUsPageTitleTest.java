package testCases.TS_013_ContactUs;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ContactUsPage;
import pageObjects.HomePage;
import testBase.BaseClass;

    public class TC_CU_008_ValidateContactUsPageTitleTest extends BaseClass {

        @Test
        public void validateContactUsPage() {
            // Step 1: Navigate to the Home Page
            driver.get(appURL);
            logger.info("Application URL opened: " + appURL);

            // Step 2: Click on 'Contact Us' from the homepage
            HomePage homePage = new HomePage(driver);
            homePage.clickContactUsHeaderOption();
            logger.info("Navigated to 'Contact Us' page.");

            // Step 3: Verify Page Title
            ContactUsPage contactUsPage = new ContactUsPage(driver);
            String pageTitle = contactUsPage.getPageTitle();
            Assert.assertEquals(pageTitle, "Contact Us", "Page Title mismatch.");

            // Step 4: Verify Page URL
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("route=information/contact"), "Page URL mismatch. Current URL: " + currentUrl);

            // Step 5: Verify Page Heading
            String pageHeading = contactUsPage.getPageTitle();
            Assert.assertEquals(pageHeading, "Contact Us", "Page Heading mismatch.");

            logger.info("Page URL, Title, and Heading validated successfully.");
        }
    }



