/*package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;

    public static ExtentReports extent;

    public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

    String repName;

    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        // This line now correctly refers to the class-level field
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\" + repName);

        sparkReporter.config().setDocumentTitle("opencart Automation Report");
        sparkReporter.config().setReportName("opencart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "Opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", "Automation Tester");
        extent.setSystemInfo("Environemnt", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        if (os != null) extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        if (browser != null) extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    public void onTestStart(ITestResult result) {
        ExtentTest t = extent.createTest(result.getMethod().getMethodName());
        t.assignCategory(result.getMethod().getGroups());
        test.set(t);
        test.get().log(Status.INFO, "Test execution started for: " + result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS,result.getMethod().getMethodName() + " got successfully executed");
    }

    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL,result.getMethod().getMethodName() + " got failed");
        test.get().log(Status.INFO, result.getThrowable());

        try {
            Object testObject = result.getInstance();
            if (testObject instanceof BaseClass) {
                BaseClass base = (BaseClass) testObject;
                // captureScreenshot in BaseClass handles driver and returns path
                String imgPath = base.captureScreenshot(result.getMethod().getMethodName());
                test.get().addScreenCaptureFromPath(imgPath, result.getMethod().getMethodName());
            } else {
                System.err.println("Test class is not an instance of BaseClass. Cannot capture screenshot.");
            }
        } catch (Exception e) {
            test.get().log(Status.WARNING, "Screenshot capture failed: " + e.getMessage());
        }
    }

    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, result.getMethod().getMethodName() + " got skipped");
        // Check for throwable before getting message to avoid NullPointerException
        if(result.getThrowable() != null) {
            test.get().log(Status.INFO, result.getThrowable().getMessage());
        }
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();

        String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

}*/











package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    String repName;

    @Override
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\" + repName);
        sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
        sparkReporter.config().setReportName("OpenCart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("User Name", "Automation Tester");
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        if (os != null) extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        if (browser != null) extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        extentTest.assignCategory(result.getMethod().getGroups());
        test.set(extentTest);

        ExtentTest currentTest = test.get();
        if (currentTest != null) {
            currentTest.log(Status.INFO, "Test execution started for: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest currentTest = test.get();
        if (currentTest != null) {
            currentTest.log(Status.PASS, result.getMethod().getMethodName() + " got successfully executed");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest currentTest = test.get();
        if (currentTest == null) {
            System.err.println("ExtentTest is null. Creating new test entry for: " + result.getMethod().getMethodName());
            currentTest = extent.createTest(result.getMethod().getMethodName());
            test.set(currentTest);
        }

        currentTest.log(Status.FAIL, result.getMethod().getMethodName() + " got failed");
        currentTest.log(Status.INFO, result.getThrowable());

        try {
            Object testObject = result.getInstance();
            if (testObject instanceof BaseClass) {
                BaseClass base = (BaseClass) testObject;
                String imgPath = base.captureScreenshot(result.getMethod().getMethodName());
                currentTest.addScreenCaptureFromPath(imgPath, result.getMethod().getMethodName());
            } else {
                System.err.println("Test class is not an instance of BaseClass. Cannot capture screenshot.");
            }
        } catch (Exception e) {
            currentTest.log(Status.WARNING, "Screenshot capture failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest currentTest = test.get();
        if (currentTest != null) {
            currentTest.log(Status.SKIP, result.getMethod().getMethodName() + " got skipped");
            if (result.getThrowable() != null) {
                currentTest.log(Status.INFO, result.getThrowable().getMessage());
            }
        }
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush();

        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }
}





















