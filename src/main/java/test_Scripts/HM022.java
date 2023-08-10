package test_Scripts;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import generic.Base_Test;
import pom_scripts.web.Home;
import pom_scripts.web.Header.Header;
import pom_scripts.web.Header.PLP;
import pom_scripts.web.Header.Plp.PDP;

public class HM022 extends Base_Test {
	@Test(priority = 1)
	public void main() {
		Home home = new Home(driver);
		Header header = new Header(driver);
		PLP plp = new PLP(driver);
		js = (JavascriptExecutor) driver;
		PDP pdp=new PDP(driver);
		WebDriverWait w=new WebDriverWait(driver, 1);
		wait.until(ExpectedConditions.elementToBeClickable(home.getIlldothislater()));
		home.getIlldothislater().click();

		boolean b = header.getPantaloons().isDisplayed();
		Assert.assertEquals(b, true);
		//base for all
		for( int i=0;i<5;i++) {
			action.sendKeys(Keys.PAGE_DOWN).perform();
		}
		//Scroll page vertically until visibility of Trending Now text in Home page
		action.moveToElement(home.getTrending_Now_text()).perform();;
		//6 Verify if Trending Now text is displayed in Home page
		b=home.getTrending_Now_text().isDisplayed();
		Assert.assertEquals(b, true);
		//7Verify if Deals Of The Day Slider button in Home page is clickable
		WebElement ele = home.getDeals_Of_The_Day_Slider_Button("Trending");
		w.until(ExpectedConditions.elementToBeClickable(ele));
		//Verify if trendingNow firstProduct image in Home page is clickable
		ele=home.gettrendingNow_firstProduct_Image();
		w.until(ExpectedConditions.elementToBeClickable(ele));
		//9Click on trendingNow firstProduct image in Home page
		ele.click();
		//11 Verify if PDP ProductName text is displayed in PDP page
		b=pdp.getPDP_ProductName_Text().isDisplayed();
		Assert.assertEquals(b, true);
	}
}
