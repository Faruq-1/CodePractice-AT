package project.bb;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class csm {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://staging.simpliaxis.com/csm-certification-training#schedule-1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        try {
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("//button[contains(@class,'pos-btn get-cross')]")));
            driver.findElement(By.xpath("//button[contains(@class,'pos-btn get-cross')]")).click();
            System.out.println("Popin is closed");
        } catch (Exception e) {
            System.out.println("Page refresh before popin appears");
        }

        // getting and storing the country into an array
        Select select = new Select(driver.findElement(By.id("schedule_countries")));
        ArrayList<String> list = new ArrayList<>();
        for (WebElement country : select.getOptions()) {
            String text = country.getText();
            list.add(text);
        }

        for (String country : list) {

            select = new Select(driver.findElement(By.id("schedule_countries")));
            select.selectByVisibleText(country);
            System.out.print(country + "-->");

            Thread.sleep(9000);

            try {
                Boolean no_shedules = driver.findElement(By.xpath("//*[@class='no-schedule-PT-card w-76']"))
                        .isDisplayed();
                        if(no_shedules==true){
                            System.out.println("No shedules");
                        }
            } catch (Exception e) {

                boolean displ =driver.findElement(By.xpath("//p[contains(@class,'offer total sale-price-FS')]")).isDisplayed();
                if (displ==true) {
                    WebElement price=driver.findElement(By.xpath("//p[contains(@class,'offer total sale-price-FS')]"));
                    System.out.println(price.getText());
                }
            }
            
        }

        driver.close();
        driver.quit();
    }
}