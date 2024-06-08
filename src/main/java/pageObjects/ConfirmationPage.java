package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "hero-primary")
	WebElement confirmationText;
	
	public String confirmationMsg() {
		String confirmMsg= confirmationText.getText();
		System.out.println(confirmMsg);
		return confirmMsg;
	}
	
	

}
