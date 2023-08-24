package extentReporter;

import java.util.Objects;
import generic.FrameworkConstants;
import generic.ScreenRecorderUtil;
import generic.Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//This class is final because no one should extend it
public final class ExtentConfig implements FrameworkConstants {
	// This is because no one should create object for this
	static ExtentSparkReporter spark;
	static Utility utility = new Utility();

	private ExtentConfig() {
	}

	private static ExtentReports extent;

	// Thread safety for ExtentTest object
	private static ThreadLocal<ExtentTest> tl = new ThreadLocal<ExtentTest>();

	// Keep these methods as default modifier(not specifying any modifier), so that
	// these can be accessed only within the package
	static ExtentTest getExtentTest() {
		return tl.get();
	}

	static void setExtentTest(ExtentTest test) {
		tl.set(test);
	}

	static void removeExtentTest() {
		tl.remove();
	}

	// To configure extent reports. Call this method in @BeforeSuite
	public static void extentConfig() {
		if (Objects.isNull(extent)) {
			String na = Utility.getCurrentDateAndTime();
			System.out.println(na);
			String path = "Report - " + na;
			if (screenRecord) {
				try {

					ScreenRecorderUtil.startRecord(path);
					System.out.println("recording started");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			utility.suiteStartTime();
			na = Utility.getCurrentDateAndTime();
			System.out.println(na);
			
			path = "Report - " + na + ".html";
			
			extent = new ExtentReports();
			spark = new ExtentSparkReporter(FrameworkConstants.REPORTS_PATH + path); // path and name of report file
			extent.attachReporter(spark);

			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle("ABFRL Document Title"); // HTML title
			spark.config().setReportName("Pantaloons Web Automation"); // This name will be displayed inside the report
																		// at right top corner

		}
	}

	// To create a entry of script in extent reports. This method will be called in
	// every scripts
	public static void createTest(String testCaseName)// (, String... group)
	{
		ExtentTest test = extent.createTest(testCaseName);
		setExtentTest(test); // Insert ExtentTest object in ThreadLocal object
//		 getExtentTest().assignAuthor("Author Name");
//		 getExtentTest().assignDevice("Device Name");
		// getExtentTest().assignCategory(group);

	}

	// This method need to be called in @AfterSuite once the execution is completed
	// in order to push all the reports in html page
	public static void flushExtentReport() {
		// add additional details
		extent.setSystemInfo("Tester name", System.getProperty("user.name"));
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("Browser name", "Chrome");
		String time = utility.suiteEndTime();
		spark.config().setReportName("Pantaloons Automation Report" + " | Suite Duration : " + time);
		if (Objects.nonNull(extent)) {
			extent.flush();
		}
		if (screenRecord) {
			try {
				ScreenRecorderUtil.stopRecord();
				System.out.println("recording stoped");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
