package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.WebDriverManager;

public class GoogleSearchResultsPage extends WebDriverManager{

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
