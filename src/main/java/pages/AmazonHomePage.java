package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AmazonHomePage {

    WebDriver driver;
    WebDriverWait wait;
    
    By locationPopWrapDiv = By.className("a-popover-wrapper");
    By locationPopLink = By.xpath("//a[@id='nav-global-location-popover-link']");
    By countrySelector = By.id("GLUXCountryList");
    By locationPopDoneBtn = By.xpath("//button[@name='glowDoneButton']");
    By deleverToDiv = By.id("glow-ingress-block");
    By deleverToCountryDiv = By.id("glow-ingress-line2");
    By strongTxt = By.xpath("//div[@id='glow-toaster-body']//span[@class='a-size-base']//strong");
    By searchBoxInput = By.id("twotabsearchtextbox");
    
    //By locationPopInnerDiv = By.id("a-popover-content-1");
    //By locationAdrListDiv = By.id("GLOWFeature_AddressList");
    By locationSpecifyDiv = By.id("GLUXSpecifyLocationDiv");
    

	public AmazonHomePage(WebDriver driver) {
		
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
	/*
    public void refreshPage() {
    	driver.navigate().refresh();
    }
    */
    
    public void clickChangeDeliveryOptionButton() {
    	
        driver.findElement(locationPopLink).click();
    }
    
    public void selectDeliveryOption(String country) {
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(locationSpecifyDiv)); // outer element
    	
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(countrySelector));
        //WebElement dropdownElement = driver.findElement(locationCountriesDdown);
        
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(country);

        
    }
    
    
    public void clickLocationPopupDoneBtn() {

        WebElement button = driver.findElement(locationPopDoneBtn);
        
        button.click();
        
    }
    
    public void waitForLocationPopupClose() {
    	
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(locationPopWrapDiv));
    	
    }
    
    /*
    public void waitForInfoPopupLocation1(String country) {
    	
    	wait.until(ExpectedConditions.textToBePresentInElementLocated(strongTxt, country));
    }
    */
    
    public boolean waitForInfoPopupLocation(String country) {
        
    	try {
    		return wait.until(ExpectedConditions.textToBePresentInElementLocated(strongTxt, country));
    		
        } catch (TimeoutException e) {
            return false;
        }
    }
    
    
    public String getDeliveryLocationText() {

    	wait.until(ExpectedConditions.visibilityOfElementLocated(deleverToDiv));
    	
    	WebElement DelCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(deleverToCountryDiv));
    	
    	//WebElement deleverToCountryDiv = driver.findElement(deleverToDiv);
    	
        String countryText = DelCountry.getText();
        
        System.out.println("Deliver to :- "+countryText);
        
        return countryText;
        
    }
    
    public void typeSearchCriteria(String searchTerm) {
    	
    	driver.findElement(searchBoxInput).sendKeys(searchTerm);
    }
    
    public void pressEnter() {
    	
    	driver.findElement(searchBoxInput).sendKeys(Keys.RETURN);
    }
    
    
}
