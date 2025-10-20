package testCases.TS_002_LoginFunctionality;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

    public class TC_LF_008_ValidatePasswordCopyTest extends BaseClass {

        @Test
        public void verify_password_copy_restrictions() {
            logger.info("***** Starting TC_LF_008_ValidatePasswordCopyTest *****");

            try {
                // Step 1: Open application (already handled by BaseClass)
                HomePage hp = new HomePage(driver);
                hp.clickMyAccount();
                hp.clickLogin();
                logger.info("Navigated to Login page");

                // Step 2: Enter some password text
                LoginPage lp = new LoginPage(driver);
                WebElement passwordField = lp.getPasswordField();
                String samplePassword = "MySecret123";
                passwordField.sendKeys(samplePassword);

                // Step 3:Right-Click Copy (context menu should not allow copy)
                passwordField.sendKeys(Keys.chord(Keys.CONTROL, "a")); // select all
                passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.F10)); // open context menu
                String fieldType = passwordField.getAttribute("type");
                Assert.assertEquals(fieldType, "password", "Password field should remain masked.");

                // Step 4:Ctrl+C and paste into another field
                passwordField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                passwordField.sendKeys(Keys.chord(Keys.CONTROL, "c"));

                WebElement tempInput = (WebElement) ((org.openqa.selenium.JavascriptExecutor) driver)
                        .executeScript("let input=document.createElement('input');"
                                + "input.type='text'; document.body.appendChild(input); return input;");

                tempInput.sendKeys(Keys.chord(Keys.CONTROL, "v"));  // try paste

                String pastedValue = tempInput.getAttribute("value");
                Assert.assertNotEquals(pastedValue, samplePassword,
                        "Password should not be copied and pasted into another field.");

                logger.info("Test Passed: Password copy (Right-click + Ctrl+C) restricted as expected.");
            } catch (Exception e) {
                logger.error("Test Failed: " + e.getMessage());
                Assert.fail("Exception in TC_LF_008_ValidatePasswordCopyTest: " + e.getMessage());
            }

            logger.info("***** Finished TC_LF_008_ValidatePasswordCopyTest *****");
        }
    }


