package test_Scripts.PLP_Web;

import static org.testng.Assert.expectThrows;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import generic.Base_Test;
import pom_scripts.web.Home;
import pom_scripts.web.Header.Header;
import pom_scripts.web.Header.PLP;
import pom_scripts.web.Header.Wishlist;
import pom_scripts.web.Header.Plp.PDP;

public class PLP007 extends Base_Test{
	@Test
	public void main() throws Exception {
		Home home = new Home(driver);
		Header header = new Header(driver);
		PLP plp = new PLP(driver);
		js = (JavascriptExecutor) driver;
		PDP pdp = new PDP(driver);
		String product="Kurta";
		Wishlist wishlist=new Wishlist(driver);
		WebDriverWait w = new WebDriverWait(driver, 1);
		wait.until(ExpectedConditions.elementToBeClickable(home.getIlldothislater()));
		home.getIlldothislater().click();
		boolean b = header.getPantaloons().isDisplayed();
		Assert.assertEquals(b, true);
		//image verifcation completed
		WebElement ele = home.getcategory("WOMEN");
		action.moveToElement(ele).perform();
		// Wait till Sub Catgeory link in Header page is visible
		String subCategory="Kurtis & Tunics";
		ele = header.getSub_Catgeory_Link(subCategory);
		
		ele.click();
		// Verify if First Product image is displayed in PLP page
		 b = plp.getFirst_Product_Image().isDisplayed();
		Assert.assertEquals(b, true);
		//Get text from BreadcrumbTwo text in PLP page
		String breadcrumbSubCatagory = plp.getBreadcrumbTwo_Text().getText();
		//Verify if string breadcrumbSubCatagory contains string subCategory
		b=breadcrumbSubCatagory.contains(subCategory);
		Assert.assertEquals(b, true);
		//Verify if PLP Category Page text is displayed in PLP page
		//Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(plp.getPLP_Category_Page_text2(subCategory)));
		b=plp.getPLP_Category_Page_text2(subCategory).isDisplayed();
		System.out.println(b);
		Assert.assertEquals(b, true);
		//Verify if First Product image is displayed in PLP page
		b=plp.getFirst_Product_Image().isDisplayed();
		Assert.assertEquals(b, true);
	}
}
