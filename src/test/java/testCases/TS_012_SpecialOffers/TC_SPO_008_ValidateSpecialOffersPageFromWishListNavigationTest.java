package testCases.TS_012_SpecialOffers;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SpecialOffersPage;
import pageObjects.WishListPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_SPO_008_ValidateSpecialOffersPageFromWishListNavigationTest extends BaseClass {

    @Test
    public void validateAddingProductToWishList() {
        logger.info("Starting the test: validateAddingProductToWishList");

        performLogin();
        logger.info("User logged in successfully.");

        HomePage homepage = new HomePage(getDriver());
        homepage.clickFooterWishList();

        SpecialOffersPage specialOffersPage = new SpecialOffersPage(getDriver());
        specialOffersPage.clickSpecialsLink();

        Assert.assertTrue(specialOffersPage.areSpecialOffersDisplayed(),
                "Special Offers are not displayed.");

        specialOffersPage.clickFirstSpecialOffer();
        specialOffersPage.clickAddToCart();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div.alert-success")));

        specialOffersPage.clickwishlisticon();

        WishListPage wishListPage = new WishListPage(getDriver());
        wishListPage.clickWishListHeader();

        String wishListPageHeading = wishListPage.getWishListPageHeading();
        logger.info("Wish List Page Heading: " + wishListPageHeading);
        Assert.assertEquals(wishListPageHeading, "My Wish List",
                "Failed to navigate to the Wish List page.");

        logger.info("Test completed. Tearing down...");
        tearDown();
        logger.info("Test execution finished.");
    }
}