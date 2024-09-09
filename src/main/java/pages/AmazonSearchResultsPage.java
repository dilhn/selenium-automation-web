package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.WebDriverManager;

public class AmazonSearchResultsPage extends WebDriverManager {
	
    WebDriverWait wait;
    
	private String searchResults = "//div[@data-cy='title-recipe']//h2//a//span[contains(text(), '%s')]";
	
	public AmazonSearchResultsPage(WebDriver driver) {
		
		super(driver);
    }

    public void clickOnResulteGiftCard(String searchParam) {
    	
    	wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	String formattedXpath = String.format(searchResults, searchParam);
    	
    	WebElement searchResultURL = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(formattedXpath)));
    	
    	searchResultURL.click();
    	
    }
}
