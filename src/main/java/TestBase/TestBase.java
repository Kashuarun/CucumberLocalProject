package TestBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ResoucePath.ResourcePath;

public class TestBase {
	public static WebDriver driver;
	
	public static void launchingBrowser(String browser)
	{if(System.getProperty("os.name").contains("Window")){
		if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", ResourcePath.USER_DIR+"Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", ResourcePath.USER_DIR+"Drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
	}
	else if(System.getProperty("os.name").contains("Mac")){
		System.out.println(System.getProperty("os.name"));
		if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", ResourcePath.USER_DIR+"Drivers/geckodriver");
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", ResourcePath.USER_DIR+"Drivers/chromedriver");
			driver = new ChromeDriver();
		}
	}
		
	}

}
