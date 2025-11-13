

package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
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

import pageObjects.BasePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class BaseClass  {

    public WebDriver driver;
    //public Logger logger;
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

    public static Logger logger = LogManager.getLogger(BaseClass.class);

    @BeforeClass
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {
        // Logger setup

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
            initializeDriver(br, os);
        } catch (MalformedURLException e) {
            logger.error("Failed to initialize WebDriver: Remote URL is malformed.", e);
            throw new RuntimeException("WebDriver initialization failed due to configuration error.", e);
        }

        // Browser setup
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(rb.getString("appURL"));
    }

    public void initializeDriver(String br, String os) throws MalformedURLException {
        String env = p.getProperty("execution_env", "local");

        if (env.equalsIgnoreCase("local")) {
            URL grid = new URL("http://localhost:4444"); // Grid 4 router

            switch (br.toLowerCase()) {
                case "chrome": {
                    ChromeOptions co = new ChromeOptions();
                    // co.setPlatformName("LINUX"); // optional
                    driver = new RemoteWebDriver(grid, co);
                    break;
                }
                case "firefox": {
                    FirefoxOptions fo = new FirefoxOptions();
                    driver = new RemoteWebDriver(grid, fo);
                    break;
                }
                case "edge": {
                    EdgeOptions eo = new EdgeOptions();
                    driver = new RemoteWebDriver(grid, eo);
                    break;
                }
                default:
                    throw new IllegalArgumentException("Unsupported browser for remote: " + br);
            }

        } else { // local (legacy behavior preserved)
            switch (br.toLowerCase()) {
                case "chrome":  driver = new ChromeDriver();  break;
                case "firefox": driver = new FirefoxDriver(); break;
                case "edge":    driver = new EdgeDriver();    break;
                default: throw new IllegalArgumentException("Invalid browser name: " + br);
            }
        }
    }




    public void performLogin() {
        HomePage home = new HomePage(driver);
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

   // Call this at the start of each test method, passing the test name
    public void createTest(String testName) {
        if (extent != null) {
            test = extent.createTest(testName);
        }
    }

   public String captureScreenshot(String testName) {


        if (driver == null) {
            System.out.println("️WebDriver is null. Cannot take screenshot.");
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

    protected WebDriverWait waitShort() {
        // Example implementation: waits for 10 seconds
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}











































/*package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utilities.ExtentManager;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class BaseClass {

    public WebDriver driver;
    public Properties p;
    protected String browserName;
    protected String osName;
    protected String appURL;
    protected String username;
    protected String password;

    public static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static Logger logger = LogManager.getLogger(BaseClass.class);

    // ---------------------------------------------------------
    // 🔹 SUITE SETUP / TEARDOWN
    // ---------------------------------------------------------
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        extent = ExtentManager.createInstance();
        logger.info("ExtentReports initialized.");
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        if (extent != null) {
            extent.flush();
            logger.info("ExtentReports flushed successfully.");
        }
    }

    // ---------------------------------------------------------
    // 🔹 CLASS SETUP / TEARDOWN
    // ---------------------------------------------------------
    @BeforeClass(alwaysRun = true)
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {
        loadProperties();
        this.browserName = br;
        this.osName = os;

        logger.info("Setting up driver for browser: " + browserName + " on OS: " + osName);
        initializeDriver(br, os);

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(p.getProperty("appURL"));

        // Update system info in ExtentReport
        if (extent != null) {
            extent.setSystemInfo("OS", osName);
            extent.setSystemInfo("Browser", browserName);
            extent.setSystemInfo("Tester", "Automation QA");
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("WebDriver closed successfully for " + browserName);
        }
    }

    // ---------------------------------------------------------
    // 🔹 DRIVER INITIALIZATION
    // ---------------------------------------------------------
    public void initializeDriver(String br, String os) throws MalformedURLException {

        String execEnv = p.getProperty("execution_env", "local");

        if (execEnv.equalsIgnoreCase("remote")) {
            DesiredCapabilities caps = new DesiredCapabilities();

            if (os.equalsIgnoreCase("windows")) {
                caps.setPlatform(Platform.WINDOWS);
            } else if (os.equalsIgnoreCase("mac")) {
                caps.setPlatform(Platform.MAC);
            } else {
                throw new IllegalArgumentException("Unsupported OS: " + os);
            }

            switch (br.toLowerCase()) {
                case "chrome": caps.setBrowserName("chrome"); break;
                case "firefox": caps.setBrowserName("firefox"); break;
                case "edge": caps.setBrowserName("MicrosoftEdge"); break;
                default: throw new IllegalArgumentException("Invalid browser: " + br);
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
        } else {
            switch (br.toLowerCase()) {
                case "chrome": driver = new ChromeDriver(); break;
                case "firefox": driver = new FirefoxDriver(); break;
                case "edge": driver = new EdgeDriver(); break;
                default: throw new IllegalArgumentException("Invalid browser: " + br);
            }
        }
    }

    // ---------------------------------------------------------
    // 🔹 UTILITY METHODS
    // ---------------------------------------------------------
    private void loadProperties() throws IOException {
        FileReader file = new FileReader(".//src//test//resources//config.properties");
        p = new Properties();
        p.load(file);
    }

    public void performLogin() {
        HomePage home = new HomePage(driver);
        home.clickMyAccount();
        home.clickLogin();

        LoginPage login = new LoginPage(driver);
        login.login(p.getProperty("username"), p.getProperty("password"));

        logger.info("User logged in successfully.");
        if (test.get() != null) test.get().pass("Login successful");
    }

    public String captureScreenshot(String testName) {
        if (driver == null) {
            logger.error("WebDriver is null. Cannot capture screenshot.");
            return null;
        }

        testName = testName.replaceAll("[^a-zA-Z0-9_-]", "_");

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String path = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";

            File dest = new File(path);
            dest.getParentFile().mkdirs();
            FileHandler.copy(src, dest);

            logger.info("Screenshot captured: " + path);
            if (test.get() != null) test.get().addScreenCaptureFromPath(path);

            return path;
        } catch (IOException e) {
            logger.error("Screenshot capture failed: " + e.getMessage());
            return null;
        }
    }

    // ---------------------------------------------------------
    // 🔹 RANDOM DATA GENERATORS
    // ---------------------------------------------------------
    public String randomString() { return RandomStringUtils.randomAlphabetic(5); }
    public String randomNumber() { return RandomStringUtils.randomNumeric(10); }
    public String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphabetic(3) + "@" + RandomStringUtils.randomNumeric(3);
    }
    public String randomCustomString() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 5; i++) sb.append(chars.charAt(rand.nextInt(chars.length())));
        return sb.toString();
    }

    protected WebDriverWait waitShort() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}*/

