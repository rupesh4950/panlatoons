package pom_scripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import generic.JavaScriptUtil;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(xpath="//strong[text()='Featured products']")
	private WebElement featured;
	public WebElement getFeatured() {
		return featured;
	}
	public void setFeatured(WebElement featured) throws InterruptedException {
		this.featured = featured;
		
	}
	@FindBy(xpath="//a[text()='Log in']")
	private WebElement login;
	public WebElement getLogin() {
		return login;
	}
	public void setLogin(WebElement login) {
		this.login = login;
	}
	

}
