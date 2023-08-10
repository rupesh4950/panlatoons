package test_Scripts.My_Bag_Web;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import generic.Base_Test;
import generic.Base_Test1;
import generic.StepGroups;
import pom_scripts.web.Home;
import pom_scripts.web.Header.Bag;
import pom_scripts.web.Header.Header;
import pom_scripts.web.Header.PLP;
import pom_scripts.web.Header.Wishlist;
import pom_scripts.web.Header.Plp.PDP;
import pom_scripts.web.Header.Plp.Quick_View;

public class MB011 extends Base_Test1 {

	@Test
	public void main() throws Exception {
		Home home = new Home(driver);
		Header header = new Header(driver);
		PLP plp = new PLP(driver);
		js = (JavascriptExecutor) driver;
		PDP pdp = new PDP(driver);
		//System.out.println("before");
		StepGroups sg = new StepGroups(driver);
		Wishlist wishlist = new Wishlist(driver);
		WebDriverWait w = new WebDriverWait(driver, 1);
		Quick_View quickView = new Quick_View(driver);
		Bag bag = new Bag(driver);
//		wait.until(ExpectedConditions.elementToBeClickable(home.getIlldothislater()));
//		home.getIlldothislater().click();
		
		
		boolean b = header.getPantaloons().isDisplayed();
		Assert.assertEquals(b, true);
		//mobile
	//	sg.Login_to_Pantaloons("");
		sg.Navigate_to_PLP_Page("WOMEN", "Skirts");
		//Move mouse pointer on Pantaloons Logo image in Home page
		action.moveToElement(home.getPantaloons_Logo()).perform();
		//Press PAGE_DOWN key
		action.sendKeys(Keys.ARROW_DOWN).perform();
		//Verify if First Product image is displayed in PLP page
		b=plp.getFirst_Product_Image().isDisplayed();
		Assert.assertEquals(b, true);
		//Move mouse pointer on First Product image in PLP page
		action.moveToElement(plp.getFirst_Product_Image()).perform();
		//Verify if QUICK VIEW button is displayed in PLP page
		b=plp.getQUICK_VIEW_button().isDisplayed();
		Assert.assertEquals(b, true);
		//Wait till QUICK VIEW button in PLP page is clickable
		wait.until(ExpectedConditions.elementToBeClickable(plp.getQUICK_VIEW_button()));
		plp.getQUICK_VIEW_button().click();
		//Verify if Quick view popup is displayed in PLP page
		wait.until(ExpectedConditions.visibilityOf(quickView.getQuick_view_popup()));
		b=quickView.getQuick_view_popup().isDisplayed();
		Assert.assertEquals(b, true);
		sg.Add_product_to_Bag_from_Quick_View();
		//Verify if My Bag text is displayed in Bag page
		b=bag.getMy_Bag_Page_text().isDisplayed();
		Assert.assertEquals(b, true);
		//Verify if APPLY COUPON link in Bag page is clickable
		wait.until(ExpectedConditions.elementToBeClickable(bag.getAPPLY_COUPON_link())).click();
		//Wait till APPLY COUPON text in Apply Coupon Pop Up page is visible
		wait.until(ExpectedConditions.visibilityOf(bag.getAPPLY_COUPON_text()));
		//Verify if APPLY COUPON text is displayed in Apply_Coupon_Pop_Up page
		b=bag.getAPPLY_COUPON_text().isDisplayed();
		Assert.assertEquals(b, true);
		//Enter invalidCoupon into Enter Coupon Code textfield in Apply Coupon Pop Up page
		String invalidCoupon = "ABVkj bfvwbvbdskvbkhb12345678";
		wait.until(ExpectedConditions.elementToBeClickable(bag.getEnter_Coupon_Code_textfield()));
		bag.getEnter_Coupon_Code_textfield().sendKeys(invalidCoupon);
		//Sorry__this_coupon_code_is_not_valid
		b=bag.getSorry__this_coupon_code_is_not_valid_text().isDisplayed();
		Assert.assertEquals(b, true);
	}

}
