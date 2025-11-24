package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import testBase.BaseClass;

public class ExtentReportListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        // ExtentReports should already be initialized in BaseClass @BeforeSuite
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Automatically create ExtentTest using the test method name
        if (BaseClass.extent != null) {
            BaseClass.test = BaseClass.extent.createTest(result.getMethod().getMethodName());
            BaseClass.test.info("Test started: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (BaseClass.test != null) {
            BaseClass.test.log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (BaseClass.test != null) {
            BaseClass.test.log(Status.FAIL, "Test failed: " + result.getMethod().getMethodName());
            BaseClass.test.log(Status.FAIL, result.getThrowable());

            try {
                Object testObject = result.getInstance();
                if (testObject instanceof BaseClass) {
                    BaseClass base = (BaseClass) testObject;
                    String screenshotPath = base.captureScreenshot(result.getMethod().getMethodName());
                    if (screenshotPath != null) {
                        BaseClass.test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
                    }
                }
            } catch (Exception e) {
                BaseClass.test.log(Status.WARNING, "Screenshot capture failed: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (BaseClass.test != null) {
            BaseClass.test.log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
    }
}
