import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;

import java.nio.file.WatchEvent;
import java.time.Duration;

public class FitPeoAutomation {
    public static void main(String[] args) {
        // Optional: Auto setup ChromeDriver if you use WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);

        try {
            driver.get("https://www.fitpeo.com/");
            driver.manage().window().maximize();

//              Navigate to Revenue Calculator page
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Revenue Calculator"))).click();

//            Wait for page load
            Thread.sleep(2000); // Add a small wait to ensure full load

//             Screen Scrolling
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 200)");
            System.out.println("Scrolling happened");
            Thread.sleep(3000);

//            Unable to adjust the slider using traditional methods as it is built using React or JavaScript-based UI. Instead, I entered the value directly into the input field associated with the slider and verified the value update in the DOM

            By slider = By.cssSelector("input[type='range']");
            WebElement sliderElement = driver.findElement(slider);
            int width = sliderElement.getSize().width;
            int targetOffsetX = (int) (width * 820 / 2000.0);
            Actions action = new Actions(driver);
            action.moveToElement(sliderElement, 86, 0)
                    .clickAndHold()
                    .moveByOffset(targetOffsetX, 0)
                    .release()
                    .perform();
            Thread.sleep(2000);

            WebElement textbox = driver.findElement(By.xpath("//input[@type='number']"));
            if (textbox.getAttribute("value").equals("823")) {
                System.out.println("Successfully updated to 823!");
            } else {
                System.out.println(" Value not set correctly.");
            }
            Thread.sleep(3000);
            driver.navigate().refresh();
            Thread.sleep(6000);

//      Clear the text field and enter the values 563
            By slider1 = By.cssSelector("input[type='range']");
            WebElement sliderElement1 = driver.findElement(slider1);
            int width1 = sliderElement1.getSize().width;
            int targetOffset1X = (int) (width1 * 820 / 2000.0);
            Actions act1 = new Actions(driver);
            act1.moveToElement(sliderElement1, 47, 0)
                    .clickAndHold()
                    .moveByOffset(targetOffset1X, 0)
                    .release()
                    .perform();
            Thread.sleep(2000);

//      Select CPT Codes
            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("window.scrollBy(0, 600)");
            System.out.println("Scrolling happened");
            Thread.sleep(3000);


            WebElement checkbox99091 = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
            checkbox99091.click();
            Thread.sleep(2000);
            WebElement checkbox99453 = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));
            checkbox99453.click();
            Thread.sleep(2000);
            WebElement checkbox99454 = driver.findElement(By.xpath("(//input[@type='checkbox'])[3]"));
            checkbox99454.click();
            Thread.sleep(2000);

            JavascriptExecutor js2 = (JavascriptExecutor) driver;
            js2.executeScript("window.scrollBy(0, 600)");
            System.out.println("Scrolling happened");
            Thread.sleep(3000);
            WebElement checkbox99474 = driver.findElement(By.xpath("(//input[@type='checkbox'])[8]"));
            checkbox99474.click();
            Thread.sleep(6000);
//      Validate total recurring Reimbursement
            WebElement totalAmount = driver.findElement(By.xpath("//p[text()='Total Recurring Reimbursement for all Patients Per Month:']//p"));
            String actualText = totalAmount.getText();
            Assert.assertEquals(actualText, "$76005");
            System.out.println(actualText);
//      Verify that the header

            WebElement reimbursementHeader = driver.findElement(By.xpath("//*[contains(text(),'Total Recurring Reimbursement')]"));
            String actualText2 = reimbursementHeader.getText();
            System.out.println("Actual Header Text: " + actualText2);
            Assert.assertTrue(actualText.contains("$76005"), "Expected $110700 but found: " + actualText2);
            Thread.sleep(2000);

        }
        catch (Exception e) {
            System.err.println(" Test failed: " + e.getMessage());
            e.printStackTrace();
        }
        finally {
            driver.quit();
        }


    }
}

