package com.w2a.testcases;


import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class BankManagerLoginTest extends TestBase {
	
	@Test
	public void loginAsBankManager() throws InterruptedException{
		


		//try{
			//System.setProperty("org.uncommons.reportng.escape-output", "false");

			
			verifyEquals("ABC", "XYZ");
			
			Thread.sleep(3000);
			
			log.debug("Going to do login");
			//driver.findElement(By.cssSelector(OR.getProperty("bmlBtn_CSS"))).click();
			click("bmlBtn_CSS");
			log.debug("Login done");
			Reporter.log("LOGIN DONE SUCCESSFULLY");
			Reporter.log("<a href=\"_blank\"\"D:\\TestImage.jpg\">Screenshot</a>");
			Reporter.log("<br>");
			Reporter.log("<a href=\"_blank\"\"D:\\TestImage.jpg\"><img src=\"D:\\TestImage.jpg\"</img></a>");
			Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))));
			Thread.sleep(5000);
			//Assert.fail("login not successfull");
			
		/*}catch(Exception e){
			e.printStackTrace();
		}
*/

	}

}
