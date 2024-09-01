package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonSearchResultsPage {
	
	WebDriver driver;
    WebDriverWait wait;
    
	private String searchResults = "//div[@data-cy='title-recipe']//h2//a//span[contains(text(), '%s')]";
	
	public AmazonSearchResultsPage(WebDriver driver) {
		
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickOnResulteGiftCard(String searchParam) {
    	
    	String formattedXpath = String.format(searchResults, searchParam);
    	
    	WebElement searchResultURL = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(formattedXpath)));
    	
    	searchResultURL.click();
    }
}
