package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;

import testBase.BaseClass;

import static utilities.ExtentManager.extent;

public class ExtentReportListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

        BaseClass.setTest(
                extent.createTest(result.getMethod().getQualifiedName())
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        BaseClass.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        BaseClass testBase = (BaseClass) result.getInstance();

        String base64 = testBase.captureScreenshotBase64();

        BaseClass.getTest().fail(result.getThrowable());

        if (!base64.isEmpty()) {
            BaseClass.getTest().fail(
                    MediaEntityBuilder
                            .createScreenCaptureFromBase64String(base64)
                            .build()
            );
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        BaseClass.getTest().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}