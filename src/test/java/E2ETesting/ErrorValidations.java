package E2ETesting;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.ProductCatalogue;
import pageObjects.CartPage;
import testComponents.BaseTest;
import testComponents.Retry;

public class ErrorValidations extends BaseTest{

			@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
			public void LoginErrorValidation() throws IOException {
			//before method will launch the application
			landingPage.loginAppilication("gerev16419@ebuthor.com", "Tester@123");
			Assert.assertEquals("Incorrect email or password.", landingPage.errorMessage());
			
			}
			
			@Test
			public void ProductErrorValidation() throws InterruptedException {
				String productName = "ZARA COAT 3";
				ProductCatalogue productCatalogue=landingPage.loginAppilication("koden85821@rartg.com", "Testing@123");
				Thread.sleep(2000);
				List<WebElement> products= productCatalogue.getProductsList();
				productCatalogue.addProductToCart(productName);
				CartPage cartPage=productCatalogue.goToCartPage();
				boolean match =cartPage.verifyProductDisplay("ZARA COAT 33");
				Assert.assertFalse(match);
			}
			
			
}
