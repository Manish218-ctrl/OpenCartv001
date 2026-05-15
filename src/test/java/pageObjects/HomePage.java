package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.time.Duration;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {

        super(driver);
    }

    //Locators

    @FindBy(xpath = "//span[normalize-space()='My Account']")
    public WebElement lnkMyAccount;

    @FindBy(xpath = "//a[normalize-space()='Login']")
    public WebElement lnkLogin;

    @FindBy(linkText = "Login")
    public WebElement linkLogin;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement lnkRegister;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    public WebElement lnkLogoutFromDropdown;

    @FindBy(linkText = "Logout")
    WebElement lnkLogout;

    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[contains(@href,'account/account')]")
    private WebElement lnkMyAccountFromDropdown;

    @FindBy(xpath = "//nav[@id='top']//a[contains(@href,'information/contact')]/i[@class='fa fa-phone']")
    public WebElement contactUsHeaderOption;

    @FindBy(xpath = "//nav[@id='top']//span[contains(@class,'hidden-xs') and contains(.,'123')]")
    public WebElement phoneNumberElement;

    @FindBy(xpath = "//div[@id='top-links']//a[contains(@href,'checkout/cart')]")
    private WebElement directcartbtn;

    @FindBy(xpath = "//div[@id='logo']//h1/a")
    private WebElement logoYourStore;

    @FindBy(name = "search")
    private WebElement txtGlobalSearchInput;

    @FindBy(css = "button.btn.btn-default.btn-lg")
    private WebElement btnSearch;

    @FindBy(id = "cart-total")
    public WebElement btnCartBlock;

    @FindBy(xpath = "//div[@id='cart']//ul[@class='dropdown-menu pull-right']//p/a[1]/strong")
    public WebElement lnkViewCart;

    @FindBy(xpath = "//div[@id='cart']//ul[@class='dropdown-menu pull-right']//p/a[2]/strong")
    public WebElement btnCheckout;

    @FindBy(xpath = "//a[contains(@class,'btn') and contains(text(),'Cart')]")
    private WebElement btnCart;

    @FindBy(xpath = "//div[@id='content']//a[contains(@href,'checkout/cart')]")
    public WebElement Checkoutemptycart;

    @FindBy(xpath = "//div[@id='content']//a[contains(@href,'checkout/checkout')]")
    public WebElement checkoutfromcart;

    @FindBy(xpath = "//div[@id='product-product']//a[contains(@href,'checkout/cart')]")
    public WebElement shoppingcartbtnmsg;

    @FindBy(id = "button-cart")
    public WebElement addtocart;

    @FindBy(xpath = "//div[contains(@class,'product-layout')][1]//button[contains(@onclick,'cart.add')]")
    private WebElement addtocart0;

    @FindBy(id = "button-cart")
    public WebElement addtocart4;

    @FindBy(xpath = "//div[@id='content']//div[contains(@class,'button-group')]/button[1]")
    private WebElement addtocarthpbtn;

    @FindBy(xpath = "//div[@id='content']//div[contains(@class,'button-group')]/button[2]")
    public WebElement AddToWishListIconForProduct;

    @FindBy(linkText = "product page")
    private WebElement linkProductPage;

    @FindBy(id = "list-view")
    public WebElement listViewOption;

    @FindBy(id = "grid-view")
    public WebElement gridViewOption;

    @FindBy(xpath = "//nav[@id='menu']//a[normalize-space()='Desktops']")
    private WebElement menuDesktops;

    @FindBy(xpath = "//nav[@id='menu']//a[normalize-space()='PC (0)']")
    private WebElement subMenuPC;

    @FindBy(id = "button-payment-address")
    private WebElement btnContinue;

    @FindBy(id = "button-payment-address")
    public WebElement billingdetails;

    @FindBy(xpath = "//input[@name='agree'][@type='checkbox']")
    private WebElement chkTermsAndConditions;

    @FindBy(name = "confirm")
    private WebElement btnConfirmOrder;

    @FindBy(xpath = "//a[contains(text(),'Continue Shopping')]")
    private WebElement btnContinueShopping;

    @FindBy(xpath = "//aside[@id='column-right']//a[normalize-space()='Register']")
    WebElement lnkRightColumnRegister;

    @FindBy(xpath = "//aside[@id='column-right']//a[contains(@href,'newsletter')]")
    WebElement NewsletterLink;

    @FindBy(xpath = "//aside[@id='column-right']//a[contains(@href,'newsletter')]")
    public WebElement lnkRightColumnNewsletter;

    @FindBy(xpath = "//aside[@id='column-right']//a[contains(@href,'reward')]")
    public WebElement lnkRewardPoints;

    @FindBy(xpath = "//aside[@id='column-right']//a[contains(@href,'recurring')]")
    public WebElement recurringPaymentsLink;

    @FindBy(xpath = "//aside[@id='column-right']//a[contains(@href,'transaction')]")
    public WebElement transactionsrightcolumn;

    @FindBy(xpath = "//a[normalize-space()='Transactions']")
    public WebElement lnkTransactions;

    @FindBy(xpath = "//a[normalize-space()='Subscribe/unsubscribe to newsletter']")
    public WebElement lnkNewsletterSubscription;

    @FindBy(xpath = "//a[normalize-space()='Order History']")
    public WebElement orderHistoryLink;

    @FindBy(linkText = "My Account")
    private WebElement myAccountMenu;

    @FindBy(xpath = "//div[@id='content']//ul/li/a[contains(@href,'password')]")
    private WebElement changePasswordLink;

    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[contains(@href,'account/account')]")
    public WebElement myaccdpdwn;

    @FindBy(xpath = "//footer//a[normalize-space()='Privacy Policy']")
    private WebElement lnkFooterPrivacyPolicy;

    @FindBy(xpath = "//footer//a[normalize-space()='Terms & Conditions']")
    private WebElement lnkFooterTermsConditions;

    @FindBy(xpath = "//footer//a[normalize-space()='Wish List']")
    private WebElement lnkFooterWishList;

    @FindBy(xpath = "//footer//a[normalize-space()='Delivery Information']")
    private WebElement lnkFooterDeliveryInfo;

    @FindBy(xpath = "//footer//a[contains(@href,'information/contact')]")
    public  WebElement lnkFooterContactUs;

    @FindBy(xpath = "//footer//a[normalize-space()='About Us']")
    private WebElement lnkFooterAboutUs;

    @FindBy(xpath = "//footer//a[normalize-space()='Brands']")
    private WebElement lnkFooterBrands;

    @FindBy(xpath = "//footer//a[normalize-space()='Newsletter']")
    public WebElement lnkFooterNewsletter;

    @FindBy(xpath = "//div[contains(@class,'swiper-viewport')]")
    private WebElement partnerCarouselSection;

    @FindBy(xpath = "//div[contains(@class,'swiper-viewport')]//img")
    private List<WebElement> partnerLogos;

    @FindBy(css = ".swiper-button-next")
    private WebElement nextArrow;

    @FindBy(css = ".swiper-button-prev")
    private WebElement prevArrow;

    @FindBy(css = ".swiper-pagination-bullet")
    private List<WebElement> paginationBullets;

    @FindBy(css = "ul.breadcrumb")
    private WebElement breadcrumbElement;

    @FindBy(xpath = "//ul[@class='breadcrumb']/li[1]/a")
    private WebElement breadcrumbHome;

    @FindBy(xpath = "//footer//a[normalize-space()='Site Map']")
    public WebElement footerSiteMapLink;

    @FindBy(xpath = "(//div[@id='content']//div[contains(@class,'product-layout')])[1]//button[@data-toggle='tooltip' and @title='Add to Wish List']")
    public WebElement firstFeaturedProductWishListBtn;

    @FindBy(xpath = "//div[@id='content']//div[contains(@class,'product-layout')]//h4/a")
    private WebElement firstBrandProduct;

    @FindBy(xpath = "//div[@id='content']//div[contains(@class,'product-thumb')]//h4/a")
    private WebElement firstBrandProductLink;

    @FindBy(id = "compare-total")
    private WebElement lnkProductCompare;

    @FindBy(id = "input-limit")
    private WebElement dropdownShowLimit;

    @FindBy(css = ".product-layout")
    private List<WebElement> displayedProducts;

    @FindBy(xpath = "//div[@id='content']//div[contains(@class,'product-thumb')]")
    private List<WebElement> featuredProducts;

    @FindBy(linkText = "Specials")
    private WebElement specialsFooterLink;

    @FindBy(xpath = "//div[@id='top-links']//a[contains(@href,'account/account')]")
    private WebElement myAccountDropdown;

    @FindBy(linkText = "My Account")
    private WebElement myAccountOption;

    @FindBy(xpath = "//ul[contains(@class,'dropdown-menu-right')]//a[contains(@href,'transaction')]")
    private WebElement transactionsLink;

    @FindBy(css = ".swiper-slide img")
    private List<WebElement> heroImages;

    @FindBy(css = ".swiper-slide-active img")
    private WebElement activeHeroImage;

    @FindBy(css = "div.swiper-button-next")
    private WebElement heroSliderNextButton;

    @FindBy(css = ".swiper-pagination-bullet")
    private List<WebElement> heroPaginationBullets;

    @FindBy(css = ".swiper-wrapper")
    private WebElement heroSliderContainer;

    //UTILITY METHODS

    private String getActiveHeroImageSrc() {

        wait.until(
                ExpectedConditions.visibilityOf(activeHeroImage)
        );

        return activeHeroImage.getAttribute("src");
    }

    private boolean waitForHeroImageChange(String previousImageSrc) {

        try {

            return wait.until(driver -> {

                String currentSrc =
                        activeHeroImage.getAttribute("src");

                return !currentSrc.equals(previousImageSrc);
            });

        } catch (Exception e) {

            logger.warn(
                    "Hero image did not change: {}",
                    e.getMessage()
            );

            return false;
        }
    }

    //Action Methods

    public void clickMyAccount() {
        clickElement(lnkMyAccount);
    }

    public void clickMyAccountFromDropdown() {
        lnkMyAccountFromDropdown.click();
    }

    public void clickMyAccountDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccountDropdown)).click();
    }

    public void selectMyAccountOption() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccountOption)).click();
    }

    public void clickmyaccdpdwn() {
        myaccdpdwn.click();
    }

    public void clickRegister() {
        lnkRegister.click();
    }

    public void clickRightColumnRegister() {
        lnkRightColumnRegister.click();
    }

    public void clickLogin() {
        linkLogin.click();
    }

    public void clickLogout() {
        lnkLogout.click();
    }

    public void clickLogoutFromDropdown() {
        lnkLogoutFromDropdown.click();
    }

    public void clickChangePasswordLink() {
        changePasswordLink.click();
    }

    public void clickContactUsHeaderOption() {
        wait.until(ExpectedConditions.elementToBeClickable(contactUsHeaderOption)).click();
    }

    public String getPhoneNumber() {
        wait.until(ExpectedConditions.visibilityOf(phoneNumberElement));
        return phoneNumberElement.getText();
    }

    public  void clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logoYourStore)).click();
    }

    public void clickdircartbtn() {
        directcartbtn.click();
    }

    public void enterSearchText(String text) {

        wait.until(
                ExpectedConditions.visibilityOf(txtGlobalSearchInput)
        );
        txtGlobalSearchInput.clear();
        txtGlobalSearchInput.sendKeys(text);
        logger.info("Entered search text: {}", text);
    }

    public void clickSearchButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(btnSearch)
        ).click();
        logger.info("Clicked Search button.");
    }

    public void searchProduct(String productName) {
        enterSearchText(productName);
        clickSearchButton();
        logger.info("Searched for product: " + productName);
    }

    public void enterProductNameInSearch(String productName) {
        wait.until(ExpectedConditions.visibilityOf(txtGlobalSearchInput)).sendKeys(productName);
        logger.info("Entered product name in search: " + productName);
    }

    public void clickProductPage() {
        wait.until(ExpectedConditions.elementToBeClickable(linkProductPage)).click();
    }

    public void clickProductByName(String productName) {

        By productLinkLocator =
                By.xpath(
                        "//a[normalize-space()='" + productName + "']"
                );

        WebElement productLink =
                wait.until(
                        ExpectedConditions.elementToBeClickable(productLinkLocator)
                );

        try {

            productLink.click();

            logger.info(
                    "Clicked product link using standard click: {}",
                    productName
            );

        } catch (Exception e) {

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", productLink);

            logger.info(
                    "Clicked product link using JavaScript click: {}",
                    productName
            );
        }
    }

    public void navigateToEmptyPCCategory() {
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(menuDesktops));
        actions.moveToElement(menuDesktops).perform();
        wait.until(ExpectedConditions.elementToBeClickable(subMenuPC)).click();
    }

    public void clickFirstFeaturedProduct() {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        firstBrandProductLink)).click();
    }

    public void selectListView() {
        wait.until(ExpectedConditions.elementToBeClickable(listViewOption)).click();
    }

    public void selectGridView() {
        wait.until(ExpectedConditions.elementToBeClickable(gridViewOption)).click();
    }

    public void clickaddtocart() {
        addtocart.click();
    }

    public void clickaddtocart0() {
        addtocart0.click();
    }

    public void clickaddtocart4() {
        addtocart4.click();
    }

    public void clickaddtocarthpbtn() {
        addtocarthpbtn.click();
    }

    public void clickAddToCart1(String productName) {
        driver.findElement(By.id("button-cart")).click();
    }

    public void addtocartmain() {
        driver.findElement(By.xpath(
                "//div[@id='content']//div[contains(@class,'product-layout')][1]//button[contains(@onclick,'cart.add')]")).click();
    }

    public void addtocart2() {
        driver.findElement(By.id("button-cart")).click();
    }

    public void clickAddToCart(String productName) {
        try {
            WebElement productContainer = new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[contains(@class,'product-thumb')][.//a[normalize-space()='" + productName + "']]")
                    ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productContainer);

            List<WebElement> selects = productContainer.findElements(By.tagName("select"));
            for (WebElement select : selects) {
                Select dropdown = new Select(select);
                if (dropdown.getOptions().size() > 1) dropdown.selectByIndex(1);
            }

            List<WebElement> inputs = productContainer.findElements(
                    By.cssSelector("input[type='radio'], input[type='checkbox']"));
            for (WebElement input : inputs) {
                if (!input.isSelected()) input.click();
            }

            List<WebElement> textInputs = productContainer.findElements(
                    By.cssSelector("input[type='text'][required]"));
            for (WebElement input : textInputs) {
                input.clear();
                input.sendKeys("Test");
            }

            List<WebElement> dateInputs = productContainer.findElements(
                    By.cssSelector("input[type='date']"));
            for (WebElement input : dateInputs) {
                input.sendKeys("2025-09-01");
            }

            WebElement addToCartBtn = productContainer.findElement(
                    By.xpath(".//button[contains(@onclick,'cart.add')]"));
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(addToCartBtn));
            addToCartBtn.click();
            logger.info(productName + " added to cart successfully.");

        } catch (TimeoutException e) {
            logger.error("ERROR: Product " + productName + " not found or not clickable.", e);
            throw e;
        } catch (Exception e) {
            logger.error("ERROR while adding " + productName + " to cart: " + e.getMessage(), e);
            throw e;
        }
    }

    public void addProductToCart(String productName) {
        try {
            clickAddToCart(productName);
        } catch (Exception e) {
            logger.warn("Falling back to product page button-cart locator...");
            try {
                WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@id='content']//div[contains(@class,'product-layout')][1]//button[contains(@onclick,'cart.add')]")));
                addBtn.click();
            } catch (Exception inner) {
                throw new RuntimeException("Failed to add " + productName + " to cart", inner);
            }
        }
    }

    public void addProductToCartDirectly(String productName) {
        logger.info("Attempting to add product: " + productName + " to cart.");
        txtGlobalSearchInput.clear();
        txtGlobalSearchInput.sendKeys(productName);
        btnSearch.click();

        By specificAddToCartLocator = By.xpath(
                "//div[contains(@class,'product-layout')]//a[text()='" + productName + "']/../../following-sibling::div//button[contains(@onclick,'cart.add')]"
        );

        try {
            WebElement addToCartButton = waitShort().until(
                    ExpectedConditions.elementToBeClickable(specificAddToCartLocator));
            addToCartButton.click();

            By successAlertLocator = By.xpath("//div[contains(@class,'alert-success')]");
            waitShort().until(ExpectedConditions.visibilityOfElementLocated(successAlertLocator));
            logger.info("Successfully added " + productName + " to cart via search results.");

        } catch (Exception e) {
            logger.error("Failed to add product " + productName + " to cart: " + e.getMessage());
            throw new NoSuchElementException("Product " + productName + " or its Add to Cart button not found.", e);
        }
    }

    public void clickAddToWishListIconForProduct() {
        AddToWishListIconForProduct.click();
    }

    public void clickAddToWishList(String productName) {
        try {
            WebElement productContainer = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[contains(@class,'product-thumb')][.//a[normalize-space(text())='" + productName + "']]")
                    ));
            WebElement wishlistBtn = productContainer.findElement(
                    By.xpath(".//button[contains(@onclick,'wishlist.add')]"));
            wait.until(ExpectedConditions.elementToBeClickable(wishlistBtn)).click();
            logger.info("Clicked Add to Wishlist for product: " + productName);
        } catch (Exception e) {
            logger.error("Failed to click Add to Wishlist for product: " + productName, e);
            throw e;
        }
    }

    public void clickWishListLinkFromSuccessMessage() {
        try {
            WebElement successAlert = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert-success")));
            WebElement wishListLink = successAlert.findElement(By.linkText("wish list"));
            wait.until(ExpectedConditions.elementToBeClickable(wishListLink)).click();
            logger.info("Clicked Wish List link from success message.");
        } catch (Exception e) {
            logger.error("Failed to click Wish List link from success message: " + e.getMessage());
            throw e;
        }
    }

    public void clickCartBlock() {
        btnCartBlock.click();
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

    public void clickshoppingcartbtnmsg() {
        shoppingcartbtnmsg.click();
    }

    public void clickViewCartFromCartDropdown() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement viewCartLink = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//p[@class='text-right']/a[1]")));
            viewCartLink.click();
            logger.info("Clicked on View Cart link from cart dropdown.");
        } catch (Exception e) {
            logger.error("Failed to click View Cart from cart dropdown: " + e.getMessage());
            throw e;
        }
    }

    public void clickViewCartOption() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement successAlert = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert-success")));
            WebElement viewCartLink = successAlert.findElement(By.linkText("shopping cart"));
            wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
            logger.info("Clicked on View Cart link from success alert.");
        } catch (TimeoutException e) {
            logger.warn("View Cart not found in success alert, trying cart dropdown...");
            clickCartBlock();
            WebElement viewCartLink = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("cart-total")));
            viewCartLink.click();
            logger.info("Clicked on View Cart link from cart dropdown.");
        } catch (Exception e) {
            logger.error("Failed to click on View Cart link: " + e.getMessage());
            throw e;
        }
    }

    public void clickViewCartFromSuccessAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement successAlert = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert-success")));
            WebElement viewCartLink = successAlert.findElement(
                    By.xpath(".//a[contains(text(),'shopping cart')]"));
            wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
            logger.info("Clicked View Cart from success alert.");
        } catch (TimeoutException e) {
            logger.warn("No success alert found, falling back to cart dropdown...");
            clickCartBlock();
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//p[@class='text-right']/a[1]"))).click();
            logger.info("Clicked View Cart from cart dropdown.");
        }
    }

    public void navigateDirectlyToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            clickCartBlock();
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("ul.dropdown-menu.pull-right")));
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@id='cart']//ul[@class='dropdown-menu pull-right']//p/a[1]/strong"))).click();
            logger.info("Navigated directly to Shopping Cart page via cart dropdown.");
        } catch (Exception e) {
            logger.error("Failed to navigate directly to cart: " + e.getMessage());
            throw e;
        }
    }

    public void clickbtnCheckout() {
        btnCheckout.click();
    }

    public void clickCheckout() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@id='cart']//ul[@class='dropdown-menu pull-right']//p/a[2]/strong"))).click();
    }

    public void clickCheckoutemptycart() {
        Checkoutemptycart.click();
    }

    public void clickcheckoutfromcart() {
        checkoutfromcart.click();
    }

    public void clickContinue() {
        btnContinue.click();
    }

    public void clickbillingdetails() {
        billingdetails.click();
    }

    public void clickContinueBilling() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("button-payment-address"))).click();
    }

    public void clickContinueDeliveryDetails() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("button-shipping-address"))).click();
    }

    public void clickContinueDeliveryMethod() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("button-shipping-method"))).click();
    }

    public void clickContinuePaymentMethod() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("button-payment-method"))).click();
    }

    public void selectTermsAndConditions() {
        chkTermsAndConditions.click();
    }

    public void clickConfirmOrder() {
        btnConfirmOrder.click();
    }

    public void selectGuestCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@value='guest']"))).click();
        driver.findElement(By.id("button-account")).click();
    }

    public void clickContinueShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(btnContinueShopping)).click();
        logger.info("Clicked Continue Shopping button");
    }

    public void clickOrderHistory() {
        driver.findElement(
                By.xpath("//aside[@id='column-right']//a[contains(@href,'account/order')]")).click();
    }

    public void clickorderhistory0() {
        driver.findElement(
                By.xpath("//aside[@id='column-right']//a[contains(@href,'account/download')]")).click();
    }

    public void clickRewardPoints() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkRewardPoints)).click();
        logger.info("Clicked on Your Reward Points link");
    }

    public void clickRecurringPaymentsLink() {
        recurringPaymentsLink.click();
    }

    public void clicktransactionsrightcolumn() {
        transactionsrightcolumn.click();
    }

    public void clickTransactions() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(transactionsLink)).click();
            logger.info("Clicked Transactions link successfully.");
        } catch (TimeoutException te) {
            throw new RuntimeException("Transactions link not found/clickable within timeout.", te);
        }
    }

    public void clickNewsletterLink() {
        NewsletterLink.click();
    }

    public void clickRightColumnNewsletter() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkRightColumnNewsletter)).click();
    }

    public void clickPrivacyPolicyFooterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkFooterPrivacyPolicy)).click();
    }

    public void clickTermsConditionsFooterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkFooterTermsConditions)).click();
    }

    public void clickFooterWishList() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkFooterWishList)).click();
    }

    public void clickDeliveryInfoFooterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkFooterDeliveryInfo)).click();
    }

    public  void clickFooterContactUsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkFooterContactUs)).click();
        logger.info("Clicked on Contact Us footer link.");
    }

    public void clickAboutUsFooterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkFooterAboutUs)).click();
    }

    public void clickBrandsFooterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkFooterBrands)).click();
    }

    public void clickBrandByName(String brandName) {
        By brandLocator = By.linkText(brandName);
        wait.until(
                ExpectedConditions.elementToBeClickable(brandLocator)
        ).click();
        logger.info("Clicked brand: {}", brandName);
    }

    public void clickFooterNewsletterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkFooterNewsletter)).click();
        logger.info("Clicked on the Newsletter footer link.");
    }

    public void clickFooterSpecialsLink() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//footer//a[normalize-space()='Specials']"))).click();
            logger.info("Clicked on Specials footer link.");
        } catch (Exception e) {
            logger.error("Error while clicking Specials footer link: " + e.getMessage());
        }
    }

    public void clickFooterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(specialsFooterLink)).click();
    }

    public void clickFooterLink(String linkText) {
        wait.until(ExpectedConditions.elementToBeClickable(
                driver.findElement(By.xpath("//footer//a[normalize-space()='" + linkText + "']")))).click();
    }

    public boolean isPartnerCarouselDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(partnerCarouselSection)).isDisplayed();
    }

    public int getPartnerLogosCount() {
        return partnerLogos.size();
    }

    public boolean areLogosDisplayed() {
        for (WebElement logo : partnerLogos) {
            if (!logo.isDisplayed()) return false;
        }
        return true;
    }

    public void clickNextArrow() {
        wait.until(ExpectedConditions.elementToBeClickable(nextArrow)).click();
    }

    public void clickPrevArrow() {
        wait.until(ExpectedConditions.elementToBeClickable(prevArrow)).click();
    }

    public void clickPaginationBullet(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(paginationBullets.get(index))).click();
    }

    public void dragCarousel() {
        Actions actions = new Actions(driver);
        actions.clickAndHold(partnerLogos.get(0))
                .moveByOffset(-200, 0)
                .release()
                .perform();
    }

    public String getBreadcrumb() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(breadcrumbElement));
        return breadcrumbElement.getText();
    }

    public String getSuccessMessage() {
        try {
            WebElement successAlert = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("div.alert-success")));
            return successAlert.getText().trim();
        } catch (Exception e) {
            logger.error("Failed to fetch success message: " + e.getMessage());
            return null;
        }
    }

    public void createNewOrder(String productName) {
        searchProduct("HP LP3065");
        ProductDisplayPage productPage = new ProductDisplayPage(driver);
        productPage.addToCart();
        productPage.goToCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.completeCheckout();
    }


    public void clickFirstFeaturedProductWishListBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(firstFeaturedProductWishListBtn)).click();
        logger.info("Clicked Add to Wish List for first featured product.");
    }

    public void clickBreadcrumbHome() {
        wait.until(ExpectedConditions.elementToBeClickable(breadcrumbHome)).click();
        logger.info("Clicked Home breadcrumb.");
    }

    public void clickSiteMapFooterLink() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        footerSiteMapLink
                )
        ).click();
    }

    public boolean isBrandProductDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(
                        firstBrandProduct
                )
        ).isDisplayed();
    }

    public void clickFirstBrandProduct() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        firstBrandProductLink
                )
        ).click();

        logger.info(
                "Clicked first product from brand page."
        );
    }

    public boolean isUserNavigatedToProductDisplayPage() {

        return wait.until(
                ExpectedConditions.urlContains(
                        "product_id"
                )
        );
    }

    public void clickProductCompareLink() {

        wait.until(ExpectedConditions.elementToBeClickable(lnkProductCompare));

        try {

            lnkProductCompare.click();

        } catch (Exception e) {

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", lnkProductCompare);
        }

        logger.info("Clicked Product Compare link.");
    }

    public String getCurrentPageURL() {

        return driver.getCurrentUrl();
    }

    public void selectShowLimit(String visibleText) {

        wait.until(ExpectedConditions.visibilityOf(dropdownShowLimit));

        Select select = new Select(dropdownShowLimit);

        select.selectByVisibleText(visibleText);

        logger.info("Selected Show dropdown value: {}", visibleText);

        wait.until(ExpectedConditions.visibilityOfAllElements(displayedProducts));
    }

    public int getDisplayedProductsCount() {

        wait.until(ExpectedConditions.visibilityOfAllElements(displayedProducts));

        return displayedProducts.size();
    }

    public int getFeaturedProductsCount() {

        wait.until(
                ExpectedConditions.visibilityOfAllElements(featuredProducts)
        );

        return featuredProducts.size();
    }

    public String clickFeaturedProductByIndex(int index) {

        wait.until(
                ExpectedConditions.visibilityOfAllElements(featuredProducts)
        );

        WebElement productContainer =
                featuredProducts.get(index);

        WebElement productLink =
                productContainer.findElement(
                        By.xpath(".//div[contains(@class,'caption')]//h4/a")
                );

        String productName =
                productLink.getText().trim();

        wait.until(
                ExpectedConditions.elementToBeClickable(productLink)
        ).click();

        logger.info("Clicked featured product: {}", productName);

        return productName;
    }

    public String getCurrentPageTitle() {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.tagName("h1"))
        );

        return driver.getTitle();
    }

    public int getHeroImagesCount() {

        wait.until(
                ExpectedConditions.visibilityOfAllElements(heroImages)
        );

        return heroImages.size();
    }

    public boolean validateHeroAutoSlide() {

        if (heroImages.size() <= 1) {

            logger.warn("Only one Hero Image present.");

            return false;
        }

        String firstImageSrc =
                getActiveHeroImageSrc();

        return waitForHeroImageChange(firstImageSrc);
    }

    public boolean validateHeroNextButtonSlide() {

        String beforeClickSrc =
                getActiveHeroImageSrc();

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView(true);",
                        heroSliderNextButton
                );

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        heroSliderNextButton
                );

        return waitForHeroImageChange(beforeClickSrc);
    }

    public boolean validateHeroPaginationBulletSlide() {

        if (heroPaginationBullets.size() <= 1) {

            logger.warn("Pagination bullets not available.");

            return false;
        }

        String beforeBulletClick =
                getActiveHeroImageSrc();

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        heroPaginationBullets.get(1)
                );

        return waitForHeroImageChange(beforeBulletClick);
    }

    public boolean validateHeroDragSlide() {

        try {

            ((JavascriptExecutor) driver)
                    .executeScript(
                            "arguments[0].scrollIntoView({block: 'center'});",
                            heroSliderContainer
                    );

            String beforeDrag =
                    getActiveHeroImageSrc();

            Rectangle rect =
                    heroSliderContainer.getRect();

            int dragDistance =
                    (int) (rect.getWidth() * 0.30);

            Actions actions =
                    new Actions(driver);

            actions.moveToElement(heroSliderContainer)
                    .clickAndHold()
                    .moveByOffset(-dragDistance, 0)
                    .release()
                    .perform();

            return waitForHeroImageChange(beforeDrag);

        } catch (Exception e) {

            logger.warn(
                    "Skipping drag validation due to exception: {}",
                    e.getMessage()
            );

            return false;
        }
    }

    public boolean isMyAccountDisplayed() {
        return isElementDisplayed(lnkMyAccount);
    }
}