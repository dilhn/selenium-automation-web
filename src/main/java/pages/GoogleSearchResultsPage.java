package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleSearchResultsPage {

	WebDriver driver;
	
	private String resultHref = "//a[@href='%s']";
	
    public GoogleSearchResultsPage(WebDriver driver) {
    	
        this.driver = driver;
    }
    
    public void clickOnSearchResults(String url) {
    	By searchResult = By.xpath(String.format(resultHref, url));
        driver.findElement(searchResult).click();
    }
}
