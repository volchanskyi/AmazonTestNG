package core;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Logic {
    static WebDriver driver;
//    static final String baseUrl = "http://demo.guru99.com/selenium/newtours/";
    // String csvFile = "./test_data/csv/bat/test.csv";
    // String csvFile = "./test_data/csv/bat/elements_validation_chrome.csv";
    // String csvFile = "./test_data/csv/bat/elements_validation_firefox.csv";
    // String csvFile = "./test_data/csv/bat/elements_validation_safari.csv";
    // private String test_name = "";
    private String elem;
    private String keys;
    private String url;
    private By e;


    private static boolean isPresent(String element_id) {
	if ((driver.findElements(By.id(element_id)).size() > 0)) {
	    return true;
	} else {
	    System.out.println("elem is not present");
	    return false;
	}
    }

    public static void quit() {
	driver.quit();
    }

    public String getTitle() {
	return driver.getTitle();
    }

    // public static boolean click(String param){
    // if (isPresent(param)) {
    // driver.findElement(By.id(param)).click();
    // } else
    // System.out.println("elem is not visable");
    // return false;
    // }

    public Logic click(String element) {
	if (isPresent(element)) {
	    this.elem = element;
	    driver.findElement(By.id(this.elem)).click();
	    return this;
	} else
	return this;
    }
    public Logic newClick(By e) {
	WebDriverWait exWait = new WebDriverWait(driver, 10);
	if (exWait.until(ExpectedConditions.visibilityOfElementLocated(e)) != null) {
	    this.e = e;
	    driver.findElement(e).click();
	    return this;
	} else
	    return this;
    }

    // public static boolean type(String param, String keys){
    // if (isPresent(param)) {
    // driver.findElement(By.id(param)).sendKeys(keys);
    // } else
    // System.out.println("elem is not visable");
    // return false;
    // }
    public Logic type(String keys) {
	this.keys = keys;
	driver.findElement(this.e).sendKeys(this.keys);
	return this;
    }

    public Logic selectFirstSuggestedItem(String element) {
	if (isPresent(element)) {
	    this.elem = element;
	    driver.findElement(By.id(this.elem)).click();
	    return this;
	} else
	    System.out.println("elem is not visable");
	return this;
    }

    public Logic navigateToUrl(String url) {
	if (url != null) {
	    this.url = url;
	    driver.navigate().to(this.url);
	    return this;
	} else
	    System.out.println("there`s no url entered");
	return this;
    }

    public void getDriver(String browser, String url) {
	Logger logger = Logger.getLogger("");
	logger.setLevel(Level.OFF);
	if (browser.equalsIgnoreCase("chrome")) {
	    String driverPath = "";
	    if (System.getProperty("os.name").toUpperCase().contains("MAC"))
		driverPath = "./resources/webdrivers/mac/chromedriver";
	    else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
		driverPath = "./resources/webdrivers/pc/chromedriver.exe";
	    else
		throw new IllegalArgumentException("Unknown OS");
	    System.setProperty("webdriver.chrome.driver", driverPath);
	    System.setProperty("webdriver.chrome.silentOutput", "true");
	    ChromeOptions option = new ChromeOptions();
	    option.addArguments("disable-infobars");
	    option.addArguments("--disable-notifications");
	    if (System.getProperty("os.name").toUpperCase().contains("MAC"))
		option.addArguments("-start-fullscreen");
	    else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
		option.addArguments("--start-maximized");
	    else
		throw new IllegalArgumentException("Unknown OS");
	    driver = new ChromeDriver(option);
	    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	else if (browser.equalsIgnoreCase("firefox")) {
	    String driverPath = "";
	    if (System.getProperty("os.name").toUpperCase().contains("MAC"))
		driverPath = "./resources/webdrivers/mac/geckodriver.sh";
	    else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
		driverPath = "./resources/webdrivers/pc/geckodriver.exe";
	    else
		throw new IllegalArgumentException("Unknown OS");

	    System.setProperty("webdriver.gecko.driver", driverPath);
	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	}

	else if (browser.equalsIgnoreCase("safari")) {
	    if (!System.getProperty("os.name").contains("Mac")) {
		throw new IllegalArgumentException("Safari is available only on Mac");
	    }
	    driver = new SafariDriver();
	    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	} else {
	    driver = new HtmlUnitDriver();
	    ((HtmlUnitDriver) driver).setJavascriptEnabled(true);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	driver.get(url);
    }

}
