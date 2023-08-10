package test_Scripts;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import generic.Base_Test;
import generic.ReadExcel;
import pom_scripts.web.Home;
import pom_scripts.web.Header.Header;
import pom_scripts.web.Header.PLP;
import pom_scripts.web.Header.Plp.PDP;

public class HM055 extends Base_Test {
	@Test(priority = 1)
	public void main() throws Exception, Exception {
		Home home = new Home(driver);
		Header header = new Header(driver);
		PLP plp = new PLP(driver);
		js = (JavascriptExecutor) driver;
		PDP pdp = new PDP(driver);
		WebDriverWait w = new WebDriverWait(driver, 1);
		wait.until(ExpectedConditions.elementToBeClickable(home.getIlldothislater()));
		home.getIlldothislater().click();

		boolean b = header.getPantaloons().isDisplayed();
		Assert.assertEquals(b, true);
		/// setps upto 4 completed
		int start = 1, end = 2;
		Object[][] values = ReadExcel.getMultipleData("./TestData/Men_Category.xlsx", "TOP WEAR", 1, 2);
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[0].length; j++) {
				// System.out.println(i+" "+j+" "+values[i][j]);
				String val = values[i][j].toString();
				startIteration(val);
			}
			// System.out.println();
		}

	}

	private void startIteration(String val) {
		Home home = new Home(driver);
		PLP plp = new PLP(driver);
		Header header = new Header(driver);
		WebElement ele = home.getcategory("MEN");
		action.moveToElement(ele).perform();
		// Wait till Sub Catgeory link in Header page is visible
		ele = header.getSub_Catgeory_Link(val);
		ele.click();
		// Verify if First Product image is displayed in PLP page
		boolean b = plp.getFirst_Product_Image().isDisplayed();
		Assert.assertEquals(b, true);
		// 5 Verify if HEADING TITLE PLP text is displayed in PLP page
		b = plp.getHEADING_TITLE_PLP_Text().isDisplayed();
		Assert.assertEquals(b, true);
		// Get text from BreadcrumbTwo text in PLP page
		String breadcrumbSubCatagory = plp.getBreadcrumbTwo_Text().getText();
		// Print Value of breadcrumbSubCatagory
		System.out.println(breadcrumbSubCatagory);
		// Verify if string breadcrumbSubCatagory contains string Men Category:TOP WEAR
		b = (val.equalsIgnoreCase(breadcrumbSubCatagory));
		Assert.assertEquals(b, true);

	}

}
