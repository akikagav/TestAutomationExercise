package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    protected WebDriver driver;

    @FindBy(how = How.XPATH, using = "//li/a[@href=\"/products\"]")
    WebElement productsButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals("Automation Exercise")) {
            throw new IllegalStateException("This is not Home Page," +
                    " current page is: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(driver, this);
    }

    public AllProductsPage clickOnProductsButton() {
        productsButton.click();
        return new AllProductsPage(driver);
    }


}
