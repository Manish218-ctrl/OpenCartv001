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

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pageObjects.HomePage;
import pageObjects.LoginPage;

import static utilities.ExtentManager.extent;

public class BaseClass {

    // ================= THREAD SAFE DRIVER =================
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return driverThread.get();
    }

    // ================= CONFIG =================
    protected Properties p;
    public static Logger logger = LogManager.getLogger(BaseClass.class);

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

    // ================= REPORT =================
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void setTest(ExtentTest t) {
        extentTest.set(t);
    }

    // ================= SUITE SETUP =================
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

    // ================= TEST SETUP =================
    @BeforeClass
    @Parameters({"os", "browser"})
    public void setup(
            @Optional("windows") String os,
            @Optional("chrome") String br) throws IOException {

        FileReader file = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        this.browserName = br;
        this.osName = os;

        appURL = p.getProperty("appURL");
        username = p.getProperty("username");
        password = p.getProperty("password");

        productName = p.getProperty("productName");
        searchProduct = p.getProperty("searchProduct");
        searchProductName = p.getProperty("searchProductName");
        nonExistingSearchProduct = p.getProperty("nonExistingSearchProduct");
        multiProductSearchKeyword = p.getProperty("multiProductSearchKeyword");
        singleProductSearchKeyword = p.getProperty("singleProductSearchKeyword");

        initializeDriver(br);

        logger.info("Driver initialized: " + getDriver().getClass().getSimpleName());

        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Avoid maximize in headless CI
        if (!p.getProperty("execution_env", "local").equalsIgnoreCase("remote")) {
            getDriver().manage().window().maximize();
        }

        getDriver().get(appURL);
    }

    // ================= DRIVER INIT =================
    public void initializeDriver(String br) throws MalformedURLException {

        String env = p.getProperty("execution_env", "local");

        logger.info("Execution Environment: " + env);
        logger.info("Browser: " + br);

        if (env.equalsIgnoreCase("remote")) {

            String grid = p.getProperty("grid_url", "http://localhost:4444/wd/hub");
            URL gridUrl = new URL(grid);

            logger.info("Using Selenium Grid: " + gridUrl);

            switch (br.toLowerCase()) {

                case "chrome":
                    ChromeOptions chrome = new ChromeOptions();
                    chrome.addArguments(
                            "--headless=new",
                            "--no-sandbox",
                            "--disable-dev-shm-usage",
                            "--window-size=1920,1080"
                    );
                    driverThread.set(new RemoteWebDriver(gridUrl, chrome));
                    break;

                case "firefox":
                    FirefoxOptions firefox = new FirefoxOptions();
                    firefox.addArguments(
                            "--headless",
                            "--width=1920",
                            "--height=1080"
                    );
                    driverThread.set(new RemoteWebDriver(gridUrl, firefox));
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + br);
            }

        } else {

            logger.info("Running in LOCAL mode");

            switch (br.toLowerCase()) {
                case "chrome":
                    driverThread.set(new ChromeDriver());
                    break;
                case "firefox":
                    driverThread.set(new FirefoxDriver());
                    break;
                case "edge":
                    driverThread.set(new EdgeDriver());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid browser: " + br);
            }
        }
    }

    // ================= COMMON ACTION =================
    public void performLogin() {
        HomePage home = new HomePage(getDriver());
        home.clickMyAccount();
        home.clickLogin();

        LoginPage login = new LoginPage(getDriver());
        login.login(username, password);

        logger.info("Login successful");
    }

    // ================= CLEANUP =================
    @AfterMethod
    public void cleanUpTest() {
        extentTest.remove();
    }

    @AfterClass
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driverThread.remove();
        }
    }

    // ================= EXTENT REPORT =================
    public void initExtentReport() {

        String ts = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String path = System.getProperty("user.dir") + "/reports/Extent_" + ts + ".html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("OpenCart Automation Report");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    // ================= SCREENSHOT (FILE) =================
    public String captureScreenshot(String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) getDriver();
            File src = ts.getScreenshotAs(OutputType.FILE);

            String path = System.getProperty("user.dir") +
                    "/screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";

            FileHandler.copy(src, new File(path));
            return path;

        } catch (Exception e) {
            logger.error("Screenshot failed", e);
            return "";
        }
    }

    // ================= SCREENSHOT (BASE64) =================
    public String captureScreenshotBase64() {
        try {
            return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            logger.error("Base64 screenshot failed", e);
            return "";
        }
    }

    // ================= UTILITIES =================
    protected WebDriverWait waitShort() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }

    // ================= RANDOM UTILS =================
    private static final Random RANDOM = new Random();

    public String randomString() {
        return generateRandom("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", 5);
    }

    public String randomNumber() {
        return generateRandom("0123456789", 10);
    }

    public String randomAlphaNumeric() {
        return generateRandom("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", 3)
                + "@"
                + generateRandom("0123456789", 3);
    }

    private String generateRandom(String chars, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(RANDOM.nextInt(chars.length())));
        }
        return sb.toString();
    }
}