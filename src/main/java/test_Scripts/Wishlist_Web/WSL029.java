package test_Scripts.Wishlist_Web;
import static extentReporter.ExtentLogger.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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
import pom_scripts.web.Header.Login;
import pom_scripts.web.Header.PLP;
import pom_scripts.web.Header.Wishlist;
import pom_scripts.web.Header.Plp.PDP;
import pom_scripts.web.Header.Plp.Quick_View;

public class WSL029 extends Base_Test {
	@Test(priority = 1)
	public void main() throws Exception, Exception {
		className="WSL029";
		Utility u=new Utility();
		u.OpenBrowser();
		Header header = new Header(driver);
		PLP plp = new PLP(driver);
		js = (JavascriptExecutor) driver;
		Login login=new Login(driver);
		PDP pdp = new PDP(driver);
		StepGroups sg = new StepGroups(driver);
		boolean b = header.getPantaloons().isDisplayed();
		u.checkIsDisplayed(b, "Pantaloons logo");
		Assert.assertEquals(b, true);
		// image verifcation completed
		sg.Navigate_to_PLP_Page("WOMEN", "WESTERN WEAR");
		String sizeLocator = "(//div[@class='size size-web ']//span)[1]";
		String firstProductLocator = "(//div[contains(@class,'PlpWeb_product-card-content')])";
		sg.WEB_VerifyUserIsAbleToSelectProductOfAvailableSize(firstProductLocator,sizeLocator,5);
		b=plp.getBreadcrumb_text().isDisplayed();
		u.checkIsDisplayed(b, "Breadcrumbs text");
		Assert.assertEquals(b, true);
		//Get text from Product Name text in PDP page
		String productNameInPDP = pdp.getProduct_Name_text().getText();
		pass("productNameInPDP  :"+productNameInPDP);
		WebElement ele = pdp.getQUANTITY_text();
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		pass("scrolled till quantity text");
	//	Wait till ADD TO WISHLIST button in PDP page is visible//
		wait.until(ExpectedConditions.visibilityOf(pdp.getADD_TO_WISHLIST_button()));
	//Click on ADD TO WISHLIST button in PDP page
		pdp.getADD_TO_WISHLIST_button().click();
		u.isClicked("WISHLIST button");
		//Verify if Login/Register text is displayed in Login page
		b=login.getLoginRegister().isDisplayed();
		u.checkIsDisplayed(b, "LoginRegister");
		Assert.assertEquals(b, true);
		sg.Login_through_POP_UP();
		//Verify if Breadcrumb text is displayed in PLP page
		b=plp.getBreadcrumb_text().isDisplayed();
		u.checkIsDisplayed(b, "Breadcrumb text");
		Assert.assertEquals(b, true);
		//Press PAGE_DOWN for n times
		int n=1;
		for(int i=0;i<n;i++) {
			action.sendKeys(Keys.PAGE_DOWN).perform();
		}
		//Verify if REMOVE_FROM_WISHLIST_text is displayed in PDP page
		b=pdp.getREMOVE_FROM_WISHLIST_text().isDisplayed();
		u.checkIsDisplayed(b, "REMOVE_FROM_WISHLIST_text");
		bool=true;
		
	}

}
