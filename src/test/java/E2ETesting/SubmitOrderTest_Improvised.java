package E2ETesting;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CheckOutPage;
import pageObjects.ConfirmationPage;
import pageObjects.OrdersPage;
import pageObjects.ProductCatalogue;
import pageObjects.CartPage;
import testComponents.BaseTest;

public class SubmitOrderTest_Improvised extends BaseTest{
	String productName = "IPHONE 13 PRO";
	
	 
	//@Test(dataProvider="getData", groups= {"purchase"})
	public void submitOrder(String email, String password, String product) throws IOException, InterruptedException {
	
		//before method will launch the application
	ProductCatalogue productCatalogue=landingPage.loginAppilication(email, password);
	Thread.sleep(2000);
	List<WebElement> products= productCatalogue.getProductsList();
	productCatalogue.addProductToCart(product);
	CartPage cartPage=productCatalogue.goToCartPage();
	boolean match =cartPage.verifyProductDisplay(product);
	Assert.assertTrue(match);
	cartPage.scrollElm();
	Thread.sleep(2000);
	CheckOutPage checkoutPage = cartPage.goToCheckOut();
	
	checkoutPage.selectCountry("Ind");
	ConfirmationPage confirmationPage= checkoutPage.submitOrder();
	
	String orderConfirmMsg= confirmationPage.confirmationMsg();
	Assert.assertTrue(orderConfirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	Thread.sleep(1000);
	//after method will close the browser
	}
	
	//@Test(dataProvider="getData1", groups= {"purchase"})
	public void submitOrder1(HashMap<String,String> input) throws IOException, InterruptedException {
	
		//before method will launch the application
	ProductCatalogue productCatalogue=landingPage.loginAppilication(input.get("email"), input.get("password"));
	Thread.sleep(2000);
	List<WebElement> products= productCatalogue.getProductsList();
	productCatalogue.addProductToCart(input.get("product"));
	CartPage cartPage=productCatalogue.goToCartPage();
	boolean match =cartPage.verifyProductDisplay(input.get("product"));
	Assert.assertTrue(match);
	cartPage.scrollElm();
	Thread.sleep(2000);
	CheckOutPage checkoutPage = cartPage.goToCheckOut();
	
	checkoutPage.selectCountry("Ind");
	ConfirmationPage confirmationPage= checkoutPage.submitOrder();
	
	String orderConfirmMsg= confirmationPage.confirmationMsg();
	Assert.assertTrue(orderConfirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	Thread.sleep(1000);
	//after method will close the browser
	}
	
	@Test(dataProvider="getData2", groups= {"purchase"})
	public void submitOrder2(HashMap<String,String> input) throws IOException, InterruptedException {
	
		//before method will launch the application
	ProductCatalogue productCatalogue=landingPage.loginAppilication(input.get("email"), input.get("password"));
	Thread.sleep(2000);
	List<WebElement> products= productCatalogue.getProductsList();
	productCatalogue.addProductToCart(input.get("product"));
	CartPage cartPage=productCatalogue.goToCartPage();
	boolean match =cartPage.verifyProductDisplay(input.get("product"));
	Assert.assertTrue(match);
	cartPage.scrollElm();
	Thread.sleep(2000);
	CheckOutPage checkoutPage = cartPage.goToCheckOut();
	
	checkoutPage.selectCountry("Ind");
	ConfirmationPage confirmationPage= checkoutPage.submitOrder();
	
	String orderConfirmMsg= confirmationPage.confirmationMsg();
	Assert.assertTrue(orderConfirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	Thread.sleep(1000);
	//after method will close the browser
	}
	
	@Test(dependsOnMethods= {"submitOrder2"})
	public void verifyOrderHistory() {
		ProductCatalogue productCatalogue=landingPage.loginAppilication("gerev16419@ebuthor.com", "Tester@12");
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	
	}
	
	
	//runs tests with given set of data (here it runs the test for 2 times as two sets of data are provided)
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"gerev16419@ebuthor.com", "Tester@12", "ZARA COAT 3"},{"koden85821@rartg.com", "Testing@123", "ADIDAS ORIGINAL"}};
	}
	
	//sending data using hashmap
	@DataProvider
	public Object[][] getData1() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "gerev16419@ebuthor.com");
		map.put("password", "Tester@12");
		map.put("product", "IPHONE 13 PRO");
		
		HashMap<String,String> map1 = new HashMap<String, String>();
		map1.put("email","koden85821@rartg.com");
		map1.put("password","Testing@123"); 
		map1.put("product", "ADIDAS ORIGINAL");
		return new Object[][] {{map},{map1}};
	}
	
	//Sending data from Json file
	
	@DataProvider
	public Object[][] getData2() throws IOException {
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//jsonData//purchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
