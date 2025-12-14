import TestBase.BaseTest;
import TestBase.Factory.TestDataFactory;
import TestBase.Factory.TestUser;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @Disabled
    public void addressForAccount() throws InterruptedException {
        driver.get("https://www.bigbang.ba/customer/account/login/");
        //driver.manage().window().maximize();
        Thread.sleep(3000);

        // Log in
        WebElement email = driver.findElement(By.cssSelector("input[type='email']"));
        email.sendKeys("emanhrustemovic6@gmail.com");
        Thread.sleep(3000);

        WebElement password = driver.findElement(By.id("pass"));
        password.sendKeys("eman03hrustemovic04");
        password.submit();
        Thread.sleep(2000);

        // Navigate to account page
        driver.navigate().to("https://www.bigbang.ba/customer/account/");
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.bigbang.ba/customer/account/", currentUrl, "Failed to navigate to the account page!");

        // Click on address section
        WebElement accAddress = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[4]/div[1]/a"));
        accAddress.click();
        Thread.sleep(4000);

        WebElement addNewAddress =driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[5]/div[1]/button"));
        addNewAddress.click();
        Thread.sleep(4000);

        // Fill out the address form
        WebElement name = driver.findElement(By.id("firstname"));
        name.clear();
        name.sendKeys("Arnela");

        WebElement lastname = driver.findElement(By.id("lastname"));
        lastname.clear();
        lastname.sendKeys("Sokolić");

        WebElement phone = driver.findElement(By.id("telephone")); // Correct XPath for phone
        phone.clear();
        phone.sendKeys("00387225883");
        Thread.sleep(7000);


        WebElement fax = driver.findElement(By.id("fax"));
        fax.clear();
        fax.sendKeys("as15d56d89d8");

        WebElement address = driver.findElement(By.id("street_1"));
        address.clear();
        address.sendKeys("Francuske revolucije, Ilidža");

        WebElement city = driver.findElement(By.id("city"));
        city.clear();
        city.sendKeys("Sarajevo");

        WebElement postalNumber = driver.findElement(By.id("zip"));
        postalNumber.clear();
        postalNumber.sendKeys("756962");

        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"form-validate\"]/div/div[1]/button"));
        submitButton.click();

        // Verify success
        Thread.sleep(3000); // Wait for redirection or address update
        String currentUrl1 = driver.getCurrentUrl();
        assertEquals("https://www.bigbang.ba/customer/address/index/", currentUrl1, "Failed to navigate to the address page!");

        System.out.println("Address successfully added!");
    }

}