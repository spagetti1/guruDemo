package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import pom.AddNewAccount;
import pom.AddNewCustomer;
//
//import pom.EditCustomer;
import pom.LoginPage;
import utility.Utility;

public class Start {

	WebDriver driver;
	FileInputStream readProperty;
	JavascriptExecutor js;
	LoginPage login,invalid_login;
	AddNewCustomer addNewCustomer,missingfiled;
	
	String CUSTOMERID = null;
	String ACCOUNTID =null;

	@BeforeTest
	private void prepareClassProperties() throws IOException {
		readProperty = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\properties\\generalProperties.properties");
		Properties prop = new Properties();
		prop.load(readProperty);

		System.setProperty("webdriver.gecko.driver", prop.getProperty("firefoxdriver"));
		System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriver"));

		driver = new FirefoxDriver();
		js = (JavascriptExecutor) driver;
		login = new LoginPage(driver);
		addNewCustomer = new AddNewCustomer(driver);}
		
	@Test(priority = 1)
	private void startApplication() throws InterruptedException {
		// Mazimize current window
		driver.manage().window().maximize();
		// navigate to website
		driver.get("https://demo.guru99.com/V4/");
		// take screenshot to login page
		Utility.captureScreenshot(driver, "LoginPage");
		// wait for 5 sec
		Thread.sleep(5000);
	}

	@Test(priority = 2)
	private void login() throws InterruptedException {
		// add username
		login.userName.sendKeys("mngr474242");
		// add password
		login.userPassword.sendKeys("AhanunE");
		// click login button
		login.loginButton.click();
		// take screenshot to verify login
		Utility.captureScreenshot(driver, "HomePage");
		// wait for 10 sec
		Thread.sleep(5000);
		// verify login successfully
		Assert.assertEquals(driver.getPageSource().contains("mngr474242"), true);
	}
	
	
	
	@Test(priority = 3)
	private void invalid_login() throws InterruptedException {
		// add username
		invalid_login.userName.sendKeys("mngr474242");
		// add invalid password
		invalid_login.userPassword.sendKeys("Ahanun");
		// click login button
		invalid_login.loginButton.click();
		// take screenshot to verify login
		Utility.captureScreenshot(driver, "HomePage");
		// wait for 10 sec
		Thread.sleep(5000);
		// verify login successfully
		Assert.assertEquals(driver.getPageSource().contains("mngr474242"), true);
	}
	
	
	
	
	

	@Test(priority = 4)
	private void addCustomer() throws InterruptedException {
		// add New Customer
		addNewCustomer.addNewCUstomer.click();
		// wait for 5 sec
		Thread.sleep(10000);
		// verify page
		Assert.assertEquals(driver.getPageSource().contains("Add New Customer"), true);
		// add customer name
		addNewCustomer.customerName.sendKeys("fatma mostafa");
		// choose gender
		addNewCustomer.gender.click();
		// add birth date
		addNewCustomer.gender.sendKeys("01011997");
		Thread.sleep(10000);
		// scroll down
		js.executeScript("window.scrollBy(0,300)", "");
		// add customer address
		addNewCustomer.addAddress.sendKeys("cairo");
		// add customer city
		addNewCustomer.addCity.sendKeys("cairo");
		// add customer state
		addNewCustomer.addState.sendKeys("cairo ");
		// add customer postal code
		addNewCustomer.addPINCode.sendKeys("446290");
		// add mobile number
		addNewCustomer.addMobileNumber.sendKeys("01068387108");
		// add customer email address
		addNewCustomer.addEmail.sendKeys("fatma111@gmail.com");
		// add customer password
		addNewCustomer.addPassword.sendKeys("fatma111");
		// take screenshot to starting add customer page
		Utility.captureScreenshot(driver, "addCustomer");
		// submit form
		addNewCustomer.submitForm.click();
		Thread.sleep(10000);
		// take screenshot to confirm submitting form
		Utility.captureScreenshot(driver, "submit");
		// save customer id to be used later
		CUSTOMERID = driver.getCurrentUrl().toString().substring(driver.getCurrentUrl().toString().indexOf("?") + 5);
		// verify page
		Assert.assertEquals(driver.getPageSource().contains("Customer Registered Successfully!!!"), true);
		// scroll down
		js.executeScript("window.scrollBy(0,300)", "");
		// return to home pgae
		addNewCustomer.continueButton.click();
		Thread.sleep(5000);
	}

	
	
	
	
	
	@Test(priority = 5)
	private void misingfiled() throws InterruptedException {
		// add New Customer
		addNewCustomer.addNewCUstomer.click();
		// wait for 5 sec
		Thread.sleep(10000);
		// verify page
		Assert.assertEquals(driver.getPageSource().contains("Add New Customer"), true);
		// add customer name
		addNewCustomer.customerName.sendKeys("fatma mostafa");
		// choose gender
		addNewCustomer.gender.click();
		// add birth date
		addNewCustomer.gender.sendKeys("01011997");
		Thread.sleep(10000);
		// scroll down
		js.executeScript("window.scrollBy(0,300)", "");
		//missing  this filed 
		// add customer address
	//	addNewCustomer.addAddress.sendKeys("cairo");
		// add customer city
	//	addNewCustomer.addCity.sendKeys("cairo");
		// add customer state
	//	addNewCustomer.addState.sendKeys("cairo ");
		// add customer postal code
		addNewCustomer.addPINCode.sendKeys("446290");
		// add mobile number
		addNewCustomer.addMobileNumber.sendKeys("01068387108");
		// add customer email address
		addNewCustomer.addEmail.sendKeys("fatma111@gmail.com");
		// add customer password
		addNewCustomer.addPassword.sendKeys("fatma111");
		// take screenshot to starting add customer page
		Utility.captureScreenshot(driver, "addCustomer");
		// submit form
		addNewCustomer.submitForm.click();
		Thread.sleep(10000);
		// take screenshot to confirm submitting form
		Utility.captureScreenshot(driver, "submit");
		// save customer id to be used later
		CUSTOMERID = driver.getCurrentUrl().toString().substring(driver.getCurrentUrl().toString().indexOf("?") + 5);
		// verify page
		Assert.assertEquals(driver.getPageSource().contains("Customer Registered Successfully!!!"), true);
		// scroll down
		js.executeScript("window.scrollBy(0,300)", "");
		// return to home pgae
		addNewCustomer.continueButton.click();
		Thread.sleep(5000);
	}

	
	
	
	@AfterTest
	private void closeApplication() {
		driver.quit();
	}

}
