package E2ETesting;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import pageObjects.CheckOutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatalogue;
import pageObjects.CartPage;

public class SubmitOrderTest {

	public static void main(String[] args) throws InterruptedException {
		String productName = "ZARA COAT 3";
		//WebDriver driver = new FirefoxDriver();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		//homePage
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goToUrl();
		//create object at the end step of landing page instead of creating a new one here
		ProductCatalogue productCatalogue=landingPage.loginAppilication("gerev16419@ebuthor.com", "Tester@12");
		Thread.sleep(2000);
		//productsPage
		//ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products= productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage(); //we can access this method from productCatologue class as it is extending parent class
		boolean match =cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match); //no assertions should be in page objects
		cartPage.scrollElm();
		Thread.sleep(3000);
		CheckOutPage checkoutPage = cartPage.goToCheckOut();
		
		checkoutPage.selectCountry("Ind");
		ConfirmationPage confirmationPage= checkoutPage.submitOrder();
		
		String orderConfirmMsg= confirmationPage.confirmationMsg();
		Assert.assertTrue(orderConfirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		Thread.sleep(1000);
		driver.close();
		
	}

}
