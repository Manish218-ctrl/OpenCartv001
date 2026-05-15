package testCases.TS_027_ContactUs;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ContactUsPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_CU_004_ValidateContactUsPageValidationTest extends BaseClass {

    @Test
    public void validateContactUsPageFieldsAndDetails() {
        logger.info("Starting the test: validateContactUsPageFieldsAndDetails");

        getDriver().get(appURL);

        HomePage homepage = new HomePage(getDriver());
        homepage.clickFooterContactUsLink();

        ContactUsPage contactUsPage = new ContactUsPage(getDriver());
        String pageTitle = contactUsPage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("Contact Us"), "Contact Us page title is incorrect!");

        Assert.assertNotNull(contactUsPage.getLocationHeading(),
                "Our Location details are missing on Contact Us page!");

        Assert.assertNotNull(contactUsPage.getStoreDetails(), "Store details are missing!");
        Assert.assertNotNull(contactUsPage.getPhoneDetails(), "Telephone details are missing!");

        Assert.assertTrue(contactUsPage.nameField.isDisplayed(), "Your Name field is missing!");
        Assert.assertTrue(contactUsPage.emailField.isDisplayed(), "E-Mail Address field is missing!");
        Assert.assertTrue(contactUsPage.enquiryField.isDisplayed(), "Enquiry field is missing!");

        logger.info("Test completed successfully.");
    }
}