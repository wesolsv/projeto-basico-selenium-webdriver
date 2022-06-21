package br.ce.woliver.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
	private static WebDriver driver;
	
	private DriverFactory() {
	}	
		
	public static WebDriver getDriver() {
		if(driver == null) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\WEs\\Documents\\ChromeDriver\\chromedriver.exe");
			switch(Propriedades.browser) {
				case FIREFOX: driver = new FirefoxDriver(); break;
				case CHROME: driver = new ChromeDriver(); break;
			}
		}
		return driver;
	}
	
	public static void killDriver() {
		if(driver != null) {
		driver.quit();
		driver = null;
		}
	}
}
