package microsoft;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class microsoftPrice {
	
	WebDriver driver;
	
	//By price3 = By.cssSelector("#store-cart-root > div > div > div > section._3a6I5TkT > div > div > div > div > div > div.item-details > div.item-quantity-price > div.item-price > div > span > span:nth-child(3) > span");
	By price3 = By.xpath("//*[@id=\"store-cart-root\"]/div/div/div/section[1]/div/div/div/div/div/div[2]/div[2]/div[2]/div/span/span[2]/span");
	
    public microsoftPrice(WebDriver driver){

        this.driver = driver;

    }
    
    public String getprice3()
    {

        return (driver.findElement(price3).getText());

    }
	
}
    
