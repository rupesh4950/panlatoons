package generic;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;
import com.google.common.io.Files;


public class TakeSCreenShort extends UtilityMethod {
	void getScrennShort(String className, boolean bool) {
		try {
			System.out.println(className + "  " + bool);
			if (!bool) {
				arr.add(className);
				System.out.println("inside screenshort");
				String path = SCREENSHOT_PATH + className + ".png";
				File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				Files.copy(f, new File(path));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
