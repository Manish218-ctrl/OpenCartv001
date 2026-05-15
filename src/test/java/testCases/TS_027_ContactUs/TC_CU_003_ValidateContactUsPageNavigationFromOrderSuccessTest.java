package testCases.TS_027_ContactUs;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.OrderSuccessPage;
import pageObjects.ContactUsPage;
import pageObjects.CheckoutPage;
import testBase.BaseClass;

public class TC_CU_003_ValidateContactUsPageNavigationFromOrderSuccessTest extends BaseClass {

    @Test
    public void validateNavigationToContactUsPageFromOrderSuccess() {
        logger.info("Starting the test: validateNavigationToContactUsPageFromOrderSuccess");

        //Open the application URL

        logger.info("Opening application URL: " + p.getProperty("appURL"));
        getDriver().get(p.getProperty("appURL"));

        //Login before searching product

        logger.info("Performing login with valid credentials.");
        performLogin();

        //Search for the product HP LP3065 and add to cart

        HomePage home = new HomePage(getDriver());


        home.enterSearchText(productName);
        home.clickSearchButton();
        home.addProductToCart(productName);
        home.clickaddtocart();
        home.clickshoppingcartbtnmsg();
        home.clickcheckoutfromcart();



        //Checkout process
        CheckoutPage checkout = new CheckoutPage(getDriver());

        checkout.continueBillingDetails();
        checkout.continueDeliveryDetails();
        checkout.continueDeliveryMethod();
        checkout.acceptTermsAndConditions();
        checkout.continuePaymentMethod();
        checkout.confirmOrder();
        logger.info("Order confirmed successfully");

        //Click on the store owner link in the Order Success page
        logger.info("Clicking on store owner link in the Order Success page.");
        OrderSuccessPage orderSuccessPage = new OrderSuccessPage(getDriver());
        orderSuccessPage.clickStoreOwnerLink();

        //Verify that the user is taken to the Contact Us page
        logger.info("Verifying if the user is navigated to the Contact Us page.");
        ContactUsPage contactUsPage = new ContactUsPage(getDriver());
        String pageTitle = contactUsPage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("Contact Us"), "User is not navigated to the Contact Us page.");

        logger.info("Test completed successfully.");
    }
}
