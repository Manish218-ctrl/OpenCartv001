package testCases.TS_024_Downloads;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DownloadsPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_DL_006_ValidateDownloadsPageNoOrdersTest extends BaseClass {

    @Test
    public void validateDownloadsPageNoOrders() {

        // Login
        performLogin();

        // Navigate to My Account page
        MyAccountPage myAccount = new MyAccountPage(getDriver());

        DownloadsPage downloadsPage = new DownloadsPage(getDriver());

        Assert.assertTrue(
                myAccount.isMyAccountPageExists(),
                "My Account page is not displayed."
        );

        // Click Downloads
        myAccount.clickDownloadsFromRightColumn();

        // Validate no downloadable orders message
        String expectedMessage =
                "You have not made any previous downloadable orders!";

        String actualText =
                downloadsPage.getNoDownloadsMessage();

        Assert.assertEquals(
                actualText,
                expectedMessage,
                "Downloads page message mismatch."
        );
    }
}