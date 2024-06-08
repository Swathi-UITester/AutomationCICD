package abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.OrdersPage;
import pageObjects.CartPage;

public class ReusableComponents {
	WebDriver driver;

	public ReusableComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="button[routerLink*='myorders']")
	WebElement orders;
	
	public WebDriverWait webDriverWait() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait;
	}

	public void waitForElementToAppear(By findBy) {
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToAppear(WebElement findBy) {
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
			webDriverWait().until(ExpectedConditions.visibilityOf(findBy));
		}
	
	public void waitForElementToDisappear(By findBy) {
		webDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void scrollElm() {
		JavascriptExecutor js =  (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
	}
	
	public CartPage goToCartPage() {
		cartHeader.click();
		CartPage cartPage= new CartPage(driver);
		return cartPage;
	}
	
	public OrdersPage goToOrdersPage() {
		orders.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
		
	}
	
	
	
	
	
}
