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
                HomePage hp = new HomePage(getDriver());
                hp.clickMyAccount();
                hp.clickLogin();
                logger.info("Navigated to Login page");

                LoginPage lp = new LoginPage(getDriver());
                WebElement passwordField = lp.getPasswordField();
                String samplePassword = "MySecret123";
                passwordField.sendKeys(samplePassword);

                passwordField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.F10));
                String fieldType = passwordField.getAttribute("type");
                Assert.assertEquals(fieldType, "password", "Password field should remain masked.");

                passwordField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                passwordField.sendKeys(Keys.chord(Keys.CONTROL, "c"));

                WebElement tempInput = (WebElement) ((org.openqa.selenium.JavascriptExecutor) getDriver())
                        .executeScript("let input=document.createElement('input');"
                                + "input.type=text; document.body.appendChild('input'); return input;");

                tempInput.sendKeys(Keys.chord(Keys.CONTROL, "v"));

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


