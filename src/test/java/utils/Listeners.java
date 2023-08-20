package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;

public class Listeners extends TestListenerAdapter {

    private static Logger log = LogManager.getLogger(Listeners.class);
    ExtentReports extent;
    ExtentSparkReporter sparkReporter;

    public void onStart(ITestContext testContext) {
        extent = new ExtentReports();
        sparkReporter = new ExtentSparkReporter("target/ExtentReport/Extent_Report.html");
        extent.attachReporter(sparkReporter);
    }
    public void onTestSuccess(ITestResult result) {
        System.out.println("On Test Success !!"+result.getMethod());
        ExtentTest test = extent.createTest(result.getMethod().getDescription());
        test.log(Status.PASS, "TEST PASSED !!");

    }
    public void onTestFailure(ITestResult result) {
        System.out.println("On Test Fail !!"+result.getMethod());
        ExtentTest test = extent.createTest(result.getMethod().getDescription());
        test.log(Status.FAIL, "TEST FAILED !!");
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();
    }


}
