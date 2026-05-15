package testCases.TS_015_ReturnsPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductReturnsPage;
import testBase.BaseClass;

public class TC_PR_003_ValidateProductReturnsTest extends BaseClass {

    @Test
    public void validateMandatoryFieldsOnProductReturnsPage() {
        performLogin();

        HomePage home = new HomePage(getDriver());
        home.clickMyAccount();

        home.clickOrderHistory();

        ProductReturnsPage returnsPage = new ProductReturnsPage(getDriver());
        returnsPage.clickFirstOrderViewIcon();
        returnsPage.clickReturnIcon();

        returnsPage.clearAllMandatoryFields();
        returnsPage.clickSubmitrp();

        home.clickOrderHistory();

        returnsPage.clickSecondOrderViewIcon();
        returnsPage.clickReturnIcon();

        ProductReturnsPage returnsPage1 = new ProductReturnsPage(getDriver());
        returnsPage1.clearAllMandatoryFields();
        returnsPage1.clickSubmitrp();

        Assert.assertTrue(returnsPage1.isValidationErrorDisplayed(),
                "Mandatory field validation errors should appear");

        logger.info("Mandatory field validation verified successfully using credentials from config.properties");
    }
}