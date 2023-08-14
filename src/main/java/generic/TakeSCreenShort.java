package generic;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import com.google.common.io.Files;

public class TakeSCreenShort extends UtilityMethod{
	void getScrennShort(String className ,boolean bool){	
		try {
			System.out.println(className+"  "+bool);
			if (!bool) {
				System.out.println("inside screenshort");
				String path = SCREENSHOT_PATH + className + ".png";
				File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				Files.copy(f, new File(path));
				arr.add(className);
			}
		} catch (Exception e) {
			//sysout
		}}



	public void suiteStartTime() {
		LocalDateTime now = LocalDateTime.now();
		 shs = now.getHour();
		sms = now.getMinute();
		sss = now.getSecond();
		
	}



	public void classStartTime() {
		LocalDateTime now = LocalDateTime.now();
		 ihs = now.getHour();
		ims = now.getMinute();
		iss = now.getSecond();
		
	}



	public void clssEndTime() {
		LocalDateTime now = LocalDateTime.now();
		 ihe = now.getHour();
		ime = now.getMinute();
		ise = now.getSecond();
		int h=(int) Math.abs(ihe-ihs);
		int m=(int) Math.abs(ime-ims);
		int s=(int) Math.abs(ise-iss);
		System.out.println("Time taken to execuite "+className);
		System.out.println(h+":"+m+":"+s);
		
	}



	public void suiteEndTime() {
		LocalDateTime now = LocalDateTime.now();
		 she = now.getHour();
		sme= now.getMinute();
		sse= now.getSecond();
		
		int h=(int) Math.abs(she-shs);
		int m=(int) Math.abs(sme-sms);
		int s=(int) Math.abs(sse-sss);
		System.out.println("Time taken to execuite suite");
		Reporter r=new Reporter();
		r.log(h+":"+m+":"+s);
		System.out.println(h+":"+m+":"+s);
	}
}
