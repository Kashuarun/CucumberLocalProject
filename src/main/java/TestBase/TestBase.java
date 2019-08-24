/*
 * @author ArunKumar
 * 
 */
package TestBase;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import ResoucePath.ResourcePath;

public class TestBase {
	public static Logger log=getLogger(TestBase.class);
	public static WebDriver driver;

	private static void launchingBrowser(String browser) {
		try {
			if (System.getProperty("os.name").contains("Window")) {
				if (browser.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.gecko.driver", ResourcePath.USER_DIR + "Drivers/geckodriver.exe");
					driver = new FirefoxDriver();
					log.info("Launching fireforx browser");
				} else if (browser.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", ResourcePath.USER_DIR + "Drivers/chromedriver.exe");
					driver = new ChromeDriver();
					log.info("Launching chrome browser");
				}
				else if (browser.equalsIgnoreCase("IE")) {
					System.setProperty("webdriver.ie.driver", ResourcePath.USER_DIR + "Drivers/IEDriverServer.exe.exe");
					driver = new InternetExplorerDriver();
					log.info("Launching IE browser");
				}
			} else if (System.getProperty("os.name").contains("Mac")) {
				System.out.println(System.getProperty("os.name"));
				if (browser.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.gecko.driver", ResourcePath.USER_DIR + "Drivers/geckodriver");
					driver = new FirefoxDriver();
					log.info("Launching fireforx browser");
				} else if (browser.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", ResourcePath.USER_DIR + "Drivers/chromedriver");
					driver = new ChromeDriver();
					log.info("Launching chrome browser");
				}
				else if (browser.equalsIgnoreCase("IE")) {
					System.setProperty("webdriver.ie.driver", ResourcePath.USER_DIR + "Drivers/IEDriverServer.exe.exe");
					driver = new InternetExplorerDriver();
					log.info("Launching IE browser");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static void getUrl()
	{
		try {
			driver.manage().window().maximize();
			driver.get(getPropertiesValue(ResourcePath.GENERAL_UTILITIES, "url"));
			log.info("Getting the URL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void quitBrowser()
	{
		try {
			driver.quit();
			log.info("Closing the browser");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void init()
	{
		launchingBrowser(getPropertiesValue(ResourcePath.GENERAL_UTILITIES, "browser"));
		getUrl();
	}
	
	public static String getPropertiesValue(String propertiesFilePath,String key)
	{
		try {
			Properties p = new Properties();
			FileInputStream is= new FileInputStream(propertiesFilePath);
			p.load(is);
			log.info("Loading the propertiesFile key="+key+" and value="+p.getProperty(key));
			return p.getProperty(key);
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return key;
		
	}
	
	private static WebElement getLocator(String locator)
	{
		try {
			String[] split = locator.split(":");
			String locatorType = split[0];
			String locatorValue = split[1];
			if (locatorType.toLowerCase().equals("id"))
				return driver.findElement(By.id(locatorValue));
			else if (locatorType.toLowerCase().equals("name"))
				return driver.findElement(By.name(locatorValue));
			else if ((locatorType.toLowerCase().equals("classname"))|| (locatorType.toLowerCase().equals("class")))
				return driver.findElement(By.className(locatorValue));
			else if ((locatorType.toLowerCase().equals("tagname"))
					|| (locatorType.toLowerCase().equals("tag")))
				return driver.findElement(By.className(locatorValue));
			else if ((locatorType.toLowerCase().equals("linktext"))
					|| (locatorType.toLowerCase().equals("link")))
				return driver.findElement(By.linkText(locatorValue));
			else if (locatorType.toLowerCase().equals("partiallinktext"))
				return driver.findElement(By.partialLinkText(locatorValue));
			else if ((locatorType.toLowerCase().equals("cssselector"))
					|| (locatorType.toLowerCase().equals("css")))
				return driver.findElement(By.cssSelector(locatorValue));
			else if (locatorType.toLowerCase().equals("xpath"))
				return driver.findElement(By.xpath(locatorValue));
			else
				throw new Exception("Unknown locator type '" + locatorType + "'");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static List<WebElement> getListOfLocators(String locator) {
		try {
			String[] split = locator.split(":");
			String locatorType = split[0];
			String locatorValue = split[1];
			if (locatorType.toLowerCase().equals("id"))
				return driver.findElements(By.id(locatorValue));
			else if (locatorType.toLowerCase().equals("name"))
				return driver.findElements(By.name(locatorValue));
			else if ((locatorType.toLowerCase().equals("classname"))
					|| (locatorType.toLowerCase().equals("class")))
				return driver.findElements(By.className(locatorValue));
			else if ((locatorType.toLowerCase().equals("tagname"))
					|| (locatorType.toLowerCase().equals("tag")))
				return driver.findElements(By.className(locatorValue));
			else if ((locatorType.toLowerCase().equals("linktext"))
					|| (locatorType.toLowerCase().equals("link")))
				return driver.findElements(By.linkText(locatorValue));
			else if (locatorType.toLowerCase().equals("partiallinktext"))
				return driver.findElements(By.partialLinkText(locatorValue));
			else if ((locatorType.toLowerCase().equals("cssselector"))
					|| (locatorType.toLowerCase().equals("css")))
				return driver.findElements(By.cssSelector(locatorValue));
			else if (locatorType.toLowerCase().equals("xpath"))
				return driver.findElements(By.xpath(locatorValue));
			else
				throw new Exception("Unknown locator type '" + locatorType + "'");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static WebElement getWebElement(String ProFileName,String key)
	{
		try {
			return getLocator(getPropertiesValue(ProFileName, key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static List<WebElement> getWebElementList(String ProFileName,String key)
	{
		try {
			return getListOfLocators(getPropertiesValue(ProFileName, key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static Logger getLogger(Class cls)
	{
		try {
			PropertyConfigurator.configure(ResourcePath.LOG4J_PROPERTIES);
			return Logger.getLogger(cls.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(getPropertiesValue(ResourcePath.GENERAL_UTILITIES, "browser"));
	}
	
	
}
