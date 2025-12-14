package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountPage extends BasePage {

    // Locators
    private final By welcomeMessage = By.cssSelector(".welcome-msg");
    private final By accountDropdown = By.cssSelector("button.action.switch[data-action='customer-menu-toggle']");
    private final By logoutLink = By.cssSelector("a[href*='/customer/account/logout/']");
    private final By myAccountHeading = By.cssSelector("h1.page-title");
    private final By addressBookLink = By.cssSelector("a[href*='/customer/address/']");
    private final By orderHistoryLink = By.cssSelector("a[href*='/sales/order/history/']");
    private final By accountInformationLink = By.cssSelector("a[href*='/customer/account/edit/']");

    // Constructor
    public AccountPage(WebDriver driver) {
        super(driver);
    }

    // Validation
    public boolean isLoggedIn() {
        return driver.getCurrentUrl().contains("/customer/account/")
                && titleContains("Moj");
    }

    public boolean isLoaded() {
        return isLoggedIn() && isElementVisible(myAccountHeading);
    }

    public String getWelcomeMessage() {
        return getText(welcomeMessage);
    }

    public String getAccountHeading() {
        return getText(myAccountHeading);
    }


//    // Navigation to other sections
//    public AddressBookPage goToAddressBook() {
//        clickElement(addressBookLink);
//        return new AddressBookPage(driver);
//    }
//
//    public OrderHistoryPage goToOrderHistory() {
//        clickElement(orderHistoryLink);
//        return new OrderHistoryPage(driver);
//    }
//
//    public AccountInformationPage goToAccountInformation() {
//        clickElement(accountInformationLink);
//        return new AccountInformationPage(driver);
//    }

    // Logout
    public LoginPage logout() {
        clickElement(accountDropdown);
        clickElement(logoutLink);

        // Wait for redirect to login or home
        waitForUrl("https://www.bigbang.ba/");

        return new LoginPage(driver).open();
    }

    public void newAddressOpen() {
        driver.get(BASE_URL +  "/customer/address/new/");
    }

    public WebElement waitForElementToBeVisible(By locator) {
        return waitForVisibility(locator, 10);
    }
}
