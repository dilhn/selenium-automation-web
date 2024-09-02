package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {

	private WebDriver driver;
	
	public WebDriver initDriver() {
		
        driver = new ChromeDriver();
        return driver;
		
	}
	
    public void maximizeWindow(){
    	
        driver.manage().window().maximize();
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
	
	public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
	}
}
