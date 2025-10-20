/*package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import testBase.BaseClass;

public class ExtentReportListener implements ITestListener {

    // This is the BaseClass object where 'extent' and 'test' are managed.
    // Since we can't directly access static members from the listener in a clean way,
    // we'll pass the test object around or rely on the static members.

    // NOTE: BaseClass.extent and BaseClass.test are static, which makes them accessible here.

    public void onStart(ITestContext context) {
        // Since initExtentReport is called in @BeforeSuite in BaseClass,
        // we can often skip this, but it's good practice to ensure initialization.
        // BaseClass setup handles the extent object creation.
    }

    public void onTestStart(ITestResult result) {
        // 1. Get the name of the current test method
        String testName = result.getMethod().getMethodName();

        // 2. Automatically create a new test entry in the report
        if (BaseClass.extent != null) {
            BaseClass.test = BaseClass.extent.createTest(testName);
        }
    }

    public void onTestSuccess(ITestResult result) {
        if (BaseClass.test != null) {
            BaseClass.test.log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
        }
    }

    public void onTestFailure(ITestResult result) {
        if (BaseClass.test != null) {
            BaseClass.test.log(Status.FAIL, "Test failed: " + result.getMethod().getMethodName());
            BaseClass.test.log(Status.FAIL, result.getThrowable().getMessage());

            // 3. Automatically capture screenshot on failure
            try {
                // Get the driver instance from the test class
                Object testObject = result.getInstance();
                if (testObject instanceof BaseClass) {
                    BaseClass base = (BaseClass) testObject;
                    String screenshotPath = base.captureScreenshot(result.getMethod().getMethodName());
                    // The captureScreenshot method in BaseClass already attaches the screenshot
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onTestSkipped(ITestResult result) {
        if (BaseClass.test != null) {
            BaseClass.test.log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
        }
    }

    public void onFinish(ITestContext context) {
        // BaseClass's @AfterSuite already handles extent.flush()
        // If you were not using @AfterSuite, you would flush here:
        // if (BaseClass.extent != null) BaseClass.extent.flush();
    }
}*/






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
