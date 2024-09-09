package pages;

import org.openqa.selenium.WebDriver;
import base.BasePage;
import org.openqa.selenium.By;

public class GoogleSearchPage extends BasePage{
	
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
