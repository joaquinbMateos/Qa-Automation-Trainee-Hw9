package Homework09.PageObject;

import Homework09.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DigitalCollectionPage extends BasePage {
    //Locators:
    By digital_collections = By.xpath("//h1[contains(@class,'smaller-h1')]//span[contains(text(),'Digital Collections')]");
    By search_box = By.xpath("//input[contains(@class,'locsuggest header-search-input')]");
    By ul_results = By.xpath("//ul[contains(@class,'search-results gallery-view')]");

    //Constructor:
    public DigitalCollectionPage(WebDriver driver) {
        super(driver);
    }

    //Methods:
    public WebElement verifyDigitalCollections(){
        return find(digital_collections);
    }
    public WebElement searchBox(){
        return find(search_box);
    }

    public WebElement results(){
        return find(ul_results);
    }
    public void searchFor(String text){
        type(search_box, text);
    }
}