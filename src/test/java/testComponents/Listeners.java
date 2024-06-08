package testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentreporterNG;



public class Listeners extends BaseTest implements ITestListener {
	
	ExtentReports extent = ExtentreporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		//to achieve thread safety (when you run tests in parallel, there is a chance that each test override and gives improper resulst
		//to avoid this, we used THreadLocal
		extentTest.set(test); //gives unique thread id(ex: ErrorValidationTest)->Test
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//test.log(Status.PASS,"Test Passed");
		extentTest.get().log(Status.PASS,"Test Passed"); //replace test with extentTest.get()
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable());
		//capture ss and attach it to the report
		String filePath = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
				
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
