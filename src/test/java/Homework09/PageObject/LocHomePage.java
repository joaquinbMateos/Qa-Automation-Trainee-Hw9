package Homework09.PageObject;

import Homework09.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LocHomePage extends BasePage {
    //Locators:
    By nextSlideButton = By.xpath("//button[contains(@class,'slick-next slick-arrow')]");
    By last_page= By.xpath("//div[contains(@class,'paging-info') and contains(text(),'5/5')]");
    By topSearchButtons = By.xpath("//div[contains(@class,'trending-top-searches')]//child::ul//child::li");
    By digital_collections = By.xpath("//a[contains(text(),'Digital Collections')]");

    //Constructor:
    public LocHomePage(WebDriver driver) {
        super(driver);
    }

    //Methods:
    public void clickNextSlide() throws InterruptedException{
        waitForElement(nextSlideButton);
        click(nextSlideButton);
    }

    public WebElement sliderLastPage(){
        return find(last_page);
    }

    public List<WebElement> getTopSearchList(){
        List<WebElement> elements = driver.findElements(topSearchButtons);
        return elements;
    }

    public void digitalCollections(){
        click(digital_collections);
    }
}