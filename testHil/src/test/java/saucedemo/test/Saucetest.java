package saucedemo.test;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Saucetest {

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
	        // Setting up Chrome driver path
	        System.setProperty("webdriver.chrome.driver", "drivers//chromedriver");

	        // Setting Chrome options
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--start-maximized"); // Maximize browser window

	        // Initializing WebDriver
	        WebDriver driver = new ChromeDriver(options);

	        // Implicit wait to handle dynamic elements
	        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			
			Endtoendtest page = new Endtoendtest(driver);
			
			driver.get("https://www.saucedemo.com/");
			
			page.completePurchaseProcess();
			
			driver.quit();
		}

	
	

}
