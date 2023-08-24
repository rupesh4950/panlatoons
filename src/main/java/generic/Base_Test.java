package generic;

import java.io.IOException;
import java.util.Set;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;


import extentReporter.ExtentConfig;


public class Base_Test extends UtilityMethod {
	Utility utility=new Utility();
	@BeforeSuite
	public void beforeSuite() {
		className="NotEnterd";
		ExtentConfig.extentConfig();
		
	}

	@BeforeClass(alwaysRun = true)
	public void browserSetup() throws IOException {
		
		utility.classStartTime();
		
	}

	TakeSCreenShort ts = new TakeSCreenShort();

	@AfterClass
	public void CloseApp() throws Exception {
		
		utility.clssEndTime();
		System.out.println(className+" :  "+bool);
		Set<String> l = driver.getWindowHandles();
		for (String string : l) {
			driver.switchTo().window(string);
			driver.close();
		}
	}

	@AfterSuite
	public void afterSuite() {
		//report.
		ExtentConfig.flushExtentReport();
		System.out.println(arr);
	}
}
