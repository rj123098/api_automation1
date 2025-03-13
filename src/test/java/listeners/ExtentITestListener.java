package listeners;

import org.testng.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;
import utilities.ExtentReportManager;

public class ExtentITestListener implements ITestListener {

    private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flushReports();
        System.out.println("Test Suite Finished");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentReportManager.getInstance().createTest(result.getMethod().getMethodName());
        extentTestThreadLocal.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTestThreadLocal.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTestThreadLocal.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTestThreadLocal.get().skip(result.getThrowable());
    }

    // Optional: Handle partial success if needed
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not used
    }

    public static ExtentTest getTest() {
        return extentTestThreadLocal.get();
    }
}
