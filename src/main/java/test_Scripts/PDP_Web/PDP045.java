package test_Scripts.PDP_Web;

import org.openqa.selenium.JavascriptExecutor;
import static extentReporter.ExtentLogger.*;
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
import generic.StepGroups;
import generic.Utility;

public class PDP045
		extends Base_Test {
	@Test
	public void main() throws Exception {
		className="PDP045";
		Utility u=new Utility();
		u.OpenBrowser();
		Home home = new Home(driver);
		Header header = new Header(driver);
		PLP plp = new PLP(driver);
		js = (JavascriptExecutor) driver;
		PDP pdp = new PDP(driver);
		StepGroups sg=new StepGroups(driver);
		Wishlist wishlist = new Wishlist(driver);
		WebDriverWait w = new WebDriverWait(driver, 1);
		
		boolean b = header.getPantaloons().isDisplayed();
		u.checkIsDisplayed(b, "Pantaloons logo");
		Assert.assertEquals(b, true);
		// image verifcation completed
		b = home.getMy_Account_icon().isDisplayed();
		u.checkIsDisplayed(b, "My Account icon");
		Assert.assertEquals(b, true);
		String category = "WOMEN";
		String subCategory = "WESTERN WEAR";
		//Navigate to PLP Page
		sg.Navigate_to_PLP_Page(category, subCategory);
		//Move mouse pointer on Pantaloons image in Home page
		WebElement ele = home.getPantaloons_Logo();
		action.moveToElement(ele).perform();
		u.moveToElementMessage("Pantaloons logo");
		//Navigate to PDP Page
		sg.Navigate_to_PDP_Page();
		//Get text from Selected Size button in PDP page
		String selectedSize = pdp.getSelected_Size_button().getText();
		pass("selectedSize "+ selectedSize);
		//Print Value of selectedSize
		System.out.println(selectedSize);
		//Verify if Selected Size button in PDP page is selected
		b=pdp.getSelected_Size_button().getAttribute("class").contains("selected");
		u.isContains(b, pdp.getSelected_Size_button().getAttribute("class"), "selected");
		Assert.assertEquals(b, true);
		bool=true;
	}
}
