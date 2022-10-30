package Homework09.Tests;

import Homework09.Base.BaseTest;
import Homework09.PageObject.rahulshettyPracticePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestPracticePage extends BaseTest {

    private List<WebElement> radioBTN;
    private int randomButton;

    @BeforeClass
    public void startApp(){
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        practicePage = new rahulshettyPracticePage(driver);
    }

    @Test(priority = 0)
    public void clickRadioButton() {
        radioBTN = driver.findElements(practicePage.radioButtons());
        Random rand = new Random();
        randomButton = rand.nextInt(2);
        radioBTN.get(randomButton).click();

        System.out.println("Random Radio Button Option: "+ (randomButton+1));
        for(int i=0; i< radioBTN.size(); i++){
            if (i != randomButton){
                System.out.println("NOT Selected Radiobutton: "+radioBTN.get(i).getText());
            }else{
                System.out.println("selected Radiobutton: "+radioBTN.get(i).getText());
            }
        }
    }

    @Test(priority = 1)
    public void searchCountry() throws InterruptedException {
        String str = "El Sal";
        System.out.println(practicePage.searchBox().getAttribute("placeholder"));
        practicePage.searchBox().sendKeys(str);
        practicePage.searchBox().sendKeys(Keys.ARROW_DOWN);
        practicePage.searchBox().sendKeys(Keys.ENTER);

        Thread.sleep(5000);
        practicePage.printOption();
    }

    @Test(priority = 2)
    public void dropDown(){
        String option = "Option2";
        practicePage.dropDownOption(option);
        practicePage.printDropDownOption();

        option = "Option3";
        practicePage.dropDownOptionMouse(option);
        practicePage.printDropDownOption();
    }

    @Test(dataProvider = "header_buttons", priority = 3)
    public void headerButtons(String button){
        practicePage.clickHeaderButtons(button);
        practicePage.assertHeaderButtons(button);
    }

    @Test(priority = 4)
    public void openTabs(){
        for (int i = 0; i < 8; i++){
            String handle = driver.getWindowHandle();
            practicePage.tabButton();
            driver.switchTo().window(handle);
        }
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        System.out.println("Number of tabs: " + tabs.size());
    }

    @DataProvider(name = "header_buttons")
    public Object[][] dpMethod() {
        return new Object[][]{
                {"Home"},
                {"Practice"},
                {"Login"},
                {"Signup"}
        };
    }
}
