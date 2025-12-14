import TestBase.BaseTest;
import TestBase.Factory.TestDataFactory;
import TestBase.Factory.TestUser;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AccountPage;
import pages.LoginPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("E-Commerce")
@Feature("Shopping Cart")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddressTest extends BaseTest {
    private static String baseUrl;

    // No need for @BeforeAll - inherited from BaseTest
    // Driver is automatically initialized in BaseTest.beforeAll()

    @AfterEach
    public void delayBetweenTests() {
        clearCookiesAndLogoOut();
        try {
            Thread.sleep(3000); // brief pause between tests
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @Story("Check login page navigation")
    @Description("User should be able to navigate to the login page from the homepage")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("qa-team")
    @Order(1)

    public void openLogin() throws InterruptedException{
        System.out.println("[AddressTest] Starting openLogin test");
        driver.get("https://www.bigbang.ba/");
        driver.manage().window().maximize();
        Thread.sleep(3000);

        String currentUrl = driver.getCurrentUrl();
        System.out.println("[AddressTest] We are currently at: " + currentUrl);
        driver.navigate().to("https://www.bigbang.ba/customer/account/login/");
        System.out.println("[AddressTest] Redirected to login page: " + driver.getCurrentUrl());

        Thread.sleep(6000);
    }

    @Test
    @Order(3)
    @Story("Successful Login from Address Flow")
    @Description("User can successfully log in using valid test data")
    @Severity(SeverityLevel.CRITICAL)
    public void successfullyLogin() {
        System.out.println("[AddressTest] Starting successfullyLogin test");
        TestUser user = TestDataFactory.validTestUser();

        System.out.println("[AddressTest] Opening login page");
        LoginPage loginPage = new LoginPage(driver).open();
        System.out.println("[AddressTest] Submitting login form for user: " + user.getEmail());
        AccountPage accountPage = loginPage.login(user.getEmail(), user.getPassword());

        System.out.println("[AddressTest] Verifying user is logged in; current URL: " + driver.getCurrentUrl());
        assertTrue(accountPage.isLoggedIn(), "User should be logged into their account");
        assertEquals("https://www.bigbang.ba/customer/account/", driver.getCurrentUrl(), "User should be on the account page after login");
        System.out.println("[AddressTest] successfullyLogin test completed");
    }


    @Test
    @Order(2)
    @Story("Address book shows add button")
    @Description("Verify that the 'Dodajte novu adresu' button is present in the address book")
    @Severity(SeverityLevel.NORMAL)
    public void addressBookHasAddNewAddressButton() {
        System.out.println("[AddressTest] Starting addressBookHasAddNewAddressButton test");
        TestUser user = TestDataFactory.validTestUser();

        System.out.println("[AddressTest] Logging in as: " + user.getEmail());
        LoginPage loginPage = new LoginPage(driver).open();
        AccountPage accountPage = loginPage.login(user.getEmail(), user.getPassword());

        System.out.println("[AddressTest] Verifying account page after login; current URL: " + driver.getCurrentUrl());
        assertTrue(accountPage.isLoggedIn(), "User should be logged in before managing addresses");
        assertEquals("https://www.bigbang.ba/customer/account/", driver.getCurrentUrl(), "Failed to navigate to the account page!");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        System.out.println("[AddressTest] Navigating to address book");
        WebElement accAddress = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Adresar']"))
        );
        accAddress.click();

        System.out.println("[AddressTest] Waiting for 'Dodajte novu adresu' button");
        wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Dodajte novu adresu']"))
        );
        System.out.println("[AddressTest] 'Dodajte novu adresu' button is present and clickable");
    }

    @Test
    @Order(4)
    @Story("User can add new address")
    @Description("Navigate to new address form and successfully fill and submit it")
    @Severity(SeverityLevel.CRITICAL)
    public void addressForAccount() throws InterruptedException {
        System.out.println("[AddressTest] Starting addressForAccount test");
        TestUser user = TestDataFactory.validTestUser();

        System.out.println("[AddressTest] Logging in as: " + user.getEmail());
        LoginPage loginPage = new LoginPage(driver).open();
        AccountPage accountPage = loginPage.login(user.getEmail(), user.getPassword());

        System.out.println("[AddressTest] Verifying account page after login; current URL: " + driver.getCurrentUrl());
        assertTrue(accountPage.isLoggedIn(), "User should be logged in before managing addresses");
        assertEquals("https://www.bigbang.ba/customer/account/", driver.getCurrentUrl(), "Failed to navigate to the account page!");

        accountPage.newAddressOpen();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        System.out.println("[AddressTest] Waiting for new address page to load");
        wait.until(ExpectedConditions.urlContains("/customer/address/new/"));


        assertEquals("https://www.bigbang.ba/customer/address/new/", driver.getCurrentUrl(), "Failed to navigate to the new address page!");

//
//
        // Fill out the address form
        System.out.println("[AddressTest] Filling address form");
        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstname")));
        name.clear();
        name.sendKeys(user.getFirstName());

        WebElement lastname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lastname")));
        lastname.clear();
        lastname.sendKeys(user.getLastName());

        WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("telephone")));
        phone.clear();
        phone.sendKeys("061123456");

//        WebElement fax = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fax")));
//        fax.clear();
//        fax.sendKeys("as15d56d89d8");

        WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("street_1")));
        address.clear();
        address.sendKeys("Francuske revolucije, Ilidža");

        WebElement city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("city")));
        city.clear();
        city.sendKeys("Sarajevo");
        Thread.sleep(1000);
        city.sendKeys(Keys.ENTER);

//        WebElement postalNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("zip")));
//        postalNumber.clear();
//        postalNumber.sendKeys("756962");


        System.out.println("[AddressTest] Submitting address form");
        Thread.sleep(5000);
//        WebElement element = driver.findElement(By.xpath("button[data-action='save-address']"));
//        WebElement elementCss = driver.findElement(By.cssSelector("button[title='Spremanje adrese']"));
//        WebElement submitButtonA = accountPage.waitForElementToBeVisible(By.xpath("//button[@data-action='save-address']"));
        WebElement submitButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Spremanje adrese']"))
        );
        submitButton.click();

        // Verify success using URL wait instead of Thread.sleep
        System.out.println("[AddressTest] Waiting for address index page");
        wait.until(ExpectedConditions.urlContains("/customer/address/index/"));
        assertEquals("https://www.bigbang.ba/customer/address/index/", driver.getCurrentUrl(), "Failed to navigate to the address page!");
        System.out.println("[AddressTest] addressForAccount test completed");
    }

}