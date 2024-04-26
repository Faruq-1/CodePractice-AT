package project.bb;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class hotel_booking {

    static String Hotel_name[] = { "Lille Centre Grand Palais", "Paris Porte des Lilas " };

    public static void main(String[] args) throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        String username = "bbhotels";
        String password = "Q1TZWshVS7dQFbfnC";
        String authString = username + ":" + password;
        options.addArguments(
                "Authorization: Basic "+ authString);

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        driver.manage().window().maximize();

        // driver.navigate().to("https://bbhotels:Q1TZWshVS7dQFbfnC@preprod-akamai.moveon-hotelbb.com/en/fr");

        try {
            driver.navigate().to("https://preprod-akamai.moveon-hotelbb.com");
            Thread.sleep(6000);

        } catch (Exception e) {
            System.out.println(e);
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-accept-btn-handler")));
        try {
            driver.findElement(By.id("onetrust-accept-btn-handler")).click();

        } catch (Exception e) {
            System.out.println("Cookies are not visibel");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));

        WebElement search = driver.findElement(By.id("edit-destination"));
        search.click();
        search.sendKeys(Hotel_name[1]);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("hotels-results")));

        // Constructing XPath expression dynamically with contains
        String xpathExpression = String.format("//div[contains(@title,'%s')]",
                String.join("' or contains(@title,'", Hotel_name[1]));

        WebElement Suggestion = driver.findElement(By.xpath(xpathExpression));
        Suggestion.click();

        WebElement from_date_input = driver.findElement(By.id("edit-datepicker"));
        from_date_input.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Next']")));

        WebElement next_arroWebElement = driver.findElement(By.xpath("//a[@title='Next']"));
        next_arroWebElement.click();

        driver.findElement(By.xpath("(//a[text()='5'])[2]")).click();
        driver.findElement(By.xpath("(//a[text()='9'])[2]")).click();

        WebElement search_input = driver.findElement(By.id("edit-search-submit"));
        search_input.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("R12")));

        WebElement checkbox = driver.findElement(By.xpath("//*[@id='R12']//*[@data-rate='FR-RACK']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);

        WebElement Booknow = driver.findElement(By.xpath("//div[@class='button__first-part']"));

        wait.until(ExpectedConditions.elementToBeClickable(Booknow));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", Booknow);

        System.out.println("pass");

    }

}
