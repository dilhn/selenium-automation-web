package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	
	public void onStart(ITestContext testContext) {
		
	String tstmp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	repName = "Test-Report-"+ tstmp +".html";
		
	String repPath = "."+ File.separator +"target"+ File.separator +"reports"+ File.separator +repName;
		
	sparkReporter = new ExtentSparkReporter(repPath);
		
	sparkReporter.config().setDocumentTitle("");
	sparkReporter.config().setReportName("");
	sparkReporter.config().setTheme(Theme.STANDARD);
		
	extent = new ExtentReports();
	extent.attachReporter(sparkReporter);
		
	extent.setSystemInfo("Application", "Amazon Web Test (eGiftCards)");
	extent.setSystemInfo("Environment", "QA");
	extent.setSystemInfo("User", "Dilhan");
		
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
		
		
	}
	
	public void onTestFailure(ITestResult result) {
		
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getClass().getName() + ": " + result.getThrowable().getMessage());
		
	}
	
	
	public void onTestSkipped(ITestResult result) {
		
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext) {
		
		extent.flush();
	}
	
	
}


