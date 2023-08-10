package test_Scripts;

import static org.testng.Assert.expectThrows;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import generic.Base_Test;
import pom_scripts.web.Home;
import pom_scripts.web.Header.Header;
import pom_scripts.web.Header.PLP;

public class HM002 extends Base_Test {
	@Test(priority = 1)
	public void main() {
		Home home = new Home(driver);
		Header header = new Header(driver);
		PLP plp = new PLP(driver);
		js = (JavascriptExecutor) driver;
		wait.until(ExpectedConditions.elementToBeClickable(home.getIlldothislater()));
		home.getIlldothislater().click();

		boolean b = header.getPantaloons().isDisplayed();
		Assert.assertEquals(b, true);
		// 4 Verify if Banner link is displayed in Home page
		b = home.getBanner().isDisplayed();
		Assert.assertEquals(b, true);

		// 5 Click on swiper bullet button in Home page using js
		WebElement ele = home.getswiper_bullet("1");
		js.executeScript("arguments[0].click();", ele);

		// 6 Get title attribute value of Banner image
		ele = home.getBanner_Image("2");
		String bannerTit = ele.getAttribute("title");

		// 7 Click on banners image in Home page using javascript executor
		ele = home.getbanners_Image("2");
		js.executeScript("arguments[0].click();", ele);
		// ele.click();

		/// change in script verify sub banner//
		// 8Get text from BreadcrumbTwo text in PLP page
		try {
			String isDisplayed = plp.getBreadcrumbTwo_Text().getText();
			//System.out.println(isDisplayed);
			// 9Verify if First Product image is displayed in PLP page
			b = plp.getFirst_Product_Image().isDisplayed();
			Assert.assertEquals(b, true);
		} catch (Exception e) {
			b = driver.findElement(By.xpath("//div[@class='SubBanner_sub-banner-double__KzWC8']")).isDisplayed();
			Assert.assertEquals(b, true);
		}

		// 11 click on pantalons logo
		home.getPantaloons_Logo().click();
		// 12 Click on swiper bullet button in Home page using javascript executor
		ele = home.getswiper_bullet("3");
		js.executeScript("arguments[0].click();", ele);

		// 13 Click on banners image in Home page using javascript executor
		ele = home.getbanners_Image("3");
		js.executeScript("arguments[0].click();", ele);
		try {
			WebDriverWait wait1 = new WebDriverWait(driver, 5);
			Thread.sleep(5);
			String isDisplayed = plp.getBreadcrumbTwo_Text().getText();
			//System.out.println(isDisplayed);
			b = plp.getFirst_Product_Image().isDisplayed();
			Assert.assertEquals(b, true);
		} catch (Exception e) {
			b = driver.findElement(By.xpath("//div[@class='SubBanner_sub-banner-double__KzWC8']")).isDisplayed();
			Assert.assertEquals(b, true);
		}
		// 14Navigate to previous page in browser window
		driver.navigate().back();
		// 17 Click on swiper bullet button in Home page
		ele = home.getswiper_bullet("4");
		js.executeScript("arguments[0].click();", ele);
		//18Get title attribute value of Banner image in Home page
		WebElement bannerTitle = home.getBanner_Image("4");
		//19Click on Banner image in Home page
		home.getBanner_Image("4").click();
		try {
			Thread.sleep(5);
			String isDisplayed = plp.getBreadcrumbTwo_Text().getText();
			//System.out.println(isDisplayed);
			b = plp.getFirst_Product_Image().isDisplayed();
			Assert.assertEquals(b, true);
		} catch (Exception e) {
			b = driver.findElement(By.xpath("//div[@class='SubBanner_sub-banner-double__KzWC8']")).isDisplayed();
			Assert.assertEquals(b, true);
		}
		
		/////
		// 22Navigate to previous page in browser window
		driver.navigate().back();
		// 23 Click on swiper bullet button in Home page
		ele = home.getswiper_bullet("5");
		js.executeScript("arguments[0].click();", ele);
		//24Get title attribute value of Banner image in Home page
		 bannerTitle = home.getBanner_Image("5");
		//25Click on Banner image in Home page
		home.getBanner_Image("5").click();
		try {
			Thread.sleep(5);
			String isDisplayed = plp.getBreadcrumbTwo_Text().getText();
			//System.out.println(isDisplayed);
			b = plp.getFirst_Product_Image().isDisplayed();
			Assert.assertEquals(b, true);
		} catch (Exception e) {
			b = driver.findElement(By.xpath("//div[@class='SubBanner_sub-banner-double__KzWC8']")).isDisplayed();
			Assert.assertEquals(b, true);
		}
		////////////////////////
		// 29 Navigate to previous page in browser window
				driver.navigate().back();
				// 31 Click on swiper bullet button in Home page
				ele = home.getswiper_bullet("6");
				js.executeScript("arguments[0].click();", ele);
				//32 Get title attribute value of Banner image in Home page
				 bannerTitle = home.getBanner_Image("6");
				//33 Click on Banner image in Home page
				home.getBanner_Image("6").click();
				try {
					Thread.sleep(5);
					String isDisplayed = plp.getBreadcrumbTwo_Text().getText();
					////System.out.println(isDisplayed);
					b = plp.getFirst_Product_Image().isDisplayed();
					Assert.assertEquals(b, true);
				} catch (Exception e) {
					b = driver.findElement(By.xpath("//div[@class='SubBanner_sub-banner-double__KzWC8']")).isDisplayed();
					Assert.assertEquals(b, true);
				}
				System.out.println("all completed");
}}
