package microsoft;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import amazonTest.searchProduct;
import microsoft.microsoftPrice;
import microsoft.clickPopSearch;
import microsoft.config;

public class microsoftweb {
	WebDriver driver;
	clickPopSearch courseSearch;
	microsoftPrice objPrice;
	config configmanagement;
	
	@BeforeTest
	
	public void before_test() {
		configmanagement = new config();
		System.out.println("MicrosoftWebTest");
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\u26494\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		//Accessing the driverpath using the configuration properties file
		
		System.setProperty("webdriver.chrome.driver", configmanagement.getDriverPath());
		driver = new ChromeDriver();
	    driver.manage().window().maximize();	
	    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
	}
	
	@Test (priority = 1)
	
	public void microweb() {
		String USER_XPATH = "//*[@id=\"c-shellmenu_";
		
		//URL accessing through the configuration properties file
		
	    driver.get(configmanagement.getApplicationUrl());
	    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
		
	    //Xpath related to the Windows tab line items
		List<WebElement> microarray = driver.findElements(By.xpath("//*[@class='c-uhf-nav-link']"));
		
		String [] expected = {"Microsoft 365", "Office", "Windows", "Surface", "Xbox", "Deals", "Support"};
		
		//Validate all menu items which are present
		for(int i = 0; i<microarray.size(); i++) 
		{
			System.out.println(microarray.get(i).getText() + "\t" + expected[i]);
			Assert.assertEquals(expected[i], microarray.get(i).getText());
		}
		
		//Go to Windows and click to navigate to that page
		for (WebElement we : microarray)
		{
			if (we.getText().equalsIgnoreCase("windows")) 
			{
				we.click();
				driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				break;
			}
		}
		
		//Once in Windows page, click on Windows 10 Menu
		try {
		WebElement win_dropdown = driver.findElement(By.id("c-shellmenu_50"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		win_dropdown.click();
		win_dropdown = driver.findElement(By.id("c-shellmenu_50"));
		
		
		//Print all Elements in the dropdown items
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		for (int j = 51;j<=60;j++)
		{
			String str = Integer.toString(j);
			String fullxpath = USER_XPATH + str + "\"]";
    		System.out.println(driver.findElement(By.xpath(fullxpath)).getText());
		}
		}
		catch(StaleElementReferenceException e) {
			System.out.println(e);
		}
		
		//Methods to be accessed from POM
		 courseSearch = new clickPopSearch(driver);
		 objPrice = new microsoftPrice(driver);
		 courseSearch.clickSearch();
		    courseSearch.driver_wait();
		    courseSearch.setSearch("Visual Studio");
		    courseSearch.driver_wait();
		    courseSearch.clickSearch();
		    courseSearch.driver_wait();
		    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		    
		    //Handling of the pop up windows
			for (String currentWindow: driver.getWindowHandles())
			       driver.switchTo().window(currentWindow);
			{
				driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			   //Now you are in Popup window and you can get the pop-up window URL here
			   driver.findElement(By.xpath("//*[@class='c-glyph glyph-cancel']")).click();
			   
			}
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			boolean b = false;
			
			//Print the price for the 3 first elements listed in Software result list
			for (int k = 0 ; k <=2; k++)
			{
				String str1 = Integer.toString(k);
				String xpath1 = "//*[@id=\"coreui-productplacement-30l7ywa_" + str1 + "\"]/div[2]/div/span[3]/span[1]";
								
				//Store the price of the first one
				String price1 = driver.findElement(By.xpath(xpath1)).getText();
							
				if (k == 0 && b)
				{
					driver.findElement(By.xpath(xpath1)).click();
					
					driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
					for (String currentWindow: driver.getWindowHandles())
					       driver.switchTo().window(currentWindow);
					{
					   //Now you are in Popup window and you can get the pop-up window URL here
					   driver.findElement(By.xpath("//*[@class='c-glyph glyph-cancel']")).click();
					   
					}
					driver.switchTo().defaultContent();
					
					//Click on the first one to go to the details page
					String price2 = driver.findElement(By.cssSelector("#ProductPrice_productPrice_PriceContainer > span")).getText();
					System.out.println(price2);
					
					//Once in the details page, validate both prices are the same
					Assert.assertEquals(price1,price2);
					
					/* To scroll down the page to find the AddToCart button and click it*/
					JavascriptExecutor js = (JavascriptExecutor) driver;
					WebElement element = driver.findElement(By.xpath("//button[@id='buttonPanel_AddToCartButton']"));
					js.executeScript("arguments[0].scrollIntoView();", element);
					element.click();
					
					//Verify all 3 price amounts are the same
					String dispPrice3 = objPrice.getprice3();
					if ((price1.equals(price2)) && (price2.equals(dispPrice3)) && (price1.equals(dispPrice3)))
					//if ((price1.equals(price2)) && (price2.equals(objPrice.getprice3())) && (price1.equals(objPrice.getprice3())))
						//if ((price1.equals(price2)) && (price2.equals(objPrice.getprice3(strPrice3)))
					{
						System.out.println("ALL THE 3 prices are matched - " + "price1: " + price1 + "price2: " + price2 + "price3: " + objPrice.getprice3());
					}
					
					/* To update the list of units to 20 for this product */
					WebElement units_dropdown=driver.findElement(By.xpath("//*[@id=\"store-cart-root\"]/div/div/div/section[1]/div/div/div/div/div/div[2]/div[2]/div[1]/select"));
					Select month=new Select(units_dropdown);
					 
					month.selectByVisibleText("20");
					
					/* To ge the final price of the 20 UNITS */
					String fp = driver.findElement(By.xpath("//*[@id=\"store-cart-root\"]/div/div/div/section[2]/div/div/div[2]/div/span/span[2]/strong/span")).getText();
					
					fp = fp.replaceAll("[^0-9]", "");
					int fp2 = Integer.parseInt(fp);

					System.out.println(fp2);
					
					String price3 = objPrice.getprice3().replaceAll("[^0-9]", "");
					int fp1 = Integer.parseInt(price3);
					System.out.println(fp1);
					
					int finalprice = fp1 * 20;
					
					if (finalprice == fp2)
					{
						long fPayment = finalprice;
						long fPayment1 = fp2;
						NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US); 
						String strfpayment = n.format(fPayment / 100.0);
						//System.out.println(strfpayment);
						String strfpayment1 = n.format(fPayment1 / 100.0);
						//System.out.println(strfpayment1);
						System.out.println("20 Units price = " + strfpayment + " Total price: " + strfpayment1);
					}
					
						
					break;
					
				}
				
				System.out.println(price1);
				
				if (k == 2)
				{
					b = true;
					k = k-3;
				}
			}
	}
		
    	
	@AfterClass
	  public void after_Test() {
	          try {
	             System.out.println("Closing Window"); 
	          } finally {
	              driver.quit(); 
	          }
	  }

}
