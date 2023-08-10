package test_Scripts;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.openqa.selenium.Keys.*;


public class REname {

    public static void main(String[] args) throws InterruptedException, MalformedURLException {
       WebDriverManager.chromedriver().setup();
       String[] phoneNumber ={"8951362128"};
       // {"8867683721"};
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.pantaloons.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//button[@class='No thanks' and contains(text(),'I’ll do this later')])")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='No thanks' and contains(text(),'I’ll do this later')])")));
       driver.findElement(By.xpath("//button[@class='No thanks' and contains(text(),'I’ll do this later')]")).click();
        driver.findElement(By.xpath("//a[@title=\"My Account\"]")).click();
        driver.findElement(By.xpath("//input[@placeholder='Mobile Number']")).sendKeys(phoneNumber);
        driver.findElement(By.xpath("//span[text()='Get OTP']")).click();
        Thread.sleep(10000);
      
      /*  mobileDriver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Clear,Button\"]")).click();
        //mobileDriver.navigate().back();
        mobileDriver.quit();
       // driver.findElement(By.xpath("//input[@name=\"otp\"]")).sendKeys(onlyNumberOTP);
        driver.findElement(By.xpath("//span[text()='Start Shopping']")).click();
     //   driver.findElement(By.xpath("//div[contains(@class,'accountMenuHolder withpromo isHidden')]")).isDisplayed();*/
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//button[@class='No thanks' and contains(text(),'I’ll do this later')])")));
        driver.findElement(By.xpath("//button[@class='No thanks' and contains(text(),'I’ll do this later')]")).click();
        String s="//div[@class=\"swiper-pagination swiper-pagination-clickable swiper-pagination-bullets\"]//span[@tabindex=\"0\"]";
        String t="//img[@class=\"banner-img\"]";
        List<WebElement> banners=driver.findElements(By.xpath("//div[@class=\"swiper-pagination swiper-pagination-clickable swiper-pagination-bullets\"]//span[@tabindex=\"0\"]"));
        int numberofbanners=banners.size();
        System.out.println(numberofbanners);
        for(int i=1;i<=numberofbanners;i++){
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//div[@class=\"swiper-pagination swiper-pagination-clickable swiper-pagination-bullets\"]//span[@tabindex=\"0\"])")));
            driver.findElement(By.xpath(s+"["+i+"]")).click();
            driver.findElement(By.xpath(t+"["+i+"]")).click();
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=\"MuiPaper-root MuiCard-root PlpWeb_product-card__1TKOq MuiPaper-elevation1 MuiPaper-rounded\"]")));
            driver.findElement(By.xpath("//img[@title=\"Pantaloons\"]")).click();
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@title=\"My Account\"]")));

        }



    }
}



