package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonProductPage {
	
	WebDriver driver;
    WebDriverWait wait;
    
	By selectedDesignImg = By.id("gc-standard-design-image");
	By designOptImgMain = By.xpath("//img[@alt='Amazon Gift Card - Beach Design']");
	By DesignOptImgAlt = By.xpath("//img[@alt='Thank You Flowers']");
	By previewPopClose = By.xpath("//button[@data-action='a-popover-close']");
	By previewImgDes = By.xpath("//img[@alt='eGift Card design preview']");
	//By previewImgLocator = By.xpath("//td[@id='Imgcontainer']/img");
	By giftCardAmtBtn = By.xpath("//button[@value='75']");
	By previewAmt = By.xpath("//table//td[@valign='middle']//span");
	By previewPopCloseBtn = By.className("a-popover-wrapper");
	
	
	public AmazonProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
	
    public void clickOnAGiftCardDesign() {
    	
        try {
            WebElement imageElement = driver.findElement(designOptImgMain);
            imageElement.click();
        }
        
        catch(NoSuchElementException e) {
        WebElement imageElement = driver.findElement(DesignOptImgAlt);
        imageElement.click();
        }
        // ALT='Thank You Flowers'
    }
    
    public String getSRCofselectedGiftCardDesign() {
    	
        WebElement imageDesc = driver.findElement(selectedDesignImg);
        
        String imageSrc = imageDesc.getAttribute("src");
        
        return imageSrc;
    }
    
    public void clickonSelectedGiftCardDesign() {
    	
    	driver.findElement(selectedDesignImg).click();
    }
    
    public String getSRCofGiftCardPreview() {
    	
    	 WebElement previewImg = wait.until(ExpectedConditions.visibilityOfElementLocated(previewImgDes));
    	 
    	 String previewSrc = previewImg.getAttribute("src");
    	 
    	 return previewSrc;
    }
    
    public void closeGiftCardPreview() {
    	
        driver.findElement(previewPopClose).click();
    }
    
    public void selectGiftCardAmount() {
    	
        wait.until(ExpectedConditions.invisibilityOfElementLocated(previewPopCloseBtn));
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(giftCardAmtBtn));
        
        WebElement btnAmt = wait.until(ExpectedConditions.elementToBeClickable(giftCardAmtBtn));
        
        btnAmt.click();
        
        System.out.println("Selected (Amt) is :"+btnAmt.getText());
    }
    
    public String getGiftCardAmountVal() {
    	
    	String amount = driver.findElement(giftCardAmtBtn).getText();
    	
    	return amount;
    }
    
    public String GiftCardPreviewAmount() {
    
    	WebElement previewspan = wait.until(ExpectedConditions.visibilityOfElementLocated(previewAmt));
    	
        String previewAmtTxt = previewspan.getText();
        
        System.out.println("Preview amount displayed is "+previewAmtTxt);

    	return previewAmtTxt;
    }

}
