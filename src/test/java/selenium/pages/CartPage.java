package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    protected WebDriver driver;
    By cartItemPrice = By.className("cart_price");
    By cartItemTitle = By.xpath("//td[@class=\"cart_description\"]/h4/a");
    By cartItemQuantity = By.xpath("//td[@class=\"cart_quantity\"]/button");
    By cartItemTotalPrice = By.className("cart_total_price");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals("Automation Exercise - Checkout")) {
            throw new IllegalStateException("This is not Cart," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }

    public int getItemsCount() {
        return driver.findElements(cartItemPrice).size();
    }

    public String getProductTitle(int productId) {
        return driver.findElements(cartItemTitle).get(productId).getText();
    }

    public int getProductPrice(int productId) {
        return Integer.parseInt(driver.findElements(cartItemPrice).get(productId).getText().replaceAll("[^\\d]", ""));
    }

    public int getProductQuantity(int productId) {
        return Integer.valueOf(driver.findElements(cartItemQuantity).get(productId).getText());
    }

    public int getProductTotalPrice(int productId) {
        return Integer.valueOf(driver.findElements(cartItemTotalPrice).get(productId).getText().replaceAll("[^\\d]", ""));
    }

}
