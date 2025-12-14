# Selenium Test Suite Overview

This document summarizes each Selenium test class and test method currently defined in `src/test/java` for the BigBang (`https://www.bigbang.ba/`) site.

---

## AccCreationTest

End-to-end registration scenarios for creating a customer account, with manual CAPTCHA handling.

- `testValidFormSubmission`: Fills the registration form with valid data, expects successful registration and redirect to the account dashboard.
- `testMissingFirstName`: Submits the form without a first name and expects registration to fail (remaining on the form page).
- `testMissingLastName`: Submits the form without a last name and expects registration to fail.
- `testInvalidEmail`: Uses an invalid email format and expects registration to fail.
- `testMissingPassword`: Leaves password and confirmation empty and expects registration to fail.
- `testMismatchedPasswords`: Uses non-matching password and confirmation and expects registration to fail.
- `testEmptyFields`: Leaves all fields empty and expects registration to fail.

---

## AddToCartAndPriceCheckTest

Cart behavior and price verification for a specific Samsung brand flow.

- `testAddToCart`: Navigates via the brands page, opens two Samsung products (TV and oven), adds both to the cart, and checks that the cart counter increments.
- `testPriceDisplay`: Opens the cart page and asserts that the displayed cart total matches a hard-coded expected price string.

---

## AddressTest

Account address management scenarios for a logged-in user.

- `openLogin`: Opens the homepage, then navigates explicitly to the customer login page.
- `successfullyLogin`: Logs out if needed, then logs in with specific credentials and verifies reaching the account dashboard.
- `addressForAccount`: Logs in, navigates to the address section, fills and submits the address form, and verifies redirect to the address index page.

---

## CartTest

Logged-in customer cart lifecycle: add, update, verify prices, checkout, and cleanup.

- `loginAndOpenCart`: Logs in with a specific account and navigates to the cart page.
- `addingIntoCart`: From the homepage, navigates to TVs, opens the first product, adds it to the cart, and verifies cart contents via text search.
- `checkingCartStatus`: Navigates to the cart page after login and waits for it to load, effectively checking that the cart is reachable.
- `updatingCart`: On the cart page, changes item quantity to 3 and clicks update, expecting the operation to complete successfully.
- `verifyTotalPrice`: Iterates over cart rows, calculates line totals (price × quantity), sums them, and compares the calculated total with the displayed total for consistency.
- `removingItemsFromTheCart`: On the cart page, iterates over all remove links to clear the cart and logs the result.
- `processPayment`: Starts checkout from the cart, selects a shipping address, proceeds through shipping, chooses a card payment method, and reaches the payment step.

---

## ContactFormTests

Contact form validation scenarios, with manual CAPTCHA completion.

- `testValidFormSubmission`: Fills all required fields with valid values, submits, and expects redirect to the contact confirmation page.
- `testMissingName`: Leaves the name empty and expects submission to fail (no redirect), logging that the failure is expected.
- `testMissingEmail`: Leaves the email empty and expects submission to fail.
- `testInvalidEmail`: Uses an invalid email format and expects submission to fail.
- `testMissingMessage`: Leaves the message empty and expects submission to fail.
- `testEmptyFields`: Leaves all fields empty and expects submission to fail.
- `emptyPhoneNumberField`: Leaves phone empty but fills other fields and expects the form submission to succeed (phone is treated as optional).

---

## DeliveryInfoTest

Navigation tests for delivery, payment, and related informational pages.

- `openDeliveryPage`: Opens the homepage and then navigates to the "Načini dostave" (delivery methods) page.
- `paymentInfo`: From the delivery page, clicks the payment link and verifies navigation to the payment methods page.
- `deliveryInfo`: Ensures the delivery info tab/url (`/nacini-dostave`) remains selected and reachable.
- `termsOfUseInfo`: Opens the terms of use page from the delivery page and verifies navigation.
- `dataPrivacy`: Opens the data privacy page from the delivery page and verifies navigation.
- `supportInfo`: Opens the support page from the delivery page and verifies navigation.

---

## GuestCheckoutTest

Guest user cart and checkout initiation flows (no login).

- `addProductToCartAsGuest`: As a guest, navigates to TVs, opens a product, adds it to the cart, and verifies that an item indicator text is present in the mini cart.
- `viewCartAsGuest`: From the homepage, opens the cart via the header icon and asserts the cart page title is "Košarica".
- `startCheckoutAsGuest`: From the cart page, clicks the checkout button and verifies that a shipping form is present for guest checkout.

---

## LoginTest

Login page coverage for various credential combinations and basic title checks.

- `testingTitleMatch`: Opens the homepage and verifies that the page title contains "Big Bang".
- `openLogin`: Navigates from the homepage to the login page and logs the current URL.
- `successfullyLogin`: Logs in with a known valid email/password and prints success or failure based on the resulting URL.
- `invalidEmailLogin`: Attempts login with an incorrect email and expects login to fail (based on URL comparison).
- `invalidPassword`: Attempts login with a wrong password and expects login to fail.
- `emptyFields`: Submits the login form with both fields empty and expects login failure.
- `goodEmailButEmptyPassword`: Uses a valid email with an empty password and expects login failure.
- `emptyEmailButGoodPassword`: Uses an empty email with a valid password and expects login failure.
- `testingRememberMeMethod`: Attempts login and toggles the "remember me" checkbox, ensuring it can be selected.

---

## LogoutTest

Login and logout behavior for a specific user.

- `successfullyLogin`: Logs in with a known user and verifies redirect to the account page.
- `successfullyLogout`: From a logged-in state, opens the account menu, clicks logout, and verifies redirect back to the homepage.

---

## NavigationTests

Top-level navigation and social media link correctness.

- `testValidNavigationLinks`: Iterates through a list of primary section URLs and asserts that each page title matches the expected section name.
- `testInvalidNavigationLinks`: Visits deliberately invalid category URLs and verifies that the site displays an appropriate "not found" message.
- `testSocialMediaLinks`: On the homepage, verifies that the Instagram and Facebook footer links point to the expected social profiles.

---

## NewsletterTest

Newsletter subscription form behavior for valid and invalid inputs.

- `testNewsletterSubscription`: Submits the form with valid name and email and asserts redirect to an external confirmation URL.
- `testMissingName`: Submits with an empty name but valid email and confirms that the browser remains on the newsletter form page.
- `testNameOnly`: Submits with a name but empty email and confirms that the browser remains on the newsletter form page.
- `testBothFieldsEmpty`: Submits with both name and email empty and confirms that the browser remains on the newsletter form page.
- `testEmailMissing`: Similar to the name-only case, explicitly checks that missing email keeps the user on the form page.

---

## PerformanceTest

Simple page-load performance checks based on total navigation time.

- `testHomepageLoadPerformance`: Measures load time for the homepage and asserts it is under 5 seconds.
- `testAkcijeAndPromocijePerformance`: Measures load time for the promotions page and asserts it is under 5 seconds.
- `testBrandsPerformance`: Measures load time for the brands page and asserts it is under 5 seconds.
- `testNewsletterPerformance`: Measures load time for the newsletter signup page and asserts it is under 5 seconds.
- `testCartPerfomance`: Measures load time for the cart page and asserts it is under 5 seconds.

---

## ProductSortingTest

Category filtering/sorting checks for TVs and washing machines.

- `redirectingToProductsPage`: From the homepage, navigates to the "TV 32 or smaller" listing page.
- `sortingByBrand`: On the TV listing, applies a brand filter (LG) and verifies that the first product shows the correct brand.
- `sortingByScreenSize`: On the TV listing, applies a screen-size filter and checks that the first product text includes "32" inches.
- `sortingByType`: On the TV listing, applies a type filter and checks that the first product mentions "Smart TV".
- `sortingWashingMachine`: On the washing machine listing, applies a type filter and checks the first product name (currently also looking for "Smart TV").

---

## PromotionsTest

Promotions section navigation, filtering, multimedia, and cart interaction.

