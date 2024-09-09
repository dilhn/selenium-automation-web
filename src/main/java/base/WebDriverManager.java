package base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverManager {

	protected WebDriver driver;
	
	  
    public WebDriverManager(WebDriver driver) {
        this.driver = driver;
    }
	
    public void maximizeWindow(){
    	
        driver.manage().window().maximize();
    }
    
    public void refreshWindow(){
    	
    	driver.navigate().refresh();
    }
	
    public void openPage(String url){
    	
        driver.get(url);
    }
    
	public String getPageTitle() {
		
		return driver.getTitle();
	}
	
	public String getPageURL() {
		
		return driver.getCurrentUrl();
	}
	
	public void typeText(By locator, String value) {
        driver.findElement(locator).sendKeys(value);
	}
	
	public void clickElement(By locator) {
		driver.findElement(locator).click();
	}
	
	public void selectDropDownValue(By locator, String country) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(country);
        
	}
	
    public void pressEnter(By locator) {
        driver.findElement(locator).sendKeys(Keys.RETURN);
    }
    
	public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
	}
}
