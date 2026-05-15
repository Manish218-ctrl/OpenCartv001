package testCases.TS_013_ChangePassword;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ChangePasswordPage;
import testBase.BaseClass;

import java.util.List;

public class TC_CP_010_ValidateChangePasswordBreadcrumbTest extends BaseClass {

    @Test
    public void validateBreadcrumb() {

        logger.info("===== Starting Test: TC_CP_010 – Change Password Breadcrumb =====");

        // Login
        performLogin();

        logger.info("User logged in successfully.");

        ChangePasswordPage cpPage = new ChangePasswordPage(getDriver());

        cpPage.openChangePasswordPage(appURL);

        Assert.assertTrue(
                cpPage.isBreadcrumbDisplayed(),
                "Breadcrumb is not displayed on Change Password page!"
        );

        logger.info("Breadcrumb is displayed.");

        List<WebElement> breadcrumbLinks =
                cpPage.getBreadcrumbLinks();

        logger.info("Breadcrumb links found: {}", breadcrumbLinks.size());

        for (WebElement link : breadcrumbLinks) {

            String linkText = link.getText();

            String href = link.getAttribute("href");

            logger.info(
                    "Breadcrumb link: {} -> {}",
                    linkText,
                    href
            );
        }

        logger.info("===== Test Completed Successfully – Breadcrumb validated =====");
    }
}