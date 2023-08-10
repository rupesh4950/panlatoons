package test_Scripts.My_Bag_Web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.Base_Test;
import generic.StepGroups;
import pom_scripts.web.Footer;
import pom_scripts.web.Home;
import pom_scripts.web.Header.Bag;
import pom_scripts.web.Header.Header;
import pom_scripts.web.Header.PLP;
import pom_scripts.web.Header.Wishlist;
import pom_scripts.web.Header.Plp.PDP;
import pom_scripts.web.Header.Plp.Quick_View;

public class MB016 extends Base_Test {
	{
		
	}
	Home home;
	Header header;
	PLP plp;
	PDP pdp;
	StepGroups sg;
	Wishlist wishlist;
	WebDriverWait w;
	Quick_View quickView;
	Bag bag;
	Footer footer;

	@Test
	public void main() throws Exception {
		home = new Home(driver);
		header = new Header(driver);
		plp = new PLP(driver);

		pdp = new PDP(driver);
		sg = new StepGroups(driver);
		wishlist = new Wishlist(driver);
		w = new WebDriverWait(driver, 1);
		quickView = new Quick_View(driver);
		bag = new Bag(driver);
		footer = new Footer(driver);
		js = (JavascriptExecutor) driver;
		wait.until(ExpectedConditions.elementToBeClickable(home.getIlldothislater()));
		home.getIlldothislater().click();
		boolean b = header.getPantaloons().isDisplayed();
		Assert.assertEquals(b, true);
		// image verifuued
		sg.Add_the_Product_From_Category_to_Bag_with_All_Verifications();
	}

	
}