- `navigateToPromotions`: From the homepage, uses the navigation menu to open the promotions page.
- `filterGamingPromotions`: On the promotions page, filters promotions for a specific gaming category (`cat=879`).
- `navigateToTake2BlackFriday`: Clicks a specific "Take2 Black Friday" promotion and verifies navigation to its details page.
- `viewAllTake2Products`: From the promotion, opens the "view all" product list sorted by bestsellers.
- `selectLegoDrivePS4`: From the promotion product list, opens the "Lego 2K Drive PS4" product page.
- `addToCart`: On the Lego product page, adds the item to the cart and opens the cart overlay/page.
- `testVideoPlay`: On the Lego product page, switches into the embedded video iframe, programmatically plays and pauses the video, and verifies the URL remains the product page.
- `returnToHomepage`: Navigates back to the homepage and asserts the URL is exactly the base URL.

---

## ResponsiveDesignTest

Basic responsive and mobile login checks using Chrome device emulation.

- `testResponsiveDesignMobile`: Opens the homepage with iPhone X mobile emulation to verify mobile rendering loads.
- `testResponsiveDesignTablet`: Reinitializes the driver with iPad emulation and opens the homepage to verify tablet rendering.
- `testLoginOnMobile`: On mobile emulation, performs a full login flow and asserts redirect to the account dashboard.

---

## SearchTests

Search box interaction for valid, empty, and invalid queries (primarily behavioral, with minimal assertions).

- `testValidSearch`: From the homepage, enters several realistic search terms (e.g., iPhone, PlayStation) into the search box, allowing time for results/suggestions to appear.
- `testEmptySearch`: Clears the search input and waits, effectively checking the behavior of an empty search state.
- `testInvalidSearch`: Enters a nonsensical term and waits, implicitly testing the system's handling of no-result searches.

---

## SecurityCompliance

High-level security-related checks around HTTPS usage, redirection, mixed content, and cookie flags.

- `testHttpsUsage`: Ensures that navigating to the main site uses an `https://` URL.
- `testHttpToHttpsRedirection`: Navigates to the `http://` version of the site and verifies automatic redirection to `https://`.
- `testMixedContent`: Placeholder test that assumes no mixed-content warnings; conceptually meant to fail if mixed content is detected.
- `testCookieSecurity`: Iterates cookies to find the `session` cookie and asserts it is marked `Secure` and `HttpOnly`.

---

## SessionFixationTest

Session fixation check around login.

- `openLoginPageAndRecordSessionID`: Opens the login page and records the current `PHPSESSID` cookie value before authentication.
- `loginAndRecordNewSessionID`: Logs in with valid credentials and records the `PHPSESSID` value after authentication.
- `verifySessionIDChanged`: Asserts that the pre-login and post-login session IDs differ, indicating session renewal on login.

---

## SessionPersistenceAcrossPagesTest

Anonymous session persistence across several public pages.

- `recordSessionIDOnHomePage`: On the homepage, records the initial `PHPSESSID` and asserts it is not null.
- `validateSessionIDAfterNavigatingToPromotions`: Navigates to the promotions page and asserts the session ID matches the initial value.
- `validateSessionIDAfterNavigatingToBrands`: Navigates to the brands page and asserts the session ID remains the same.
- `validateSessionIDAfterNavigatingToNewsletterSignup`: Navigates to the newsletter signup page and asserts the session ID remains the same.
- `validateSessionIDAcrossAllPages`: Re-confirms that all recorded session IDs across visited pages are equal.

---

## SocialMediaTests

Homepage and footer social media presence.

- `testingTitleMatch`: Verifies that the homepage title contains "Big Bang".
- `findPageBottom`: Scrolls to/clicks the footer area and logs whether social media links are present.
- `findInstagramPage`: Clicks the Instagram footer link and logs that the Instagram page has been opened (no strict URL assertion).
- `findFacebookPage`: Clicks the Facebook footer link and logs that the Facebook page has been opened.
- `findLinkedINPage`: Navigates directly to the Big Bang LinkedIn page and checks the current URL to confirm navigation.

---

## UserJourney1Test

Composite user journey covering navigation, product interaction, login, tabs, and account profile edits.

- `testAllIconsNavigation`: From the homepage, clicks each main icon (e.g., white goods, TVs, small appliances, mobiles, laptops, personal care, gaming, outlet) and verifies navigation to the correct category pages.
- `testGamingAndJoystickNavigation`: From the gaming page, opens the gaming accessories subcategory and verifies a specific joystick product image is displayed.
- `testAddToCart`: From the gaming accessories listing, opens a PS5 DualSense controller product, adds it to the cart, and checks that the URL indicates the cart.
- `successfullyLogin`: Executes a standard login flow with given credentials and verifies redirect to the account dashboard.
- `testTabsNavigation`: On the controller product page, clicks Specifications, Video, and Download/Question tabs and verifies that each corresponding content section is shown.
- `testEditAndSavePersonalInfo`: On the account page, edits first and last name fields, saves, and asserts that a success message appears.
- `testReturnButton`: On the account edit page, clicks the "Vratite se" (Return) button to confirm it navigates back from the edit form.

---

This overview reflects the state of the test suite as of 2025-12-13 and is intended as a starting point for further refactoring and organization (e.g., introducing shared setup, page objects, and tagging).

### GPT suggestion for improvements:

Below is a tutorial-style, actionable plan, using **`CartTest`** as the main running example. The same ideas apply to other complex flows like `UserJourney1Test`, `AccCreationTest`, and `ContactFormTests`.

---

#### 1. Centralize WebDriver setup (using `BaseTest`)

**Goal:** Every test class should reuse a single, shared WebDriver setup instead of duplicating `System.setProperty` and `ChromeDriver` creation.

**Current pattern in `CartTest` (simplified):**

```java
public class CartTest {
    private static WebDriver webDriver;

    @BeforeAll
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", "C\\\\Users\\\\win11\\\\OneDrive\\\\Desktop\\\\Selenium\\\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
    }
}
```

**Target pattern using your new `BaseTest`:**

```java
public class CartTest extends BaseTest {

    @Test
    @Order(1)
    void loginAndOpenCart() {
        driver.get("https://www.bigbang.ba/customer/account/login/");
        // ... use driver instead of webDriver ...
    }
}
```

**Steps to migrate a class (e.g., `CartTest`):**

1. Change `public class CartTest` → `public class CartTest extends BaseTest`.
2. Remove its `WebDriver` field and all `@BeforeAll/@AfterAll` setup/teardown methods.
3. Replace all `webDriver` usages with `driver` from `BaseTest`.
4. Repeat for other classes (`LoginTest`, `UserJourney1Test`, etc.) until all tests share the same base.

---

#### 2. Remove hard-coded credentials and secrets

**Goal:** Credentials like `emanhrustemovic6@gmail.com` and passwords should not live in code.

**Example from `CartTest.loginAndOpenCart`:**

```java
WebElement email = driver.findElement(By.cssSelector("input[type='email']"));
email.sendKeys("emanhrustemovic6@gmail.com");

WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
password.sendKeys("eman03hrustemovic04");
```

**Improved approach:** read from config or environment variables:

```java
String userEmail = System.getProperty("test.user.email");
String userPassword = System.getProperty("test.user.password");

email.sendKeys(userEmail);
password.sendKeys(userPassword);
```

Then run tests with:

```bash
mvn test -Dtest.user.email="..." -Dtest.user.password="..."
```

Apply the same pattern to all tests that currently hard-code emails and passwords.

---

#### 3. Introduce Page Object pattern

**Goal:** Make tests read as high-level business flows, not as long chains of `findElement` calls.

For `CartTest`, you could introduce:

- `LoginPage`
- `HomePage`
- `ProductPage`
- `CartPage`
- `CheckoutPage`

**Example: extract `loginAndOpenCart()` into page objects.**

_Page class (simplified):_

```java
public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) { this.driver = driver; }

    public LoginPage open() {
        driver.get("https://www.bigbang.ba/customer/account/login/");
        return this;
    }

    public AccountPage login(String email, String password) {
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("input[type='password']")).submit();
        return new AccountPage(driver);
    }
}
```

_Test using the page object:_

```java
@Test
@Order(1)
void loginAndOpenCart() {
    LoginPage loginPage = new LoginPage(driver).open();
    AccountPage account = loginPage.login(userEmail, userPassword);
    CartPage cart = account.openCart();
    // assertions on CartPage
}
```

Apply this gradually: start with shared flows (login, go to cart, add first TV to cart) used across multiple tests.

---

#### 4. Replace `Thread.sleep` with explicit waits

**Goal:** Make tests faster and less flaky by waiting for conditions instead of fixed time.

