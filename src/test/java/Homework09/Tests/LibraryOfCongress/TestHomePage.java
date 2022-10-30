package Homework09.Tests.LibraryOfCongress;

import Homework09.Base.BaseTest;
import Homework09.PageObject.LocHomePage;
import Homework09.Utilities.Retry;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class TestHomePage extends BaseTest {
    private String expectedUrl = "https://www.loc.gov/";

    @BeforeClass
    public void startApp(){
        driver.get("https://www.loc.gov/");
        locHomePage = new LocHomePage(driver);
    }

    @Test(groups = {"homePage"} ,priority = 0)
    public void assertUrl(){
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "URL mismatch!");
    }

    @Test(groups = {"homePage"} ,retryAnalyzer = Retry.class, priority = 1)
    public void testSlider() throws InterruptedException{
        Thread.sleep(2000);
        locHomePage.clickNextSlide();
        locHomePage.sliderLastPage();
    }
    @Test(groups = {"homePage"}, dependsOnMethods = {"testSlider"}, priority = 1, dataProvider = "top searchs")
    public void testTopSearchs(List list){
        SoftAssert sa = new SoftAssert();
        for (int i=0; i<locHomePage.getTopSearchList().size(); i++){
            sa.assertEquals(locHomePage.getTopSearchList().get(i).getText(), list.get(i));
        }
    }

    @Test(groups = {"homePage"} ,dependsOnMethods = {"assertUrl","testSlider","testTopSearchs"}, priority = 1)
    public void testGoToDigitalColletions(){
        locHomePage.digitalCollections();
    }

    @DataProvider(name = "top searchs")
    public Object[][] dpMethod() {
        List<String> list = new ArrayList<>();
        list.add("Civil War");
        list.add("WPA");
        list.add("National Parks");
        list.add("Dust Bowl");
        list.add("Maps");
        list.add("Ukraine");
        return new Object[][]{
                {list}
        };
    }
}
