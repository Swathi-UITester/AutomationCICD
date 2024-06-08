package E2ETesting;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class EcomStandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		String productName = "ZARA COAT 3";
		//WebDriver driver = new FirefoxDriver();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("gerev16419@ebuthor.com");
		driver.findElement(By.id("userPassword")).sendKeys("Tester@12");
		driver.findElement(By.id("login")).click();
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		List<WebElement> list = driver.findElements(By.cssSelector(".col-lg-4"));
		
//		for(WebElement a : list) {
//			String title = a.findElement(By.cssSelector(".card-body h5")).getText();
//			if(title.contains(productName)) {
//				System.out.println("Actual title :"+ title);
//			}
//		}
		
		WebElement product= list.stream().filter(s->s.findElement(By.cssSelector("h5")).
				getText().equals(productName)).findFirst().orElse(null);
		
		product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading-text")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		System.out.println(driver.findElement(By.id("toast-container")).getText());
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartItems= driver.findElements(By.cssSelector(".cartSection h3"));
		cartItems.stream().map(s->s.getText()).forEach(s->System.out.println(s));
		Boolean match= cartItems.stream().anyMatch(cartProduct->cartProduct.getText().equals(productName));
		Assert.assertTrue(match);
	
		JavascriptExecutor js =  (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "Ind").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.ta-results")));
		driver.findElement(By.cssSelector("section >button:nth-child(3)")).click();
		
		driver.findElement(By.className("action__submit")).click();
		String confirmMsg= driver.findElement(By.className("hero-primary")).getText();
		System.out.println(confirmMsg);
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
		driver.close();
		
	}

}