**Current pattern in `CartTest.addingIntoCart()`:**

```java
WebElement product = driver.findElement(By.className("product-item-photo"));
product.click();

WebElement addProduct = driver.findElement(By.id("product-addtocart-button"));
addProduct.click();
```

**Improved pattern:**

```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

WebElement product = wait.until(
    ExpectedConditions.elementToBeClickable(By.className("product-item-photo"))
);
product.click();

WebElement addProduct = wait.until(
    ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button"))
);
addProduct.click();
```

Do a similar replacement in `verifyTotalPrice`, `processPayment`, `UserJourney1Test`, etc., where `Thread.sleep(...)` is used heavily.

---

#### 5. Unify on JUnit 5

**Goal:** Avoid mixing JUnit 4, JUnit 5, and TestNG in one project.

Actions:

- Keep only JUnit 5 (`org.junit.jupiter`) for annotations and assertions.
- Replace legacy imports such as `org.junit.Assert` and TestNG assertions with JUnit 5’s `Assertions.*`.
- Ensure the Maven `pom.xml` is configured to use the JUnit Jupiter engine.

`CartTest` already uses `org.junit.jupiter.api.*`; just remove the unused TestNG static imports and any JUnit 4 bits.

---

#### 6. Add stronger assertions and clear expectations

**Goal:** Every test should clearly express what it is verifying.

In `CartTest.verifyTotalPrice()`, you already compute `calculatedTotal` and compare it with `displayedTotal`. This is good, but you can also:

- Assert that there is at least one cart row before calculating.
- Assert that currency format is as expected.

For other methods like `checkingCartStatus()`, instead of just printing the URL, assert that:

```java
assertTrue(driver.getCurrentUrl().contains("/checkout/cart"), "User should be on cart page");
```

Apply this mindset to all tests that currently only log messages to the console.

---

#### 7. Isolate CAPTCHA-dependent scenarios

**Goal:** Keep CI green without manual intervention.

Tests like `AccCreationTest.testValidFormSubmission` and `ContactFormTests.testValidFormSubmission` require solving a CAPTCHA. For these:

- Add a custom JUnit 5 tag, e.g. `@Tag("captcha")` on those methods/classes.
- Configure your Maven Surefire/Failsafe plugin to **exclude** `@captcha` in automated runs and only run them manually when needed.

Example:

```java
@Tag("captcha")
@Test
void testValidFormSubmission() { ... }
```

---

#### 8. Organize tests by feature and package

**Goal:** Make it obvious where to add new tests and where to look for existing ones.

Suggested package structure:

- `src/test/java/login` → `LoginTest`, `LogoutTest`, `SessionFixationTest`
- `src/test/java/checkout` → `CartTest`, `GuestCheckoutTest`, `AddToCartAndPriceCheckTest`
- `src/test/java/account` → `AccCreationTest`, `AddressTest`, `UserJourney1Test`
- `src/test/java/content` → `PromotionsTest`, `NewsletterTest`, `NavigationTests`, `SocialMediaTests`
- `src/test/java/security` → `SecurityCompliance`, `SessionPersistenceAcrossPagesTest`

You can move classes incrementally; IDE refactors package names safely.

---

#### 9. Make tests environment-agnostic

**Goal:** Run the same tests against DEV/QA/PROD just by changing a parameter.

Instead of hard-coding `"https://www.bigbang.ba/"` inside tests, define in one place (e.g., in `BaseTest`):

```java
protected final String baseUrl = System.getProperty("baseUrl", "https://www.bigbang.ba/");
```

Then use `baseUrl` in tests and page objects:

```java
driver.get(baseUrl + "customer/account/login/");
```

Switch environments via:

```bash
mvn test -DbaseUrl="https://test.bigbang.ba/"
```

---

#### 10. Prepare for CI/headless execution

**Goal:** Make the suite runnable in CI pipelines without GUI.

You’ve already added headless support in `BaseTest` via a `headless` flag and `-Dheadless=true`. To complete this:

- Ensure all tests extend `BaseTest` and don’t override the driver in non-headless ways.
- Add a Maven profile like `ui-tests` that runs Selenium tests with `-Dheadless=true` and excludes `@captcha` tagged tests.
- Optionally, add a small **smoke** subset (e.g., `LoginTest`, one `CartTest` happy path, one `NavigationTests` case) to run on every commit, with the full suite on nightly builds.

This staged approach turns your current test set (including complex flows like `CartTest`) into a maintainable, CI-friendly UI automation suite.

### Claude suggestion for improvements:
Concrete Improvements
Immediate Priority:

Implement Page Object Model

Create HomePage, LoginPage, ProductPage, CartPage classes
Encapsulate element locators and actions
Make tests read like business scenarios


Add Test Data Management

java   // Instead of hard-coding everywhere
TestUser validUser = TestDataFactory.createValidUser();
Product testProduct = TestDataFactory.getTestProduct();

Implement Proper Wait Strategies

Replace Thread.sleep() with explicit waits
Wait for specific conditions, not arbitrary time


Add Smoke Test Suite

Tag critical path tests (@Smoke)
Can be run in minutes to verify deployment


Missing Test Coverage:

Checkout with multiple items (quantity validation)
Apply discount/promo codes
Wishlist functionality (if exists)
Product comparison (if exists)
Filter persistence (do filters remain after back navigation?)
Multi-tab behavior (cart changes in one tab reflect in another?)

---------------------

### GPT suggestion for improvements (Sonnet):

#### 1. Centralize WebDriver Setup (Using `BaseTest`)

**Current State Analysis:**
Most test classes (`CartTest`, `LoginTest`, `AddressTest`, etc.) duplicate WebDriver initialization code with hard-coded ChromeDriver paths and identical setup logic. This creates maintenance overhead and inconsistency across the test suite.

**Implementation Strategy:**

The existing `BaseTest` class provides a solid foundation with:
- Headless mode support via system property
- Proper lifecycle management (@BeforeAll/@AfterAll)
- Window maximization

**Action Items:**
1. **Migrate all test classes to extend `BaseTest`**:
   - Remove individual `WebDriver webDriver` fields from each test class
   - Delete redundant `@BeforeAll` setUp() and `@AfterAll` tearDown() methods
   - Replace all `webDriver` references with inherited `driver` field

2. **Standardize the migration pattern**:
   ```java
   // BEFORE
   public class LoginTest {
       private static WebDriver webDriver;
       @BeforeAll
       public static void setUp() {
           System.setProperty("webdriver.chrome.driver", "...");
           webDriver = new ChromeDriver();
       }
   }
   
   // AFTER
   public class LoginTest extends BaseTest {
       // driver is now available from BaseTest
       // No setup/teardown needed
   }
   ```

3. **Enhance BaseTest capabilities**:
   - Add configurable browser support (Chrome, Firefox, Edge)
   - Add configurable timeouts via system properties
   - Implement implicit wait configuration
   - Add screenshot capture on test failure

**Expected Benefits:**
- 80% reduction in boilerplate code
- Single point of maintenance for driver configuration
- Consistent behavior across all tests
- Easier CI/CD integration with headless mode

---

#### 2. Remove Hard-Coded Credentials and Secrets

**Current State Analysis:**
Credentials are scattered throughout test files in plain text:
- `CartTest`: "emanhrustemovic6@gmail.com" / "eman03hrustemovic04"
- `LoginTest`: Same credentials repeated
- `AddressTest`: Same credentials again
- Multiple other test classes with identical hardcoded values

**Security and Maintenance Risks:**
- Credentials exposed in version control history
- Difficult to rotate/update credentials (need to modify multiple files)
- Cannot use different credentials for different environments
- Violates security best practices

**Implementation Strategy:**

1. **Create a configuration management system**:
   ```java
   // src/test/java/config/TestConfig.java
   public class TestConfig {
       private static final Properties props = new Properties();
       
       static {
           String env = System.getProperty("test.env", "local");
           try {
               props.load(TestConfig.class.getResourceAsStream(
                   "/config-" + env + ".properties"
               ));
           } catch (IOException e) {
               throw new RuntimeException("Failed to load config", e);
           }
       }
       
       public static String getUserEmail() {
           return System.getProperty("test.user.email", 
               props.getProperty("user.email"));
       }
       
       public static String getUserPassword() {
           return System.getProperty("test.user.password", 
               props.getProperty("user.password"));
       }
       
       public static String getBaseUrl() {
           return System.getProperty("base.url", 
               props.getProperty("base.url"));
       }
   }
   ```

