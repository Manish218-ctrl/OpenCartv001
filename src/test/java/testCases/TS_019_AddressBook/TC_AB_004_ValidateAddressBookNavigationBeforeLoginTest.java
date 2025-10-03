package testCases.TS_019_AddressBook;



import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;

    public class TC_AB_004_ValidateAddressBookNavigationBeforeLoginTest extends BaseClass {

        @Test
        public void validateAddressBookNavigationBeforeLogin() throws InterruptedException {
            logger.info("=== TC_AB_004: Start Test ===");

            try {
                // Step 1: Open the Application URL
                driver.get(appURL);
                logger.info("Opened Application URL: " + appURL);

                Homepage homePage = new Homepage(driver);

                // Step 2: Click on 'My Account' dropmenu
                homePage.clickMyAccount();
                logger.info("Clicked on 'My Account' dropdown.");

                // Step 3: Click on 'Register' option
                homePage.clickRegister();
                logger.info("Clicked on 'Register' option.");
Thread.sleep(20000);
                // Step 4: Click 'Address Book' option from the Right Column
                RegisterPage regPage = new RegisterPage(driver);
                regPage.clickAddressBook();
                logger.info("Navigated to Address Book from Registration page.");

                logger.info("Clicked on 'Address Book' option from the Right Column.");

                // Step 5: Perform login with valid credentials
                LoginPage loginPage = new LoginPage(driver);
                loginPage.login(username, password);
                logger.info("Performed login with username: " + username);

                // Step 6: Verify Address Book page is displayed
                AddressBookPage addressBookPage = new AddressBookPage(driver);
                addressBookPage.verifyAddressBookPage();
                logger.info("Address Book page verified successfully.");

            } catch (Exception e) {
                logger.error("Test TC_AB_004 Failed due to exception: " + e.getMessage());
                throw e; // Re-throw to fail the test
            }

            logger.info("=== TC_AB_004: End Test ===");
        }
    }


