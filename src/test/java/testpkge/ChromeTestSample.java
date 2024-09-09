package testpkge;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.Assert;

import base.BasePage;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.AmazonHomePage;
import pages.AmazonProductPage;
import pages.AmazonSearchResultsPage;
import pages.GoogleSearchPage;
import pages.GoogleSearchResultsPage;
import utils.CSVDataReader;


public class ChromeTestSample {
	
	WebDriver driver;
	BasePage basepage;
	GoogleSearchPage googleSearchPage;
	GoogleSearchResultsPage googleSearchResultsPage;
	AmazonHomePage amazonhome;
	AmazonSearchResultsPage amazonsearchresults;
	AmazonProductPage amazonproducts;
	
	Map<String, String> testData;
	
	@BeforeClass
	public void setup() throws IOException {
        
		driver = new ChromeDriver();
		basepage = new BasePage(driver);
		basepage.maximizeWindow();
		
		testData = CSVDataReader.readCSVData("src/main/resources/testData/data.csv");
        
		googleSearchPage = new GoogleSearchPage(driver);
		googleSearchResultsPage = new GoogleSearchResultsPage(driver);
		amazonhome = new AmazonHomePage(driver);
		amazonsearchresults = new AmazonSearchResultsPage(driver);
		amazonproducts = new AmazonProductPage(driver);
	}
	
	
	@Test(priority=1)
	public void navigateToAmazonOnGoogle() {
		
		
		String googleUrl = testData.get("url1");
		String amazonUrl = testData.get("url2");
		String title = testData.get("title");
		String amazonText = testData.get("sParam1");
		
		
		basepage.openPage(googleUrl);	
        
        String actualTitle = basepage.getPageTitle();
        
        try {
            Assert.assertEquals(actualTitle, title);
            
            //test.pass("Page title is as expected :- " + actualTitle);
            
        } catch (AssertionError e) {
        	
            Assert.fail("Incorrect page title :- " + e.getMessage());
            throw e; 
        }
        
        googleSearchPage.typeSearch(amazonText); // modified
        
        googleSearchPage.pressSearchEnter(); // modified
        
        googleSearchResultsPage.clickOnSearchResults(amazonUrl); // modified
        
        basepage.refreshWindow();
        
        
        String currentURL = basepage.getPageURL();
        
        try {
            Assert.assertEquals(currentURL, amazonUrl);
            
            //test.pass("Page URL is as expected :- " + currentURL);
            
        } catch (AssertionError e) {
        	
            Assert.fail("Incorrect page URL :- " + e.getMessage());
            throw e; 
        }
        
        //amazonhome.waitForInfoPopupLocation(country); 
        
	}
	
	@Test(priority=2)
	public void changeDeliveryCountry() {
		
		String country = testData.get("country");
		
		amazonhome.clickInfoPopDismissBtn();
        
        amazonhome.clickChangeDeliveryOptionButton();
        
        amazonhome.selectDeliveryOption(country); 
        
        amazonhome.clickLocationPopupDoneBtn();
        
        //amazonhome.waitForLocationPopupClose();
	}
	
	@Test(priority=3)
	public void searchEGiftCard() {
		
		String sParameter = testData.get("sParam2");
		String sResult = testData.get("sResult");
		
		amazonhome.typeSearchCriteria(sParameter);
        
        amazonhome.pressEnterOnResults(); 
        
        amazonsearchresults.clickOnResulteGiftCard(sResult);
	}
	
	@Test(priority=4)
	public void verifyEGiftCardinPreview() {
		
		amazonproducts.clickOnAGiftCardDesign();   
        
        String designSrc = amazonproducts.getSRCofselectedGiftCardDesign();

        amazonproducts.clickonSelectedGiftCardDesign();
        
        String previewSrc = amazonproducts.getSRCofGiftCardPreview();
        
        try {
            Assert.assertEquals(previewSrc, designSrc);
            
            //test.pass("Selected eGift card Design & Preview images are the same");
            
        } catch (AssertionError e) {
        	
            Assert.fail("Error :- " + e.getMessage());
            throw e; 
        }

        amazonproducts.closeGiftCardPreview();
	}
	
	@Test(priority=5)
	public void verifyEGiftCardAmtInPreview() {
		
        amazonproducts.selectGiftCardAmount();

        String giftCardAmt = amazonproducts.getGiftCardAmountVal();
        
        amazonproducts.clickonSelectedGiftCardDesign();
        
        String cardPreviewAmt = amazonproducts.GiftCardPreviewAmount();
        
        double value1 = Double.parseDouble(giftCardAmt.replace("$", ""));
        
        double value2 = Double.parseDouble(cardPreviewAmt.replace("$", ""));
        
        try {
            Assert.assertEquals(value1, value2, 0.01);
            
            //test.pass("Selected eGift card amount and the amount displayed on the preview are the same");
            
        } catch (AssertionError e) {
        	
            Assert.fail("Error :- " + e.getMessage());
            throw e; 
        }
		
	}
	
	@AfterClass
    public void tearDown() {
    	
		basepage.quitDriver();
        //test.log(com.aventstack.extentreports.Status.INFO, "Browser closed");
    }
	

}
