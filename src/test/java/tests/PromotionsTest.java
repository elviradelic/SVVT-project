package tests;

import TestBase.BaseTest;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PromotionsTest extends BaseTest {

    private WebDriverWait wait;

    private static final String PROMOTIONS_URL = BasePage.BASE_URL + "/promotions";
    private static final String GAMING_PROMOTIONS_URL = PROMOTIONS_URL + "?cat=879";
    private static final String LEGO_PS4_URL = BasePage.BASE_URL + "/lego-2k-drive-ps4.html";

    @BeforeEach
    void initWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.manage().deleteAllCookies();
    }

    @Test
    @Order(1)
    void navigateToPromotions() {
        driver.get(BasePage.BASE_URL + "/");

        WebElement promotionsLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a#ui-id-671")));
        promotionsLink.click();

        wait.until(ExpectedConditions.urlContains("/promotions"));
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/promotions"),
                "Failed to navigate to promotions page. Actual URL: " + currentUrl);
    }

    @Test
    @Order(2)
    void filterGamingPromotions() {
        driver.get(PROMOTIONS_URL);

        WebElement gamingLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[href='" + GAMING_PROMOTIONS_URL + "']")));
        gamingLink.click();

        wait.until(ExpectedConditions.urlContains("cat=879"));
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("cat=879"),
                "Failed to filter gaming promotions. Actual URL: " + currentUrl);
    }

//    @Test
//    @Order(5)
//    void selectLegoDrivePS4() {
//        // assumes we are on the Take2 product list page from previous test
//        WebElement legoDriveLink = wait.until(ExpectedConditions.elementToBeClickable(
//                By.cssSelector("img[alt='Lego 2K Drive PS4']")));
//        legoDriveLink.click();
//
//        wait.until(ExpectedConditions.urlContains("lego-2k-drive-ps4"));
//        String currentUrl = driver.getCurrentUrl();
//        assertTrue(currentUrl.contains("lego-2k-drive-ps4"),
//                "Failed to navigate to Lego Drive PS4 product page. Actual URL: " + currentUrl);
//    }

    @Test
    @Order(6)
    void addToCart() {
        driver.get(LEGO_PS4_URL);

        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button#product-addtocart-button")));
        addToCartButton.click();

        WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a.action.showcart")));
        cartLink.click();

        wait.until(ExpectedConditions.urlContains("checkout/cart"));
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("checkout/cart"),
                "Failed to navigate to the cart. Actual URL: " + currentUrl);
    }

    @Test
    @Order(7)
    void testVideoPlay() throws InterruptedException {
        driver.get(LEGO_PS4_URL);

        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div.embed-container iframe")));
        driver.switchTo().frame(iframe);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('video').play();");

        Thread.sleep(5000);

        js.executeScript("document.querySelector('video').pause();");
        driver.switchTo().defaultContent();

        assertEquals(LEGO_PS4_URL, driver.getCurrentUrl(),
                "After playing video, user should remain on the product page.");
    }

    @Test
    @Order(8)
    void returnToHomepage() {
        driver.get(BasePage.BASE_URL + "/");

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BasePage.BASE_URL + "/", currentUrl,
                "Failed to return to the homepage. Actual URL: " + currentUrl);
    }
}
