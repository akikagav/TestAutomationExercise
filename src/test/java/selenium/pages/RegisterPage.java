package selenium.pages;

import org.openqa.selenium.WebDriver;

public class RegisterPage {
    protected WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals("Automation Exercise - Register")) {
            throw new IllegalStateException("This is not Register," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }
}
