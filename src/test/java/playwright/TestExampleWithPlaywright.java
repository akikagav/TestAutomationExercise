package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestExampleWithPlaywright {
    static Playwright playwright;
    static Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void registerUser() {
        page.navigate("https://automationexercise.com/");
        assertEquals(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Home")).getAttribute("style"), "color: orange;");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Signup / Login")).click();
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("New User Signup!"))).isVisible();
        page.getByPlaceholder("Name").click();
        page.getByPlaceholder("Name").fill("Ast");
        page.locator("form").filter(new Locator.FilterOptions().setHasText("Signup")).getByPlaceholder("Email Address").click();
        page.locator("form").filter(new Locator.FilterOptions().setHasText("Signup")).getByPlaceholder("Email Address").fill("asttest@mailinator.com");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Signup")).click();
        assertThat(page.getByText("Enter Account Information")).isVisible();
        page.getByText("Mrs.").click();
        page.getByLabel("Mrs.").check();
        page.getByLabel("Password *").click();
        page.getByLabel("Password *").fill("astghik");
        page.locator("#days").selectOption("22");
        page.locator("#months").selectOption("3");
        page.locator("#years").selectOption("1993");
        page.getByLabel("Sign up for our newsletter!").check();
        page.getByLabel("Receive special offers from").check();
        page.getByLabel("First name *").click();
        page.getByLabel("First name *").fill("Ast");
        page.getByLabel("Last name *").click();
        page.getByLabel("Last name *").fill("Hamb");
        page.getByLabel("Company", new Page.GetByLabelOptions().setExact(true)).click();
        page.getByLabel("Company", new Page.GetByLabelOptions().setExact(true)).fill("Office");
        page.getByLabel("Address * (Street address, P.").click();
        page.getByLabel("Address * (Street address, P.").fill("Address");
        page.getByLabel("Address 2").dblclick();
        page.getByLabel("Address 2").fill("Address2");
        page.getByLabel("Country *").selectOption("United States");
        page.getByLabel("State *").click();
        page.getByLabel("State *").fill("US");
        page.getByLabel("City *").click();
        page.getByLabel("City *").fill("US");
        page.locator("#zipcode").click();
        page.locator("#zipcode").fill("0054");
        page.getByLabel("Mobile Number *").dblclick();
        page.getByLabel("Mobile Number *").fill("+37495555555");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Create Account")).click();
        assertThat(page.getByText("Account Created!")).isVisible();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Continue")).click();
        assertThat(page.getByText("Logged in as Ast")).isVisible();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Delete Account")).click();
        assertThat(page.getByText("Account Deleted!")).isVisible();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Continue")).click();

    }

    @Test
    void loginWithCorrectEmailPass() {
        page.navigate("https://automationexercise.com/");
        assertEquals(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Home")).getAttribute("style"), "color: orange;");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Signup / Login")).click();
        page.locator("form").filter(new Locator.FilterOptions().setHasText("Login")).getByPlaceholder("Email Address").click();
        page.locator("form").filter(new Locator.FilterOptions().setHasText("Login")).getByPlaceholder("Email Address").fill("testastgh@mailinator.com");
        page.getByPlaceholder("Password").click();
        page.getByPlaceholder("Password").fill("astghik");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        assertThat(page.getByText("Logged in as Astghik")).isVisible();

    }

    @Test
    void loginWithIncorrectEmailPass() {
        page.navigate("https://automationexercise.com/");
        assertEquals(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Home")).getAttribute("style"), "color: orange;");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Signup / Login")).click();
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Login to your account"))).isVisible();
        page.locator("form").filter(new Locator.FilterOptions().setHasText("Login")).getByPlaceholder("Email Address").click();
        page.locator("form").filter(new Locator.FilterOptions().setHasText("Login")).getByPlaceholder("Email Address").fill("testastgh@gmail.com");
        page.getByPlaceholder("Password").click();
        page.getByPlaceholder("Password").fill("astghik");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        assertThat(page.getByText("Your email or password is")).isVisible();

    }


    @Test
    void logoutUser() {
        page.navigate("https://automationexercise.com/");
        assertEquals(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Home")).getAttribute("style"), "color: orange;");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Signup / Login")).click();
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Login to your account"))).isVisible();
        page.locator("form").filter(new Locator.FilterOptions().setHasText("Login")).getByPlaceholder("Email Address").click();
        page.locator("form").filter(new Locator.FilterOptions().setHasText("Login")).getByPlaceholder("Email Address").fill("testastgh@mailinator.com");
        page.getByPlaceholder("Password").click();
        page.getByPlaceholder("Password").fill("astghik");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        assertThat(page.getByText("Logged in as Astghik")).isVisible();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Logout")).click();
        assertThat(page).hasURL("https://automationexercise.com/login");

    }

    @Test
    void registerUserWithExistingEmail() {
        page.navigate("https://automationexercise.com/");
        assertEquals(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Home")).getAttribute("style"), "color: orange;");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Signup / Login")).click();
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("New User Signup!"))).isVisible();
        page.getByPlaceholder("Name").click();
        page.getByPlaceholder("Name").fill("Astgh");
        page.locator("form").filter(new Locator.FilterOptions().setHasText("Signup")).getByPlaceholder("Email Address").click();
        page.locator("form").filter(new Locator.FilterOptions().setHasText("Signup")).getByPlaceholder("Email Address").fill("testastgh@mailinator.com");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Signup")).click();
        assertThat(page.getByText("Email Address already exist!")).isVisible();

    }

    @Test
    void contactUsForm() {
        page.navigate("https://automationexercise.com/");
        assertEquals(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Home")).getAttribute("style"), "color: orange;");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Contact us")).click();
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Get In Touch"))).isVisible();
        page.getByPlaceholder("Name").click();
        page.getByPlaceholder("Name").fill("Test Name");
        page.getByPlaceholder("Email", new Page.GetByPlaceholderOptions().setExact(true)).click();
        page.getByPlaceholder("Email", new Page.GetByPlaceholderOptions().setExact(true)).fill("test@mailinator.com");
        page.getByPlaceholder("Subject").click();
        page.getByPlaceholder("Subject").fill("Test Subject");
        page.getByPlaceholder("Your Message Here").click();
        page.getByPlaceholder("Your Message Here").fill("Test Message");
        page.locator("input[name=\"upload_file\"]").click();
        page.locator("input[name=\"upload_file\"]").setInputFiles(Paths.get("src/main/resources/1.png"));
        page.onDialog(dialog -> {
            System.out.println(String.format("Dialog message: %s", dialog.message()));
            dialog.accept();
        });
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
        assertThat(page.locator("#contact-page").getByText("Success! Your details have")).isVisible();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Home")).click();
        assertThat(page).hasURL("https://automationexercise.com/");

    }

    @Test
    void verifyTestCasesPage() {
        page.navigate("https://automationexercise.com/");
        assertEquals(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Home")).getAttribute("style"), "color: orange;");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Test Cases")).click();
        assertThat(page).hasURL("https://automationexercise.com/test_cases");
    }

    @Test
    void verifyAllProductsAndProductDetailPage() {
        page.navigate("https://automationexercise.com/");
        assertEquals(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Home")).getAttribute("style"), "color: orange;");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Products")).click();
        assertThat(page).hasURL("https://automationexercise.com/products");
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("All Products"))).isVisible();
        page.locator(".choose > .nav > li > a").first().click();
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Blue Top"))).isVisible();
        assertThat(page.getByText("Rs. 500")).isVisible();
        assertThat(page.getByText("Availability:")).isVisible();
        assertThat(page.getByText("Condition:")).isVisible();
        assertThat(page.getByText("Brand:")).isVisible();
        //Detail data can't be static, it can be tested with API call

    }

    @Test
    void searchProduct() {
        page.navigate("https://automationexercise.com/");
        assertEquals(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Home")).getAttribute("style"), "color: orange;");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Products")).click();
        assertThat(page).hasURL("https://automationexercise.com/products");
        assertThat(page.getByText("ALL PRODUCTS")).isVisible();
        page.getByPlaceholder("Search Product").fill("Tshirt");
        page.click("#submit_search");
        assertThat(page.getByText("SEARCHED PRODUCTS")).isVisible();
        assertThat(page).hasURL("https://automationexercise.com/product_details/1");
        //8. Verify all the products related to search are visible
    }

    @Test
    void verifySubscriptionInHomePage() {
        page.navigate("https://automationexercise.com/");
        assertEquals(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Home")).getAttribute("style"), "color: orange;");
        assertThat(page.locator("#footer")).containsText("Subscription");
        page.getByPlaceholder("Your email address").click();
        page.getByPlaceholder("Your email address").fill("test@gmail.com");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("")).click();
        assertThat(page.getByText("You have been successfully subscribed!")).isVisible();

    }

    @Test
    void verifySubscriptionInCartPage() {
        page.navigate("https://automationexercise.com/");
        assertEquals(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Home")).getAttribute("style"), "color: orange;");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Cart")).click();
        page.getByPlaceholder("Your email address").click();
        page.getByPlaceholder("Your email address").fill("aaaa@gmail.com");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("")).click();
        assertThat(page.getByText("You have been successfully subscribed!")).isVisible();
    }
}