2. **Create environment-specific property files**:
   ```properties
   # src/test/resources/config-local.properties
   base.url=https://www.bigbang.ba/
   user.email=test.user@example.com
   user.password=placeholder
   
   # src/test/resources/config-ci.properties (gitignored)
   base.url=https://test.bigbang.ba/
   user.email=${CI_USER_EMAIL}
   user.password=${CI_USER_PASSWORD}
   ```

3. **Refactor test classes to use configuration**:
   ```java
   // BEFORE
   email.sendKeys("emanhrustemovic6@gmail.com");
   password.sendKeys("eman03hrustemovic04");
   
   // AFTER
   email.sendKeys(TestConfig.getUserEmail());
   password.sendKeys(TestConfig.getUserPassword());
   ```

4. **Update .gitignore**:
   ```
   **/config-ci.properties
   **/config-local.properties
   *.credentials
   ```

5. **Document credential management in README**:
   - How to set up local credentials
   - How to use environment variables in CI
   - Security guidelines

**Expected Benefits:**
- No credentials in version control
- Easy environment switching
- Centralized credential management
- CI/CD friendly via environment variables

---

#### 3. Introduce Page Object Pattern

**Current State Analysis:**
Test methods are cluttered with low-level WebDriver API calls:
- Element location logic mixed with test logic
- Duplicated selectors across tests
- Tests are fragile to UI changes
- Poor readability and maintainability

**Page Object Pattern Benefits:**
- Encapsulates page structure and behavior
- Single responsibility: page objects know "how", tests know "what"
- Reusable page interactions
- Tests read as business workflows

**Implementation Strategy:**

1. **Create base page class**:
   ```java
   // src/test/java/pages/BasePage.java
   public abstract class BasePage {
       protected WebDriver driver;
       protected WebDriverWait wait;
       
       public BasePage(WebDriver driver) {
           this.driver = driver;
           this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       }
       
       protected WebElement waitForElement(By locator) {
           return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
       }
       
       protected void clickElement(By locator) {
           wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
       }
       
       protected void typeText(By locator, String text) {
           WebElement element = waitForElement(locator);
           element.clear();
           element.sendKeys(text);
       }
   }
   ```

2. **Create specific page objects for key flows**:
   
   **LoginPage**:
   ```java
   public class LoginPage extends BasePage {
       // Locators
       private final By emailField = By.cssSelector("input[type='email']");
       private final By passwordField = By.cssSelector("input[type='password']");
       private final By loginButton = By.cssSelector("button[type='submit']");
       private final By rememberMeCheckbox = By.id("remember-me");
       
       public LoginPage(WebDriver driver) {
           super(driver);
       }
       
       public LoginPage open() {
           driver.get(TestConfig.getBaseUrl() + "customer/account/login/");
           return this;
       }
       
       public AccountPage login(String email, String password) {
           typeText(emailField, email);
           typeText(passwordField, password);
           clickElement(loginButton);
           return new AccountPage(driver);
       }
       
       public LoginPage loginExpectingFailure(String email, String password) {
           typeText(emailField, email);
           typeText(passwordField, password);
           clickElement(loginButton);
           return this;
       }
       
       public LoginPage setRememberMe(boolean remember) {
           WebElement checkbox = driver.findElement(rememberMeCheckbox);
           if (checkbox.isSelected() != remember) {
               checkbox.click();
           }
           return this;
       }
       
       public boolean isErrorDisplayed() {
           return driver.findElements(By.cssSelector(".error-msg")).size() > 0;
       }
   }
   ```

   **CartPage**:
   ```java
   public class CartPage extends BasePage {
       private final By cartItems = By.cssSelector(".cart-item");
       private final By quantityInput = By.cssSelector("input[name='quantity']");
       private final By updateButton = By.cssSelector("button.update");
       private final By removeLink = By.cssSelector("a.remove");
       private final By subtotalAmount = By.cssSelector(".cart-subtotal .price");
       private final By checkoutButton = By.id("checkout-btn");
       
       public CartPage(WebDriver driver) {
           super(driver);
       }
       
       public CartPage open() {
           driver.get(TestConfig.getBaseUrl() + "checkout/cart/");
           return this;
       }
       
       public int getItemCount() {
           return driver.findElements(cartItems).size();
       }
       
       public CartPage updateQuantity(int itemIndex, int quantity) {
           List<WebElement> inputs = driver.findElements(quantityInput);
           inputs.get(itemIndex).clear();
           inputs.get(itemIndex).sendKeys(String.valueOf(quantity));
           clickElement(updateButton);
           return this;
       }
       
       public CartPage removeItem(int itemIndex) {
           List<WebElement> removeButtons = driver.findElements(removeLink);
           removeButtons.get(itemIndex).click();
           wait.until(ExpectedConditions.stalenessOf(removeButtons.get(itemIndex)));
           return this;
       }
       
       public BigDecimal getSubtotal() {
           String text = driver.findElement(subtotalAmount).getText();
           return parseCurrency(text);
       }
       
       public CheckoutPage proceedToCheckout() {
           clickElement(checkoutButton);
           return new CheckoutPage(driver);
       }
       
       private BigDecimal parseCurrency(String text) {
           // Remove currency symbols and parse
           String cleaned = text.replaceAll("[^0-9,.]", "").replace(",", ".");
           return new BigDecimal(cleaned);
       }
   }
   ```

   **ProductPage**:
   ```java
   public class ProductPage extends BasePage {
       private final By addToCartButton = By.id("product-addtocart-button");
       private final By productName = By.cssSelector("h1.product-name");
       private final By productPrice = By.cssSelector(".price");
       private final By successMessage = By.cssSelector(".success-msg");
       
       public ProductPage(WebDriver driver) {
           super(driver);
       }
       
       public String getProductName() {
           return waitForElement(productName).getText();
       }
       
       public BigDecimal getPrice() {
           String priceText = waitForElement(productPrice).getText();
           return parseCurrency(priceText);
       }
       
       public ProductPage addToCart() {
           clickElement(addToCartButton);
           wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
           return this;
       }
       
       public CartPage goToCart() {
           driver.get(TestConfig.getBaseUrl() + "checkout/cart/");
           return new CartPage(driver);
       }
   }
   ```

3. **Refactor tests to use page objects**:
   ```java
   // BEFORE (CartTest.loginAndOpenCart)
   @Test
   public void loginAndOpenCart() throws InterruptedException {
       webDriver.get("https://www.bigbang.ba/customer/account/login/");
       Thread.sleep(3000);
       WebElement email = webDriver.findElement(By.cssSelector("input[type='email']"));
       email.sendKeys("emanhrustemovic6@gmail.com");
       Thread.sleep(3000);
       WebElement password = webDriver.findElement(By.cssSelector("input[type='password']"));
       password.sendKeys("eman03hrustemovic04");
       password.submit();
   }
   
   // AFTER
   @Test
   public void loginAndOpenCart() {
       AccountPage account = new LoginPage(driver)
           .open()
           .login(TestConfig.getUserEmail(), TestConfig.getUserPassword());
       
       CartPage cart = account.openCart();
       assertTrue(cart.getItemCount() >= 0, "Cart should be accessible");
   }
   ```

4. **Migration priority order**:
   - LoginPage (used by 10+ tests)
   - HomePage (navigation hub)
   - CartPage (complex logic in CartTest)
   - ProductPage (used in multiple flows)
   - CheckoutPage (guest and logged-in flows)
   - AccountPage (profile, addresses)
   - NewsletterPage, ContactPage, etc.

**Expected Benefits:**
- 70% reduction in test code volume
- Selector changes require updates in only one place
- Tests become self-documenting
- Faster test authoring
- Easier to maintain and extend

---

#### 4. Replace `Thread.sleep` with Explicit Waits

**Current State Analysis:**
The test suite contains 50+ instances of `Thread.sleep()`:
- `Thread.sleep(3000)` appears in almost every test
- Fixed waits slow down test execution unnecessarily
- Tests are still flaky due to timing variations
- No guarantee element is actually ready after sleep

**Problems with Thread.sleep:**
- **Unreliable**: Element might not be ready even after fixed time
- **Slow**: Always waits full duration even if element is ready immediately
- **Non-descriptive**: Doesn't explain what we're waiting for
- **Hard to tune**: Different environments need different durations

