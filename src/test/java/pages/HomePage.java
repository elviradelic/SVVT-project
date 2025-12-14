package pages;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;



public class HomePage extends BasePage{
    private final String baseUrl = "https://www.bigbang.ba/";

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public HomePage open() {
        driver.get(baseUrl);
        return this;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
