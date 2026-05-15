package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SpecialOffersPage extends BasePage {

    public SpecialOffersPage(WebDriver driver) {

        super(driver);

        
    }

    //LOCATORS

    @FindBy(xpath = "//div[@id='content']//h2[contains(normalize-space(),'Special Offers')]")
    public WebElement pageTitle;

    @FindBy(xpath = "//a[@id='compare-total' and contains(normalize-space(),'Product Compare')]")
    public WebElement productcompararisonmsg;

    @FindBy(xpath = "//footer//a[contains(@href,'product/special') and contains(normalize-space(),'Specials')]")
    public WebElement specialsFooterLink;

    @FindBy(xpath = "//button[@data-original-title='Compare this Product' or @title='Compare this Product']")
    public WebElement compareThisProductButton;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    public WebElement successMessage;

    @FindBy(xpath = "(//div[contains(@class,'product-thumb')]//button[contains(@onclick,'wishlist.add')])[1]")
    public WebElement wishlisticon;

    @FindBy(xpath = "(//div[contains(@class,'product-thumb')]//button[contains(@onclick,'compare.add')])[1]")
    public WebElement productcomparebtn;

    @FindBy(xpath = "(//div[contains(@class,'product-layout')]//div[contains(@class,'product-thumb')])[1]")
    public WebElement specialOfferItem;

    @FindBy(xpath = "//div[contains(@class,'product-layout')]")
    public WebElement productGrid;

    @FindBy(xpath = "(//div[contains(@class,'button-group')]//button[contains(@onclick,'cart.add')])[1]")
    public WebElement addToCartButton;

    @FindBy(xpath = "//a[@id='compare-total']")
    public WebElement productCompareLink;

    @FindBy(id = "input-sort")
    public WebElement sortByDropdown;

    @FindBy(id = "list-view")
    public WebElement listViewOption;

    @FindBy(xpath = "//div[@id='content']//div[contains(@class,'product-thumb')]")
    private List<WebElement> offerProducts;

    @FindBy(xpath = "(//div[contains(@class,'product-thumb')]//button[contains(@onclick,'compare.add')])[1]")
    private WebElement compareProductIcon;

    @FindBy(xpath = "//div[contains(@class,'product-thumb')]//h4/a")
    private List<WebElement> productTitles;

    //ACTION METHODS

    public String getPageTitle() {

        return pageTitle.getText();
    }

    public void clickproductcomparisonmsg() {

        clickElement(productcompararisonmsg);
    }

    public void clickproductcomparebtn() {

        clickElement(productcomparebtn);
    }

    public void clickwishlisticon() {

        clickElement(wishlisticon);
    }

    public void clickSpecialsLink() {

        clickElement(specialsFooterLink);
    }

    public boolean areSpecialOffersDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(specialOfferItem)
        ).isDisplayed();
    }

    public void clickFirstSpecialOffer() {

        clickElement(specialOfferItem);
    }

    public boolean areOfferProductsDisplayed() {

        try {

            wait.until(
                    ExpectedConditions.visibilityOfAllElements(offerProducts)
            );

            return !offerProducts.isEmpty()
                    && offerProducts.get(0).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public void selectGridView() {

        WebElement gridViewOption =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.id("grid-view")
                        )
                );

        gridViewOption.click();
    }

    public boolean areProductsInGridView() {

        return productGrid.isDisplayed();
    }

    public void clickAddToCart() {

        clickElement(addToCartButton);
    }

    public void clickProductPage() {

        clickElement(productCompareLink);
    }

    public void selectSortByOption(String option) {

        Select sortBySelect =
                new Select(sortByDropdown);

        sortBySelect.selectByVisibleText(option);
    }

    public List<WebElement> getProductTitles() {

        wait.until(
                ExpectedConditions.visibilityOfAllElements(productTitles)
        );

        return productTitles;
    }

    public boolean areProductsSorted(List<WebElement> products) {

        String previousProductName = "";

        for (WebElement product : products) {

            String currentProductName =
                    product.getText();

            if (previousProductName.compareTo(currentProductName) > 0) {

                return false;
            }

            previousProductName = currentProductName;
        }

        return true;
    }

    public void selectListView() {

        clickElement(listViewOption);
    }

    public void clickFirstSpecialOfferCompare() {

        clickElement(compareProductIcon);

        wait.until(
                ExpectedConditions.visibilityOf(successMessage)
        );
    }

    public String getSuccessMessage() {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOf(successMessage)
            ).getText().trim();

        } catch (Exception e) {

            return "";
        }
    }

    public void clickFooterSpecialsLink() {

        try {

            clickElement(specialsFooterLink);

            logger.info("Clicked on Specials footer link.");

        } catch (Exception e) {

            logger.error(
                    "Error while clicking Specials footer link: "
                            + e.getMessage()
            );
        }
    }

    public void selectProductForComparison(String productName) {

        try {

            WebElement productElement =
                    driver.findElement(
                            By.xpath(
                                    "//div[contains(@class,'product-thumb')]//h4/a[normalize-space()='"
                                            + productName +
                                            "']"
                            )
                    );

            WebElement compareButton =
                    productElement.findElement(
                            By.xpath(
                                    "./ancestor::div[contains(@class,'caption')]/following-sibling::div[contains(@class,'button-group')]//button[contains(@onclick,'compare.add')]"
                            )
                    );

            wait.until(
                    ExpectedConditions.elementToBeClickable(compareButton)
            ).click();

            logger.info(
                    "Selected product "
                            + productName
                            + " for comparison."
            );

        } catch (Exception e) {

            logger.error(
                    "Error while selecting product for comparison: "
                            + e.getMessage()
            );
        }
    }

    public int getOfferProductsCount() {

        wait.until(
                ExpectedConditions.visibilityOfAllElements(offerProducts)
        );

        return offerProducts.size();
    }

    public int getListViewProductsCount() {

        wait.until(
                ExpectedConditions.visibilityOfAllElements(offerProducts)
        );

        return offerProducts.size();
    }

}