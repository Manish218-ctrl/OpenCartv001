package testCases.TS_010_Currencies;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CurrencyPage;
import testBase.BaseClass;

public class TC_CR_003_ValidateCurrencySelectionUSDollarTest extends BaseClass {

    @Test
    public void validateCurrencySelectionUSDollar() {

        logger.info(
                "Test Case TC_CR_003 - Currency Selection for US Dollar Started"
        );

        try {

            logger.info(
                    "Opening the application URL: " + p.getProperty("appURL")
            );

            getDriver().get(
                    p.getProperty("appURL")
            );

            CurrencyPage currencyPage =
                    new CurrencyPage(getDriver());

            logger.info(
                    "Clicking on the Currency dropdown"
            );

            currencyPage.clickCurrencyDropdown();

            logger.info(
                    "Selecting US Dollar from the currency options"
            );

            currencyPage.selectUSDCurrency();

            logger.info(
                    "Validating that US Dollar currency is selected"
            );

            String selectedCurrency =
                    currencyPage.getCurrentSelectedCurrencySymbol();

            Assert.assertEquals(
                    selectedCurrency,
                    "$",
                    "Currency selection did not change to US Dollar"
            );

            logger.info(
                    "Test Case TC_CR_003 - Currency Selection for US Dollar Completed Successfully"
            );

        } catch (Exception e) {

            logger.error(
                    "Test Case TC_CR_003 - Error occurred during Currency Selection Test: "
                            + e.getMessage()
            );

            Assert.fail(
                    "Test failed due to an error: "
                            + e.getMessage()
            );
        }
    }
}