package selenium.pages;

import org.openqa.selenium.WebDriver;

public class ProductDetailsPage {
    protected WebDriver driver;

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals("Automation Exercise - Product Details")) {
            throw new IllegalStateException("This is not Product Details," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }
}
