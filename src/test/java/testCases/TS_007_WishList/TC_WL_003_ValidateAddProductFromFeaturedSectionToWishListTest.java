package testCases.TS_007_WishList;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;

    public class TC_WL_003_ValidateAddProductFromFeaturedSectionToWishListTest extends BaseClass {

        @Test
        public void test_AddProductFromFeaturedSection_ToWishList() {
            logger.info("***** Starting TC_WL_003 Add Product from Featured Section to Wish List *****");

            try {
                // 1. Login
                Homepage hp = new Homepage(driver);
                hp.clickMyAccount();
                hp.clickLogin();

                LoginPage lp = new LoginPage(driver);
                lp.login(rb.getString("email"), rb.getString("password"));
                logger.info("Login successful");

                // 2. Click on Store Logo (in this site, "Your Store")
                driver.findElement(org.openqa.selenium.By.xpath("/html/body/header/div/div/div[1]/div/h1/a")).click();
                logger.info("Clicked on Store logo → navigated to Home page");
                Assert.assertTrue(driver.getTitle().contains("Your Store"),
                        "ER-1 Failed: Not navigated to Home page");

                // 3. Scroll to Featured Section
                FeaturedSectionPage fsp = new FeaturedSectionPage(driver);
                String featuredProduct = fsp.getFirstFeaturedProductName();
                logger.info("First Featured Product: " + featuredProduct);

                // 4. Add to Wish List from Featured Section
                driver.findElement(org.openqa.selenium.By.xpath(
                        "(//div[@id='content']//div[contains(@class,'product-layout')])[1]//button[@data-original-title='Add to Wish List']"
                )).click();

                // Validate ER-2 (Success Message)
                ProductDisplayPage pdp = new ProductDisplayPage(driver);
                String successMsg = pdp.getSuccessMessage();
                Assert.assertTrue(successMsg.contains("Success: You have added"),
                        "ER-2 Failed: Success message not displayed");
                logger.info("Success message displayed: " + successMsg);

                // 5. Click on "wish list!" link
                pdp.clickWishListLink();

                // 6. Validate ER-3 (Product present in My Wish List)
                WishListPage wlp = new WishListPage(driver);
                Assert.assertTrue(wlp.isProductInWishList(featuredProduct),
                        "ER-3 Failed: Product not found in Wish List");
                logger.info("Validation Passed: Product '" + featuredProduct + "' present in Wish List");

            } catch (Exception e) {
                logger.error("Test Failed: " + e.getMessage());
                Assert.fail();
            }

            logger.info("***** Finished TC_WL_003 Add Product from Featured Section to Wish List *****");
        }
    }



