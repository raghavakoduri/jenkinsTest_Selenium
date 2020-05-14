package microsoft;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class clickPopSearch extends methodAbstract {
	
	
	
	WebDriver driver;
	boolean b = false;
	int fp1 = 0;
	int fp2 = 0;
	int finalprice = 0;
	

	@FindBy(id="search")
	
	WebElement searchButton;
	
	@FindBy(name="q")
	
	WebElement search; 
	
	@FindBy(xpath="//*[@class='c-glyph glyph-cancel']")
	
	WebElement popUpClose;
	
	@FindBy(css="#ProductPrice_productPrice_PriceContainer > span")
	
	/*WebElement price2;
	
	@FindBy(xpath="//button[@id='buttonPanel_AddToCartButton']")*/
	
	WebElement element;
	
	public void clickPopUp(){

		popUpClose.click();

    }  
	
	public void clickSearch(){

        searchButton.click();

    }  
	
    public clickPopSearch(WebDriver driver){

        this.driver = driver;

        //This initElements method will create all WebElements

        PageFactory.initElements(driver, this);

    }
	
	/*public String getPrice_2(){

        return price2.getText();

    } */ 
	
	public void setSearch(String searchStr){

	    search.sendKeys(searchStr); 
		
	}

	public void driver_wait() {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
		
	}
	
}
