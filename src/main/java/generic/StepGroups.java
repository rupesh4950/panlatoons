package generic;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import pom_scripts.web.Footer;
import pom_scripts.web.Home;
import pom_scripts.web.Header.Bag;
import pom_scripts.web.Header.Header;
import pom_scripts.web.Header.Login;
import pom_scripts.web.Header.My_Account;
import pom_scripts.web.Header.PLP;
import pom_scripts.web.Header.Wishlist;
import pom_scripts.web.Header.Checkout.Address;
import pom_scripts.web.Header.Checkout.Checkout;
import pom_scripts.web.Header.Plp.PDP;
import pom_scripts.web.Header.Plp.Quick_View;

public class StepGroups extends UtilityMethod {
	public static WebDriver driver;

	public StepGroups(WebDriver d) {
		driver = d;
		a = new Actions(driver);
		header = new Header(driver);
		plp = new PLP(driver);
		pdp = new PDP(driver);
		home = new Home(driver);
		Login login = new Login(driver);
		myAccount = new My_Account(d);
		wishlist = new Wishlist(driver);
		quickView = new Quick_View(driver);
		bag = new Bag(driver);
		checkout=new Checkout(driver);
		address=new Address(driver);
		wait=new WebDriverWait(driver, 20);
		footer = new Footer(driver);
	}
	Checkout checkout;
	Actions a;
	Home home;
	Header header;
	PLP plp;
	PDP pdp;
	Login login;
	My_Account myAccount;
	Wishlist wishlist;
	Quick_View quickView;
	Bag bag;
	Address address;
	Footer footer;
	String productBrandPLP, productNamePLP, productPricePLP;
	String ProductBrandPDP, productNamePDP, ProductPricePDP;

	public void Navigate_to_PLP_Page(String Category, String SubCategory) {

		a.moveToElement(home.getcategory(Category)).perform();
		// Wait till Sub Catgeory link in Header page is visible
		wait.until(ExpectedConditions.visibilityOf(header.getSub_Catgeory_Link(SubCategory)));
		header.getSub_Catgeory_Link(SubCategory).click();
		boolean b = plp.getFirst_Product_Image().isDisplayed();
		Assert.assertEquals(b, true);
	}

	public void Navigate_to_PDP_Page() throws Exception {
		Get_Product_Details_In_PLP_Copy();
		Get_Product_Details_In_PDP();
		Verify_PLP_and_PDP_Products_are_same();
	}

	public void Get_Product_Details_In_PLP_Copy() {
		boolean b = plp.getProduct_Brand_text().isDisplayed();
		Assert.assertEquals(b, true);
		// Get text from Product Brand text in PLP page
		productBrandPLP = plp.getProduct_Brand_text().getText();
		// System.out.println(productBrandPLP);
		// Verify if Product Name text is displayed in PLP page
		b = plp.getProduct_Name_text().isDisplayed();
		Assert.assertEquals(b, true);
		// Get text from Product Name text in PLP page
		productNamePLP = plp.getProduct_Name_text().getText();
		// Verify if Product Price text is displayed in PLP page
		b = plp.getProduct_Price_text().isDisplayed();
		Assert.assertEquals(b, true);
		productPricePLP = plp.getProduct_Price_text().getText();
		productPricePLP = productPricePLP.replaceAll("\\s", "");
	}

	public void Get_Product_Details_In_PDP() throws Exception {
		String val1 = "(//div[contains(@class,'PlpWeb_product-card-content')])",
				val2 = "(//div[@class='size size-web ']//span)[1]";
		WEB_VerifyUserIsAbleToSelectProductOfAvailableSize(val1, val2, 5);
		wait.until(ExpectedConditions.visibilityOf(pdp.getProduct_Name_text()));
		boolean b = pdp.getProduct_Brand_text().isDisplayed();
		Assert.assertEquals(b, true);
		ProductBrandPDP = pdp.getProduct_Brand_text().getText();
		// System.out.println(ProductBrandPDP);
		// Verify if Product Name text is displayed in PDP page
		b = pdp.getProduct_Name_text().isDisplayed();
		Assert.assertEquals(b, true);
		productNamePDP = pdp.getProduct_Name_text().getText();
		// Verify if Product Price text is displayed in PDP page
		b = pdp.getProduct_Price_text().isDisplayed();
		Assert.assertEquals(b, true);
		// Get text from Product Price text in PDP page
		ProductPricePDP = pdp.getProduct_Price_text().getText();
		ProductPricePDP = ProductPricePDP.replaceAll("\\s", "");

	}

