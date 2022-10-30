package Homework09.Tests;

import Homework09.Base.BaseTest;
import Homework09.PageObject.demoQaPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

public class TestDemoQaPage extends BaseTest {
    private String formsUrl = "https://demoqa.com/forms";
    private String practiceFormsUrl = "https://demoqa.com/automation-practice-form";

    @BeforeClass
    public void startApp(){
        driver.get("https://demoqa.com");
        demoqaPage = new demoQaPage(driver);
    }

    @Test
    public void assertFormsUrl(){
        demoqaPage.clickForms();
        Assert.assertEquals(driver.getCurrentUrl(), formsUrl, "URL mismatch!");
    }

    @Test(dependsOnMethods = "assertFormsUrl")
    public void assertPracticeFormsUrl(){
        demoqaPage.clickPracticeForm();
        Assert.assertEquals(driver.getCurrentUrl(), practiceFormsUrl, "URL mismatch!");
    }

    @Test(dataProvider = "inputs", priority = 0)
    public void fillInputs(List inputs){
        for (int i=0; i<inputs.size(); i++){
            demoqaPage.fillInput(inputs.get(i).toString());
        }
    }

    @Test(priority = 1)
    public void selectGender(){
        demoqaPage.selectGender();
        demoqaPage.verifyGender();
    }

    @Test(priority = 2)
    public void completeBirthDate(){
        String day = "22";
        String month = "Mar";
        String year = "1991";
        demoqaPage.completeBirthDate(day,month,year);
        demoqaPage.verifyDate(day,month,year);
    }
    @Test(priority = 3)
    public void selectHobby(){
        demoqaPage.selectHobby();
        demoqaPage.verifyHobby();
    }

    @Test(priority = 4)
    public void uploadImage(){
        demoqaPage.uploadImage();
        demoqaPage.verifyImage();
    }

    @Test(priority = 5)
    public void completeState(){
        String state = "";
        String city = "";
        demoqaPage.selectState(state);
        demoqaPage.selectCity(city);
        demoqaPage.verifyState(state);
        demoqaPage.verifyCity(city);
    }

    @Test(priority = 6)
    public void submit(){
        demoqaPage.clickSubmit();
    }

    @DataProvider(name = "inputs")
    public Object[][] dpMethod() {
        List<String> list = new ArrayList<>();
        list.add("name");
        list.add("lastName");
        list.add("email");
        list.add("mobile");
        list.add("subjects");
        list.add("address");
        return new Object[][]{
                {list}
        };
    }
}