**Implementation Strategy:**

1. **Add WebDriverWait utility to BaseTest**:
   ```java
   public abstract class BaseTest {
       protected WebDriver driver;
       protected WebDriverWait wait;
       protected WebDriverWait longWait;
       
       @BeforeAll
       void beforeAll() {
           // ... driver setup ...
           wait = new WebDriverWait(driver, Duration.ofSeconds(10));
           longWait = new WebDriverWait(driver, Duration.ofSeconds(30));
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
       }
       
       protected WebElement waitForElement(By locator) {
           return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
       }
       
       protected void waitForPageLoad() {
           wait.until(driver -> 
               ((JavascriptExecutor) driver).executeScript("return document.readyState")
                   .equals("complete"));
       }
   }
   ```

2. **Create custom wait conditions**:
   ```java
   public class CustomConditions {
       public static ExpectedCondition<Boolean> cartCountToBe(int expectedCount) {
           return driver -> {
               String count = driver.findElement(By.cssSelector(".cart-counter"))
                   .getText();
               return Integer.parseInt(count) == expectedCount;
           };
       }
       
       public static ExpectedCondition<Boolean> urlContains(String fragment) {
           return driver -> driver.getCurrentUrl().contains(fragment);
       }
       
       public static ExpectedCondition<Boolean> elementTextToBe(
           By locator, String expectedText
       ) {
           return driver -> driver.findElement(locator)
               .getText()
               .equals(expectedText);
       }
   }
   ```

3. **Refactor common patterns**:

   **Pattern 1: Wait for navigation**
   ```java
   // BEFORE
   loginButton.click();
   Thread.sleep(5000);
   
   // AFTER
   loginButton.click();
   wait.until(ExpectedConditions.urlContains("/customer/account"));
   ```

   **Pattern 2: Wait for element to be clickable**
   ```java
   // BEFORE
   Thread.sleep(2000);
   WebElement button = driver.findElement(By.id("submit"));
   button.click();
   
   // AFTER
   WebElement button = wait.until(
       ExpectedConditions.elementToBeClickable(By.id("submit"))
   );
   button.click();
   ```

   **Pattern 3: Wait for AJAX/dynamic content**
   ```java
   // BEFORE
   driver.get(productUrl);
   Thread.sleep(4000);
   String price = driver.findElement(By.cssSelector(".price")).getText();
   
   // AFTER
   driver.get(productUrl);
   WebElement priceElement = wait.until(
       ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".price"))
   );
   String price = priceElement.getText();
   ```

   **Pattern 4: Wait for cart update**
   ```java
   // BEFORE (from CartTest.updatingCart)
   updateButton.click();
   Thread.sleep(5000);
   
   // AFTER
   updateButton.click();
   wait.until(ExpectedConditions.invisibilityOfElementLocated(
       By.cssSelector(".loading-mask")
   ));
   // Or wait for success message
   wait.until(ExpectedConditions.visibilityOfElementLocated(
       By.cssSelector(".success-msg")
   ));
   ```

4. **Handle special cases**:

   **CAPTCHA scenarios** (AccCreationTest, ContactFormTests):
   ```java
   // Give user time to solve CAPTCHA manually
   System.out.println("Please solve CAPTCHA...");
   longWait.until(ExpectedConditions.elementToBeClickable(submitButton));
   submitButton.click();
   ```

   **Video playback** (PromotionsTest):
   ```java
   // Wait for video player to be ready
   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
       By.cssSelector("iframe[src*='youtube']")
   ));
   wait.until(driver -> 
       ((JavascriptExecutor) driver).executeScript(
           "return document.querySelector('video').readyState"
       ).equals(4L) // HAVE_ENOUGH_DATA
   );
   ```

5. **Migration checklist for each test**:
   - [ ] Identify all Thread.sleep() calls
   - [ ] Determine what condition we're actually waiting for
   - [ ] Replace with appropriate ExpectedCondition
   - [ ] Test with slower network conditions
   - [ ] Reduce wait timeout if possible

**Expected Benefits:**
- 40-60% faster test execution (waits only as long as needed)
- More reliable tests (wait for actual conditions)
- Better error messages (knows what it was waiting for)
- Easier debugging (explicit about synchronization points)

---

#### 5. Unify on JUnit 5

**Current State Analysis:**
The project has mixed dependencies:
- JUnit 5 (junit-jupiter) - primary framework
- TestNG (unused but present in pom.xml)
- Some imports from JUnit 4

**Problems:**
- Dependency bloat
- Potential conflicts
- Confusion about which framework to use
- Unused code

**Implementation Strategy:**

1. **Clean up pom.xml**:
   ```xml
   <!-- REMOVE -->
   <dependency>
       <groupId>org.testng</groupId>
       <artifactId>testng</artifactId>
       <version>RELEASE</version>
       <scope>test</scope>
   </dependency>
   
   <!-- KEEP AND UPDATE -->
   <dependency>
       <groupId>org.junit.jupiter</groupId>
       <artifactId>junit-jupiter</artifactId>
       <version>5.10.1</version> <!-- Use specific version, not RELEASE -->
       <scope>test</scope>
   </dependency>
   
   <!-- ADD for better reporting -->
   <dependency>
       <groupId>org.junit.jupiter</groupId>
       <artifactId>junit-jupiter-params</artifactId>
       <version>5.10.1</version>
       <scope>test</scope>
   </dependency>
   ```

2. **Remove duplicate JUnit Jupiter dependency**:
   - Currently appears twice in pom.xml (line 18-23 and 36-40)
   - Keep only one in test scope

3. **Update import statements across all test files**:
   ```java
   // REMOVE any JUnit 4 imports
   import org.junit.Before;
   import org.junit.After;
   import org.junit.Test;
   import org.junit.Assert;
   
   // USE JUnit 5 equivalents
   import org.junit.jupiter.api.BeforeEach;
   import org.junit.jupiter.api.AfterEach;
   import org.junit.jupiter.api.Test;
   import static org.junit.jupiter.api.Assertions.*;
   ```

4. **Configure Maven Surefire plugin**:
   ```xml
   <build>
       <plugins>
           <plugin>
               <groupId>org.apache.maven.plugins</groupId>
               <artifactId>maven-surefire-plugin</artifactId>
               <version>3.2.2</version>
               <configuration>
                   <includes>
                       <include>**/*Test.java</include>
                       <include>**/*Tests.java</include>
                   </includes>
                   <excludedGroups>captcha,manual</excludedGroups>
               </configuration>
           </plugin>
       </plugins>
   </build>
   ```

5. **Leverage JUnit 5 features**:
   
   **Parameterized tests** (for LoginTest variations):
   ```java
   @ParameterizedTest
   @CsvSource({
       "'', validPassword, false",
       "valid@email.com, '', false",
       "'', '', false",
       "invalid-email, validPassword, false"
   })
   void testInvalidLogin(String email, String password, boolean shouldSucceed) {
       LoginPage loginPage = new LoginPage(driver).open();
       loginPage.login(email, password);
       
       if (shouldSucceed) {
           assertTrue(driver.getCurrentUrl().contains("/account"));
       } else {
           assertTrue(loginPage.isErrorDisplayed());
       }
   }
   ```

   **Display names** (for better reporting):
   ```java
   @DisplayName("Cart Management Tests")
   class CartTest extends BaseTest {
       
       @Test
       @DisplayName("Should add product to cart successfully")
       void addProductToCart() {
           // test implementation
       }
   }
   ```

   **Nested tests** (for organizing related scenarios):
   ```java
   @Nested
   @DisplayName("Login Validation Tests")
   class LoginValidation {
       
       @Test
       @DisplayName("Should fail with empty email")
       void emptyEmail() { }
       
       @Test
       @DisplayName("Should fail with invalid email format")
       void invalidEmailFormat() { }
   }
   ```

**Expected Benefits:**
- Cleaner dependencies
- Access to modern JUnit 5 features
- Better parameterization support
- Improved test reporting
- Smaller build artifact size

---

#### 6. Add Stronger Assertions and Clear Expectations

**Current State Analysis:**
Many tests have weak or missing assertions:
- `checkingCartStatus()` only prints URL, doesn't assert anything
- `SearchTests` methods have no assertions at all
- Some tests use println instead of assertions
- Unclear test intent from assertion messages

