package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;


public class AmazonHomePage extends BasePage{

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
    By inforPopDismissBtn = By.xpath("//input[@data-action-type='DISMISS']");
    

	public AmazonHomePage(WebDriver driver) {
		
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
	

	
	public void clickInfoPopDismissBtn() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(inforPopDismissBtn));
		
		//driver.findElement(inforPopDismissBtn).click();
		clickElement(inforPopDismissBtn);
	}
    
    public void clickChangeDeliveryOptionButton() {
    	
       //driver.findElement(locationPopLink).click();
    	clickElement(locationPopLink);
    }
    
    public void selectDeliveryOption(String country) {
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(locationSpecifyDiv)); // outer element
    	
    	
        //WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(countrySelector));
        //WebElement dropdownElement = driver.findElement(locationCountriesDdown);
        
        //Select select = new Select(dropdownElement);
        //select.selectByVisibleText(country);
    	selectDropDownValue(countrySelector, country);
        
    }
    
    
    public void clickLocationPopupDoneBtn() {

        //WebElement button = driver.findElement(locationPopDoneBtn);
        
        //button.click();
        
        clickElement(locationPopDoneBtn);
        
    }
    
    /*
    public void waitForLocationPopupClose() {
    	
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(locationPopWrapDiv)); // *** moved the line to bottom
    	
    }
    */
    
    /* no longer used?
    public void waitForInfoPopupLocation1(String country) {
    	
    	wait.until(ExpectedConditions.textToBePresentInElementLocated(strongTxt, country));
    }
    */
    
    /* 
     * CHECK LATER
     * 
    public boolean waitForInfoPopupLocation(String country) {
        
    	try {
    		return wait.until(ExpectedConditions.textToBePresentInElementLocated(strongTxt, country));
    		
        } catch (TimeoutException e) {
            return false;
        }
    }
    */
    
    /* 
     * CHECK LATER
     * 
    public String getDeliveryLocationText() {

    	wait.until(ExpectedConditions.visibilityOfElementLocated(deleverToDiv));
    	
    	WebElement DelCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(deleverToCountryDiv));
    	
    	//WebElement deleverToCountryDiv = driver.findElement(deleverToDiv);
    	
        String countryText = DelCountry.getText();
        
        System.out.println("Deliver to :- "+countryText);
        
        return countryText;
        
    }
    */
    
    public void typeSearchCriteria(String searchTerm) {
    	
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(locationPopWrapDiv)); // *** added from the top
    	//driver.findElement(searchBoxInput).sendKeys(searchTerm);
    	typeText(searchBoxInput, searchTerm);
    	
    }
    
    public void pressEnterOnResults() { 
    	
    	pressEnter(searchBoxInput);
    	//driver.findElement(searchBoxInput).sendKeys(Keys.RETURN);
    }
    
    
}
