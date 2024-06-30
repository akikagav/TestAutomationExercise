package selenium.pages;

import org.openqa.selenium.WebDriver;

public class OrderPlacedPage {
    protected WebDriver driver;

    public OrderPlacedPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals("Automation Exercise - Order Placed")) {
            throw new IllegalStateException("This is not Order Placed," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }
}
