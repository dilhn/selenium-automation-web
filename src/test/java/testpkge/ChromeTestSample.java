package testpkge;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AmazonHomePage;
import pages.AmazonProductPage;
import pages.AmazonSearchResultsPage;
import pages.GoogleSearchPage;
import pages.GoogleSearchResultsPage;
import utils.CSVDataReader;


public class ChromeTestSample {
	

	WebDriver driver;
    ExtentReports extent;
    ExtentTest test;
	GoogleSearchPage googleSearchPage;
	GoogleSearchResultsPage googleSearchResultsPage;
	AmazonHomePage amazonhome;
	AmazonSearchResultsPage amazonsearchresults;
	AmazonProductPage amazonproducts;
	
	@BeforeClass
	public void setup() {
		
		String reportPath = "target/reports/extent.html";
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("User Name", "Dilhan Nakandala");
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
		googleSearchPage = new GoogleSearchPage(driver);
		googleSearchResultsPage = new GoogleSearchResultsPage(driver);
		amazonhome = new AmazonHomePage(driver);
		amazonsearchresults = new AmazonSearchResultsPage(driver);
		amazonproducts = new AmazonProductPage(driver);
	}
	
	
	@Test
	public void amazonWebTest() throws IOException{
		
		Map<String, String> testData = CSVDataReader.readCSVData("src/main/resources/testData/data.csv");

		String googleUrl = testData.get("url1");
		String amazonUrl = testData.get("url2");
		String title = testData.get("title");
		String country = testData.get("country");
		String amazonText = testData.get("sParam1");
		String sParameter = testData.get("sParam2");
		String sResult = testData.get("sResult");

		
		test = extent.createTest("Amazon Web Test for eGiftCards");
		
        test.log(com.aventstack.extentreports.Status.INFO, "Browser opened - navigate to Google.com");
        
		driver.get(googleUrl);
		
        String actualTitle = driver.getTitle();

        
        try {
            Assert.assertEquals(actualTitle, title);
            
            test.pass("Page title is as expected :- " + actualTitle);
            
        } catch (AssertionError e) {
        	
            test.fail("Incorrect page title :- " + e.getMessage());
            throw e; 
        }
        
        googleSearchPage.enterSearchCriteria(amazonText);
        
        googleSearchPage.pressEnter();
        
        googleSearchResultsPage.clickOnSearchResults(amazonUrl);
        
        amazonhome.refreshPage();
        
        String currentURL = driver.getCurrentUrl();
        
        try {
            Assert.assertEquals(currentURL, amazonUrl);
            
            test.pass("Page URL is as expected :- " + currentURL);
            
        } catch (AssertionError e) {
        	
            test.fail("Incorrect page URL :- " + e.getMessage());
            throw e; 
        }
        
        amazonhome.clickChangeDeliveryOptionButton();
        
        amazonhome.selectDeliveryOption(country); 
        
        amazonhome.clickLocationPopupDoneBtn();
        
        amazonhome.waitForLocationPopupClose();
        
        amazonhome.waitForInfoPopupLocation(country); 
        
        amazonhome.typeSearchCriteria(sParameter);
        
        amazonhome.pressEnter(); 
        
        amazonsearchresults.clickOnResulteGiftCard(sResult);

        amazonproducts.clickOnAGiftCardDesign();   
        
        String designSrc = amazonproducts.getSRCofselectedGiftCardDesign();

        amazonproducts.clickonSelectedGiftCardDesign();
        
        String previewSrc = amazonproducts.getSRCofGiftCardPreview();
        
        try {
            Assert.assertEquals(previewSrc, designSrc);
            
            test.pass("Selected eGift card Design & Preview images are the same");
            
        } catch (AssertionError e) {
        	
            test.fail("Error :- " + e.getMessage());
            throw e; 
        }

        amazonproducts.closeGiftCardPreview();
        
        amazonproducts.selectGiftCardAmount();

        String giftCardAmt = amazonproducts.getGiftCardAmountVal();
        
        amazonproducts.clickonSelectedGiftCardDesign();
        
        String cardPreviewAmt = amazonproducts.GiftCardPreviewAmount();
        
        double value1 = Double.parseDouble(giftCardAmt.replace("$", ""));
        
        double value2 = Double.parseDouble(cardPreviewAmt.replace("$", ""));
        
        try {
            Assert.assertEquals(value1, value2, 0.01);
            
            test.pass("Selected eGift card amount and the amount displayed on the preview are the same");
            
        } catch (AssertionError e) {
        	
            test.fail("Error :- " + e.getMessage());
            throw e; 
        }
        
	}
	
    @AfterClass
    public void tearDown() {
    	
        driver.quit();
        test.log(com.aventstack.extentreports.Status.INFO, "Browser closed");
        extent.flush();
    }
	

}
