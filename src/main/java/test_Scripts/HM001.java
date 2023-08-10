package test_Scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import generic.Base_Test;
import io.appium.java_client.functions.ExpectedCondition;
import pom_scripts.web.Home;
import pom_scripts.web.Header.Header;
import pom_scripts.web.Header.Login;

public class HM001 extends Base_Test {
	@Test(priority = 1)
	public void VerifyTheFunctionalityOfCategoriesForNonLoggedInUser() throws Exception {
		Home home=new Home(driver);
		Header header = new Header(driver);
		wait.until(ExpectedConditions.elementToBeClickable(home.getIlldothislater()));
		home.getIlldothislater().click();
		
		boolean b = header.getPantaloons().isDisplayed();
		Assert.assertEquals(b, true);
		b=home.getMy_Account().isDisplayed();
		Assert.assertEquals(b, true);
		home.getMy_Account().click();
		Login login=new Login(driver);
		b=login.getLoginRegister().isDisplayed();
		Assert.assertEquals(b, true);
		home.getPantaloons_Logo().click();
		//women clicked
		b=home.getcategory("WOMEN").isDisplayed();
		Assert.assertEquals(b, true);	
		action.moveToElement(home.getcategory("WOMEN")).build().perform();
		b=header.getWomen_Category("WESTERN WEAR").isDisplayed();
		Assert.assertEquals(b, true);
		action.moveToElement(header.getWomen_Category("WESTERN WEAR")).build().perform();
		//10
		//men move
		header.getPantaloons().click();
		//home=new Home(driver);
		WebElement ele = home.getcategory("MEN");
		b=ele.isDisplayed();
		Assert.assertEquals(b, true);
		action.moveToElement(ele).build().perform();
		b=header.getMen_Category("Formal Shirts").isDisplayed();
		Assert.assertEquals(b, true);
		//kids clicked 
		header.getPantaloons().click();
		 ele = home.getcategory("KIDS");
		b=ele.isDisplayed();
		Assert.assertEquals(b, true);
		action.moveToElement(ele).build().perform();
		b=header.getKids_Category("BOYS").isDisplayed();
		Assert.assertEquals(b, true);
		//home
		header.getPantaloons().click();
		 ele = home.getcategory("HOME");
		b=ele.isDisplayed();
		Assert.assertEquals(b, true);
		action.moveToElement(ele).build().perform();
		b=header.getHome_category("DECOR").isDisplayed();
		Assert.assertEquals(b, true);
		//ACCESSORIES category
		header.getPantaloons().click();
		 ele = home.getcategory("ACCESSORIES");
		b=ele.isDisplayed();
		Assert.assertEquals(b, true);
		action.moveToElement(ele).build().perform();
		b=header.getAccessories_Category("BAGS").isDisplayed();
		Assert.assertEquals(b, true);
		
		
		///BRANDS
		ele = home.getcategory("BRANDS");
		b=ele.isDisplayed();
		Assert.assertEquals(b, true);
		ele.click();
		b=home.getBrands_in_Focus().isDisplayed();
		Assert.assertEquals(b, true);
		
	}
	
}
