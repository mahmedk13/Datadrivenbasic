package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class OpenAccountTest extends TestBase{
	
	@Test(dataProviderClass=TestUtil.class, dataProvider="dp")
	public void openAccountTest(Hashtable<String, String> data){
		click("openaccount_CSS");
		type("customer_CSS", data.get("customer"));
		type("currency_CSS", data.get("currency"));
		click("process_CSS");
		
		
	}
	


}
