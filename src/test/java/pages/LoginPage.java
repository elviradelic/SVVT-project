 package pages;

     import org.openqa.selenium.By;
     import org.openqa.selenium.WebDriver;

     public class LoginPage extends BasePage {

         // Page URL
         private static final String PAGE_URL = "https://www.bigbang.ba/customer/account/login/";

         // Locators (all in one place, easy to maintain)
         private final By emailInput = By.cssSelector("input[type='email']");
         private final By passwordInput = By.cssSelector("input[type='password']");
         private final By loginButton = By.cssSelector("button[type='submit']");
         private final By errorMessage = By.cssSelector(".message-error");
         private final By forgotPasswordLink = By.cssSelector("a.action.remind");

         // Constructor
         public LoginPage(WebDriver driver) {
             super(driver);
         }

         // Navigation
         public LoginPage open() {
             driver.get(PAGE_URL);
             waitForElement(emailInput); // Wait for page to load
             return this; // Return 'this' for method chaining
         }

         // Actions - Individual field setters
         public LoginPage setEmail(String email) {
             enterText(emailInput, email);
             return this;
         }

         public LoginPage setPassword(String password) {
             enterText(passwordInput, password);
             return this;
         }

         public void clickLogin() {
             clickElement(loginButton);
         }

         // High-level action - combines everything
         public AccountPage login(String email, String password) {
             setEmail(email);
             setPassword(password);
             clickLogin();

             // Wait for navigation to complete
             waitForUrl("/customer/account/");

             // Return new page object for the next page
             return new AccountPage(driver);
         }

         // Failed login stays on login page
         public LoginPage loginExpectingFailure(String email, String password) {
             setEmail(email);
             setPassword(password);
             clickLogin();

             // Wait for error message to appear
             waitForElement(errorMessage);

             return this; // Still on login page
         }

         // Validation methods
         public boolean isLoaded() {
             return driver.getCurrentUrl().contains("/customer/account/login/")
                     && isElementVisible(emailInput);
         }

         public String getErrorMessage() {
             return getText(errorMessage);
         }

         public boolean isErrorDisplayed() {
             return isElementVisible(errorMessage);
         }

//         // Other page interactions
//         public ForgotPasswordPage clickForgotPassword() {
//             clickElement(forgotPasswordLink);
//             return new ForgotPasswordPage(driver);
//         }
     }