// java
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactPage extends BasePage {

    private static final String BASE_URL = "https://www.bigbang.ba";

    private final By nameInput = By.id("name");
    private final By emailInput = By.id("email");
    private final By telephoneInput = By.id("telephone");
    private final By commentInput = By.id("comment");
    private final By submitButton = By.xpath("//span[text()='Pošalji']");
    private final By successMessage = By.id("success-message");
    private final By genericError = By.id("error-message");
    private final By captchaSelector = By.cssSelector(".g-recaptcha, iframe[src*='recaptcha']");

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public ContactPage open() {
        driver.get(BASE_URL + "/contact");
        // ensure form is visible (safe wait in BasePage)
        waitForVisibility(nameInput);
        return this;
    }

    public ContactPage setName(String name) {
        enterText(nameInput, name);
        return this;
    }

    public ContactPage setEmail(String email) {
        enterText(emailInput, email);
        return this;
    }

    public ContactPage setPhone(String phone) {
        enterText(telephoneInput, phone);
        return this;
    }

    public ContactPage setMessage(String message) {
        enterText(commentInput, message);
        return this;
    }

    public ContactPage submit() {
        clickElement(submitButton);
        return this;
    }

    public boolean isSubmitted() {
        // primary check: redirect URL
        if (driver.getCurrentUrl().contains("/contact/index/")) {
            return true;
        }
        // secondary check: visible success element
        return waitForVisibility(successMessage, 3) != null;
    }

    public boolean waitForSuccessOrError() {
        // Wait briefly for either a success message or a generic error to appear
        if (waitForVisibility(successMessage, 5) != null) {
            return true;
        }
        return waitForVisibility(genericError, 5) != null;
    }

    public boolean isCaptchaPresent() {
        return waitForVisibility(captchaSelector, 2) != null;
    }

    public String getFieldError(String fieldName) {
        // typical pattern: name-error, email-error, message-error, etc.
        By locator = By.id(fieldName + "-error");
        WebElement el = waitForVisibility(locator, 2);
        return el == null ? null : el.getText().trim();
    }

    public String getGenericError() {
        WebElement el = waitForVisibility(genericError, 2);
        return el == null ? null : el.getText().trim();
    }
}
