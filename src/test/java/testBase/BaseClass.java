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
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
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

import pageObjects.HomePage;
import pageObjects.LoginPage;

public class BaseClass {

    protected WebDriver driver;
    protected Properties p;
    protected ResourceBundle rb;

    protected String browserName;
    protected String osName;
    protected String appURL;
    protected String username;
    protected String password;

    public static ExtentReports extent;
    public static ExtentTest test;
    public static Logger logger = LogManager.getLogger(BaseClass.class);

    // =====================================================
    // SUITE SETUP
    // =====================================================
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

    // =====================================================
    // TEST SETUP
    // =====================================================
    @BeforeClass
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {

        FileReader file = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        rb = ResourceBundle.getBundle("config");

        this.browserName = br;
        this.osName = os;

        this.appURL = p.getProperty("appURL");
        this.username = p.getProperty("username");
        this.password = p.getProperty("password");

        try {
            initializeDriver(br);
        } catch (MalformedURLException e) {
            throw new RuntimeException("WebDriver initialization failed", e);
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(appURL);
    }

    // =====================================================
    // DRIVER INITIALIZATION (LOCAL + CI SAFE)
    // =====================================================
    public void initializeDriver(String br) throws MalformedURLException {

        String env = p.getProperty("execution_env", "remote");

        // ==========================
        // REMOTE (CI / SELENIUM GRID)
        // ==========================
        if (env.equalsIgnoreCase("remote")) {

            URL gridUrl = new URL("http://localhost:4444/wd/hub");

            switch (br.toLowerCase()) {

                case "chrome": {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments(
                            "--headless=new",
                            "--no-sandbox",
                            "--disable-dev-shm-usage",
                            "--window-size=1920,1080"
                    );
                    driver = new RemoteWebDriver(gridUrl, options);
                    break;
                }

                case "firefox": {
                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments("-headless");
                    driver = new RemoteWebDriver(gridUrl, options);
                    break;
                }

                case "edge": {
                    EdgeOptions options = new EdgeOptions();
                    options.addArguments(
                            "--headless=new",
                            "--no-sandbox",
                            "--disable-dev-shm-usage"
                    );
                    driver = new RemoteWebDriver(gridUrl, options);
                    break;
                }

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + br);
            }
        }

        // ==========================
        // LOCAL EXECUTION
        // ==========================
        else {

            switch (br.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid browser name: " + br);
            }
        }
    }

    // =====================================================
    // COMMON ACTIONS
    // =====================================================
    public void performLogin() {
        HomePage home = new HomePage(driver);
        home.clickMyAccount();
        home.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        logger.info("Login successful");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // =====================================================
    // EXTENT REPORT
    // =====================================================
    public void initExtentReport() {

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = System.getProperty("user.dir")
                + "/reports/ExtentReport_" + timestamp + ".html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("OpenCart Automation Report");
        reporter.config().setDocumentTitle("Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("OS", osName);
        extent.setSystemInfo("Browser", browserName);
    }

    // =====================================================
    // SCREENSHOT
    // =====================================================
    public String captureScreenshot(String testName) {

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String path = System.getProperty("user.dir")
                    + "/screenshots/" + testName + "_" + timestamp + ".png";

            FileHandler.copy(src, new File(path));
            return path;

        } catch (Exception e) {
            logger.error("Screenshot capture failed", e);
            return null;
        }
    }

    // =====================================================
    // UTILITIES
    // =====================================================
    protected WebDriverWait waitShort() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

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
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}











