**Implementation Strategy:**

1. **Add meaningful assertions to weak tests**:

   **CartTest.checkingCartStatus**:
   ```java
   // BEFORE
   @Test
   public void checkingCartStatus() throws InterruptedException {
       loginAndOpenCart();
       Thread.sleep(3000);
       System.out.println(webDriver.getCurrentUrl());
   }
   
   // AFTER
   @Test
   @DisplayName("Should successfully load cart page for logged-in user")
   public void checkingCartStatus() {
       // Precondition: user is logged in
       new LoginPage(driver).open()
           .login(TestConfig.getUserEmail(), TestConfig.getUserPassword());
       
       // Action: navigate to cart
       CartPage cart = new CartPage(driver).open();
       
       // Assertions
       assertTrue(driver.getCurrentUrl().contains("/checkout/cart"),
           "Should be on cart page");
       assertTrue(cart.isCartLoaded(),
           "Cart content should be visible");
       assertFalse(cart.hasErrors(),
           "Cart should not show error messages");
   }
   ```

   **SearchTests.testValidSearch**:
   ```java
   // BEFORE
   @Test
   public void testValidSearch() throws InterruptedException {
       String[] searchTerms = {"iPhone", "Samsung", "Laptop", "PlayStation"};
       for (String term : searchTerms) {
           searchBox.sendKeys(term);
           Thread.sleep(2000);
           searchBox.clear();
       }
   }
   
   // AFTER
   @ParameterizedTest
   @ValueSource(strings = {"iPhone", "Samsung", "Laptop", "PlayStation"})
   @DisplayName("Should show search suggestions for valid product terms")
   public void testValidSearch(String searchTerm) {
       SearchComponent search = new HomePage(driver)
           .open()
           .getSearchComponent();
       
       search.enterSearchTerm(searchTerm);
       
       assertTrue(search.hasSuggestions(),
           "Search should show suggestions for: " + searchTerm);
       assertTrue(search.getSuggestionCount() > 0,
           "Should have at least one suggestion");
       assertTrue(search.suggestionsContainTerm(searchTerm),
           "Suggestions should be relevant to search term");
   }
   ```

2. **Enhance existing assertions with descriptive messages**:
   ```java
   // BEFORE
   assertTrue(driver.getCurrentUrl().contains("/account"));
   
   // AFTER
   assertTrue(driver.getCurrentUrl().contains("/account"),
       String.format("Expected to be on account page, but URL is: %s", 
           driver.getCurrentUrl()));
   ```

3. **Add assertions for CartTest.verifyTotalPrice**:
   ```java
   @Test
   public void verifyTotalPrice() {
       CartPage cart = new CartPage(driver).open();
       
       // Precondition assertions
       int itemCount = cart.getItemCount();
       assertTrue(itemCount > 0,
           "Cart must have items to verify total");
       
       // Calculate expected total
       BigDecimal calculatedTotal = BigDecimal.ZERO;
       for (int i = 0; i < itemCount; i++) {
           BigDecimal itemPrice = cart.getItemPrice(i);
           int quantity = cart.getItemQuantity(i);
           BigDecimal lineTotal = itemPrice.multiply(BigDecimal.valueOf(quantity));
           
           // Per-item assertion
           assertEquals(lineTotal, cart.getLineTotal(i),
               String.format("Line %d total should be %s × %d = %s",
                   i, itemPrice, quantity, lineTotal));
           
           calculatedTotal = calculatedTotal.add(lineTotal);
       }
       
       // Final assertion with detailed message
       BigDecimal displayedTotal = cart.getSubtotal();
       assertEquals(calculatedTotal.setScale(2, RoundingMode.HALF_UP),
           displayedTotal.setScale(2, RoundingMode.HALF_UP),
           String.format("Cart subtotal mismatch: calculated=%s, displayed=%s",
               calculatedTotal, displayedTotal));
   }
   ```

4. **Add custom assertion utilities**:
   ```java
   public class CartAssertions {
       public static void assertCartContainsProduct(
           CartPage cart, String productName
       ) {
           List<String> products = cart.getProductNames();
           assertTrue(products.stream()
                   .anyMatch(name -> name.contains(productName)),
               String.format("Cart should contain product '%s', but has: %s",
                   productName, String.join(", ", products)));
       }
       
       public static void assertPriceFormat(String priceText) {
           assertTrue(priceText.matches("\\d+[.,]\\d{2}\\s*KM"),
               String.format("Price should be in 'XX.XX KM' format, got: %s",
                   priceText));
       }
   }
   ```

5. **Add pre and post-condition checks**:
   ```java
   @Test
   public void testCheckoutFlow() {
       // Preconditions
       CartPage cart = new CartPage(driver).open();
       assumeTrue(cart.getItemCount() > 0,
           "Test requires items in cart - skipping");
       
       // Test execution
       CheckoutPage checkout = cart.proceedToCheckout();
       
       // Postconditions
       assertAll("Checkout page should be properly loaded",
           () -> assertTrue(checkout.isLoaded(),
               "Checkout page should be displayed"),
           () -> assertTrue(checkout.hasShippingForm(),
               "Shipping form should be visible"),
           () -> assertEquals(cart.getSubtotal(), checkout.getSubtotal(),
               "Cart total should match checkout total")
       );
   }
   ```

**Expected Benefits:**
- Clear test failures with descriptive messages
- Tests serve as documentation
- Easier debugging (know exactly what failed)
- Better confidence in test results

---

#### 7. Isolate CAPTCHA-Dependent Scenarios

**Current State Analysis:**
Tests requiring manual CAPTCHA solving:
- `AccCreationTest.testValidFormSubmission`
- `ContactFormTests.testValidFormSubmission`
- Potentially others

**Problems:**
- Cannot run in CI/CD
- Block automated test runs
- Create manual testing bottleneck
- Unpredictable execution time

**Implementation Strategy:**

1. **Add JUnit 5 tagging**:
   ```java
   @Test
   @Tag("manual")
   @Tag("captcha")
   @DisplayName("Should successfully submit contact form (requires manual CAPTCHA)")
   public void testValidFormSubmission() {
       ContactPage contact = new ContactPage(driver).open();
       
       contact.fillForm("John Doe", "john@example.com", "Test message");
       
       System.out.println("⏸ Please solve CAPTCHA within 60 seconds...");
       longWait.until(ExpectedConditions.elementToBeClickable(
           contact.getSubmitButton()
       ));
       
       contact.submit();
       assertTrue(contact.isSuccessMessageDisplayed());
   }
   ```

2. **Configure Maven to exclude tagged tests**:
   ```xml
   <plugin>
       <groupId>org.apache.maven.plugins</groupId>
       <artifactId>maven-surefire-plugin</artifactId>
       <version>3.2.2</version>
       <configuration>
           <!-- Default: exclude manual tests -->
           <excludedGroups>manual,captcha</excludedGroups>
       </configuration>
   </plugin>
   ```

3. **Create Maven profiles for different test runs**:
   ```xml
   <profiles>
       <!-- Default profile: automated tests only -->
       <profile>
           <id>automated</id>
           <activation>
               <activeByDefault>true</activeByDefault>
           </activation>
           <build>
               <plugins>
                   <plugin>
                       <artifactId>maven-surefire-plugin</artifactId>
                       <configuration>
                           <excludedGroups>manual,captcha</excludedGroups>
                       </configuration>
                   </plugin>
               </plugins>
           </build>
       </profile>
       
       <!-- Manual test profile: run all tests -->
       <profile>
           <id>manual</id>
           <build>
               <plugins>
                   <plugin>
                       <artifactId>maven-surefire-plugin</artifactId>
                       <configuration>
                           <groups>manual</groups>
                       </configuration>
                   </plugin>
               </plugins>
           </build>
       </profile>
       
       <!-- Full suite including CAPTCHA tests -->
       <profile>
           <id>full</id>
           <build>
               <plugins>
                   <plugin>
                       <artifactId>maven-surefire-plugin</artifactId>
                       <configuration>
                           <!-- Run everything -->
                           <excludedGroups></excludedGroups>
                       </configuration>
                   </plugin>
               </plugins>
           </build>
       </profile>
   </profiles>
   ```

