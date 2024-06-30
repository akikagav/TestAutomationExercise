package selenium.pages;

import org.openqa.selenium.WebDriver;

public class SingUpLoginPage {
    protected WebDriver driver;

    public SingUpLoginPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals("Automation Exercise - Signup/Login")) {
            throw new IllegalStateException("This is not Signup/Login," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }
}
