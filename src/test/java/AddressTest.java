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

    @Test
    @Story("Check login page navigation")
    @Description("User should be able to navigate to the login page from the homepage")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("qa-team")
    @Order(1)

    public void openLogin() throws InterruptedException{
        driver.get("https://www.bigbang.ba/");
        driver.manage().window().maximize();
        Thread.sleep(3000);

        String currentUrl = driver.getCurrentUrl();
        System.out.println("We are currently at : " + currentUrl);
        //assertEquals("https://www.bigbang.ba/customer/account/login/",currentUrl);
        driver.navigate().to("https://www.bigbang.ba/customer/account/login/");
        System.out.println("We are redirected successfully to the login page : 'https://www.bigbang.ba/customer/account/login/'");

        Thread.sleep(6000);
//        assertEquals("https://www.bigbang.ba/customer/address/index/", driver.getCurrentUrl(), "Failed to navigate to the address page!");
    }
    @Test
    @Order(3)
    @Disabled
    public void successfullyLogin()throws InterruptedException{
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//
//        WebElement promijeniButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.action.switch[data-action='customer-menu-toggle']")));
//        promijeniButton.click();
//
//        WebElement odjaviSeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='https://www.bigbang.ba/customer/account/logout/']")));
//        odjaviSeButton.click();
//
//        driver.get("https://www.bigbang.ba/customer/account/login/");
//        driver.manage().window().maximize();
//        Thread.sleep(3000);
//
//        WebElement email = driver.findElement(By.cssSelector("input[type='email']"));
//        email.sendKeys("emanhrustemovic6@gmail.com");
//        Thread.sleep(3000);
//
//        WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
//        password.sendKeys("eman03hrustemovic04");
//        password.submit();
//        Thread.sleep(3000);
//
//        String message = "We have successfully logged into our account";
//        String currentUrl = driver.getCurrentUrl();
//        String expectedUrl ="https://www.bigbang.ba/customer/account/";
//
//        if (currentUrl.equals(expectedUrl)) {
//            System.out.println("We have successfully logged into our account");
//        } else {
//            System.out.println("Failed to log in");
//        }

        TestUser testUser = TestDataFactory.validTestUser();



    }


    @Test
    @Order(2)
    public void addressForAccount() throws InterruptedException {
        TestUser user = TestDataFactory.validTestUser();

        // Login using the page objects, same approach as LoginTest
        LoginPage loginPage = new LoginPage(driver).open();
        AccountPage accountPage = loginPage.login(user.getEmail(), user.getPassword());

        assertTrue(accountPage.isLoggedIn(), "User should be logged in before managing addresses");
        assertEquals("https://www.bigbang.ba/customer/account/", driver.getCurrentUrl(), "Failed to navigate to the account page!");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to address section from account page
        WebElement accAddress = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Adresar']"))
        );
        accAddress.click();

        WebElement addNewAddress = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Dodajte novu adresu']"))
        );
        addNewAddress.click();

        // Fill out the address form
        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstname")));
        name.clear();
        name.sendKeys(user.getFirstName());

        WebElement lastname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lastname")));
        lastname.clear();
        lastname.sendKeys(user.getLastName());

        WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("telephone")));
        phone.clear();
        phone.sendKeys("00387225883");

        WebElement fax = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fax")));
        fax.clear();
        fax.sendKeys("as15d56d89d8");

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

        WebElement submitButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"form-validate\"]/div/div[1]/button"))
        );
        submitButton.click();

        // Verify success using URL wait instead of Thread.sleep
        wait.until(ExpectedConditions.urlContains("/customer/address/index/"));
        assertEquals("https://www.bigbang.ba/customer/address/index/", driver.getCurrentUrl(), "Failed to navigate to the address page!");
    }

}