package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import abstractComponents.ReusableComponents;

public class CartPage extends ReusableComponents{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css= ".cartSection h3")
	List<WebElement> productTitles;
	
	@FindBy(css= ".totalRow button")
	WebElement checkoutEle;
	
	
	public boolean verifyProductDisplay(String productName) {
		productTitles.stream().map(s->s.getText()).forEach(s->System.out.println(s));
		Boolean match= productTitles.stream().anyMatch(cartProduct->cartProduct.getText().equals(productName));
		return match;
	}
	
	public CheckOutPage goToCheckOut() {
		checkoutEle.click();
		CheckOutPage checkoutPage = new CheckOutPage(driver);
		return checkoutPage;
		
	}
	

}
