package tests;

import TestBase.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("HomePage Tests")
@Feature("Home Page")
public class HomePageTest extends BaseTest {

    @Test
    void homePageLoadSuccessful() {
        HomePage homePage = new HomePage(driver).open();

        String title = homePage.getTitle();
        System.out.println("Page Title: " + title);

        assertTrue(title.toLowerCase().contains("big bang"), "Home page title does not contain 'Big Bang'");

    }
}
