package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage {

    WebDriver driver;

    public static WebDriverWait wait;  // Removed static declaration
    static final Logger logger = LoggerFactory.getLogger(HomePage.class);


    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize the wait object
        PageFactory.initElements(driver, this); // Initialize WebElements
    }

    @FindBy(xpath = "//*[@id=\"top-links\"]/ul/li[2]/a/span[1]")
    public WebElement lnkMyaccount;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement lnkRegister;

    @FindBy(linkText = "Login")
    public WebElement linkLogin;

    @FindBy(xpath = "//aside[@id='column-right']//a[normalize-space()='Register']")
    WebElement lnkRightColumnRegister;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    public WebElement lnkLogoutFromDropdown;

    @FindBy(xpath = "//footer//a[normalize-space()='Privacy Policy']")
    private WebElement lnkFooterPrivacyPolicy;

    @FindBy(xpath = "//footer//a[normalize-space()='Terms & Conditions']")
    private WebElement lnkFooterTermsConditions;

    @FindBy(name = "search")
    private WebElement txtGlobalSearchInput;

    // Method to click on the 'Terms & Conditions' footer link
    public void clickTermsConditionsFooterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkFooterTermsConditions)).click();
    }


    // Method to click on the 'Privacy Policy' footer link
    public void clickPrivacyPolicyFooterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkFooterPrivacyPolicy)).click();
    }

    public void clickLogoutFromDropdown() {
        lnkLogoutFromDropdown.click();
    }

    public void clickRightColumnRegister() {
        lnkRightColumnRegister.click();
    }

    public void clickMyAccount() {
        lnkMyaccount.click();
    }

    public void clickRegister() {
        lnkRegister.click();
    }

    public void clickLogin() {
        linkLogin.click();
    }

    @FindBy(xpath = "//footer//a[normalize-space()='Wish List']")
    private WebElement lnkFooterWishList;

    public void clickFooterWishList() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkFooterWishList)).click();
    }

    @FindBy(xpath = "//input[@name='search']")
    private WebElement txtSearchBox;

    @FindBy(css = "button.btn.btn-default.btn-lg")
    private WebElement btnSearch;

    @FindBy(xpath = "//*[@id=\"cart-total\"]")
    public WebElement btnCartBlock;

    @FindBy(xpath = "/html/body/header/div/div/div[3]/div/ul/li[2]/div/p/a[1]/strong")
    public WebElement lnkViewCart;

    @FindBy(xpath = "//footer//a[normalize-space()='Delivery Information']")
    private WebElement lnkFooterDeliveryInfo;

    // Method to click on the 'Delivery Information' footer link
    public void clickDeliveryInfoFooterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkFooterDeliveryInfo)).click();
    }



    /*public void enterSearchText(String productName) {
        txtSearchBox.clear();
        txtSearchBox.sendKeys(productName);
    }*/

   /* public void clickSearchButton() {
        btnSearch.click();
    }*/

    /* public void clickAddToCart(String productName) {
        // Click 'Add to Cart' button for the product in search results
        WebElement addBtn = driver.findElement(By.xpath("//a[text()='" + productName + "']/ancestor::div[@class='product-thumb']//button[@onclick[contains(.,'cart.add')]]"));
        addBtn.click();
    }*/

    public void clickAddToCart1(String productName) {
        // Click 'Add to Cart' button for the product in search results
        WebElement addBtn = driver.findElement(By.xpath("//*[@id=\"button-cart\"]"));
        addBtn.click();
    }

    public void clickCartBlock() {
        btnCartBlock.click();
    }


    @FindBy(xpath = "/html/body/nav/div/div[2]/ul/li[1]/a/i")  // Added the Contact Us header option
    public WebElement contactUsHeaderOption;


    @FindBy(xpath = "/html/body/nav/div/div[2]/ul/li[1]/span")  // The phone number in the header
    public WebElement phoneNumberElement;

    @FindBy(xpath = "//footer//a[normalize-space()='About Us']")
    private WebElement lnkFooterAboutUs;

    // Method to click on the 'About Us' footer link
    public void clickAboutUsFooterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkFooterAboutUs)).click();
    }


    // Method to get the phone number displayed next to the 'Contact Us' option
    public String getPhoneNumber() {
        wait.until(ExpectedConditions.visibilityOf(phoneNumberElement));
        return phoneNumberElement.getText();  // Return the text of the phone number
    }

    @FindBy(xpath = "//footer//a[normalize-space()='Brands']")
    private WebElement lnkFooterBrands;

    // Method to click on 'Brands' footer link
    public void clickBrandsFooterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkFooterBrands)).click();
    }

    // Method to select a brand by name (generic, based on brand name input)
    public void clickBrandByName(String brandName) {
        WebElement brand = driver.findElement(By.linkText(brandName));
        wait.until(ExpectedConditions.elementToBeClickable(brand)).click();
    }

    // Method to select 'List' view for products
    @FindBy(id = "list-view")
    public WebElement listViewOption;

    public void selectListView() {
        wait.until(ExpectedConditions.elementToBeClickable(listViewOption)).click();
    }


    public void clickFooterLink(String linkText) {
        WebElement footerLink = driver.findElement(By.xpath("//footer//a[normalize-space()='" + linkText + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(footerLink)).click();
    }


    // Method to select 'Grid' view for products
    @FindBy(id = "grid-view")
    public WebElement gridViewOption;

    public void selectGridView() {
        wait.until(ExpectedConditions.elementToBeClickable(gridViewOption)).click();
    }

    // Define the WebElement for the Product Page link (replace with actual selector)
    @FindBy(linkText = "")
    private WebElement linkProductPage;

    // Method to click on the 'Product Page' link
    public void clickProductPage() {
        wait.until(ExpectedConditions.elementToBeClickable(linkProductPage)).click();
    }


    // Locator for the "Specials" footer link (using Link Text for simplicity)
    private By specialsFooterLink = By.linkText("Specials");


    // Method to click the 'Specials' footer link
    public void clickFooterLink() {
        WebElement link = driver.findElement(specialsFooterLink);
        link.click();
    }


    public void clickFooterSpecialsLink() {
        try {
            WebElement specialsLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//footer//a[normalize-space()='Specials']")
            ));
            specialsLink.click();
            logger.info("Clicked on 'Specials' footer link.");
        } catch (Exception e) {
            logger.error("Error while clicking 'Specials' footer link: " + e.getMessage());
        }
    }


    // Checkout steps
    // @FindBy(name = "checkout")
    @FindBy(xpath = "/html/body/header/div/div/div[3]/div/ul/li[2]/div/p/a[2]/strong")
    public WebElement btnCheckout;

    @FindBy(xpath = "//*[@id=\"button-payment-address\"]")
    private WebElement btnContinue;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div[5]/div[2]/div/div[2]/div/input[1]")
    private WebElement chkTermsAndConditions;

    @FindBy(name = "confirm")
    private WebElement btnConfirmOrder;


    @FindBy(xpath = "/html/body/footer/div/div/div[2]/ul/li[1]/a")
    public static WebElement lnkFooterContactUs;

    public static void clickFooterContactUsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkFooterContactUs)).click();
        logger.info("Clicked on 'Contact Us' footer link.");
    }


    // Method to click 'Continue' during checkout
    public void clickContinue() {
        btnContinue.click();
    }

    public void clickbtnCheckout() {
        btnCheckout.click();
    }

    // Method to select 'Terms and Conditions'
    public void selectTermsAndConditions() {
        chkTermsAndConditions.click();
    }

    // Method to confirm the order
    public void clickConfirmOrder() {
        btnConfirmOrder.click();
    }

    public void clickContactUsHeaderOption() {
        WebElement contactUsButton = driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[1]/a/i"));
        contactUsButton.click();
    }

    @FindBy(xpath = "//span[normalize-space()='My Account']")
    public WebElement lnkMyAccount;

    @FindBy(xpath = "//a[normalize-space()='Login']")
    public WebElement lnkLogin;

    @FindBy(xpath = "//a[normalize-space()='Subscribe/unsubscribe to newsletter']")
    public WebElement lnkNewsletterSubscription;

    // Assuming there's a link or button for the newsletter
    @FindBy(xpath = "/html/body/div[2]/div/aside/div/a[12]")  // Modify the XPath as per your page
            WebElement NewsletterLink;

    // Method to click on the 'Newsletter' link
    public void clickNewsletterLink() {
        NewsletterLink.click();
    }


    @FindBy(xpath = "/html/body/div[2]/div/aside/div/a[12]")
    public WebElement lnkRightColumnNewsletter;

    public void clickRightColumnNewsletter() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkRightColumnNewsletter)).click();
    }

    @FindBy(xpath = "//footer//a[normalize-space()='Newsletter']")
    public WebElement lnkFooterNewsletter;

    public void clickFooterNewsletterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkFooterNewsletter)).click();
        logger.info("Clicked on the 'Newsletter' footer link.");
    }

    @FindBy(xpath ="/html/body/div[2]/ul")
    private WebElement breadcrumbElement;

    // Method to get the breadcrumb text
    public String getBreadcrumb() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(breadcrumbElement));
        return breadcrumbElement.getText();
    }

    @FindBy(xpath = "//a[normalize-space()='Order History']")
    public WebElement orderHistoryLink;



    public void clickCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement checkoutButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/div/div[3]/div/ul/li[2]/div/p/a[2]/strong"))
        );
        checkoutButton.click();
    }

    @FindBy(xpath="/html/body/div[2]/div/div/div/div/a")
    public WebElement Checkoutemptycart;

    public void clickCheckoutemptycart(){
        Checkoutemptycart.click();
    }


    @FindBy(xpath = "//a[contains(text(),'Continue Shopping')]")
    private WebElement btnContinueShopping;

    public void clickContinueShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(btnContinueShopping)).click();
        logger.info("Clicked 'Continue Shopping' button");
    }


    @FindBy(xpath = "/html/body/header/div/div/div[1]/div/h1/a")   // Adjust XPath as per your application
    private static WebElement logoYourStore;

    public static void clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logoYourStore)).click();
    }


    @FindBy(xpath = "//a[normalize-space()='Desktops']")
    private WebElement menuDesktops;

    @FindBy(xpath = "//a[normalize-space()='PC (0)']")
    private WebElement subMenuPC;

    public void navigateToEmptyPCCategory() {
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(menuDesktops));
        actions.moveToElement(menuDesktops).perform();
        wait.until(ExpectedConditions.elementToBeClickable(subMenuPC)).click();
    }


    // Partner Carousel Section
    @FindBy(xpath = "//div[@class='partner-carousel']")
    private WebElement partnerCarouselSection;

    // Carousel Logos
    @FindBy(xpath = "//div[@class='partner-carousel']//img")
    private List<WebElement> partnerLogos;

    // Next & Previous Arrows
    @FindBy(css = ".swiper-button-next")
    private WebElement nextArrow;

    @FindBy(css = ".swiper-button-prev")
    private WebElement prevArrow;

    // Pagination Bullets
    @FindBy(css = ".swiper-pagination-bullet")
    private List<WebElement> paginationBullets;

    // Cart button
    @FindBy(xpath = "//a[contains(@class,'btn') and contains(text(),'Cart')]")
    private WebElement btnCart;




    // Check if carousel section is displayed
    public boolean isPartnerCarouselDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(partnerCarouselSection)).isDisplayed();
    }

    // Get partner logos count
    public int getPartnerLogosCount() {
        return partnerLogos.size();
    }

    // Check if logos are displayed
    public boolean areLogosDisplayed() {
        for (WebElement logo : partnerLogos) {
            if (!logo.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    // Click next arrow
    public void clickNextArrow() {
        wait.until(ExpectedConditions.elementToBeClickable(nextArrow)).click();
    }

    // Click previous arrow
    public void clickPrevArrow() {
        wait.until(ExpectedConditions.elementToBeClickable(prevArrow)).click();
    }

    // Click pagination bullet by index
    public void clickPaginationBullet(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(paginationBullets.get(index))).click();
    }

    // Drag carousel using mouse
    public void dragCarousel() {
        Actions actions = new Actions(driver);
        actions.clickAndHold(partnerLogos.get(0))
                .moveByOffset(-200, 0)   // drag left
                .release()
                .perform();
    }


    // From cart dropdown (used in empty cart tests)
    public void clickViewCartFromCartDropdown() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement viewCartLink = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='text-right']/a[1]"))
            );
            viewCartLink.click();
            logger.info("Clicked on 'View Cart' link from cart dropdown.");
        } catch (Exception e) {
            logger.error("Failed to click 'View Cart' from cart dropdown: " + e.getMessage());
            throw e;
        }
    }


    public void clickViewCartOption() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // --- Case 1: Success alert after adding product ---
            WebElement successAlert = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert-success"))
            );
            WebElement viewCartLink = successAlert.findElement(By.linkText("shopping cart"));
            wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
            logger.info("Clicked on 'View Cart' link from success alert.");
        } catch (TimeoutException e) {
            // --- Case 2: Fallback → cart dropdown ---
            logger.warn("'View Cart' not found in success alert, trying cart dropdown...");

            clickCartBlock(); // open dropdown
            WebElement viewCartLink = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cart-total\"]"))
            );
            viewCartLink.click();
            logger.info("Clicked on 'View Cart' link from cart dropdown.");
        } catch (Exception e) {
            logger.error("Failed to click on 'View Cart' link: " + e.getMessage());
            throw e;
        }
    }


    public void navigateDirectlyToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Open the cart dropdown
            clickCartBlock();

            // Wait for dropdown to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("ul.dropdown-menu.pull-right")));

            // Click the "View Cart" / "Shopping Cart" link (first <a> in the footer)
            WebElement viewCartLink = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/div/div[3]/div/ul/li[2]/div/p/a[1]/strong"))
            );
            viewCartLink.click();

            logger.info("Navigated directly to Shopping Cart page via cart dropdown.");
        } catch (Exception e) {
            logger.error("Failed to navigate directly to cart: " + e.getMessage());
            throw e;
        }
    }


    public void clickCart1() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(btnCart)).click();
            logger.info("Clicked Cart button successfully.");
        } catch (Exception e) {
            logger.error("Failed to click Cart button: " + e.getMessage());
            throw e;
        }
    }



    @FindBy(xpath = "/html/body/div[2]/div/aside/div/a[9]")
    public WebElement lnkRewardPoints;

    public void clickRewardPoints() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkRewardPoints)).click();
        logger.info("Clicked on 'Your Reward Points' link");
    }


    @FindBy(xpath = "//a[normalize-space()='Transactions']")
    public WebElement lnkTransactions;


    // Locator for Transactions link
    private By transactionsLink = By.xpath("/html/body/nav/div/div[2]/ul/li[2]/ul/li[3]/a");


    // Replace old method
    public void clickTransactions() {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.elementToBeClickable(transactionsLink)
            );
            element.click();
            logger.info("Clicked 'Transactions' link successfully.");
        } catch (TimeoutException te) {
            throw new RuntimeException("Transactions link not found/clickable within timeout.", te);
        }

    }

    @FindBy (xpath ="//*[@id='button-cart']")
    public WebElement addtocart;

    public void clickaddtocart(){addtocart.click();}


    @FindBy(xpath = "/html/body/div[2]/div/aside/div/a[8]")
    public WebElement recurringPaymentsLink;

    // Method to click on the "Recurring Payments" link in the right column
    public void clickRecurringPaymentsLink() {
        recurringPaymentsLink.click();
    }


    // My Account dropdown menu
    @FindBy(xpath = "//*[@id='top-links']/ul/li[2]/ul/li[1]/a")
    private WebElement lnkMyAccountFromDropdown;


    public void clickMyAccountFromDropdown() {
        lnkMyAccountFromDropdown.click();
    }

    public void clickAddToCart(String productName) {
        try {
            //  Wait for the product container to be visible
            WebElement productContainer = new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[1]/a" + productName + "']]")
                    ));
            //  Scroll into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productContainer);

            //  Handle dropdowns (select boxes)
            List<WebElement> selects = productContainer.findElements(By.tagName("select"));
            for (WebElement select : selects) {
                Select dropdown = new Select(select);
                if (dropdown.getOptions().size() > 1) {
                    dropdown.selectByIndex(1); // select first non-default option
                }
            }

            //  Handle radio buttons and checkboxes
            List<WebElement> inputs = productContainer.findElements(By.cssSelector("input[type='radio'], input[type='checkbox']"));
            for (WebElement input : inputs) {
                if (!input.isSelected()) {
                    input.click();
                }
            }

            //  Handle required text inputs
            List<WebElement> textInputs = productContainer.findElements(By.cssSelector("input[type='text'][required]"));
            for (WebElement input : textInputs) {
                input.clear();
                input.sendKeys("Test"); // customize if needed
            }


            //  Handle date inputs
            List<WebElement> dateInputs = productContainer.findElements(By.cssSelector("input[type='date']"));
            for (WebElement input : dateInputs) {
                input.sendKeys("2025-09-01"); // adjust format as required
            }

            //  Click Add to Cart button
            WebElement addToCartBtn = productContainer.findElement(By.xpath(".//button[contains(@onclick,'cart.add')]"));
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(addToCartBtn));
            addToCartBtn.click();

            logger.info(productName + " added to cart successfully.");

        } catch (TimeoutException e) {
            logger.error(" ERROR: Product '" + productName + "' not found or not clickable.", e);
            throw e;
        } catch (Exception e) {
            logger.error(" ERROR while adding '" + productName + "' to cart: " + e.getMessage(), e);
            throw e;
        }


    }


    @FindBy(xpath = "/html/body/div[2]/div/div/div[3]/div/div/div[2]/div[2]/button[1]")

    private WebElement addtocart0;

    public void clickaddtocart0() {

        addtocart0.click();
    }



    public void addtocartmain() {
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[2]/button[1]")).click();
    }

    public void addtocart2() {
        driver.findElement(By.xpath("//*[@id=\"button-cart\"]")).click();
    }

    public void clickOrderHistory() {
        //  @FindBy(xpath = "/html/body/div[2]/div/aside/div/a[6]").click();
        driver.findElement(By.xpath("/html/body/div[2]/div/aside/div/a[6]")).click();
    }

    public void clickorderhistory0() {
        driver.findElement(By.xpath("/html/body/div[2]/div/aside/div/a[7]")).click();
    }


    // --- Add inside HomePage.java ---

    // Search product (wrapper for enterSearchText + clickSearchButton)
    public void searchProduct(String productName) {
        enterSearchText(productName);
        clickSearchButton();
        logger.info("Searched for product: " + productName);
    }

    // Click "Add to Wishlist" for a given product
    public void clickAddToWishList(String productName) {
        try {
            WebElement productContainer = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[contains(@class,'product-thumb')][.//a[normalize-space(text())='" + productName + "']]")
                    ));

            WebElement wishlistBtn = productContainer.findElement(By.xpath(".//button[contains(@onclick,'wishlist.add')]"));
            wait.until(ExpectedConditions.elementToBeClickable(wishlistBtn)).click();
            logger.info("Clicked 'Add to Wishlist' for product: " + productName);
        } catch (Exception e) {
            logger.error("Failed to click 'Add to Wishlist' for product: " + productName, e);
            throw e;
        }
    }



    // Get success message text (e.g., after adding to cart/wishlist)
    public String getSuccessMessage() {
        try {
            WebElement successAlert = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[1]"))
            );
            return successAlert.getText().trim();
        } catch (Exception e) {
            logger.error("Failed to fetch success message: " + e.getMessage());
            return null;
        }
    }

    // Click "Wish List" link inside success alert
    public void clickWishListLinkFromSuccessMessage() {
        try {
            WebElement successAlert = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert-success"))
            );
            WebElement wishListLink = successAlert.findElement(By.linkText("wish list"));
            wait.until(ExpectedConditions.elementToBeClickable(wishListLink)).click();
            logger.info("Clicked 'Wish List' link from success message.");
        } catch (Exception e) {
            logger.error("Failed to click 'Wish List' link from success message: " + e.getMessage());
            throw e;
        }
    }

    By myAccountDropdown = By.xpath("/html/body/nav/div/div[2]/ul/li[2]/a");
    By myAccountOption = By.linkText("My Account");


    public void clickMyAccountDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccountDropdown)).click();
    }

    public void selectMyAccountOption() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccountOption)).click();
    }

    @FindBy(linkText = "Logout")
    WebElement lnkLogout;

    public void clickLogout() {
        lnkLogout.click();
    }

    @FindBy(linkText = "My Account")
    private WebElement myAccountMenu;

    @FindBy(xpath = "/html/body/div[2]/div/div/ul[1]/li[2]/a")
    private WebElement changePasswordLink; // Make sure the text matches your site


    public void clickChangePasswordLink() {
        changePasswordLink.click();
    }

    @FindBy(xpath = "/html/body/nav/div/div[2]/ul/li[4]/a/span")
    private WebElement directcartbtn;

    public void clickdircartbtn() {
        directcartbtn.click();
    }

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div[2]/div[2]/div[2]/button")

    private WebElement addtocarthpbtn;

    public void clickaddtocarthpbtn() {
        addtocarthpbtn.click();

    }

    @FindBy(xpath = "//*[@id=\"button-cart\"]")

    public WebElement addtocart4;

    public void clickaddtocart4() {
        addtocart4.click();
    }

    @FindBy(xpath = "/html/body/div[2]/div/aside/div/a[12]")

    public WebElement transactionsrightcolumn;

    public void clicktransactionsrightcolumn() {
        transactionsrightcolumn.click();
    }


    @FindBy(xpath = "//*[@id=\"product-product\"]/div[1]/a[2]")

    public WebElement shoppingcartbtnmsg;

    public void clickshoppingcartbtnmsg() {
        shoppingcartbtnmsg.click();
    }

    @FindBy(xpath = "/html/body/div[2]/div/div/div[3]/div[2]/a")

    public WebElement checkoutfromcart;

    public void clickcheckoutfromcart() {
        checkoutfromcart.click();
    }

    @FindBy(xpath = "/html/body/nav/div/div[2]/ul/li[2]/ul/li[1]/a")

    public WebElement myaccdpdwn;

    public void clickmyaccdpdwn() {

        myaccdpdwn.click();
    }

    public void addProductToCart(String productName) {
        try {
            clickAddToCart(productName);  // works on search/category page
        } catch (Exception e) {
            logger.warn("Falling back to product page button-cart locator...");
            try {
                WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[2]/button[1]")));
                addBtn.click();
            } catch (Exception inner) {
                throw new RuntimeException("Failed to add " + productName + " to cart", inner);
            }
        }
    }


    public void clickViewCartFromSuccessAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            // Try success alert
            WebElement successAlert = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert-success"))
            );
            WebElement viewCartLink = successAlert.findElement(By.xpath(".//a[contains(text(),'shopping cart')]"));
            wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
            logger.info("Clicked 'View Cart' from success alert.");
        } catch (TimeoutException e) {
            logger.warn("No success alert found, falling back to cart dropdown...");

            // Fallback: use cart dropdown → "View Cart"
            clickCartBlock();
            WebElement viewCartLink = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='text-right']/a[1]")));
            viewCartLink.click();
            logger.info("Clicked 'View Cart' from cart dropdown.");
        }
    }


    public void clickContinueBilling() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("button-payment-address"))
        );
        continueBtn.click();
    }

    public void clickContinueDeliveryDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("button-shipping-address"))
        );
        continueBtn.click();
    }

    public void clickContinueDeliveryMethod() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("button-shipping-method"))
        );
        continueBtn.click();
    }

    public void clickContinuePaymentMethod() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("button-payment-method"))
        );
        continueBtn.click();
    }

    public void selectGuestCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement guestOption = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='guest']"))
        );
        guestOption.click();

        WebElement continueBtn = driver.findElement(By.id("button-account"));
        continueBtn.click();
    }

    public void clickFirstFeaturedProduct() {
        driver.findElement(By.xpath("(//div[@class='product-thumb']//a)[1]")).click();
    }




    public void createNewOrder(String productName) {
        searchProduct("HP LP3065");

        ProductDisplayPage productPage = new ProductDisplayPage(driver);
        productPage.addToCart();
        productPage.goToCheckout();

        // Step 3: Complete checkout
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.completeCheckout();
    }


    @FindBy(xpath = "//*[@id='button-payment-address']")

    public WebElement billingdetails;

    public void clickbillingdetails(){

        billingdetails.click();
    }

    private String productByNameXpath = "//a[text()='%s']";


    public void clickProductByName(String productName) {

        By productLinkLocator = By.xpath(String.format(productByNameXpath, productName));

        // This ensures the link is fully loaded, visible, and not obscured by other elements.
        WebElement productLink = wait.until(
                ExpectedConditions.elementToBeClickable(productLinkLocator)
        );

        // Use JavaScript click as a fallback/reliable method if the element remains un-interactable
        try {
            productLink.click();
            logger.info("Clicked product link using standard click: " + productName);
        } catch (Exception e) {
            // Fallback: Use JavaScript Executor to force the click
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", productLink);
            logger.info("Clicked product link using JavaScript click: " + productName);
        }
    }


    public void enterProductNameInSearch(String productName) {
        wait.until(ExpectedConditions.visibilityOf(txtGlobalSearchInput)).sendKeys(productName);
        logger.info("Entered product name in search: " + productName);
    }

    private final By searchInput = By.name("search");
    private final By searchButton = By.xpath("//div[@id='search']/span/button");



    public void addProductToCartDirectly(String productName) {

        logger.info("Attempting to add product: " + productName + " to cart.");

        // 1. Enter the product name in the search bar
        driver.findElement(searchInput).sendKeys(productName);
        driver.findElement(searchButton).click();

        // 2. Construct the specific locator for the 'Add to Cart' button
        // associated with the search result item.
        // This is a robust XPath that finds the button related to the product link text.
        By specificAddToCartLocator = By.xpath(
                "//div[contains(@class, 'product-layout')]//a[text()='" + productName + "']/../../following-sibling::div//button[contains(@onclick, 'cart.add')]"
        );

        try {
            // 3. Wait for the button to be clickable and click it using waitShort()
            WebElement addToCartButton = waitShort().until(
                    ExpectedConditions.elementToBeClickable(specificAddToCartLocator)
            );
            addToCartButton.click();

            // Optional: Wait for the success notification pop-up/banner to confirm the action
            By successAlertLocator = By.xpath("//div[contains(@class, 'alert-success')]");
            waitShort().until(ExpectedConditions.visibilityOfElementLocated(successAlertLocator));

            logger.info("Successfully added '" + productName + "' to cart via search results.");

        } catch (Exception e) {
            logger.error("Failed to add product '" + productName + "' to cart. Locator or element not found: " + e.getMessage());
            throw new NoSuchElementException("Product '" + productName + "' or its Add to Cart button not found.", e);
        }
    }

    @FindBy (xpath = "//*[@id=\"content\"]/div[3]/div[1]/div/div[2]/div[2]/button[2]")

    public WebElement AddToWishListIconForProduct;

    public void clickAddToWishListIconForProduct(){

        AddToWishListIconForProduct.click();
    }




    private final By txt_search_locator = By.xpath("//input[@name='search']");
    private final By btn_search_locator = By.xpath("//button[@class='btn btn-default btn-lg']");


    public void enterSearchText(String text) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        WebElement searchField = wait.until(ExpectedConditions.presenceOfElementLocated(txt_search_locator));


        searchField.clear();
        searchField.sendKeys(text);
    }

    public void clickSearchButton() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(btn_search_locator));
        searchButton.click();
    }


}









































