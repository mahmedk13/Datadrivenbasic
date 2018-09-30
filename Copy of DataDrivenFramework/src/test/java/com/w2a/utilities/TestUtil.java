package com.w2a.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.TestBase;

public class TestUtil extends TestBase {
	public static String screenshotPath;
	public static String screemshotName;
	
	public static void captureScreenshot(){
		Date d = new Date();
		screemshotName  = d.toString().replace(" ", "_").replace(":", "_")+".jpg";
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"//target//surefire-reports//html//"+screemshotName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@DataProvider(name="dp")
	public Object[][] getData(Method m){
		
		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		System.out.println("rows are: "+rows);
		System.out.println("cols are: "+cols);
		
		Hashtable<String, String> table = null;

		Object[][] data = new Object[rows-1][1];
		
		for(int rowNum=2; rowNum<=rows; rowNum++){
			table = new Hashtable<String, String>();
			for(int colNum=0; colNum<cols; colNum++){
				
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum-2][0] = table;
			}
		}
		return data;
		
	}
	
	
	static WebElement dropdown;

	public void select(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);

		test.log(LogStatus.INFO, "Selecting from dropdown : " + locator + " value as " + value);

	}
	
	
	public static boolean isRunnable(String testName){
		int rows = excel.getRowCount("test_suite");
		
		for(int rNum=2; rNum<=rows; rNum++){
			String data = excel.getCellData("test_suite", 0, rNum);
			if(data.equalsIgnoreCase(testName)){
				String runMode = excel.getCellData("test_suite", 1, rNum);
				if(runMode.equalsIgnoreCase("Y")){
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
		
	}


}
