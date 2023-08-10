package generic;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base_Test extends UtilityMethod{
	
	@BeforeSuite
	public void beforeSuite(){
		report=new ExtentReports(REPORTS_PATH+name()+".html");
		test=report.startTest(TEST_NAME);
	}
	
	@BeforeClass(alwaysRun = true)
	public void browserSetup() throws IOException {
//		browserName=getValueProperty("browser");
//		if(browserName.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions cp=new ChromeOptions();
		//cp.addArguments("--headless");
	    driver=new ChromeDriver(cp);
	    test.log(LogStatus.INFO, "Browser is launched");
		
		//}
//		else if(browserName.equalsIgnoreCase("forefox")) {
//			WebDriverManager.firefoxdriver().setup();
//			driver=new FirefoxDriver();
//			test.log(LogStatus.INFO, "Browser is launched");
//		}
//		else {
//			Reporter.log("Enter Valid Browser name");
//		}
	    
		driver.manage().window().maximize();
		driver.get("https://www.pantaloons.com/");
		driver.manage().timeouts().implicitlyWait(TIMEOUTS_WAIT, TimeUnit.SECONDS);
		wait=new WebDriverWait(driver, TIMEOUTS_WAIT);
		
	//	URL=getValueProperty("https://www.google.com");
		initObjects();
	}
	
//	@AfterClass
//	public void CloseApp() {
//		driver.quit();
//		test.log(LogStatus.INFO,"End Test");
//	}
	@AfterSuite
	public void afterSuite() {
		report.endTest(test);
		report.flush();
	}

}