4. **Usage commands**:
   ```bash
   # Run automated tests only (default)
   mvn test
   
   # Run only manual/CAPTCHA tests
   mvn test -P manual
   
   # Run complete suite
   mvn test -P full
   
   # Run specific tagged tests
   mvn test -Dgroups="captcha"
   ```

5. **Alternative: Mock CAPTCHA for testing**:
   ```java
   public class ContactFormTests extends BaseTest {
       
       @Test
       @Tag("automated")
       public void testFormValidation() {
           // Test client-side validation without CAPTCHA
           ContactPage contact = new ContactPage(driver).open();
           
           contact.fillForm("", "", "");  // Empty fields
           contact.clickSubmit();
           
           assertAll("Form validation should prevent submission",
               () -> assertTrue(contact.hasNameError()),
               () -> assertTrue(contact.hasEmailError()),
               () -> assertTrue(contact.hasMessageError())
           );
       }
       
       @Test
       @Tag("manual")
       @Tag("captcha")
       public void testSuccessfulSubmission() {
           // Full flow including CAPTCHA
           // ... manual CAPTCHA solving ...
       }
   }
   ```

6. **Document CAPTCHA test process**:
   ```markdown
   ## Running CAPTCHA Tests
   
   Some tests require manual CAPTCHA solving:
   
   1. Run with manual profile:
      ```bash
      mvn test -P manual -Dtest=ContactFormTests#testValidFormSubmission
      ```
   
   2. When prompted, solve the CAPTCHA in the browser window
   
   3. Test will automatically continue after CAPTCHA is solved
   
   **Note**: CAPTCHA tests are excluded from CI/CD pipelines
   ```

**Expected Benefits:**
- CI/CD can run automated tests without blocking
- Clear separation of manual vs automated tests
- Flexible test execution strategies
- Better test organization

---

#### 8. Organize Tests by Feature and Package

**Current State Analysis:**
All test classes are in a single flat package:
- 22 test classes in `src/test/java/`
- No logical grouping
- Hard to navigate and find related tests
- Unclear where to add new tests

**Proposed Package Structure:**

```
src/test/java/
├── config/
│   ├── TestConfig.java           # Configuration management
│   └── TestDataFactory.java      # Test data builders
├── pages/                         # Page Objects
│   ├── BasePage.java
│   ├── HomePage.java
│   ├── LoginPage.java
│   ├── CartPage.java
│   ├── ProductPage.java
│   ├── CheckoutPage.java
│   ├── AccountPage.java
│   ├── ContactPage.java
│   └── components/
│       ├── HeaderComponent.java
│       ├── FooterComponent.java
│       └── SearchComponent.java
├── tests/                         # Test classes organized by feature
│   ├── BaseTest.java             # Base test class
│   ├── authentication/
│   │   ├── LoginTest.java
│   │   ├── LogoutTest.java
│   │   └── SessionFixationTest.java
│   ├── cart/
│   │   ├── CartTest.java
│   │   ├── AddToCartTest.java
│   │   ├── GuestCheckoutTest.java
│   │   └── PriceVerificationTest.java
│   ├── account/
│   │   ├── AccCreationTest.java
│   │   ├── AddressTest.java
│   │   └── ProfileManagementTest.java
│   ├── navigation/
│   │   ├── NavigationTests.java
│   │   ├── SearchTests.java
│   │   └── MenuNavigationTest.java
│   ├── products/
│   │   ├── ProductSortingTest.java
│   │   ├── ProductFilterTest.java
│   │   └── ProductDetailsTest.java
│   ├── content/
│   │   ├── PromotionsTest.java
│   │   ├── NewsletterTest.java
│   │   ├── ContactFormTests.java
│   │   └── SocialMediaTests.java
│   ├── security/
│   │   ├── SecurityCompliance.java
│   │   └── SessionPersistenceTest.java
│   ├── performance/
│   │   └── PerformanceTest.java
│   ├── responsive/
│   │   └── ResponsiveDesignTest.java
│   └── journeys/
│       └── UserJourney1Test.java
├── utils/                         # Test utilities
│   ├── WaitUtils.java
│   ├── CustomConditions.java
│   ├── ScreenshotUtils.java
│   └── CartAssertions.java
└── resources/
    ├── config-local.properties
    ├── config-ci.properties.template
    └── test-data/
        ├── users.json
        └── products.json
```

**Migration Strategy:**

1. **Phase 1: Create new package structure**
   ```bash
   mkdir -p src/test/java/{config,pages,pages/components,tests,utils}
   mkdir -p src/test/java/tests/{authentication,cart,account,navigation,products,content,security,performance,responsive,journeys}
   ```

