package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.support.ui.Select;

public class SpecialOffersPage {

    WebDriver driver;
    private WebDriverWait wait;

    private static final Logger logger = LoggerFactory.getLogger(SpecialOffersPage.class);


    public SpecialOffersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait
        PageFactory.initElements(driver, this); // Initializes all @FindBy elements
    }

    @FindBy(xpath = "/html/body/div[2]/div/div/h2")
    public WebElement pageTitle;

    @FindBy(xpath = "/html/body/div[2]/div[1]/a[2]")
    public WebElement productcompararisonmsg;



    public String getPageTitle() {
        return pageTitle.getText();
    }

    @FindBy(linkText = "Specials")
    public WebElement specialsFooterLink;

    public By compareProductIcon = By.xpath("//div[@class='product-thumb']//button[contains(@onclick, 'compare.add')]");

    public By successMessageLocator = By.xpath("//div[contains(@class, 'alert-success')]");

    @FindBy(xpath = "//button[@title='Compare this Product']")
    public WebElement compareThisProductButton;

    @FindBy(xpath = "//div[contains(@class, 'alert-success')]")
    public WebElement successMessage;

    @FindBy(xpath = "//*[@id='content']/div[1]/div[2]/div[1]/button[1]")
    public WebElement wishlisticon;

    @FindBy(xpath = "//*[@id='content']/div[2]/div[2]/div/div[2]/div[2]/button[3]")
    public WebElement productcomparebtn;

    public By successMessageText = By.xpath("//div[contains(@class, 'alert-success')]//text()");

public void clickproductcomparisonmsg(){
    productcompararisonmsg.click();
}

public void clickproductcomparebtn(){
    productcomparebtn.click();
}

public void clickwishlisticon(){
    wishlisticon.click();
}

    public void clickSpecialsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(specialsFooterLink)).click();
    }

    @FindBy(xpath = "//div[@class='product-thumb']")
    public WebElement specialOfferItem;

    public boolean areSpecialOffersDisplayed() {
        return specialOfferItem.isDisplayed();
    }

    public void clickFirstSpecialOffer() {
        specialOfferItem.click();
    }

    public By offerProductsLocator = By.cssSelector(".offer-product");

    public boolean areOfferProductsDisplayed() {
        try {
            // Wait for the offer products to be visible on the page
            List<WebElement> offerProducts = driver.findElements(offerProductsLocator);
            return offerProducts.size() > 0 && offerProducts.get(0).isDisplayed();
        } catch (Exception e) {
            return false; // If any exception occurs (e.g., elements not found), return false
        }
    }

    public void selectGridView() {
        WebElement gridViewOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("grid-view")));
        gridViewOption.click();
    }

    @FindBy(xpath = "//div[@class='product-thumb']")
    public WebElement productGrid;

    public boolean areProductsInGridView() {
        return productGrid.isDisplayed();  // Validate if product grid is visible
    }

    @FindBy(xpath = "//button[contains(@onclick, 'cart.add')]")
    public WebElement addToCartButton;

    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }








    @FindBy(linkText = "Product Compare") // Locator for Product Compare link
    public WebElement productCompareLink;

    // Method to click on the 'Product Compare' link
    public void clickProductPage() {
        wait.until(ExpectedConditions.elementToBeClickable(productCompareLink)).click();
    }

    @FindBy(id = "input-sort")  // Assuming the 'Sort By' dropdown has id "input-sort"
    public WebElement sortByDropdown;


    public void selectSortByOption(String option) {
        Select sortBySelect = new Select(sortByDropdown);
        sortBySelect.selectByVisibleText(option);
    }

    public By productTitlesLocator = By.xpath("//div[@class='product-thumb']//h4/a"); // Adjust this if necessary


    public List<WebElement> getProductTitles() {
        return driver.findElements(productTitlesLocator); // Assumes 'productTitlesLocator' is defined correctly
    }


    public boolean areProductsSorted(List<WebElement> products) {
        // Assuming the products are sorted by name, we can compare the product names to check if they are in ascending order
        String previousProductName = "";
        for (WebElement product : products) {
            String currentProductName = product.getText();
            if (previousProductName.compareTo(currentProductName) > 0) {
                return false;  // Products are not sorted in ascending order
            }
            previousProductName = currentProductName;
        }
        return true;  // Products are sorted
    }

    // Method to select 'List' view for products in Special Offers page
    @FindBy(id = "list-view")
    public WebElement listViewOption;

    public void selectListView() {
        wait.until(ExpectedConditions.elementToBeClickable(listViewOption)).click();
    }

    // Method to click the 'Compare this Product' icon for the first product in the list
    public void clickFirstSpecialOfferCompare() {
        WebElement compareButton = driver.findElement(compareProductIcon);
        compareButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageLocator));  // Wait for success message
    }



    // Method to get the success message after adding a product to the comparison
    public String getSuccessMessage() {
        try {
            // Wait until the success message is visible and then retrieve the text
            WebElement message = wait.until(ExpectedConditions.visibilityOf(successMessage));
            return message.getText().trim();
        } catch (Exception e) {
            return "";  // Return an empty string if no success message is found
        }
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

    public void selectProductForComparison(String productName) {
        try {
            // Find the product based on the name and click the "Compare this Product" icon next to it
            WebElement productElement = driver.findElement(By.xpath("//div[contains(@class,'product-layout')]//h4/a[text()='" + productName + "']"));

            // Locate the 'Compare this Product' button/icon
            WebElement compareButton = productElement.findElement(By.xpath("..//following-sibling::div//button[@data-original-title='Compare this Product']"));

            // Wait for the button to be clickable and click it
            wait.until(ExpectedConditions.elementToBeClickable(compareButton)).click();

            // Log success
            logger.info("Selected product '" + productName + "' for comparison.");
        } catch (Exception e) {
            logger.error("Error while selecting product for comparison: " + e.getMessage());
        }
    }

}
