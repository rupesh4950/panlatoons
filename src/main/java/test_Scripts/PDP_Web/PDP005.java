package test_Scripts.PDP_Web;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import generic.Base_Test1;
import generic.StepGroups;
import pom_scripts.web.Home;
import pom_scripts.web.Header.Header;
import pom_scripts.web.Header.PLP;
import pom_scripts.web.Header.Wishlist;
import pom_scripts.web.Header.Plp.PDP;

public class PDP005 extends Base_Test1 {
	@Test
	public void main() throws Exception {
		Home home = new Home(driver);
		Header header = new Header(driver);
		PLP plp = new PLP(driver);
		js = (JavascriptExecutor) driver;
		PDP pdp = new PDP(driver);
		StepGroups sg = new StepGroups(driver);
		Wishlist wishlist = new Wishlist(driver);
		WebDriverWait w = new WebDriverWait(driver, 1);
		//wait.until(ExpectedConditions.elementToBeClickable(home.getIlldothislater()));
		//home.getIlldothislater().click();
		boolean b = header.getPantaloons().isDisplayed();
		Assert.assertEquals(b, true);
		// image verifcation completed
		//number
		//sg.Login_to_Pantaloons("");
		//Move mouse pointer on category link in Home page
		action.moveToElement(home.getcategory("MEN")).perform();
		//Get text from category link in Home page
		String categoryName = home.getcategory("MEN").getText();
		//Wait till Men_Sub_Category link in Header page is visible
		wait.until(ExpectedConditions.visibilityOf(header.getMen_Sub_Category("Pyjamas & Salwar")));
		//Get text from Men Sub-Category link in Header page
		String subcategoryName = header.getMen_Sub_Category("Pyjamas & Salwar").getText();
		header.getMen_Sub_Category("Pyjamas & Salwar").click();
		//Move mouse pointer on Pantaloons Logo image in Home page
		action.moveToElement(home.getPantaloons_Logo()).perform();
		//Verify if Breadcrumb text is displayed in PLP page
		b=plp.getBreadcrumb_text().isDisplayed();
		Assert.assertEquals(b, true);
		//Get text from BreadcrumbOne_text in PLP page
		String breadcrumbOne = plp.getBreadcrumbOne_text().getText();
		//Get text from BreadcrumbTwo text in PLP page
		String breadcrumbTwo = plp.getBreadcrumbTwo_Text().getText();
	//	Verify if string breadcrumbOne matches string categoryName
		b=breadcrumbOne.equalsIgnoreCase(categoryName);
		System.out.println(breadcrumbOne);
		System.out.println(categoryName);
		Assert.assertEquals(b, true);
		//Verify if string breadcrumbTwo matches string subcategoryName
		b=breadcrumbTwo.matches(subcategoryName);
		Assert.assertEquals(b, true);
		sg.Get_Product_Details_In_PLP_Copy();
		sg.Get_Product_Details_In_PDP();
		sg.Verify_PLP_and_PDP_Products_are_same();
	}
}
