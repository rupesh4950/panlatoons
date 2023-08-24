package test_Scripts.PDP_Web;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import generic.Base_Test;
import generic.StepGroups;
import generic.Utility;
import pom_scripts.web.Header.Header;
import pom_scripts.web.Header.PLP;
import pom_scripts.web.Header.Wishlist;
import pom_scripts.web.Header.Plp.PDP;
public class PDP034 extends Base_Test{
	@Test
	public void main() throws Exception {
		className="PDP034";
		Utility u=new Utility();
		if(deurgerMode) {
			u.BrowserInDeburger();
		}
		else {
			u.OpenBrowser();
		}
		Header header = new Header(driver);
		new PLP(driver);
		js = (JavascriptExecutor) driver;
		PDP pdp = new PDP(driver);
		StepGroups sg = new StepGroups(driver);
		new Wishlist(driver);
		new WebDriverWait(driver, 1);
		try {
			//	wait.until(ExpectedConditions.elementToBeClickable(home.getIlldothislater()));
			//home.getIlldothislater().click();
			boolean b = header.getPantaloons().isDisplayed();
			u.checkIsDisplayed(b, "Pantaloons logo");
			Assert.assertEquals(b, true);
			// image verifcation completed
			//login to pantaloons
			if(!deurgerMode)
				sg.Login_to_Pantaloons();
			sg.Remove_Multiple_Products_From_Wishlist();
			
			sg.Navigate_to_PLP_Page("WOMEN","Kurtas");
			
			//Press PAGE_DOWN key
			//action.sendKeys(Keys.PAGE_DOWN).perform();
			
			sg.Get_Product_Details_In_PLP_Copy();
			//System.out.println("pdp");
			sg.Get_Product_Details_In_PDP();
			//System.out.println("pdp completed");
			sg.Verify_PLP_and_PDP_Products_are_same();
			//System.out.println("pdp product are same");
			//Press PAGE_DOWN key
			//action.sendKeys(Keys.PAGE_DOWN).perform();
			//Wait till ADD TO WISHLIST button in PDP page is visible
			wait.until(ExpectedConditions.visibilityOf(pdp.getADD_TO_WISHLIST_button()));
			//Click on ADD TO WISHLIST button in PDP page
			//System.out.println("add produucts to wishlist");
			
			pdp.getADD_TO_WISHLIST_button().click();
			u.isClicked("ADD_TO_WISHLIST_button");
			//Wait till REMOVE FROM WISHLIST text in PDP page is visible
			wait.until(ExpectedConditions.visibilityOf(pdp.getREMOVE_FROM_WISHLIST_text()));
			//Wait till Wishlist icon in Header page is clickable
			wait.until(ExpectedConditions.elementToBeClickable(header.getWishlist_icon()));
			//Click on Wishlist icon in Header page using javascript executor
			js.executeScript("arguments[0].click();", header.getWishlist_icon());
			u.isClicked("Wishlist_icon");
			sg.Get_Product_Details_in_Wishlist();
		//Verify if string productBrandWishlist matches string ProductBrandPDP
			sg.verfiyforPDP034();
			bool=true;
		} catch (Exception e) {
			System.out.println(e);

		}}}
