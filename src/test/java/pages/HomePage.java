package pages;

import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public class HomePage {
    private final WebDriver driver;
    private final String baseUrl = "https://www.bigbang.ba/";


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
