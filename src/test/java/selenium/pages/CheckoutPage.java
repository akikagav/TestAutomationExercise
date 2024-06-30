package selenium.pages;

import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    protected WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals("Automation Exercise - Checkout")) {
            throw new IllegalStateException("This is not Checkout," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }

}

