package selenium.pages;

import org.openqa.selenium.WebDriver;

public class ContactUsPage {
    protected WebDriver driver;

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals("Automation Exercise - Contact Us")) {
            throw new IllegalStateException("This is not Contact Us," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }

}
