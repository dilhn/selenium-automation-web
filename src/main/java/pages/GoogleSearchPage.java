package pages;

import org.openqa.selenium.WebDriver;
import base.WebDriverManager;
import org.openqa.selenium.By;

public class GoogleSearchPage extends WebDriverManager{
	
    By searchBox = By.name("q");
	
    public GoogleSearchPage(WebDriver driver) {
    	
    	super(driver); 
    }
    
    public void typeSearch(String search) {
    	typeText(searchBox,search);
    }

    
    public void pressSearchEnter() {
    	pressEnter(searchBox);
    }


}
