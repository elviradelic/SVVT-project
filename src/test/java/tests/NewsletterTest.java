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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NewsletterTest extends BaseTest {

    private static final String NEWSLETTER_URL = BasePage.BASE_URL + "/newsletter-prijava";

    private WebDriverWait wait;

    @BeforeEach
    void setUpEach() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();
    }

    private void openNewsletterPage() {
        driver.get(NEWSLETTER_URL);
    }

    private void submitForm(String name, String email) {
        openNewsletterPage();

        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.name("ime")));
        nameField.clear();
        nameField.sendKeys(name);

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@type='submit']")));
        submitButton.click();
    }

    @Test
    @Order(1)
    void testNewsletterSubscription() {
        submitForm("Test", "test@gmail.com");

        // Wait for confirmation page to load (hosted on email.bigbang.ba)
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p.response")));

        WebElement responseParagraph = driver.findElement(By.cssSelector("p.response"));
        String responseText = responseParagraph.getText();

        assertTrue(responseText.contains("Vaša prijava je zaprimljena."),
                "Confirmation message not found on the newsletter results page.");
        assertTrue(responseText.contains("Uskoro ćete primiti potvrdni e-mail."),
                "Expected follow-up confirmation text not present on the newsletter results page.");
    }

    @Test
    @Order(2)
    @Disabled
    void testMissingName() {
        submitForm("", "email@example.com");

        assertEquals(NEWSLETTER_URL, driver.getCurrentUrl(),
                "Form submission with missing name should keep user on the newsletter page.");
    }

    @Test
    @Order(3)
    void testNameOnly() {
        submitForm("Name", "");

        assertEquals(NEWSLETTER_URL, driver.getCurrentUrl(),
                "Form submission with missing email should keep user on the newsletter page.");
    }

    @Test
    @Order(4)
    void testBothFieldsEmpty() {
        submitForm("", "");

        assertEquals(NEWSLETTER_URL, driver.getCurrentUrl(),
                "Form submission with both fields empty should keep user on the newsletter page.");
    }


}
