package test_Scripts.PDP_Web;

import org.openqa.selenium.By;
import static extentReporter.ExtentLogger.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import generic.Base_Test;
import generic.StepGroups;
import generic.Utility;
import pom_scripts.web.Home;
import pom_scripts.web.Header.Bag;
import pom_scripts.web.Header.Header;
import pom_scripts.web.Header.PLP;
import pom_scripts.web.Header.Wishlist;
import pom_scripts.web.Header.Plp.PDP;
import pom_scripts.web.Header.Plp.Quick_View;

public class PDP058 extends Base_Test
{

	@Test
	public void main() throws Exception {
		className="PDP058";
		Utility u=new Utility();
		u.OpenBrowser();
		Home home = new Home(driver);
		Header header = new Header(driver);
		PLP plp = new PLP(driver);
		js = (JavascriptExecutor) driver;
		PDP pdp = new PDP(driver);
		StepGroups sg = new StepGroups(driver);
		new Wishlist(driver);
		new WebDriverWait(driver, 1);
		Quick_View quickView=new Quick_View(driver);
		Bag bag=new Bag(driver);
		
		boolean b = header.getPantaloons().isDisplayed();
		u.checkIsDisplayed(b, "Pantaloons logo");
		Assert.assertEquals(b, true);
		// image verifcation completed
		sg.Delete_Multiple_Products_From_Bag();
		sg.Navigate_to_PLP_Page("WOMEN", "Tees & Tops");
		//Move mouse pointer on Pantaloons image in Home page
		action.moveToElement(home.getPantaloons_image()).perform();
		u.moveToElementMessage("Pantaloons logo");
		//Verify if Breadcrumb text is displayed in PLP page
		b=plp.getBreadcrumb_text().isDisplayed();
		u.checkIsDisplayed(b, "Breadcrumb text");
		Assert.assertEquals(b, true);
		//Scroll page vertically until visibility of FILTER BY text in PLP page
		js.executeScript("arguments[0].scrollIntoView(true);", plp.getFILTER_BY_text());
		sg.Navigate_to_PDP_Page(); 
		//Scroll page vertically until visibility of QUANTITY text in PDP page
		js.executeScript("arguments[0].scrollIntoView(true);", pdp.getQUANTITY_text());
		//Wait till ADD TO BAG button in PDP page is clickable
		wait.until(ExpectedConditions.elementToBeClickable(pdp.getADD_TO_BAG_button()));
		//Move mouse pointer on ADD TO BAG button in PDP page
		try {
			action.moveToElement(pdp.getADD_TO_BAG_button()).perform();
		} catch (Exception e) {
			action.moveToElement(pdp.getADD_TO_BAG_button()).perform();
		}
		u.moveToElementMessage("ADD TO BAG button ");
		//Click on current cursor point
		try {
			action.click().perform();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class,\"minicart_mini-cart-bag-close-icon\")]")));
		} catch (Exception e) {
			action.click().perform();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class,\"minicart_mini-cart-bag-close-icon\")]")));
		}
		u.isClicked(" ADD TO BAG button");
		//Click on Cancel button in PDP page
		pdp.getCancel_button().click();
		u.isClicked(" Cancel button");
		//Wait till GO TO BAG button in Quick View page is clickable
		wait.until(ExpectedConditions.elementToBeClickable(quickView.getGO_TO_BAG_button()));
		quickView.getGO_TO_BAG_button().click();
		u.isClicked("GO_TO_BAG_button");
		//Verify if My Bag Page text is displayed in Bag page
		b=bag.getMy_Bag_Page_text().isDisplayed();
		u.checkIsDisplayed(b, "My Bag Page text");
		Assert.assertEquals(b, true);
		//Get text from Product_Brand_text in Bag page
		String productbrand = bag.getProduct_Brand_text().getText();
		pass(productbrand+" productbrand");
//		System.out.println("in main");
		//Get text from Product_Name_text in Bag page
		String productname = bag.getProduct_Name_text().getText();
		pass(productname+" productname");
//		System.out.println(productbrand);
//		System.out.println(productname);
		sg.checkValues(productbrand,productname);
		bool=true;
		
	}
}
