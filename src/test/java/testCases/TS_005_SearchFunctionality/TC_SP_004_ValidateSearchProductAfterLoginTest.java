package testCases.TS_005_SearchFunctionality;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.SearchPage; // Import the SearchPage
import testBase.BaseClass;


    public class TC_SP_004_ValidateSearchProductAfterLoginTest extends BaseClass {

        @Test(groups = {"sanity", "regression", "master"}) // Assigning groups for test categorization
        public void test_search_product_after_login() {
            logger.info("Starting TC_SP_004_ValidateSearchProductAfterLoginTest: Validate searching for a product after login.");

            try {
                // 1. Open the Application URL in any supported browser (handled by BaseClass setup)

                // 2. Login to the Application
                Homepage hp = new Homepage(driver);
                hp.clickMyAccount();
                logger.info("Clicked 'My Account' dropdown.");
                hp.clickLogin();
                logger.info("Clicked 'Login' link to navigate to login page.");

                LoginPage lp = new LoginPage(driver);
                lp.setEmail(p.getProperty("email")); // Using email from config.properties
                logger.info("Entering login email: " + p.getProperty("email"));
                lp.setPassword(p.getProperty("password")); // Using password from config.properties
                logger.info("Entering login password.");
                lp.clickLogin();
                logger.info("Attempting login.");

                MyAccountPage macc = new MyAccountPage(driver);
                Assert.assertTrue(macc.isMyAccountPageExists(), "Login failed: My Account page not displayed.");
                logger.info("Login successful. User is on My Account page.");

                // 3. Enter any existing product name into the 'Search' text box field - <Refer Test Data>
                SearchPage searchPage = new SearchPage(driver);
                String productName = p.getProperty("searchProduct"); // Reusing 'searchProduct' from config.properties

                searchPage.enterSearchKeyword(productName);
                logger.info("Entered product name into search box: " + productName);

                // 4. Click on the button having search icon (Validate ER-1)
                searchPage.clickSearchButton();
                logger.info("Clicked search icon button.");

                // Expected Result: 1. Searched product should be displayed in the search results
                // Verify the search results heading contains the product name
                String searchResultsHeading = searchPage.getSearchResultsHeading();
                Assert.assertTrue(searchResultsHeading.contains(productName), "Search results heading does not contain the searched product name.");
                logger.info("Verified search results heading: '" + searchResultsHeading + "'");

                // Verify the product itself is displayed in the search results
                Assert.assertTrue(searchPage.isProductDisplayed(productName), "Searched product '" + productName + "' is not displayed in the search results.");
                logger.info("Verified that the product '" + productName + "' is displayed in the search results.");

            } catch (Exception e) {
                logger.error("Test execution failed for TC_SP_004_ValidateSearchProductAfterLoginTest: " + e.getMessage());
                Assert.fail("Test failed due to an exception: " + e.getMessage());
            } finally {
                logger.info("Finished TC_SP_004_ValidateSearchProductAfterLoginTest.");
            }
        }
    }




