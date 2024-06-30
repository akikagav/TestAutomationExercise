package selenium.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import selenium.pages.AllProductsPage;
import selenium.pages.CartPage;
import selenium.pages.HomePage;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestExampleWithSelenium {
    WebDriver driver;
    HomePage homePage;
    AllProductsPage allProductsPage;

    @BeforeAll
    static void launchBrowser() {

    }

    @AfterAll
    static void closeBrowser() {
    }

    @BeforeEach
    public void setup() {
        //Add AdBlocker extension for blocking adds
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("C:\\Stands-AdBlocker.crx"));

        driver = new ChromeDriver(options);

        driver.get("https://automationexercise.com/");
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    void addProductsInCartAndVerify() {
        homePage = new HomePage(driver);
        allProductsPage = homePage.clickOnProductsButton();

        // Get product details
        String firstProductTitle = allProductsPage.getProductTitle(0);
        String secondProductTitle = allProductsPage.getProductTitle(1);
        int firstProductPrice = Integer.parseInt(allProductsPage.getProductPrice(0).replaceAll("[^\\d]", ""));
        int secondProductPrice = Integer.parseInt(allProductsPage.getProductPrice(1).replaceAll("[^\\d]", ""));

        // Add first product to cart
        allProductsPage.hoverToProduct(1);
        allProductsPage.clickToAddToCartButton(1);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        allProductsPage.clickOnContinueShoppingButton();

        // Add second product to cart
        allProductsPage.hoverToProduct(2);
        allProductsPage.clickToAddToCartButton(2);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to cart page and perform assertions
        CartPage cartPage = allProductsPage.clickOnViewCartButton();
        assertEquals(cartPage.getItemsCount(), 2, "Incorrect number of items in cart.");
        assertEquals(cartPage.getProductTitle(0), firstProductTitle, "Incorrect first product title in cart.");
        assertEquals(cartPage.getProductTitle(1), secondProductTitle, "Incorrect second product title in cart.");
        assertEquals(cartPage.getProductPrice(0), firstProductPrice, "Incorrect first product price in cart.");
        assertEquals(cartPage.getProductPrice(1), secondProductPrice, "Incorrect second product price in cart.");
        assertEquals(cartPage.getProductQuantity(0), 1, "Incorrect first product quantity in cart.");
        assertEquals(cartPage.getProductQuantity(1), 1, "Incorrect second product quantity in cart.");
        assertEquals(cartPage.getProductTotalPrice(0), Math.multiplyExact(cartPage.getProductQuantity(0), cartPage.getProductPrice(0)), "Incorrect first product total price in cart.");
        assertEquals(cartPage.getProductTotalPrice(1), Math.multiplyExact(cartPage.getProductQuantity(1), cartPage.getProductPrice(1)), "Incorrect second product total price in cart.");
    }

}
