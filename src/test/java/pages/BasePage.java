package pages;

     import org.openqa.selenium.By;
     import org.openqa.selenium.TimeoutException;
     import org.openqa.selenium.WebDriver;
     import org.openqa.selenium.WebElement;
     import org.openqa.selenium.support.ui.ExpectedConditions;
     import org.openqa.selenium.support.ui.WebDriverWait;

     import java.time.Duration;

     public abstract class BasePage {
         protected WebDriver driver;
         protected WebDriverWait wait;

         public BasePage(WebDriver driver) {
             this.driver = driver;
             this.wait = new WebDriverWait(driver, Duration.ofSeconds(1));
         }

         // Reusable wait methods
         protected WebElement waitForElement(By locator) {
             return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
         }

         protected WebElement waitForClickable(By locator) {
             return wait.until(ExpectedConditions.elementToBeClickable(locator));
         }

         protected void waitForUrl(String urlFragment) {
             wait.until(ExpectedConditions.urlContains(urlFragment));
         }

         // Reusable action methods
         protected void clickElement(By locator) {
             waitForClickable(locator).click();
         }

         protected void enterText(By locator, String text) {
             WebElement element = waitForElement(locator);
             element.clear();
             element.sendKeys(text);
         }

         protected boolean titleContains(String titleFragment) {
             try {
                 wait.until(ExpectedConditions.titleContains(titleFragment));
                 return true;
             }catch (TimeoutException e) {
                 return false;
             }
         }

         protected String getText(By locator) {
             return waitForElement(locator).getText();
         }

         public boolean isElementVisible(By locator) {
             try {
                 return waitForElement(locator).isDisplayed();
             } catch (Exception e) {
                 return false;
             }
         }
     }