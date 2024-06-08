package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.ReusableComponents;

public class LandingPage extends ReusableComponents{

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		//initialization
		this.driver = driver;
		PageFactory.initElements(driver, this); //giving pagefactory the knowledge of driver
	}
	
	//driver.findElement(By.id("userEmail")).sendKeys("gerev16419@ebuthor.com");
	/*using page factory */
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css= "div[class*='toast-message']")
	WebElement errorMsg;
	
	
	//clubbing to perform login action
	public ProductCatalogue loginAppilication(String email, String pwd) {
	userEmail.sendKeys(email);
	password.sendKeys(pwd);
	submit.click();
	//creating object for productcatalogue page here itself as it goes to products page upon clicking submit btn
	ProductCatalogue productCatalogue = new ProductCatalogue(driver);
	return productCatalogue;
	}
	
	public void goToUrl() {
		driver.get("https://rahulshettyacademy.com/client");	
	}
	
	public String errorMessage() {
		waitForElementToAppear(errorMsg);
		System.out.println(errorMsg.getText());
		return errorMsg.getText();
		
	}
		
	}
