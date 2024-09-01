package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class GoogleSearchPage {

	WebDriver driver;
	
    By searchBox = By.name("q");
	
    public GoogleSearchPage(WebDriver driver) {
    	
        this.driver = driver;
    }
    
    public void enterSearchCriteria(String searchParam) {
    	
        driver.findElement(searchBox).sendKeys(searchParam);
    }
    
    public void pressEnter() {
    	
        driver.findElement(searchBox).sendKeys(Keys.RETURN); 
        
    }
}
