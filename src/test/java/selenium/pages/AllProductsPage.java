package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AllProductsPage {
    protected WebDriver driver;
    @FindBy(how = How.XPATH, using = "//a[@href=\"/view_cart\"]")
    WebElement viewCartButton;

    public WebElement getContinueShoppingButton() {
        return continueShoppingButton;
    }

    @FindBy(how = How.CLASS_NAME, using = "btn-success")
    WebElement continueShoppingButton;

    @FindBy(how = How.XPATH, using = "//div[@class=\"productinfo text-center\"]/p")
    List<WebElement> productTitles;

    @FindBy(how = How.XPATH, using = "//div[@class=\"productinfo text-center\"]/h2")
    List<WebElement> productPrices;

    public AllProductsPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals("Automation Exercise - All Products")) {
            throw new IllegalStateException("This is not All Products," +
                    " current page is: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(driver, this);
    }

    public void hoverToProduct(int productId) {
        WebElement firstProductElement = driver.findElement(this.getProduct(productId));
        Actions actions = new Actions(driver);

        // Perform the hover action
        actions.moveToElement(firstProductElement).perform();
    }

    public String getProductTitle(int productId) {
        return productTitles.get(productId).getText();
    }

    public String getProductPrice(int productId) {
        return productPrices.get(productId).getText();
    }

    public void clickToAddToCartButton(int productId) {
        driver.findElement(this.getProductAddToCartButton(productId)).click();
    }

    public void clickOnContinueShoppingButton() {
        continueShoppingButton.click();
    }

    public CartPage clickOnViewCartButton() {
        viewCartButton.click();
        return new CartPage(driver);
    }

    private By getProduct(int productId) {
        return By.xpath("//img[@src='/get_product_picture/" + productId + "']");
    }

    private By getProductAddToCartButton(int productId) {
        return By.xpath("//a[@data-product-id='" + productId + "' and contains(@class, 'add-to-cart')]");
    }
}
