package tests;

import TestBase.BaseTest;
import TestBase.Factory.TestDataFactory;
import TestBase.Factory.TestUser;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.AccountPage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

@Epic("E-Commerce")
@Feature("User Authentication")
public class LoginTest extends BaseTest {

    @BeforeEach
    public void ensureLoggedOut() {
        clearCookiesAndLogoOut();
    }


    @Test
    @Story("Successful Login")
    @Description("User can log in with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void userCanLoginWithValidCredentials() {
        TestUser user = TestDataFactory.validTestUser();

        // Readable, fluent API
        LoginPage loginPage = new LoginPage(driver).open();
        AccountPage accountPage = loginPage.login(user.getEmail(), user.getPassword());

        // Clean assertions
        assertTrue(accountPage.isLoggedIn(), "User should be logged in");
        assertAll("Account page validation",
                () -> assertTrue(accountPage.isElementVisible(By.cssSelector("div.block-title > strong"))),
                () -> assertEquals("Moj Račun", accountPage.getAccountHeading())
        );
    }

    @Test
    @Story("Logout")
    @Description("Logged-in user can log out")
    @Severity(SeverityLevel.NORMAL)
    public void userCanLogout() {
        System.out.println("Starting logout test...");

        TestUser user = TestDataFactory.validTestUser();

        LoginPage loginPage1 = new LoginPage(driver);
        AccountPage accountPage = loginPage1
                .open()
                .login(user.getEmail(), user.getPassword());

        assertTrue(accountPage.isLoggedIn(), "User should be logged in");

        LoginPage loginPage = accountPage.logout();

        assertTrue(loginPage.isLoaded(), "Should be back on login page");
        assertFalse(accountPage.isLoggedIn(), "User should be logged out");
    }
}
