package testCases.TS_018_AddressBook;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AddressBookPage;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_AB_007_ValidateNewBillingAddressAddedToAddressBookTest extends BaseClass {

    @Test
    public void validateNewBillingAddressAddedToAddressBook() {

        logger.info("===== TC_AB_007: Validate New Billing Address Added To Address Book =====");

        try {

            performLogin();

            logger.info("Login successful.");

            HomePage home =
                    new HomePage(getDriver());

            CheckoutPage checkout =
                    new CheckoutPage(getDriver());

            home.enterSearchText(productName);

            home.clickSearchButton();

            home.addProductToCart(productName);

            home.selectListView();

            home.clickaddtocart();

            home.clickshoppingcartbtnmsg();

            home.clickcheckoutfromcart();

            logger.info("Navigated to checkout page.");

            checkout.selectNewBillingAddress();

            logger.info("Selected new billing address option.");

            checkout.enterNewBillingAddress(
                    "John",
                    "Doe",
                    "123 New Street",
                    "New York",
                    "10001",
                    "United States",
                    "New York"
            );

            logger.info("Entered new billing address details.");

            checkout.continueBillingAddress();

            logger.info("Billing address continued.");

            checkout.continueShippingAddress();

            logger.info("Shipping address continued.");

            checkout.continueShippingMethod();

            logger.info("Shipping method continued.");

            checkout.acceptTermsAndConditions();

            logger.info("Terms and Conditions accepted.");

            checkout.continuePaymentMethod();

            logger.info("Payment method continued.");

            checkout.confirmOrder();

            logger.info("Order confirmed successfully.");

            Assert.assertTrue(
                    checkout.isOrderSuccessPageDisplayed(),
                    "Order success page is not displayed."
            );

            logger.info("Navigating to My Account.");

            home.clickMyAccount();

            home.clickMyAccountFromDropdown();

            MyAccountPage myAccount =
                    new MyAccountPage(getDriver());

            myAccount.clickAddressBookLink();

            logger.info("Address Book link clicked.");

            AddressBookPage addressBook =
                    new AddressBookPage(getDriver());

            addressBook.verifyAddressBookPage();

            logger.info("Address Book page verified.");

            addressBook.clickEditFirstAddress();

            boolean isDefaultSelected =
                    addressBook.isDefaultAddressSelected();

            Assert.assertTrue(
                    isDefaultSelected,
                    "Newly added address is not set as default."
            );

            logger.info("Newly added address verified as default.");

            logger.info("===== TC_AB_007 completed successfully =====");

        } catch (Exception e) {

            logger.error(
                    "TC_AB_007 failed due to exception: " + e.getMessage(),
                    e
            );

            Assert.fail(
                    "Test failed due to exception: " + e.getMessage()
            );
        }
    }
}