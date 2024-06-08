package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.ReusableComponents;

public class OrdersPage extends ReusableComponents {
WebDriver driver;
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css= "tr td:nth-child(3)")
	List<WebElement> orderslist;
	
	public Boolean verifyOrderDisplay(String productName) {
		Boolean match= orderslist.stream().anyMatch(orders->orders.getText().equals(productName));
		return match;
	}
}
