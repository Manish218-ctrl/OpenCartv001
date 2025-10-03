package testCases.TS_002_LoginFunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_LF_012_ValidateBreadcrumbAndPageDetailsTest extends BaseClass {

    @Test
    public void verify_LoginPage_Details() {
        logger.info("***** Starting TC_LF_012_ValidateBreadcrumbAndPageDetailsTest *****");

        // Step 1: Open Homepage and navigate to Login
        Homepage hp = new Homepage(driver);
        hp.clickMyAccount();
        hp.clickLogin();
        logger.info("Navigated to Login page");

        LoginPage login = new LoginPage(driver);

        // ER-1: Validate Breadcrumb
        String breadcrumb = login.getBreadcrumb();
        logger.info("Breadcrumb found: " + breadcrumb);
        Assert.assertTrue(
                breadcrumb.equalsIgnoreCase("Login") || breadcrumb.equalsIgnoreCase("Account Login"),
                "Breadcrumb text mismatch! Expected 'Login' or 'Account Login' but found: " + breadcrumb
        );

        // ER-2: Validate Page Heading
        String heading = login.getPageHeading();
        logger.info("Page heading found: " + heading);
        Assert.assertEquals(heading, "Returning Customer", "Page Heading mismatch!");

        // ER-3: Validate Page Title
        String title = driver.getTitle();
        logger.info("Page title found: " + title);
        Assert.assertEquals(title, "Account Login", "Page Title mismatch!");

        // ER-4: Validate Page URL
        String url = driver.getCurrentUrl();
        logger.info("Page URL found: " + url);
        Assert.assertTrue(url.contains("route=account/login"), "Page URL mismatch!");

        logger.info("***** Finished TC_LF_012_ValidateBreadcrumbAndPageDetailsTest *****");
    }
}
