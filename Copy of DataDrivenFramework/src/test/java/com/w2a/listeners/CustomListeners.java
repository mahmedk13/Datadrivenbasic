package com.w2a.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;




public class CustomListeners extends TestBase implements ITestListener {

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		TestUtil.captureScreenshot();
		
		test.log(LogStatus.FAIL, arg0.getName().toUpperCase()+" Failed with exception "+arg0.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screemshotName));

		Reporter.log("Click to see screenshot");
		Reporter.log("LOGIN DONE SUCCESSFULLY");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screemshotName+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target =\"_blank\" href="+TestUtil.screemshotName+"><img src="+TestUtil.screemshotName+"</img></a>");
		rep.endTest(test);
		rep.flush();
	
	}
	
	
	

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		test.log(LogStatus.SKIP, arg0.getName().toUpperCase()+" skipping because runmode is set as NO");
		rep.endTest(test);
		rep.flush();
		
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		test = rep.startTest(arg0.getName().toUpperCase(), "My test case");
		
		
	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		test.log(LogStatus.PASS, arg0.getName().toUpperCase()+": PASS");
		//test.log(LogStatus.INFO,test.addScreenCapture(TestUtil.screemshotName));
		rep.endTest(test);
		rep.flush();
		
	}

	
}
