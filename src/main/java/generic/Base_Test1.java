package generic;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base_Test1 extends UtilityMethod{
	public String className="";
	TakeSCreenShort ts=new TakeSCreenShort();
	public boolean bool=false;
//	@BeforeSuite
//	public void beforeSuite(){
//		ts.suiteStartTime();
//		report=new ExtentReports(REPORTS_PATH+name()+"1111.pdf");
//		test=report.startTest(TEST_NAME);
//	}
	
	@BeforeClass(alwaysRun = true)
	public void browserSetup() throws IOException, InterruptedException {
		 ts.classStartTime();
		String msDURL="https://www.pantaloons.com/";
		String dir="C:\\DataOfDebugger";
		String cmdCommand = "chrome.exe -remote-debugging-port=9292 --no-first-run --no-default-browser-check --user-data-dir=" + dir;
		String chromePath = "C:\\Program Files\\Google\\Chrome\\Application";
		Runtime.getRuntime().exec("cmd /c start cmd.exe /K " + cmdCommand, null, new File(chromePath));
		Thread.sleep(500);
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		WebDriverManager.chromedriver().setup();
//		Connect to launched browser	
		ChromeOptions opt = new ChromeOptions();
		opt.setExperimentalOption("debuggerAddress", "localhost:9292");
		opt.addArguments("--disable notifications");
		opt.addArguments("--remote-allow-origins=*");
//		
		 driver = new ChromeDriver(opt);
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
		Runtime.getRuntime().exec("taskkill /F /FI \"WINDOWTITLE eq Terminal\"");
	
		driver.manage().window().maximize();
		driver.get("https://www.pantaloons.com/");
	
		initObjects();
	}
	
	@AfterClass
	public void CloseApp() throws Exception {
		//System.out.println(className+"  "+bool);
		ts.getScrennShort(className,bool);
		//driver.quit();
		Set<String> l = driver.getWindowHandles();
		for (String string : l) {
			driver.switchTo().window(string);
			driver.close();
		}
		ts.clssEndTime();

		test.log(LogStatus.INFO,"End Test");
		//Thread.sleep(10000);
	}
//	@AfterSuite
//	public void afterSuite() {
//		ts.suiteEndTime();
//		report.endTest(test);
//		report.flush();
//		System.out.println(arr);
//	}

}
