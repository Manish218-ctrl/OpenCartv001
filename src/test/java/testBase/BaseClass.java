/*package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pageObjects.Homepage;
import pageObjects.LoginPage;

public class BaseClass {

    public WebDriver driver;
    public Logger logger;
    public Properties p;
    protected ResourceBundle rb;
    protected String browserName;
    protected String osName;
    protected String appURL;
    protected String username;
    protected String password;
    protected String productName;
    protected String searchProduct;
    protected String searchProductName;
    protected String nonExistingSearchProduct;
    protected String multiProductSearchKeyword;
    protected String singleProductSearchKeyword;

    // ExtentReports objects
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeSuite
    public void beforeSuite() {
        initExtentReport();
    }

    @AfterSuite
    public void afterSuite() {
        if (extent != null) {
            extent.flush();
        }
    }

    @BeforeClass
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {
        // Logger setup
        logger = LogManager.getLogger(this.getClass());

        // Load properties
        FileReader file = new FileReader(".//src//test//resources//config.properties");
        p = new Properties();
        p.load(file);

        rb = ResourceBundle.getBundle("config");

        this.browserName = br;
        this.osName = os;

        this.appURL = p.getProperty("appURL");
        this.username = p.getProperty("username");
        this.password = p.getProperty("password");
        this.productName = p.getProperty("productName");
        this.searchProduct = p.getProperty("searchProduct");
        this.searchProductName = p.getProperty("searchProductName");
        this.nonExistingSearchProduct = p.getProperty("nonExistingSearchProduct");
        this.multiProductSearchKeyword = p.getProperty("multiProductSearchKeyword");
        this.singleProductSearchKeyword = p.getProperty("singleProductSearchKeyword");

        // Initialize WebDriver
        initializeDriver(br);

        // Browser setup
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(rb.getString("appURL"));
    }












    public void initializeDriver(String br) {
        switch (br.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + br);
        }
    }

    public void performLogin() {
        Homepage home = new Homepage(driver);
        home.clickMyAccount();
        home.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        logger.info("User logged in successfully.");
        if (test != null) test.pass("Login successful");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        // Removed extent.flush() from here
    }

    public void initExtentReport() {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            String reportDir = System.getProperty("user.dir") + "/reports/";
            File reportsFolder = new File(reportDir);
            if (!reportsFolder.exists()) {
                reportsFolder.mkdir();
            }

            String reportPath = reportDir + "ExtentReport_" + timestamp + ".html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setReportName("OpenCart Test Automation Report");
            sparkReporter.config().setDocumentTitle("Automation Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("OS", osName);
            extent.setSystemInfo("Browser", browserName);
            extent.setSystemInfo("Tester", "Automation Tester");
        } catch (Exception e) {
            System.out.println("Failed to initialize ExtentReports: " + e.getMessage());
        }
    }

  /*  // Call this at the start of each test method, passing the test name
    public void createTest(String testName) {
        if (extent != null) {
            test = extent.createTest(testName);
        }
    }*/

    /*public String captureScreenshot(String testName) {

        if (driver == null) {
            System.out.println("️WebDriver is null. Cannot take screenshot.");
            return testName;
        }

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

            String screenshotsDir = System.getProperty("user.dir") + "/screenshots/";
            File screenshotsFolder = new File(screenshotsDir);
            if (!screenshotsFolder.exists()) {
                screenshotsFolder.mkdir();
            }

            String screenshotPath = screenshotsDir + testName + "_" + timestamp + ".png";

            FileHandler.copy(src, new File(screenshotPath));
            logger.info("Screenshot captured: " + screenshotPath);

            // Attach to report if ExtentTest is active
            if (test != null) {
                test.addScreenCaptureFromPath(screenshotPath);
            }
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
        }
        return testName;
    }*/


  /*  public String captureScreenshot(String testName) {

        // ... (unchanged initialization and null checks)

        if (driver == null) {
            System.out.println("️WebDriver is null. Cannot take screenshot.");
            // Change: Return null or an empty string on failure to indicate no path
            return null;
        }

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());


            String screenshotsDir = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator;
            File screenshotsFolder = new File(screenshotsDir);
            if (!screenshotsFolder.exists()) {
                screenshotsFolder.mkdir();
            }

            String screenshotPath = screenshotsDir + testName + "_" + timestamp + ".png";

            FileHandler.copy(src, new File(screenshotPath));
            logger.info("Screenshot captured: " + screenshotPath);

            // Attach to report if ExtentTest is active (Note: This is redundant if called from the Listener,
            // but harmless. The Listener handles the attachment using the returned path.)
            if (test != null) {
                test.addScreenCaptureFromPath(screenshotPath);
            }


            return screenshotPath;

        } catch (IOException e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
            // Change: Return null or an empty string on failure
            return null;
        }
    }




    // Random data generators
    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphabetic(3) + "@" + RandomStringUtils.randomNumeric(3);
    }

    public String randomCustomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
} */




























package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException; // Added for URL
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pageObjects.Homepage;
import pageObjects.LoginPage;

public class BaseClass {

    public WebDriver driver;
    public Logger logger;
    public Properties p;
    protected ResourceBundle rb;
    protected String browserName;
    protected String osName;
    protected String appURL;
    protected String username;
    protected String password;
    protected String productName;
    protected String searchProduct;
    protected String searchProductName;
    protected String nonExistingSearchProduct;
    protected String multiProductSearchKeyword;
    protected String singleProductSearchKeyword;

