package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.ReusableComponents;

public class CheckOutPage extends ReusableComponents {
WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath= "//input[@placeholder='Select Country']")
	WebElement select;
	@FindBy(css="section >button:nth-child(3)")
	WebElement India;
	By result= By.cssSelector("section.ta-results");
	@FindBy(className= "action__submit")
	WebElement submit;
	
	public void selectCountry(String country) {
		Actions a = new Actions(driver);
		a.sendKeys(select,country).build().perform();
		waitForElementToAppear(result);
		India.click();
	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
}
