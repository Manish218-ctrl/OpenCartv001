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
import org.testng.annotations.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.*;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class BaseClass {

    // =====================================================
    // CORE OBJECTS
    // =====================================================
    protected WebDriver driver;
    protected Properties p;
    protected ResourceBundle rb;
    public static Logger logger = LogManager.getLogger(BaseClass.class);

    // =====================================================
    // SHARED TEST DATA (⚠️ REQUIRED BY TEST CASES)
    // =====================================================
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

    // =====================================================
    // REPORTING
    // =====================================================
    public static ExtentReports extent;
    public static ExtentTest test;

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

        // ---- Load ALL test data ----
        appURL = p.getProperty("appURL");
        username = p.getProperty("username");
        password = p.getProperty("password");
        productName = p.getProperty("productName");
        searchProduct = p.getProperty("searchProduct");
        searchProductName = p.getProperty("searchProductName");
        nonExistingSearchProduct = p.getProperty("nonExistingSearchProduct");
        multiProductSearchKeyword = p.getProperty("multiProductSearchKeyword");
        singleProductSearchKeyword = p.getProperty("singleProductSearchKeyword");

        initializeDriver(br, os);

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(appURL);
    }

    // =====================================================
    // BACKWARD-COMPATIBLE DRIVER METHODS
    // =====================================================
    public void initializeDriver(String br, String os) throws MalformedURLException {
        initializeDriver(br); // backward compatibility
    }

    public void initializeDriver(String br) throws MalformedURLException {

        String env = p.getProperty("execution_env", "remote");

        // ==========================
        // REMOTE (CI / GRID)
        // ==========================
        if (env.equalsIgnoreCase("remote")) {

            URL gridUrl = new URL("http://localhost:4444/wd/hub");

            switch (br.toLowerCase()) {

                case "chrome":
                    ChromeOptions chrome = new ChromeOptions();
                    chrome.addArguments(
                            "--headless=new",
                            "--no-sandbox",
                            "--disable-dev-shm-usage",
                            "--window-size=1920,1080"
                    );
                    driver = new RemoteWebDriver(gridUrl, chrome);
                    break;

                case "firefox":
                    FirefoxOptions firefox = new FirefoxOptions();
                    firefox.addArguments("-headless");
                    driver = new RemoteWebDriver(gridUrl, firefox);
                    break;

                case "edge":
                    EdgeOptions edge = new EdgeOptions();
                    edge.addArguments(
                            "--headless=new",
                            "--no-sandbox",
                            "--disable-dev-shm-usage"
                    );
                    driver = new RemoteWebDriver(gridUrl, edge);
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + br);
            }
        }
        // ==========================
        // LOCAL
        // ==========================
        else {
            switch (br.toLowerCase()) {
                case "chrome": driver = new ChromeDriver(); break;
                case "firefox": driver = new FirefoxDriver(); break;
                case "edge": driver = new EdgeDriver(); break;
                default:
                    throw new IllegalArgumentException("Invalid browser: " + br);
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

        LoginPage login = new LoginPage(driver);
        login.login(username, password);

        logger.info("Login successful");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    // =====================================================
    // EXTENT REPORT
    // =====================================================
    public void initExtentReport() {

        String ts = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String path = System.getProperty("user.dir") + "/reports/Extent_" + ts + ".html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("OpenCart Automation Report");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    // =====================================================
    // SCREENSHOT
    // =====================================================
    public String captureScreenshot(String testName) {

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            String path = System.getProperty("user.dir") +
                    "/screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";

            FileHandler.copy(src, new File(path));
            return path;

        } catch (Exception e) {
            logger.error("Screenshot failed", e);
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
        Random r = new Random();
        for (int i = 0; i < 5; i++) sb.append(chars.charAt(r.nextInt(chars.length())));
        return sb.toString();
    }
}
