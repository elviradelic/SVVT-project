package TestBase;

import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(BaseTest.ScreenshotOnFailureExtension.class)
@ExtendWith(AllureJunit5.class)
public abstract class BaseTest {
    private static boolean headless = false;
    protected WebDriver driver;

    @BeforeAll
    public void setUp() throws Exception{
        ChromeOptions chromeOptions = new ChromeOptions();

        if (headless) {
            chromeOptions.addArguments("--headless=new");
            chromeOptions.addArguments("--window-size=1920,1080");
        } else {
            chromeOptions.addArguments("--start-maximized");
        }

        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");

        // WebDriverManager can be used here instead of manual driver path
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1920, 1080));

    }

    @AfterAll
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    /**
     * JUnit 5 TestWatcher extension to capture screenshots on test failure
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
                // Create screenshots directory if it doesn't exist
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

                // Capture screenshot
                File screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

                // Save screenshot
                Files.copy(screenshot.toPath(), screenshotPath,
                        StandardCopyOption.REPLACE_EXISTING);

                System.out.println("✓ Screenshot captured: " + screenshotPath);

            } catch (IOException e) {
                System.err.println("✗ Failed to capture screenshot: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("✗ Unexpected error during screenshot capture: " + e.getMessage());
            }
        }
    }

}
