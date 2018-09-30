package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class AddCustomerTest extends TestBase{
	
	
	@Test(dataProviderClass=TestUtil.class, dataProvider="dp")
	public void addCustomerTest(Hashtable<String, String> data){
		
		
		if(data.get("runmode").equalsIgnoreCase("N")){
			throw new SkipException("Skipping the test addCustomerTest because test data RunMode is set to NO");

		}
			
		if(TestUtil.isRunnable("AddCustomerTest")){
			throw new SkipException("Skipping the test addCustomerTest because test case run mode is set to NO");

		}
			
			try{
		//driver.findElement(By.cssSelector(OR.getProperty("addCustBtn_CSS"))).click();
		click("addCustBtn_CSS");
		
		//driver.findElement(By.cssSelector(OR.getProperty("firstname_CSS"))).sendKeys(firstName);
		type("firstname_CSS", data.get("firstname"));
		type("lastname_XPATH", data.get("lastname"));
		type("postcode_CSS", data.get("postcode"));


		//driver.findElement(By.xpath(OR.getProperty("lastname_XPATH"))).sendKeys(lastName);
		//driver.findElement(By.cssSelector(OR.getProperty("postcode_CSS"))).sendKeys(postCode);
		
		//driver.findElement(By.cssSelector(OR.getProperty("addbtn_CSS"))).click();
		click("addbtn_CSS");
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
		Thread.sleep(2000);
		
		alert.accept();
		
		//Assert.fail();
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	

}
