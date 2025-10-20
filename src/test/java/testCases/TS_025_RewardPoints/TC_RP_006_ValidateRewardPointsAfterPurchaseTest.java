package testCases.TS_025_RewardPoints;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.RewardPointsPage;
import testBase.BaseClass;

    public class TC_RP_006_ValidateRewardPointsAfterPurchaseTest extends BaseClass {


        @Test
        public void verifyRewardPointsAfterPurchase() throws InterruptedException {
            logger.info("==== Starting Test: TC_RP_006 - Reward Points ====");

            CheckoutPage checkout = new CheckoutPage(driver);

            // Step 1: Login
           performLogin();
            logger.info("User logged in successfully");

            Thread.sleep(2800);

            // Step 2: Add product
            HomePage home = new HomePage(driver);
            home.enterSearchText(productName);
            home.clickSearchButton();
            home.addProductToCart(productName);
            home.clickaddtocart();
            home.clickshoppingcartbtnmsg();
            home.clickcheckoutfromcart();
            logger.info("Navigated to checkout page");


            // Step 3: Complete Checkout
            checkout.continueBillingDetails();
            checkout.continueDeliveryDetails();
            checkout.continueDeliveryMethod();
            checkout.acceptTermsAndConditions();
            checkout.continuePaymentMethod();
            checkout.confirmOrder();
            logger.info("Order confirmed successfully");

            // Step 4: Reward Points Page
            checkout.clickOrderSuccessContinueButton();
            home.clickMyAccount();
            home.clickmyaccdpdwn();
            home.clickRewardPoints();

            RewardPointsPage rewardPage = new RewardPointsPage(driver);
            boolean areColumnsVisible = rewardPage.areTableColumnsDisplayed();
            Assert.assertTrue(areColumnsVisible, "Reward Points table columns are not displayed correctly");

            String rewardText = rewardPage.getTotalRewardPointsText();
            Assert.assertTrue(rewardText.contains("Your total number of reward points is"),
                    "Total reward points text not displayed");

            rewardPage.clickContinueRewardPoints();
            logger.info("==== Test TC_RP_006 Completed Successfully ====");
        }
    }