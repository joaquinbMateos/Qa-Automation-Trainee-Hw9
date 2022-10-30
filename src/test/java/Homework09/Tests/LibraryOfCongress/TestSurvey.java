package Homework09.Tests.LibraryOfCongress;

import Homework09.Base.BaseTest;
import Homework09.PageObject.SurveyPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Random;

public class TestSurvey extends BaseTest {
    @BeforeClass
    public void startApp(){
        driver.get("https://www.loc.gov/");
        surveyPage = new SurveyPage(driver);
    }

    @Test(groups = {"survey"})
    public void goToSurvey(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", surveyPage.findSurvey());
    }

    @Test(groups = {"survey"}, dependsOnMethods = "goToSurvey")
    public void assertQuestions(){
        SoftAssert sa = new SoftAssert();
        surveyPage.findSurvey().click();
        sa.assertEquals(surveyPage.getQuestionsList().size(),3);
    }

    @Test(groups = {"survey"}, dependsOnMethods = {"goToSurvey", "assertQuestions"})
    public void answerQuestions(){
        List<List> allAnswers = surveyPage.getListOfAnswers();

        for(int i=0; i<3; i++){
            Random rand = new Random();
            int randomButton = rand.nextInt(allAnswers.get(i).size());
            List<WebElement> answerList = allAnswers.get(i);
            WebElement selectedOption = answerList.get(randomButton);
            selectedOption.click();
            Assert.assertEquals(selectedOption.getAttribute("aria-checked"),"true", "option answer not selected!");
        }
    }

    @Test(groups = {"survey"}, dependsOnMethods = {"answerQuestions"})
    public void submitSurvey() {
        surveyPage.finishButton();
        surveyPage.message().isDisplayed();
    }
}
