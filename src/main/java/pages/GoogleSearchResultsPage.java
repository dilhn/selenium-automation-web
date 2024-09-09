package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class GoogleSearchResultsPage extends BasePage{

	//WebDriver driver;
	
	private String resultHref = "//a[@href='%s']";
	
    public GoogleSearchResultsPage(WebDriver driver) {
    	
    	super(driver); 
    }
    
    public void clickOnSearchResults(String url) {
    	By searchResult = By.xpath(String.format(resultHref, url));
        clickElement(searchResult);
        
    }
}
