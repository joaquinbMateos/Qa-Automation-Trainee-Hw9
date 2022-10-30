package Homework09.Base;

import Homework09.PageObject.*;
import Homework09.Utilities.BrowserType;
import Homework09.Utilities.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public abstract class BaseTest {

    protected WebDriver driver;
    protected LocHomePage locHomePage;
    protected DigitalCollectionPage digitalCollectionPage;
    protected SurveyPage surveyPage;
    protected demoQaPage demoqaPage;
    protected rahulshettyPracticePage practicePage;

    @BeforeClass
    @Parameters({"browser"})
    public void setUp(String browser) {
        switch(browser){
            case "FF":
                this.driver = DriverFactory.getDriver(BrowserType.FIREFOX);
                break;
            case "CHROME":
                this.driver = DriverFactory.getDriver(BrowserType.CHROME);
                break;
            case "EDGE":
                this.driver = DriverFactory.getDriver(BrowserType.EDGE);
                break;
            default: this.driver = DriverFactory.getDriver(BrowserType.CHROME);
                break;
        }
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