    // ExtentReports objects
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeSuite
    public void beforeSuite() {
        initExtentReport();
    }

    @AfterSuite
    public void afterSuite() {
        if (extent != null) {
            extent.flush();
        }
    }

    @BeforeClass
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {
        // Logger setup
        logger = LogManager.getLogger(this.getClass());

        // Load properties
        FileReader file = new FileReader(".//src//test//resources//config.properties");
        p = new Properties();
        p.load(file);

        rb = ResourceBundle.getBundle("config");

        this.browserName = br;
        this.osName = os;

        this.appURL = p.getProperty("appURL");
        this.username = p.getProperty("username");
        this.password = p.getProperty("password");
        this.productName = p.getProperty("productName");
        this.searchProduct = p.getProperty("searchProduct");
        this.searchProductName = p.getProperty("searchProductName");
        this.nonExistingSearchProduct = p.getProperty("nonExistingSearchProduct");
        this.multiProductSearchKeyword = p.getProperty("multiProductSearchKeyword");
        this.singleProductSearchKeyword = p.getProperty("singleProductSearchKeyword");

        // Initialize WebDriver
        try {
            // Updated call to pass 'os' parameter
            initializeDriver(br, os);
        } catch (MalformedURLException e) {
            logger.error("Failed to initialize WebDriver: Remote URL is malformed.", e);
            throw new RuntimeException("WebDriver initialization failed due to configuration error.", e);
        }

        // Browser setup
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        // The original code uses rb.getString("appURL") here, but loads appURL using p.getProperty("appURL") above.
        // Assuming rb.getString("appURL") is the intended source for navigation.
        driver.get(rb.getString("appURL"));
    }


    // Replaced the original initializeDriver logic with the user-provided implementation for remote/local execution
    public void initializeDriver(String br, String os) throws MalformedURLException {

        if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
        {
            DesiredCapabilities capabilities=new DesiredCapabilities();

            //os
            if(os.equalsIgnoreCase("windows"))
            {
                capabilities.setPlatform(Platform.WIN11);
            }
            else if (os.equalsIgnoreCase("mac"))
            {
                capabilities.setPlatform(Platform.MAC);
            }
            else
            {
                System.out.println("No matching os");
                return;
            }

            //browser
            switch(br.toLowerCase())
            {
                case "chrome": capabilities.setBrowserName("chrome"); break;
                case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
                case "firefox": capabilities.setBrowserName("firefox"); break;

                default: System.out.println("No matching browser"); return;
            }


            driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
        }

        else if(p.getProperty("execution_env").equalsIgnoreCase("local")) // Changed to else if for clear separation
        {
            switch(br.toLowerCase())
            {
                case "chrome" : driver=new ChromeDriver(); break;
                case "edge" : driver=new EdgeDriver(); break;
                case "firefox": driver=new FirefoxDriver(); break;
                default : System.out.println("Invalid browser name.."); return;
            }
        }
    }

    public void performLogin() {
        Homepage home = new Homepage(driver);
        home.clickMyAccount();
        home.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        logger.info("User logged in successfully.");
        if (test != null) test.pass("Login successful");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        // Removed extent.flush() from here
    }

    public void initExtentReport() {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            String reportDir = System.getProperty("user.dir") + "/reports/";
            File reportsFolder = new File(reportDir);
            if (!reportsFolder.exists()) {
                reportsFolder.mkdir();
            }

            String reportPath = reportDir + "ExtentReport_" + timestamp + ".html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setReportName("OpenCart Test Automation Report");
            sparkReporter.config().setDocumentTitle("Automation Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("OS", osName);
            extent.setSystemInfo("Browser", browserName);
            extent.setSystemInfo("Tester", "Automation Tester");
        } catch (Exception e) {
            System.out.println("Failed to initialize ExtentReports: " + e.getMessage());
        }
    }

  /* // Call this at the start of each test method, passing the test name
    public void createTest(String testName) {
        if (extent != null) {
            test = extent.createTest(testName);
        }
    }*/

    public String captureScreenshot(String testName) {

        // ... (unchanged initialization and null checks)

        if (driver == null) {
            System.out.println("️WebDriver is null. Cannot take screenshot.");
            // Change: Return null or an empty string on failure to indicate no path
            return null;
        }

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());


            String screenshotsDir = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator;
            File screenshotsFolder = new File(screenshotsDir);
            if (!screenshotsFolder.exists()) {
                screenshotsFolder.mkdir();
            }

            String screenshotPath = screenshotsDir + testName + "_" + timestamp + ".png";

            FileHandler.copy(src, new File(screenshotPath));
            logger.info("Screenshot captured: " + screenshotPath);

            // Attach to report if ExtentTest is active (Note: This is redundant if called from the Listener,
            // but harmless. The Listener handles the attachment using the returned path.)
            if (test != null) {
                test.addScreenCaptureFromPath(screenshotPath);
            }


            return screenshotPath;

        } catch (IOException e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
            // Change: Return null or an empty string on failure
            return null;
        }
    }




    // Random data generators
    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphabetic(3) + "@" + RandomStringUtils.randomNumeric(3);
    }

    public String randomCustomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
}
