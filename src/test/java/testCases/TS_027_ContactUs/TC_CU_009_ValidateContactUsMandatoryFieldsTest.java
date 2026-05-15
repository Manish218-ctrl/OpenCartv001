package testCases.TS_027_ContactUs;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.ContactUsPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_CU_009_ValidateContactUsMandatoryFieldsTest extends BaseClass {

    private HomePage homepage;
    private ContactUsPage contactUsPage;

    @BeforeClass
    public void setUp() {

        homepage = new HomePage(getDriver());
        contactUsPage = new ContactUsPage(getDriver());
    }

    @Test
    public void validateMandatoryFields() {

        getDriver().get(appURL);

        logger.info(
                "Application URL opened: " + appURL
        );

        homepage.clickFooterContactUsLink();

        logger.info(
                "Navigated to Contact Us page."
        );

        Assert.assertTrue(
                contactUsPage.isNameMandatoryLabelDisplayed(),
                "Your Name field is not marked as mandatory."
        );

        Assert.assertTrue(
                contactUsPage.isEmailMandatoryLabelDisplayed(),
                "E-Mail Address field is not marked as mandatory."
        );

        Assert.assertTrue(
                contactUsPage.isEnquiryMandatoryLabelDisplayed(),
                "Enquiry field is not marked as mandatory."
        );

        logger.info(
                "Verified mandatory fields on the Contact Us page."
        );
    }
}