	public void WEB_VerifyUserIsAbleToSelectProductOfAvailableSize(String productLocator, String sizeLocator,
			int numberOfAttempts) throws Exception {
		List<WebElement> availableSizes = new ArrayList<WebElement>();
		for (int i = 1; i <= numberOfAttempts; i++) {
			//System.out.println(productLocator + "[" + i + "]");
			action.click(driver.findElement(By.xpath(productLocator + "[" + i + "]"))).perform();
			List<WebElement> dressSizes = driver.findElements(By.xpath(sizeLocator));
			int sizes = dressSizes.size();

			if (sizes >= 1) {
				availableSizes.add(dressSizes.get(0));
				break;
			} else {
				Thread.sleep(2000);
				//System.out.println("navigate back");
				driver.navigate().back();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (availableSizes.isEmpty()) {
			System.out.println("failed");
			System.out.println("Could not find the product sizes for numberOfAttempts products");

		} else {
			WebElement availableSize = availableSizes.get(0);
			availableSize.click();

		}

	}

	public void Verify_PLP_and_PDP_Products_are_same() {
		boolean b = productBrandPLP.equals(ProductBrandPDP);
		// System.out.println(b);
		// System.out.println(productBrandPLP+" falues is "+ProductBrandPDP);
		Assert.assertEquals(b, true);

		b = ProductPricePDP.equalsIgnoreCase(productPricePLP);
		// System.out.println(ProductPricePDP+" "+productPricePLP);
		// System.out.println(b);
		Assert.assertEquals(b, true);

	}

	public String Get_OTP_from_Notification_Bar() throws Exception {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.NO_RESET, true);
		URL url = new URL("http://localhost:4723/wd/hub");
		AndroidDriver mobileDriver = new AndroidDriver(url, cap);
		Thread.sleep(5000);
		mobileDriver.openNotifications();
		Thread.sleep(10000);
		String OTP = mobileDriver
				.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/message_text']")).getText();
		System.out.println(OTP);
		String onlyNumberOTP = OTP.replaceAll("[^0-9]", "").trim().substring(0, 6);
		String mob[] = { onlyNumberOTP };
		System.out.println(onlyNumberOTP);
		return onlyNumberOTP;
	}

	public void Login_to_Pantaloons(String Mobile_Number) throws Exception {
		home.getMy_Account_icon().click();
		// Verify if Mobile Number textfield is displayed in Login page
		boolean b = login.getMobile_Number_textfield().isDisplayed();
		Assert.assertEquals(b, true);
		// Enter Mobile Number into Mobile Number textfield in Login page
		login.getMobile_Number_textfield().sendKeys(Mobile_Number);
		String validOtp = Get_OTP_from_Notification_Bar();
		// Click on Start Shopping button in Login page
		login.getStart_Shopping_button().click();
		String pantaloonsUrl = "https://www.pantaloons.com/";
		wait.until(ExpectedConditions.urlContains(pantaloonsUrl));
		// Move mouse pointer on My Account icon in Home page
		action.moveToElement(home.getMy_Account_icon()).perform();
		// Move mouse pointer on Pantaloons Logo image in Home page
		action.moveToElement(home.getPantaloons_Logo()).perform();
		action.moveToElement(home.getMy_Account_icon()).perform();
		// Verify if LOGOUT button is displayed in My Account page
		b = myAccount.getLOGOUT_button().isDisplayed();
		Assert.assertEquals(b, true);
	}

	public void Remove_Multiple_Products_From_Wishlist() throws Exception {
		WebElement ele = header.getWishlist_icon();
		js.executeScript("arguments[0].click();", ele);
		RemoveMultipleProductsFromWishlist("//div[contains(@class,\"wishlist_my-wishlist-cross\")]");
		driver.navigate().to("https://www.pantaloons.com/");

	}

	private void RemoveMultipleProductsFromWishlist(String string1) throws Exception {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		List<WebElement> removeIcon = driver.findElements(By.xpath(string1));
		int count = removeIcon.size();
		if (count > 0) {
			for (int i = 0; i <= count; i++) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(string1)));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(string1)));
				driver.findElement(By.xpath(string1)).click();
				Thread.sleep(3000);
			}
			System.out.println("Removed " + count + " Products from the bag");
		} else {
			System.out.println("Bag is empty");
		}

	}

	public void Verify_PLP_and_Wishlist_Product_are_same() {
		boolean b = productBrandPLP.equals(productBrandWishlist);
		// System.out.println(b);
		// System.out.println(productBrandPLP+" falues is "+ProductBrandPDP);
		Assert.assertEquals(b, true);
		productPriceWishlist = productPriceWishlist.replaceAll("\\s", "");
		b = productPricePLP.contains(productPriceWishlist);
		
		Assert.assertEquals(b, true);
	}

	String productBrandWishlist, productNameWishlists, productPriceWishlist;

	public void Get_Product_Details_in_Wishlist() {
		// Verify if Product Brand text is displayed in Wishlist page
		boolean b = wishlist.getProduct_Brand_text().isDisplayed();
		Assert.assertEquals(b, true);
		// Get text from Product Brand text in Wishlist page
		productBrandWishlist = wishlist.getProduct_Brand_text().getText();
		// Verify if Product Name text is displayed in Wishlist page
		b = wishlist.getProduct_Name_text().isDisplayed();
		Assert.assertEquals(b, true);
		// Get text from Product Name text in Wishlist page
		productNameWishlists = wishlist.getProduct_Name_text().getText();
		// Verify if Product Price text is displayed in Wishlist page
		b = wishlist.getProduct_Price_text().isDisplayed();
		Assert.assertEquals(b, true);
		productPriceWishlist = wishlist.getProduct_Price_text().getText();
		productPriceWishlist = productPriceWishlist.replaceAll("\\s", "");
	}

	String deleteIcon, RemoveButton;

	public void Delete_Multiple_Products_From_Bag() {
		// Click on Pantaloons Logo image in Home page
	//	System.out.println("image click");
		home.getPantaloons_Logo().click();
	//	System.out.println("image click falied");
		// Click on Bag icon in Header page
		header.getBag_icon().click();
		try {
			WebDriverWait w=new WebDriverWait(driver, 2);
			w.until(ExpectedConditions.visibilityOf(header.getBag_icon()));
			header.getBag_icon().click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//header.getBag_icon().click();
		// Press PAGE_DOWN for n times
		for (int i = 0; i < 10; i++) {
			action.sendKeys(Keys.PAGE_DOWN).perform();
		}
		// Press PAGE_UP for n times
		for (int i = 0; i < 10; i++) {
			action.sendKeys(Keys.PAGE_UP).perform();
		}
		// DeleteMutlipeProductsFromBag in WEB_Scenario page
		deleteIcon = "//img[@title=\"Remove item\"]";
		RemoveButton = "//button[text()=\"REMOVE\"]";
		DeleteMutlipeProductsFromBag(deleteIcon, RemoveButton);
		// Click on Pantaloons Logo image in Home page
		wait.until(ExpectedConditions.elementToBeClickable(home.getPantaloons_image()));
		home.getPantaloons_Logo().click();

	}

	public void DeleteMutlipeProductsFromBag(String DeleteIcon, String removeButton) {
		try {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 20);
			List<WebElement> numDeleteIcons = driver.findElements(By.xpath(DeleteIcon));
			try {
				if (numDeleteIcons.size() > 0) {
					for (int i = 1; i <= numDeleteIcons.size(); i++) {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DeleteIcon)));
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DeleteIcon))).click();
						Thread.sleep(1000);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RemoveButton)));
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(RemoveButton))).click();
						Thread.sleep(1000);
						System.out.println(numDeleteIcons.size() + "  Product Deleted Successfully");
					}
				} else {
					driver.navigate().refresh();
					if (driver.findElement(By.xpath("//div[text()='Oops!']")).isDisplayed()) {
						System.out.println("Oops sorry no products found");

					}
				}
			} catch (Exception e) {

				System.out.println("Failed to delete the product from bag" + e);
			}
		} catch (Exception e) {

			System.out.println("Failed to delete the product from bag" + e);
		}

	}

	String productNameQuickView, productBrandQuickView, productPriceQuickView;

	public void Get_Product_Details_in_Quick_View() {
		// Verify if Product Name text is displayed in Quick View page
		boolean b = quickView.getProduct_Name_text().isDisplayed();
		Assert.assertEquals(b, true);
		// Get text from Product Name text in Quick View page
		productNameQuickView = quickView.getProduct_Name_text().getText();
		// Verify if Product Brand text is displayed in Quick View page
		b = quickView.getProduct_Brand_text().isDisplayed();
		Assert.assertEquals(b, true);
		productBrandQuickView = quickView.getProduct_Brand_text().getText();
		// Verify if Product Price text is displayed in Quick View page
		b = quickView.getProduct_Price_text().isDisplayed();
		// Get text from Product Price text in Quick View page
		productPriceQuickView = quickView.getProduct_Price_text().getText();

	}

	String firstProductBrandNameBag, firstProductNameBag, productActualPriceBag;

	public void Get_Product_Details_From_Bag_Page() {
		// Verify if My Bag text is displayed in Bag page
		Boolean b = bag.getMy_Bag_Page_text().isDisplayed();
		Assert.assertEquals(true, b);
		// Verify if ProductBrandName_Bag text is displayed in Home page
		b = home.getProductBrandName_Bag_text().isDisplayed();
		Assert.assertEquals(true, b);
		// Get text from ProductBrandName_Bag text in Home page
		firstProductBrandNameBag = home.getProductBrandName_Bag_text().getText();
		// Get text from ProductName_Bag text in Bag page
		firstProductNameBag = bag.getProductName_Bag_text().getText();
		// Get text from Product_Actual_Price_text in Bag page
		productActualPriceBag = bag.getProduct_Actual_Price_text().getText();
	}

	public void Verify_Quick_View_and_Bag_Products_are_same() {
		// Verify if string firstProductBrandNameBag matches string
		// productBrandQuickView
		boolean b = firstProductBrandNameBag.matches(productBrandQuickView);
		Assert.assertEquals(true, b);
		// Verify if string firstProductNameBag matches string productNameQuickView
		b = firstProductNameBag.matches(productNameQuickView);
		Assert.assertEquals(true, b);
		// Verify if string productDiscountpriceBag contains string
		// productPriceQuickView
		b = productActualPriceBag.contains(productPriceQuickView);
		System.out.println("values");
		System.out.println(productActualPriceBag);
		System.out.println(productPriceQuickView);
		Assert.assertEquals(true, b);

	}

	public void Login_through_POP_UP(String Mobile_Number) throws Exception {
	boolean b = login.getMobile_Number_textfield().isDisplayed();
	Assert.assertEquals(true, b);
	login.getMobile_Number_textfield().sendKeys(Mobile_Number);
	String otp = Get_OTP_from_Notification_Bar();
	//Click on Start Shopping button in Login page
	login.getStart_Shopping_button().click();
	}

	public void verfiyforPDP034() {
		boolean b = productBrandWishlist.matches(ProductBrandPDP);
		Assert.assertEquals(true, b);
		b=productNameWishlists.matches(productNamePDP);
		Assert.assertEquals(true, b);
	}

	public void checkValues(String productbrand, String productname) {
		boolean b=productbrand.matches(ProductBrandPDP);
		Assert.assertEquals(true, b);
		b=productname.matches(productNamePDP);
//		System.out.println(productname+" null ");
//		System.out.println(productNamePDP+"  null");
//		System.out.println("completed");
		Assert.assertEquals(true, b);
		
	}

	public void Add_product_to_Bag_from_Quick_View() {
		//Wait till Select Size button in Quick View page is clickablew
		wait.until(ExpectedConditions.elementToBeClickable(quickView.getSelect_Size_button())).click();;
		//Press PAGE_DOWN for n times
		int n=1;
		for(int i=0;i<n;i++) {
			action.sendKeys(Keys.PAGE_DOWN).perform();
		}
		//Wait till ADD TO BAG button in Quick View page is clickable
		wait.until(ExpectedConditions.elementToBeClickable(quickView.getADD_TO_BAG_button())).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='GO TO BAG']")));
		//Verify if GO TO BAG button is displayed in Quick View page
		boolean b = quickView.getGO_TO_BAG_button().isDisplayed();
		Assert.assertEquals(b, true);
		//Press PAGE_UP for n times
		n=2;
		for(int i=0;i<n;i++) {
			action.sendKeys(Keys.PAGE_UP).perform();
		}
		//Wait till Quick_View_Close_icon in Quick View page is clickable
		wait.until(ExpectedConditions.elementToBeClickable(quickView.getQuick_View_Close_icon())).click();
		//Wait till Bag icon in Header page is clickable
		wait.until(ExpectedConditions.elementToBeClickable(header.getBag_icon())).click();
		
		
		
		
		
	}

	public void Naviagte_To_Checkout_With_FIlled_Address() {
		//Navigate to Add Address With Login sg
		Navigate_to_Add_Address_With_Login();
		//Verify if Add_new_address_text is displayed in Address page
		boolean b = address.getAdd_new_address_text().isDisplayed();
		Assert.assertEquals(true, b);
		//Verify if FIRST NAME textfield is displayed in Address page
		b=address.getFirst_Name_textfield().isDisplayed();
		Assert.assertEquals(true, b);
		//Verify if FIRST NAME textfield in Address page is clickable
		wait.until(ExpectedConditions.elementToBeClickable(address.getFirst_Name_textfield()));
		//Clear text from FIRST NAME textfield in Address page using shortcut key
		address.getFirst_Name_textfield().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		//Generate random string with length 4 characters
		 userName = Generate_random_string_with_length(4);
		//Enter userName into FIRST NAME textfield in Address page
		address.getFirst_Name_textfield().sendKeys(userName);
		//Get *attributeName* attribute value of FIRST NAME textfield in Address page
		value=address.getFirst_Name_textfield().getAttribute("value");
		//Verify if string userName contains string value
		b=userName.contains(value);
//		System.out.println(userName);
//		System.out.println(value);
		Assert.assertEquals(true, b);
		//Verify if Last_Name_textfield is displayed in Address page
		b=address.getLast_Name_textfield().isDisplayed();
		Assert.assertEquals(true, b);
		//Verify if Last Name textfield in Address page is clickable
		wait.until(ExpectedConditions.elementToBeClickable(address.getLast_Name_textfield()));
		//Clear text from Last Name textfield in Address page using shortcut key
		address.getLast_Name_textfield().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		//Generate random string with length 5 characters
		lastName=Generate_random_string_with_length(5);
		//Enter lastName into Last Name textfield in Address page
		address.getLast_Name_textfield().sendKeys(lastName);
		//Get *attributeName* attribute value of Last Name textfield in Address pag
		value=address.getLast_Name_textfield().getAttribute("value");
		//Verify if string lastName contains string value
		b=lastName.contains(value);
		Assert.assertEquals(true, b);
		//Verify if Mobile_Number_textfield is displayed in Address page
		b=address.getMobile_Number_textfield().isDisplayed();
		Assert.assertEquals(true, b);
		//Verify if Mobile Number textfield in Address page is clickable
		wait.until(ExpectedConditions.elementToBeClickable(address.getMobile_Number_textfield()));
		//Clear text from Mobile Number textfield in Address page using shortcut key
		address.getMobile_Number_textfield().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		//Generate random number with length 9 digits
		mobNumberValue=Generate_random_number_with_length_digits(9);
		//Concatenate 9 with mobNumberValue
		mobNumberValue=("9"+mobNumberValue);
		//Enter mobNumberValue into Mobile Number textfield in Address page
		address.getMobile_Number_textfield().sendKeys(mobNumberValue);
		//Get *attributeName* attribute value of Mobile Number textfield in Address page
		value=address.getMobile_Number_textfield().getAttribute("value");
		//Verify if string mobNumberValue contains string value
		b=mobNumberValue.contains(value);
		//Scroll page vertically until visibility of Use Current Location link in Address page
		js.executeScript("arguments[0].scrollIntoView(true);", address.getUse_Current_Location_link());
		//Verify if PINCODE_textfield is displayed in Address page
		b=address.getPINCODE_textfield().isDisplayed();
		Assert.assertEquals(true, b);
		//Verify if PINCODE textfield in Address page is clickable
		wait.until(ExpectedConditions.elementToBeClickable(address.getPINCODE_textfield()));
		//Clear text from PINCODE textfield in Address page using shortcut key
		address.getPINCODE_textfield().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		//Enter pinCode into PINCODE textfield in Address page
		address.getPINCODE_textfield().sendKeys(pinCode);
		//Get *attributeName* attribute value of PINCODE textfield in Address page
		value=address.getPINCODE_textfield().getAttribute("value");
		//Verify if string pinCode contains string value
		b=pinCode.contains(value);
		Assert.assertEquals(true, b);
		//Verify if House_no__Building_name_textfield is displayed in Address page
		b=address.getHouse_no__Building_name_textfield().isDisplayed();
		//Enter houseNumber into House no/ Building name textfield in Address page
		address.getHouse_no__Building_name_textfield().sendKeys(houseNumber);
		//Get *attributeName* attribute value of House no/ Building name textfield in Address page
		value=address.getHouse_no__Building_name_textfield().getAttribute("value");
		//Verify if string houseNumber contains string value
		b=houseNumber.contains(value);
		Assert.assertEquals(true, b);
		//Scroll page vertically until visibility of Pin_code*_text in Address page
		js.executeScript("arguments[0].scrollIntoView(true);", address.getPin_code_star_text());
		//Verify if street_textfield is displayed in Address page
		b=address.getstreet_textfield().isDisplayed();
		Assert.assertEquals(true, b);
		//Verify if street textfield in Address page is clickable
		wait.until(ExpectedConditions.elementToBeClickable(address.getstreet_textfield()));
		//Enter streetName into street textfield in Address page
		address.getstreet_textfield().sendKeys(streetName);
		//Get *attributeName* attribute value of street textfield in Address page
		value=address.getstreet_textfield().getAttribute("value");
//		/Verify if string streetName contains string value
		b=streetName.contains(value);
		Assert.assertEquals(true, b);
		//Verify if area_textfield is displayed in Address page
		b=address.getarea_textfield().isDisplayed();
		//Verify if area textfield in Address page is clickable
		wait.until(ExpectedConditions.elementToBeClickable(address.getarea_textfield()));
		//Enter streetName into area textfield in Address page
		address.getarea_textfield().sendKeys(Area);
		//Get value attribute value of area textfield in Address page
		value=address.getarea_textfield().getAttribute("value");
		//Verify if string Area contains string value
		b=Area.contains(value);
		Assert.assertEquals(true, b);
		//Enter landmark into landmark_textfield in Address page
		address.getlandmark_textfield().sendKeys(landmark);
		//Get value attribute value of landmark textfield in Address page
		value=address.getlandmark_textfield().getAttribute("value");
		//Verify if string landmark contains string value
		b=landmark.contains(value);
		Assert.assertEquals(true, b);
		//Verify if Address_Type_text is displayed in Address page
		b=address.getAddress_Type_text().isDisplayed();
		Assert.assertEquals(true, b);
		
	}
	String landmark="landmark";
	String streetName="street 267 road",Area="3rd cross,bangalore";
	String userName,value,lastName,mobNumberValue,pinCode="560079",houseNumber="Building 24 5th cross";
	public String Generate_random_number_with_length_digits(int n){
		int max=9,min=0;
		String temp="";
		for(int i=0;i<n;i++) {
		int k	=(int)(Math.random()*((max-min+1)+min))  ;
		temp+=(k)+"";
		}
		//System.out.println(temp);
		return temp;
	}
	public String Generate_random_string_with_length(int n) {
		int max=25,min=1;
		String temp="";
		for(int i=0;i<n;i++) {
		int k	=(int)(Math.random()*((max-min+1)+min))  ;
		temp+=(char)(97+k);
		}
		return temp;
	}
	
	public void Navigate_to_Add_Address_With_Login() {
		//Add product to Bag from Quick View
		Add_product_to_Bag_from_Quick_View();
		//Verify if My Bag text is displayed in Bag page
		boolean b = bag.getMy_Bag_Page_text().isDisplayed();
		Assert.assertEquals(true, b);
		//Click on CHECKOUT button in Bag page
		bag.getCHECKOUT_button().click();
		//Verify if Checkout text is displayed in Checkout page
		b=checkout.getCheckout_text().isDisplayed();
		Assert.assertEquals(true, b);
		//Click on Plus icon in Address page
		address.getPlus_icon().click();
		//Press PAGE_DOWN key
		action.sendKeys(Keys.PAGE_DOWN).perform();
		//Click on ADD_NEW_ADDRESS_button in Address page
		try {
			js.executeScript("arguments[0].scrollIntoView(true);", address.getADD_NEW_ADDRESS_button());
			address.getADD_NEW_ADDRESS_button().click();
		} catch (Exception e) {
			js.executeScript("arguments[0].scrollIntoView(true);", address.getADD_NEW_ADDRESS_button());
			address.getADD_NEW_ADDRESS_button().click();
		}
		
		//Verify if First_Name_textfield is displayed in Address page
		b=address.getFirst_Name_textfield().isDisplayed();
		Assert.assertEquals(true, b);
		//only 10 steps
		
	}

	public void checkLast() {
		Boolean b=address.getSpecific_Address_text(userName).isDisplayed();
		Assert.assertEquals(true, b);
	}
	String offPercentage;
	String firstProductAmount, productActualpricePLP;

	public void Add_the_Product_From_Category_to_Bag_with_All_Verifications() {
		// Verify if Pantaloons Logo image is displayed in Home page
		boolean b = home.getPantaloons_image().isDisplayed();
		Assert.assertEquals(b, true);
		String category = "WOMEN", subCategoryWeb = "Tees & Tops";
		Navigate_to_PLP_Page(category, subCategoryWeb);
		// Click on SORT BY dropdown in PLP page
		plp.getSORT_BY_dropdown().click();
		// Click on Select Sort By text in PLP page
		plp.getSelect_Sort_By_text("Discount");
		// Scroll page vertically until visibility of About_link in Footer page
		js.executeScript("arguments[0].scrollIntoView(true);", footer.getAbout_link());
		// Scroll page vertically until visibility of BreadcrumbTwo text in PLP page
		js.executeScript("arguments[0].scrollIntoView(true);", plp.getBreadcrumbTwo_Text());
		// Scroll page vertically until visibility of HEADING TITLE PLP text in PLP page
		js.executeScript("arguments[0].scrollIntoView(true);", plp.getHEADING_TITLE_PLP_Text());
		// Wait till First Product image in PLP page is clickable
		wait.until(ExpectedConditions.elementToBeClickable(plp.getFirst_Product_Image()));
		// Verify if Product Name text is displayed in PLP page
		b = plp.getProduct_Name_text().isDisplayed();
		Assert.assertEquals(b, true);
		// Get text from Product Name text in PLP page
		String productNamePLP = plp.getProduct_Name_text().getText();
		Reporter.log(productNamePLP);
		// Verify if Product Brand text is displayed in PLP page
		b = plp.getProduct_Brand_text().isDisplayed();
		Assert.assertEquals(b, true);
		productBrandPLP = plp.getProduct_Brand_text().getText();
		Reporter.log(productBrandPLP);
		b = driver.findElement(By.xpath("//div[contains(@class,\"PlpWeb_product-price\")]")).isDisplayed();
		
		if (b) {
			// Verify if Product Price text is displayed in PLP page
			boolean bb = plp.getProduct_Price_text().isDisplayed();
			Assert.assertEquals(bb, true);
			// Get text from Product Price text in PLP page
			productPricePLP = plp.getProduct_Price_text().getText();
			boolean temp = driver.findElement(By.xpath(
					"(//div[contains(@class,'PlpWeb_products-count')]/../../../div[3]/div/div/div/a/div[4]/div[3]/span[1])[1]"))
					.isDisplayed();
			System.out.println(temp);
			if (temp) {

				firstProductAmount = getDiscount(plp.getProduct_Price_text());
				//System.out.println("firstProductAmount" +firstProductAmount);
			}
			b = driver.findElement(By.xpath("//s[@class='actual-price']")).isDisplayed();
			if (b) {
				// Verify if Actual_Price_text is displayed in PLP page
				b = plp.getActual_Price_text().isDisplayed();
				Assert.assertEquals(b, true);
				plp.getActual_Price_text().getText();
			}
			
			b = driver.findElement(By.xpath("//span[@class='discount discountHighlight']")).isDisplayed();
			if (b) {
				b = plp.getOFF_Percentage_text().isDisplayed();
				Assert.assertEquals(b, true);
				offPercentage = plp.getOFF_Percentage_text().getText();
			}
		}
		// Click on First Product image in PLP page
		plp.getFirst_Product_Image().click();
		/// Verify if ProductBrandName_PDP text is displayed in Home page
		b = home.getProductBrandName_PDP_text().isDisplayed();
		firstProductBrandNamePDP = home.getProductBrandName_PDP_text().getText();
		// Get text from Product Name text in PDP page
		productNamePDP = pdp.getProduct_Name_text().getText();
		b = productBrandPLP.contains(firstProductBrandNamePDP);
		Assert.assertEquals(b, true);
		// Get text from Product_Discouted_Price_text in PDP page
		productDiscoutedPricePDP = pdp.getProduct_Discouted_Price_text().getText();
		b = productDiscoutedPricePDP.contains(firstProductAmount);
		Assert.assertEquals(b, true);
		b=false;
		try {
			b = driver.findElement(By.xpath("//span[@class=\"discount discountHighlight pdpnodealpage\"]")).isDisplayed();
		} catch (Exception e) {
			b=false;
		}
		if (b) {
			System.out.println("if block");
			// Get text from Product Actual Amount text in PDP page
			ProductActualPricePDP=pdp.getProduct_Actual_Amount_text().getText();
			// Verify if OFF_PDP_text is displayed in PDP page
			b = pdp.getOFF_PDP_text().isDisplayed();
			Assert.assertEquals(b, true);
			// Get text from OFF_PDP text in PDP page
			offPercentagePDP = pdp.getOFF_PDP_text().getText();
//			System.out.println(productActualpricePLP+"   null");
//			System.out.println(ProductActualPricePDP+"   null");
			b = productActualpricePLP.contains(ProductActualPricePDP);
			Assert.assertEquals(b, true);
			b = offPercentage.contains(offPercentagePDP);
			Assert.assertEquals(b, true);
		}
		// Verify if SELECT_SIZE_text is displayed in PDP page
		b = pdp.getSELECT_SIZE_text().isDisplayed();
		Assert.assertEquals(b, true);
		// Verify if Select Size button in PDP page is clickable
		wait.until(ExpectedConditions.elementToBeClickable(pdp.getSelect_Size_button()));
		// Get text from SELECT_AVAILABLE_SIZE_button in PDP page
		selectedSizePDP = pdp.getSELECT_AVAILABLE_SIZE_button().getText();
		// Click on SELECT AVAILABLE SIZE button in PDP page
		pdp.getSELECT_AVAILABLE_SIZE_button().click();
		// Scroll page vertically until visibility of QUANTITY_text in PDP page
		js.executeScript("arguments[0].scrollIntoView(true);", pdp.getQUANTITY_text());
		quantityPDP = pdp.getQUANTITY_text().getAttribute("value");
		// Verify if value of QUANTITY text in PDP page contains *expectedValue*
		b = pdp.getQUANTITY_text().getAttribute("value").contains(quantityPDP);
		//System.out.println(pdp.getQUANTITY_text().getText());
	//	System.out.println(quantityPDP);
		Assert.assertEquals(b, true);
		// Wait till ADD TO BAG button in PDP page is visible
		wait.until(ExpectedConditions.visibilityOf(pdp.getADD_TO_BAG_button()));
		// Verify if ADD TO BAG button in PDP page is clickable
		wait.until(ExpectedConditions.elementToBeClickable(pdp.getADD_TO_BAG_button()));
		// Move mouse pointer on ADD TO BAG button in PDP page
		action.moveToElement(pdp.getADD_TO_BAG_button()).perform();
		// Click on current cursor point
		action.click().perform();
		// Wait till MyBagBagPopUp text in Bag page is visible
		wait.until(ExpectedConditions.visibilityOf(bag.getMyBagBagPopUp_text()));
		// Click on VIEW_BAG_button in PDP page using javascript executor
		js.executeScript("arguments[0].click();", pdp.getVIEW_BAG_button());
		// Verify if My Bag text is displayed in Bag page
		b = bag.getMy_Bag_Page_text().isDisplayed();
		Assert.assertEquals(b, true);
		// Verify if ProductBrandName_Bag text is displayed in Home page
		b = home.getProductBrandName_Bag_text().isDisplayed();
		Assert.assertEquals(b, true);
		// Get text from ProductBrandName_Bag text in Home page
		firstProductBrandNameBag = home.getProductBrandName_Bag_text().getText();
		// Get text from ProductName_Bag text in Bag page
		firstProductNameBag = bag.getProductName_Bag_text().getText();
		// Get text from Product Actual Price text in Bag page
		productDiscountpriceBag = bag.getProduct_Actual_Price_text().getText();
		//System.out.println(productDiscountpriceBag);
		//System.out.println((productDiscountpriceBag.split(" ")).length);
		//productDiscountpriceBag = getDiscount(bag.getProduct_Actual_Price_text());
		// Get text from Size dropdown in Bag page
		selectedSize_Bag = bag.getSize_text().getText();
		// Get value attribute value of Quantity Count dropdown in Bag page
		String quantityCount = bag.getQuantity_Count_dropdown().getAttribute("value");
		b=false;
		try {
			b = driver.findElement(By.xpath("//div[contains(@class,'Cart_discounted-price')]/span")).isDisplayed();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (b) {
			// Verify if OFF_Bag_text is displayed in Bag page
			b = bag.getOFF_Bag_text().isDisplayed();
			Assert.assertEquals(b, true);
			// Get text from OFF_Bag text in Bag page
			offPercentageBag = bag.getOFF_Bag_text().getText();
			// Get text from Product_Striked_Price_text in Bag page
			productActualPriceBag = bag.getProduct_Striked_Price_text().getText();
			// Verify if text of OFF_Bag text in Bag page contains *expectedText*
			b = productActualPriceBag.contains(offPercentage);
			Assert.assertEquals(b, true);
			ProductActualPricePDP = WEB_RemoveSpaceFromWords(ProductActualPricePDP);
			// Verify if string ProductActualPricePDP contains string productActualPriceBag
			b = ProductActualPricePDP.contains(productActualPriceBag);
			Assert.assertEquals(b, true);
		}
		// Verify if string firstProductBrandNamePDP contains string
		// firstProductBrandNameBag
		b = firstProductBrandNamePDP.contains(firstProductBrandNameBag);
		Assert.assertEquals(b, true);
		// Verify if string productNamePDP contains string firstProductNameBag
		b = productNamePDP.contains(firstProductNameBag);
		Assert.assertEquals(b, true);
		// Verify if string productDiscountpriceBag contains string
		// productDiscountPricePDP
		productDiscountpriceBag=getOnlyNunber(productDiscountpriceBag);
		productDiscoutedPricePDP=getOnlyNunber(productDiscoutedPricePDP);
		b = productDiscountpriceBag.contains(productDiscoutedPricePDP);
//		System.out.println("new ");
//		System.out.println(productDiscountpriceBag);
//		System.out.println(productDiscoutedPricePDP);
		Assert.assertEquals(b, true);
		// Verify if string quantityCount matches string quantityPDP
		b = quantityCount.contains(quantityPDP);
		Assert.assertEquals(b, true);
		// Verify if string selectedSize_Bag contains string selectedSizePDP
		b = selectedSize_Bag.contains(selectedSizePDP);
		Assert.assertEquals(b, true);
	}
	public String getOnlyNunber(String num) {
		String temp="";
		for(int i=0;i<num.length();i++) {
			char c=num.charAt(i);
			if(c<='9'&&c>='0') {
				temp+=c+"";
			}
		}
		return temp;
	}
	String productDiscountpriceBag,quantityPDP, selectedSize_Bag, offPercentageBag;
	
	String ProductActualPricePDP, offPercentagePDP, selectedSizePDP;
	String firstProductBrandNamePDP, productDiscoutedPricePDP;

	private String WEB_RemoveSpaceFromWords(String ProductPrice) {
		return ProductPrice.replaceAll("\\s", "");

	}

	public String getDiscount(WebElement element) {
		//System.out.println("inside discount");
		String ch = element.getText();
	//	System.out.println(ch);
		String[] ch1 = ch.split(" ");
		String s2 = ch1[1];
	//	System.out.println(s2);
		return s2;
	}

}
