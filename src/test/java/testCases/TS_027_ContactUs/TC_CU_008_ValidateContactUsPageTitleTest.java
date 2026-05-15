package testCases.TS_027_ContactUs;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ContactUsPage;
import pageObjects.HomePage;
import testBase.BaseClass;

    public class TC_CU_008_ValidateContactUsPageTitleTest extends BaseClass {

        @Test
        public void validateContactUsPage() {
            //Navigate to the Home Page
            getDriver().get(appURL);
            logger.info("Application URL opened: " + appURL);

            //Click on Contact Us from the homepage
            HomePage homePage = new HomePage(getDriver());
            homePage.clickContactUsHeaderOption();
            logger.info("Navigated to Contact Us page.");

            //Verify Page Title
            ContactUsPage contactUsPage = new ContactUsPage(getDriver());
            String pageTitle = contactUsPage.getPageTitle();
            Assert.assertEquals(pageTitle, "Contact Us", "Page Title mismatch.");

            //Verify Page URL
            String currentUrl = getDriver().getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("route=information/contact"), "Page URL mismatch. Current URL: " + currentUrl);

            //Verify Page Heading
            String pageHeading = contactUsPage.getPageTitle();
            Assert.assertEquals(pageHeading, "Contact Us", "Page Heading mismatch.");

            logger.info("Page URL, Title, and Heading validated successfully.");
        }
    }



