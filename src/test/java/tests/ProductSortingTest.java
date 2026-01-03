package tests;

import TestBase.BaseTest;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductSortingTest extends BaseTest {

    private WebDriverWait wait;

    private static final String SAMSUNG_TVS =
            BasePage.BASE_URL + "/tv-i-audio/televizori-i-dodaci.html?manufacturer=10";
    private static final String WASHING_MACHINES_URL =
            BasePage.BASE_URL + "/bijela-tehnika-1/perilice-i-susilice-rublja/perilice-rublja.html?manufacturer=29569";

    @BeforeEach
    void initWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.manage().deleteAllCookies();
    }

    private WebElement firstGridProduct() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".products-grid .product-item:first-child")));
    }

    @Test
    @Order(1)
    void redirectingToProductsPage() {
        driver.get(BasePage.BASE_URL + "/");

        wait.until(ExpectedConditions.urlContains(BasePage.BASE_URL));

        driver.navigate().to(SAMSUNG_TVS);

        wait.until(ExpectedConditions.urlToBe(SAMSUNG_TVS));
        assertEquals(SAMSUNG_TVS, driver.getCurrentUrl(),
                "User should be redirected to the 32-inch-or-smaller TV listing page.");
    }

    @Test
    @Order(2)
    void sortingByBrand() {
        driver.get(SAMSUNG_TVS);

        WebElement firstProduct = firstGridProduct();
        String productName = firstProduct.getText();

        assertTrue(productName.contains("Samsung"),
                "Expected first listed TV to contain brand 'LG' after applying LG brand filter, but was: " + productName);
    }

    @Test
    @Order(3)
    void sortingByScreenSize() {
        driver.get(SAMSUNG_TVS + "&luceed_tv_velicina_ekrana=41261");


        WebElement firstProduct = firstGridProduct();
        String productName = firstProduct.getText();

        assertTrue(productName.contains("55"),
                "Expected first listed TV to include '55' in its description after size filter, but was: " + productName);
    }

    @Test
    @Order(4)
    @Disabled
    void sortingByType() {
        driver.get(SAMSUNG_TVS + "&luceed_tv_tip_televizora=27059");

        WebElement firstProduct = firstGridProduct();
        String productName = firstProduct.getText();

        assertTrue(productName.contains(" Smart TV "),
                "Expected first listed TV to be a Smart TV after type filter, but was: " + productName);
    }

    @Test
    @Order(5)
    void sortingWashingMachine() {
        driver.get(WASHING_MACHINES_URL);

        WebElement firstProduct = firstGridProduct();
        String productName = firstProduct.getText();

        assertTrue(productName.contains("Beko"),
                "Expected first listed washing machine to match the 'Beko' label used in the legacy test, but was: " + productName);
    }
}
