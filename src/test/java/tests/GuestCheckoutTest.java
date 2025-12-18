package tests;

import TestBase.BaseTest;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GuestCheckoutTest extends BaseTest {

    protected WebDriver webDriver;

    @BeforeEach
    public void initDriver() {
        this.webDriver = this.driver;
    }

    @Test
    @Order(1)
    public void addProductToCartAsGuest() {
        webDriver.get(BasePage.BASE_URL);
        webDriver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        webDriver.navigate().to("https://www.bigbang.ba/tv-i-audio/televizori-i-dodaci/televizori-1.html");

        WebElement product = webDriver.findElement(By.className("product-item-photo"));
        product.click();

        WebElement addProduct = webDriver.findElement(By.id("product-addtocart-button"));
        addProduct.click();


        WebElement cart = webDriver.findElement(By.className("minicart-wrapper"));
        cart.click();


        List<WebElement> list = webDriver.findElements(By.xpath("//*[contains(text(),'" + "Artikl" + "')]"));
        assertFalse(list.isEmpty(), "Text not found!");

    }

    @Test
    @Order(2)
    public void viewCartAsGuest() throws InterruptedException {
        webDriver.get(BasePage.BASE_URL);
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement cartIcon = webDriver.findElement(By.xpath("//*[@id=\"html-body\"]/div[2]/header/div/div[2]/div[2]/a"));
        cartIcon.click();
        Thread.sleep(3000);

        WebElement cartTitle = webDriver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/h1/span"));
        String titleText = cartTitle.getText();

        assertEquals("Košarica", titleText, "Naslov stranice nije 'Košarica'.");
    }



    @Test
    @Order(3)
    public void startCheckoutAsGuest() throws InterruptedException {
        viewCartAsGuest();
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement continueToCheckoutButton = webDriver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div/div[3]/div[1]/ul/li/button"));
        continueToCheckoutButton.click();
        Thread.sleep(6000);

        boolean isFormPresent = webDriver.findElements(By.xpath("//*[@id=\"shipping\"]")).size() > 0;

        String message = "Za neregistrovane korisnike postoji forma koju moraju popuniti prije kupovine";

        assertTrue(isFormPresent, "Forma nije pronađena!");

        if (isFormPresent) {
            System.out.println(message);
        }
    }
}