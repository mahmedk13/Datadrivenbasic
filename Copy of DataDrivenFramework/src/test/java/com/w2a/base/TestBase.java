package com.w2a.base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;
import com.w2a.utilities.TestUtil;



public class TestBase {
	
	public static WebDriver driver;
	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoylogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"//src//test//resources//excel//testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
    
	
	public void initializeBrowser(){
		try{
			if(driver==null){
				
				fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//Config.properties");
				Config.load(fis);
				log.debug("Config file loaded");
				
				fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//OR.properties");
				OR.load(fis);
				log.debug("OR file loaded");

				
				String browserName = Config.getProperty("browser");
				System.out.println("browser name is "+browserName);
				
				if(browserName.equalsIgnoreCase("firefox")){
					driver = new FirefoxDriver();
					log.debug("FF Launched");

					
				}else if(browserName.equalsIgnoreCase("chrome")){
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//test//resources//executables//chromedriver.exe");
					driver = new ChromeDriver();
					log.debug("Chrome Launched");

				}else if(browserName.equalsIgnoreCase("ie")){
					System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"//src//test//resources//executables//IEDriverServer.exe");
					driver = new InternetExplorerDriver();
					log.debug("IE Launched");

					
				}
				
				
				 driver.get(Config.getProperty("testsiteurl"));
					log.debug("Navigated to "+Config.getProperty("testsiteurl"));

				 driver.manage().window().maximize();
				 driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")), TimeUnit.SECONDS);
				 wait = new WebDriverWait(driver, 5);
				
				
				
			}
			}catch(Exception e){
				e.printStackTrace();
				
			}
	}
	
	@BeforeSuite
	public void setUp(){
		initializeBrowser();
	
		
	}
	
	@AfterSuite
	public void tearDown(){
		if(driver!=null){
			driver.quit();
			log.debug("Browser Closed");
	
		}
	}
		
		
	public boolean isElementPresent(By by){
		try{
			driver.findElement(by);
			return true;
		}catch(NoSuchElementException e){
			return false;
		}
		
	}
	
	
	public void click(String locator) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		test.log(LogStatus.INFO, "Clicking on : " + locator);
	}

	public void type(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}

		test.log(LogStatus.INFO, "Typing in : " + locator + " entered value as " + value);

	}
	
	public void verifyEquals(String expected, String actual){
		try{
			Assert.assertEquals(expected, actual);
		}catch(Throwable t){
			TestUtil.captureScreenshot();
			test.log(LogStatus.FAIL, "Verification failed with exception "+t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screemshotName));
		}
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
	
	
		

		
	

}
