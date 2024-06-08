package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.ReusableComponents;

public class ProductCatalogue extends ReusableComponents{

	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".col-lg-4")
	List<WebElement> products;
	By productsBy = By.cssSelector(".col-lg-4");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By loadingIcon= By.cssSelector("div.loading-text");
	By toastMsg= By.id("toast-container");
	
	public List<WebElement> getProductsList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) 
	{
		WebElement product= getProductsList().stream().filter(s->s.findElement(By.cssSelector("h5")).
				getText().equals(productName)).findFirst().orElse(null);
		return product;	
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToDisappear(loadingIcon);
		waitForElementToAppear(toastMsg);
		System.out.println(driver.findElement(toastMsg).getText());		
	}
	
	
	
}