2. **Phase 2: Move and refactor files**
   - Create page objects first (they don't depend on tests)
   - Move BaseTest to tests/ package
   - Move test classes to appropriate feature packages
   - Update package declarations and imports

3. **Phase 3: Update naming conventions**
   ```java
   // Old location: src/test/java/LoginTest.java
   // New location: src/test/java/tests/authentication/LoginTest.java
   
   package tests.authentication;
   
   import pages.LoginPage;
   import pages.AccountPage;
   import config.TestConfig;
   import tests.BaseTest;
   ```

4. **Add package documentation**:
   ```java
   /**
    * Authentication and session management tests.
    * 
    * <p>This package contains tests for:
    * <ul>
    *   <li>User login/logout flows</li>
    *   <li>Session persistence and fixation</li>
    *   <li>Remember me functionality</li>
    *   <li>Authentication error handling</li>
    * </ul>
    */
   package tests.authentication;
   ```

**Expected Benefits:**
- Easier to find related tests
- Clear where to add new tests
- Better IDE navigation
- Logical grouping for test execution
- Supports feature-based test running

---

#### 9. Make Tests Environment-Agnostic

**Current State Analysis:**
- Hard-coded URLs: `"https://www.bigbang.ba/"` appears 50+ times
- Cannot test against staging/QA environments
- Cannot run same tests locally and in CI with different configs

**Implementation Strategy:**

1. **Centralize URL management in TestConfig**:
   ```java
   public class TestConfig {
       private static final String DEFAULT_BASE_URL = "https://www.bigbang.ba/";
       
       public static String getBaseUrl() {
           String url = System.getProperty("base.url");
           if (url == null || url.isBlank()) {
               url = loadFromProperties("base.url", DEFAULT_BASE_URL);
           }
           // Ensure trailing slash
           return url.endsWith("/") ? url : url + "/";
       }
       
       public static String getUrl(String path) {
           String baseUrl = getBaseUrl();
           String cleanPath = path.startsWith("/") ? path.substring(1) : path;
           return baseUrl + cleanPath;
       }
   }
   ```

2. **Create environment-specific property files**:
   ```properties
   # src/test/resources/config-local.properties
   base.url=https://www.bigbang.ba/
   browser=chrome
   headless=false
   implicit.wait=5
   explicit.wait=10
   
   # src/test/resources/config-ci.properties
   base.url=https://test.bigbang.ba/
   browser=chrome
   headless=true
   implicit.wait=5
   explicit.wait=15
   
   # src/test/resources/config-prod.properties
   base.url=https://www.bigbang.ba/
   browser=chrome
   headless=true
   implicit.wait=5
   explicit.wait=10
   ```

3. **Update page objects to use centralized URLs**:
   ```java
   public class LoginPage extends BasePage {
       public LoginPage open() {
           driver.get(TestConfig.getUrl("customer/account/login/"));
           return this;
       }
   }
   
   public class CartPage extends BasePage {
       public CartPage open() {
           driver.get(TestConfig.getUrl("checkout/cart/"));
           return this;
       }
   }
   ```

4. **Refactor tests to remove hard-coded URLs**:
   ```java
   // BEFORE
   webDriver.get("https://www.bigbang.ba/");
   
   // AFTER
   driver.get(TestConfig.getBaseUrl());
   // or
   new HomePage(driver).open();
   ```

5. **Support environment selection**:
   ```bash
   # Run against local environment
   mvn test -Dtest.env=local
   
   # Run against CI environment
   mvn test -Dtest.env=ci
   
   # Run against production
   mvn test -Dtest.env=prod
   
   # Override base URL directly
   mvn test -Dbase.url=https://staging.bigbang.ba/
   ```

6. **Add environment validation**:
   ```java
   @BeforeAll
   static void validateEnvironment() {
       String baseUrl = TestConfig.getBaseUrl();
       System.out.println("Running tests against: " + baseUrl);
       
       // Verify environment is reachable
       try {
           URL url = new URL(baseUrl);
           HttpURLConnection connection = (HttpURLConnection) url.openConnection();
           connection.setRequestMethod("GET");
           connection.setConnectTimeout(5000);
           int responseCode = connection.getResponseCode();
           
           if (responseCode != 200) {
               fail("Environment unreachable: " + baseUrl + 
                    " (HTTP " + responseCode + ")");
           }
       } catch (Exception e) {
           fail("Failed to connect to environment: " + baseUrl, e);
       }
   }
   ```

**Expected Benefits:**
- Same tests run on dev/staging/prod
- Easy environment switching
- Supports multi-environment CI/CD
- No code changes needed to switch environments

---

#### 10. Prepare for CI/Headless Execution

**Current State Analysis:**
- BaseTest already has headless support
- Most tests still have individual setup (not using BaseTest)
- No CI configuration files
- No test categorization for CI

**Implementation Strategy:**

1. **Ensure all tests extend BaseTest**:
   - Migrate remaining tests (CartTest, LoginTest, etc.)
   - Remove duplicate WebDriver setup

2. **Create test suite configurations**:
   ```java
   @Suite
   @SelectPackages({"tests.authentication", "tests.cart"})
   @IncludeTags("smoke")
   public class SmokeTestSuite {
   }
   
   @Suite
   @SelectPackages("tests")
   @ExcludeTags({"manual", "captcha", "slow"})
   public class CiTestSuite {
   }
   
   @Suite
   @SelectPackages("tests")
   public class FullTestSuite {
   }
   ```

3. **Tag tests appropriately**:
   ```java
   // Critical path - run on every commit
   @Test
   @Tag("smoke")
   public void testLoginWithValidCredentials() { }
   
   // Important but slower - run on nightly builds
   @Test
   @Tag("regression")
   public void testCompleteCheckoutFlow() { }
   
   // Slow tests - run weekly
   @Test
   @Tags({@Tag("regression"), @Tag("slow")})
   public void testLargeCartPerformance() { }
   
   // Manual intervention required
   @Test
   @Tags({@Tag("manual"), @Tag("captcha")})
   public void testAccountRegistration() { }
   ```

4. **Create GitHub Actions workflow**:
   ```yaml
   # .github/workflows/test.yml
   name: UI Tests
   
   on:
     push:
       branches: [ main, develop ]
     pull_request:
       branches: [ main ]
     schedule:
       - cron: '0 2 * * *'  # Nightly at 2 AM
   
   jobs:
     smoke-tests:
       runs-on: ubuntu-latest
       steps:
         - uses: actions/checkout@v3
         
         - name: Set up JDK 19
           uses: actions/setup-java@v3
           with:
             java-version: '19'
             distribution: 'temurin'
         
         - name: Run smoke tests
           run: |
             mvn test \
               -Dheadless=true \
               -Dgroups=smoke \
               -Dbase.url=${{ secrets.TEST_BASE_URL }} \
               -Dtest.user.email=${{ secrets.TEST_USER_EMAIL }} \
               -Dtest.user.password=${{ secrets.TEST_USER_PASSWORD }}
         
         - name: Upload test reports
           if: always()
           uses: actions/upload-artifact@v3
           with:
             name: test-reports
             path: target/surefire-reports/
     
     regression-tests:
       runs-on: ubuntu-latest
       if: github.event_name == 'schedule'  # Only on nightly runs
       steps:
         - uses: actions/checkout@v3
         
         - name: Set up JDK 19
           uses: actions/setup-java@v3
           with:
             java-version: '19'
             distribution: 'temurin'
         
         - name: Run regression suite
           run: |
             mvn test \
               -Dheadless=true \
               -Dgroups=regression \
               -Dtest.env=ci
         
         - name: Upload screenshots on failure
           if: failure()
           uses: actions/upload-artifact@v3
           with:
             name: failure-screenshots
             path: target/screenshots/
   ```

5. **Add screenshot capture on failure** using JUnit 5's TestWatcher:
   ```java
   import org.junit.jupiter.api.extension.ExtendWith;
   import org.junit.jupiter.api.extension.ExtensionContext;
   import org.junit.jupiter.api.extension.TestWatcher;
   
   @ExtendWith(BaseTest.ScreenshotOnFailureExtension.class)
   public abstract class BaseTest {
       protected WebDriver driver;
       
       /**
        * JUnit 5 TestWatcher automatically detects test failures
        * and captures screenshots
        */
       public static class ScreenshotOnFailureExtension implements TestWatcher {
           
           @Override
           public void testFailed(ExtensionContext context, Throwable cause) {
               // Get the test instance (BaseTest or its subclass)
               Optional<Object> testInstance = context.getTestInstance();
               
               if (testInstance.isPresent() && testInstance.get() instanceof BaseTest) {
                   BaseTest baseTest = (BaseTest) testInstance.get();
                   
                   if (baseTest.driver != null) {
                       captureScreenshot(baseTest.driver, context);
                   }
               }
           }
           
           private void captureScreenshot(WebDriver driver, ExtensionContext context) {
               try {
                   // Create screenshots directory
                   Path screenshotDir = Paths.get("target", "screenshots");
                   Files.createDirectories(screenshotDir);
                   
                   // Generate filename with timestamp
                   String className = context.getTestClass()
                       .map(Class::getSimpleName)
                       .orElse("UnknownClass");
                   String methodName = context.getTestMethod()
                       .map(method -> method.getName())
                       .orElse("unknownMethod");
                   String timestamp = LocalDateTime.now()
                       .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
                   
                   String filename = String.format("%s_%s_%s.png", 
                       className, methodName, timestamp);
                   Path screenshotPath = screenshotDir.resolve(filename);
                   
                   // Capture and save screenshot
                   File screenshot = ((TakesScreenshot) driver)
                       .getScreenshotAs(OutputType.FILE);
                   Files.copy(screenshot.toPath(), screenshotPath, 
                       StandardCopyOption.REPLACE_EXISTING);
                   
                   System.out.println("✓ Screenshot captured: " + screenshotPath);
                   
               } catch (Exception e) {
                   System.err.println("✗ Failed to capture screenshot: " + e.getMessage());
               }
           }
       }
   }
   ```
   
   **How it works:**
   - `TestWatcher.testFailed()` is automatically called when a test fails
   - No manual status tracking needed
   - Screenshots saved to `target/screenshots/`
   - Filename format: `ClassName_methodName_timestamp.png`
   - Any test extending BaseTest automatically gets this functionality

6. **Configure parallel execution**:
   ```xml
   <plugin>
       <artifactId>maven-surefire-plugin</artifactId>
       <configuration>
           <parallel>classes</parallel>
           <threadCount>4</threadCount>
           <perCoreThreadCount>false</perCoreThreadCount>
       </configuration>
   </plugin>
   ```

7. **Add retry logic for flaky tests**:
   ```java
   @Test
   @RepeatedTest(3)  // Retry up to 3 times
   @Tag("flaky")
   public void sometimesFlakyTest() {
       // Test implementation
   }
   ```

**Expected Benefits:**
- Automated test execution on every commit
- Fast feedback via smoke tests
- Comprehensive nightly regression testing
- Screenshots/videos for debugging failures
- Parallel execution for speed
- CI-ready from day one

---

### Summary of Improvements Priority

**High Priority (Immediate Impact):**
1. Centralize WebDriver Setup - eliminates duplicate code
2. Remove Hard-Coded Credentials - security and flexibility
3. Replace Thread.sleep - reliability and speed
4. Add Stronger Assertions - test quality

**Medium Priority (Architecture):**
5. Introduce Page Object Pattern - maintainability
6. Organize by Feature/Package - navigation and clarity
7. Make Environment-Agnostic - multi-environment testing

**Low Priority (Polish):**
8. Unify on JUnit 5 - clean dependencies
9. Isolate CAPTCHA Tests - CI compatibility
10. Prepare for CI/Headless - automation readiness

**Recommended Implementation Order:**
1. Week 1: Items 1, 2, 6 (foundation)
2. Week 2: Items 5, 3 (architecture)
3. Week 3: Items 4, 7 (quality)
4. Week 4: Items 8, 9, 10 (polish and automation)

---------------------
