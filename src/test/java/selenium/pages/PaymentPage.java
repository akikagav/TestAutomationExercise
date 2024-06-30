package selenium.pages;

import org.openqa.selenium.WebDriver;

public class PaymentPage {
    protected WebDriver driver;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals("Automation Exercise - Payment")) {
            throw new IllegalStateException("This is not Payment," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }
}
